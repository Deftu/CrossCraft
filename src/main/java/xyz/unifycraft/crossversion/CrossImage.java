package xyz.unifycraft.crossversion;

import ca.weblite.objc.Client;
import ca.weblite.objc.Proxy;
import net.minecraft.client.texture.NativeImage;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.UUID;

public class CrossImage {
    /**
     * Copies the provided image to the system clipboard.
     *
     * @param image The image to copy to the clipboard.
     */
    public static void copy(@NotNull NativeImage image) throws IOException {
        Objects.requireNonNull(image);
        if (CrossClient.isOnMac()) {
            File tempFile = new File(UUID.randomUUID() + ".temp");
            if (!tempFile.createNewFile() || !tempFile.canWrite())
                throw new IllegalStateException("Cannot write to temporary file!");
            Files.write(tempFile.toPath(), image.getBytes());

            Client client = Client.getInstance();
            Proxy urlProxy = client.sendProxy("NSURL", "fileURLWithPath:", tempFile.getAbsolutePath());
            Proxy imageProxy = client.sendProxy("NSImage", "alloc");
            imageProxy.send("initWithContentsOfURL:", urlProxy);
            Proxy arrayProxy = client.sendProxy("NSArray", "arrayWithObject:", imageProxy);
            Proxy pasteboardProxy = client.sendProxy("NSPasteboard", "generalPasteboard");
            pasteboardProxy.send("clearContents");
            boolean successful = pasteboardProxy.sendBoolean("writeObjects:", arrayProxy);
            tempFile.delete();
            if (!successful)
                throw new IllegalStateException("Failed to copy image to clipboard!");

            return;
        }

        Transferable transferable = new ImageTransferable(image.getBytes());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(transferable, null);
    }

    private static class ImageTransferable implements Transferable {
        private final byte[] bytes;

        ImageTransferable(byte[] bytes) {
            this.bytes = bytes;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{ DataFlavor.imageFlavor };
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor == DataFlavor.imageFlavor;
        }

        @NotNull
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (flavor != DataFlavor.imageFlavor)
                throw new UnsupportedFlavorException(flavor);

            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
            BufferedImage converted = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            converted.getGraphics().drawImage(image, 0, 0, null);
            return converted;
        }
    }
}

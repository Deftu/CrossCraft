package xyz.unifycraft.crossversion;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class CrossDesktop {
    public static boolean browse(URI uri) throws IOException {
        return browseInternal(uri) || openForSystem(uri.toString());
    }

    public static boolean open(File file) throws IOException {
        return openInternal(file) || openForSystem(file.getPath());
    }

    public static boolean edit(File file) throws IOException {
        return editInternal(file) || openForSystem(file.getPath());
    }

    private static boolean openForSystem(String path) {
        if (CrossOperatingSystem.isWindows())
            return CrossOperatingSystem.run("explorer", path);
        if (CrossOperatingSystem.isMac())
            return CrossOperatingSystem.run("open", path);

        if (CrossOperatingSystem.isLinux()) {
            if (CrossOperatingSystem.isXdg())
                return CrossOperatingSystem.run(true, "xdg-open", path);
            else if (CrossOperatingSystem.isKde())
                return CrossOperatingSystem.run(true, "kde-open", path);
            else if (CrossOperatingSystem.isGnome())
                return CrossOperatingSystem.run(true, "gnome-open", path);
        }

        return false;
    }

    private static boolean isSupported(Desktop.Action action) {
        return Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(action);
    }

    private static boolean browseInternal(URI uri) throws IOException {
        if (!isSupported(Desktop.Action.BROWSE))
            return false;

        Desktop.getDesktop().browse(uri);
        return true;
    }

    private static boolean openInternal(File file) throws IOException {
        if (!isSupported(Desktop.Action.OPEN))
            return false;

        Desktop.getDesktop().open(file);
        return true;
    }

    private static boolean editInternal(File file) throws IOException {
        if (!isSupported(Desktop.Action.EDIT))
            return false;

        Desktop.getDesktop().edit(file);
        return true;
    }
}

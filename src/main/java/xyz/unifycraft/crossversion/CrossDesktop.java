package xyz.unifycraft.crossversion;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

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

    private static boolean openForSystem(@NotNull String path) {
        Objects.requireNonNull(path);

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

    private static boolean isNotSupported(@Nullable Desktop.Action action) {
        boolean val = !Desktop.isDesktopSupported();
        if (action != null)
            val = val || !Desktop.getDesktop().isSupported(action);
        return val;
    }

    private static boolean browseInternal(@NotNull URI uri) throws IOException {
        Objects.requireNonNull(uri);
        if (isNotSupported(Desktop.Action.BROWSE))
            return false;

        Desktop.getDesktop().browse(uri);
        return true;
    }

    private static boolean openInternal(@NotNull File file) throws IOException {
        Objects.requireNonNull(file);
        if (isNotSupported(Desktop.Action.OPEN))
            return false;

        Desktop.getDesktop().open(file);
        return true;
    }

    private static boolean editInternal(@NotNull File file) throws IOException {
        Objects.requireNonNull(file);
        if (isNotSupported(Desktop.Action.EDIT))
            return false;

        Desktop.getDesktop().edit(file);
        return true;
    }
}

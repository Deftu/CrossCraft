package xyz.unifycraft.crossversion;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class CrossOperatingSystem {
    private static boolean windows = false;
    private static boolean mac = false;

    private static boolean linux = false;
    private static boolean kde = false;
    private static boolean gnome = false;
    private static boolean xdg = false;

    public static boolean run(boolean checkExitCode, @NotNull String... command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            if (process == null)
                return false;

            if (checkExitCode) {
                if (process.waitFor(3, TimeUnit.SECONDS))
                    return process.exitValue() == 0;
                else
                    return true; // The process is still running, so just assume it succeeded.
            }

            return process.isAlive();
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean run(String... command) {
        return run(false, command);
    }

    public static boolean isWindows() {
        return windows;
    }

    public static boolean isMac() {
        return mac;
    }

    public static boolean isLinux() {
        return linux;
    }

    public static boolean isKde() {
        return kde;
    }

    public static boolean isGnome() {
        return gnome;
    }

    public static boolean isXdg() {
        return xdg;
    }

    static {
        String osName = System.getProperty("os.name");
        if (osName != null) {
            windows = osName.startsWith("Windows");
            mac = osName.startsWith("Mac");

            linux = (osName.startsWith("Linux") || osName.startsWith("LINUX"));
            if (linux) {
                String xdgSession = System.getenv("XDG_SESSION_ID");
                xdg = xdgSession != null && !xdgSession.isEmpty();

                String gnomeSession = System.getenv("GDMSESSION");
                gnome = gnomeSession != null && gnomeSession.contains("gnome");
                kde = gnomeSession != null && gnomeSession.contains("kde");
            }
        }
    }
}

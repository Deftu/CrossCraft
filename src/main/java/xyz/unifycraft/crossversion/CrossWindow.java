package xyz.unifycraft.crossversion;

public class CrossWindow {
    public static boolean isFullscreen() {
        return CrossClient.getInstance().getWindow().isFullscreen();
    }

    public static int getWidth() {
        return CrossClient.getInstance().getWindow().getWidth();
    }

    public static int getHeight() {
        return CrossClient.getInstance().getWindow().getHeight();
    }

    public static int getScaledWidth() {
        return CrossClient.getInstance().getWindow().getScaledWidth();
    }

    public static int getScaledHeight() {
        return CrossClient.getInstance().getWindow().getScaledHeight();
    }

    public static int getX() {
        return CrossClient.getInstance().getWindow().getX();
    }

    public static int getY() {
        return CrossClient.getInstance().getWindow().getY();
    }
}

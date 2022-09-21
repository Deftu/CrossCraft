package xyz.unifycraft.crossversion;

/**
 * Adapted from Essential UniversalCraft under LGPL 3.0
 * <a href="https://github.com/EssentialGG/UniversalCraft/blob/826377f9a94810d10b5cd6041cbd98a1b6b2f82c/LICENSE">License</a>
 */
public enum GuiScale {
    AUTO,
    SMALL,
    MEDIUM,
    LARGE,
    VERY_LARGE;

    private static final int STEP = 650;
    private static final int OVERRIDE = Integer.getInteger("cross.guiscale", -1);

    public static GuiScale scaleForScreen() {
        if (OVERRIDE != -1)
            return values()[OVERRIDE];

        int width = CrossWindow.getWidth();
        int height = CrossWindow.getHeight();
        return values()[Math.min(coerceIn(width / STEP, 1, 4), coerceIn(height / (STEP / 16 * 9), 1, 4))];
    }

    private static int coerceIn(int value, int min, int max) {
        if (value < min) value = min;
        if (value > max) value = max;
        return value;
    }
}

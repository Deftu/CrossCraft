package xyz.unifycraft.crossversion;

import java.util.regex.Pattern;

/**
 * An enum representing preset colors rendered alongside text,
 * most commonly used inside the chat.
 */
public enum ColorCode {
    BLACK('0', 0x000000, false),
    DARK_BLUE('1', 0x0000AA, false),
    DARK_GREEN('2', 0x00AA00, false),
    DARK_AQUA('3', 0x00AAAA, false),
    DARK_RED('4', 0xAA0000, false),
    DARK_PURPLE('5', 0xAA00AA, false),
    GOLD('6', 0xFFAA00, false),
    GRAY('7', 0xAAAAAA, false),
    DARK_GRAY('8', 0x555555, false),
    BLUE('9', 0x5555FF, false),
    GREEN('a', 0x55FF55, false),
    AQUA('b', 0x55FFFF, false),
    RED('c', 0xFF5555, false),
    LIGHT_PURPLE('d', 0xFF55FF, false),
    YELLOW('e', 0xFFFF55, false),
    WHITE('f', 0xFFFFFF, false),
    MAGIC('k', -1, true),
    BOLD('l', -1, true),
    STRIKETHROUGH('m', -1, true),
    UNDERLINE('n', -1, true),
    ITALIC('o', -1, true),
    RESET('r', -1, false);

    public static final char FORMATTING_SYMBOL = '\u00a7';
    public static final Pattern FORMATTING_PATTERN = Pattern.compile("§[0-9a-fk-or]", Pattern.CASE_INSENSITIVE);

    private final char character;
    private int color;
    private boolean formatting;

    ColorCode(char character, int color, boolean formatting) {
        this.character = character;
        this.color = color;
        this.formatting = formatting;
    }

    /**
     * @return The singular character representing this color code.
     */
    public char getCharacter() {
        return character;
    }

    /**
     * @return The color used when rendering this color code to the screen.
     */
    public int getColor() {
        return color;
    }

    /**
     * @return Whether this color code is simply used for text formatting or not,
     * such as for things like bolding text.
     */
    public boolean isFormatting() {
        return formatting;
    }

    public String toString() {
        return FORMATTING_SYMBOL + Character.toString(character);
    }

    /**
     * Replaces the provided formatting symbol with the formatting code.
     *
     * @param formattingSymbol The formatting symbol used to represent a color code.
     * @param input The input string to be transformed/replaced from.
     * @return The transformed string containing the appropriate color codes.
     */
    public static String replaceFormatting(char formattingSymbol, String input) {
        char[] inputChars = input.toCharArray();
        for (int i = 0; i < inputChars.length - 1; i++) {
            if (inputChars[i] == formattingSymbol && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(inputChars[i + 1]) > -1) {
                inputChars[i] = FORMATTING_SYMBOL;
                inputChars[i + 1] = Character.toLowerCase(inputChars[i + 1]);
            }
        }
        return new String(inputChars);
    }

    /**
     * Replaces all instances of the ampersand symbol (&) with the color formatting code.
     *
     * @param input The input string to be transformed/replaced from.
     * @return The transformed string containing the appropriate color codes.
     */
    public static String replaceFormatting(String input) {
        return replaceFormatting('&', input);
    }

    /**
     * Strips the input of all formatting/color codes.
     *
     * @param input The input string to be transformed/stripped.
     * @return The transformed string stripped of all it's formatting/color codes.
     */
    public static String stripFormatting(String input) {
        return input.replaceAll(FORMATTING_PATTERN.pattern(), "");
    }
}

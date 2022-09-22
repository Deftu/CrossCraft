package xyz.unifycraft.crossversion;

import net.minecraft.text.Text;

//#if MC<11900
//$$ import net.minecraft.text.LiteralText;
//$$ import net.minecraft.text.TranslatableText;
//#endif

public class CrossText {
    public static Text literal(String input) {
        //#if MC<11900
        //$$ return new LiteralText(input);
        //#else
        return Text.literal(input);
        //#endif
    }

    public static Text translatable(String input, Object... args) {
        //#if MC<11900
        //$$ return new TranslatableText(input, args);
        //#else
        return Text.translatable(input, args);
        //#endif
    }

    public static Text translatable(String input) {
        //#if MC<11900
        //$$ return new TranslatableText(input);
        //#else
        return Text.translatable(input);
        //#endif
    }

    public static Text empty() {
        //#if MC<11900
        //$$ return LiteralText.EMPTY;
        //#else
        return Text.empty();
        //#endif
    }
}

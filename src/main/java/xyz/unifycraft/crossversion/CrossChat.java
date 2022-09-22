package xyz.unifycraft.crossversion;

import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

//#if MC<119000
//$$ import net.minecraft.network.MessageType;
//#endif

public class CrossChat {
    public static void send(@NotNull ChatType type, @Nullable Object message, boolean translate, @Nullable Object... translateArgs) {
        Objects.requireNonNull(type);
        if (message == null || !CrossPlayer.isPresent())
            return;

        Text text;
        String msg = stringify(message);
        if (translate) {
            text = CrossText.translatable(msg, translateArgs);
        } else text = CrossText.literal(msg);

        switch (type) {
            case CHAT -> chat(text);
            case ACTION_BAR -> action(text);
        }
    }

    public static void send(@NotNull ChatType type, @Nullable Object message, boolean translatable) {
        send(type, message, translatable, (Object) null);
    }

    public static void send(@NotNull ChatType type, @Nullable Object message) {
        send(type, message, false);
    }

    private static String stringify(Object o) {
        if (o instanceof String) {
            return (String) o;
        } else return o.toString();
    }

    private static void chat(Text message) {
        //#if MC<11900
        //$$ CrossPlayer.getInstance().sendMessage(message, null);
        //#else
        CrossPlayer.getInstance().sendMessage(message);
        //#endif
    }

    private static void action(Text message) {
        CrossClient.getInstance().getNetworkHandler().onGameMessage(new GameMessageS2CPacket(
            message,
            //#if MC>=11901
            false
            //#else
            //$$ MessageType.GAME_INFO
            //#endif
            //#if MC>=11600 && MC<11900
            // , CrossPlayer.getUuid()
            //#endif
        ));
    }

    /**
     * Sends a message to the currently connected server/world
     * as the client player.
     *
     * @param message The message to be sent to the server/world.
     */
    public static void say(String message) {
        //#if MC<=11900
        //$$ CrossPlayer.getInstance().sendChatMessage(message);
        //#else
        CrossPlayer.getInstance().sendChatMessage(message, null);
        //#endif
    }

    /**
     * The different forms of chat that are available for displaying
     * messages to the user. These are intended only to send messages
     * to the client-side player.
     */
    public enum ChatType {
        /**
         * The chat box where system or player messages are displayed.
         */
        CHAT,
        /**
         * The bar just above the hotbar displaying things such as currently
         * playing music discs.
         */
        ACTION_BAR
    }
}

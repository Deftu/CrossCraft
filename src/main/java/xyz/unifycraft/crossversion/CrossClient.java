package xyz.unifycraft.crossversion;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.world.World;

public class CrossClient {
    /**
     * @return The {@code MinecraftClient} class instance.
     */
    public static MinecraftClient getInstance() {
        return MinecraftClient.getInstance();
    }

    /**
     * @return Whether the player is currently playing the game on a
     * computer running macOS or not.
     */
    public static boolean isOnMac() {
        return MinecraftClient.IS_SYSTEM_MAC;
    }

    /**
     * @return An instance of the player entity, or null
     * if not in a world.
     */
    public static ClientPlayerEntity getPlayer() {
        return CrossPlayer.getInstance();
    }

    /**
     * @return An instance of the currently loaded world, or null
     * if not in a world.
     */
    public static World getWorld() {
        return getInstance().world;
    }

    /**
     * @return An instance of the game's text renderer.
     */
    public static TextRenderer getTextRenderer() {
        return getInstance().textRenderer;
    }

    /**
     * @return An instance of the game's chat display.
     */
    public static ChatHud getChat() {
        return getInstance().inGameHud.getChatHud();
    }

    /**
     * @return An instance of the game's options class.
     */
    public static GameOptions getSettings() {
        return getInstance().options;
    }
}

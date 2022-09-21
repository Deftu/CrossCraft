package xyz.unifycraft.crossversion;

import net.minecraft.client.network.ClientPlayerEntity;

import java.util.UUID;

public class CrossPlayer {
    public static ClientPlayerEntity getInstance() {
        return CrossClient.getInstance().player;
    }

    public static boolean isPresent() {
        return getInstance() != null;
    }

    public static UUID getUuid() {
        return getInstance().getUuid();
    }

    public static double getPosX() {
        if (!isPresent())
            failNotPresent();

        return getInstance().getX();
    }

    public static double getPosY() {
        if (!isPresent())
            failNotPresent();

        return getInstance().getY();
    }

    public static double getPosZ() {
        if (!isPresent())
            failNotPresent();

        return getInstance().getZ();
    }

    private static void failNotPresent() {
        throw new IllegalStateException("Method called while no player is present, despite it requiring one!");
    }
}

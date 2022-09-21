package xyz.unifycraft.crossversion;

import net.minecraft.world.World;

public class CrossWorld {
    public static World getInstance() {
        return CrossClient.getInstance().world;
    }

    public static boolean isPresent() {
        return getInstance() != null;
    }
}

package xyz.unifycraft.crossversion.mixins;

import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.unifycraft.crossversion.CrossClient;

/**
 * Intended to fix {@code CrossImage#copy}.
 */
@Mixin({Main.class})
public class MainMixin {

    @Inject(method = "main", at = @At("HEAD"), remap = false)
    private static void disableHeadlessMode(CallbackInfo ci) {
        if (CrossClient.isOnMac())
            return;

        System.setProperty("java.awt.headless", "false");
    }

}

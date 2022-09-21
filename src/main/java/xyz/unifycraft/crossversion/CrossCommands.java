package xyz.unifycraft.crossversion;

//#if MC>=11900
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
//#elseif MC>=11700
//$$ import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
//$$ import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
//#else
//$$ import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
//#endif

public class CrossCommands {
    public static void register(
            //#if MC>=11700
            LiteralArgumentBuilder<FabricClientCommandSource> builder
            //#else
            //$$ LiteralArgumentBuilder<ServerCommandSource> builder
            //#endif
    ) {
        //#if MC>=11900
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(builder));
        //#elseif MC>=11700
        //$$ ClientCommandManager.DISPATCHER.register(builder)
        //#else
        //$$ CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(builder));
        //#endif
    }
}

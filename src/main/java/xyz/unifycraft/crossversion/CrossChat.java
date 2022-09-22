package xyz.unifycraft.crossversion;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CrossChat {
    public static boolean send(@NotNull ChatType type, @Nullable Object message) {
        Objects.requireNonNull(type);
        if (message == null || !CrossPlayer.isPresent())
            return false;

        // TODO: 9/21/2022

        if (message instanceof String) {

        } else {

        }

        return true;
    }

    public enum ChatType {
        CHAT,
        ACTION_BAR
    }
}

package xyz.unifycraft.crossversion;

public class CrossChat {
    public static boolean send(ChatType type, Object message) {
        if (!CrossPlayer.isPresent())
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

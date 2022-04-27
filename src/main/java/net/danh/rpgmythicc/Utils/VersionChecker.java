package net.danh.rpgmythicc.Utils;

import net.danh.rpgmythicc.Enum.ServerVersion;
import net.danh.rpgmythicc.NMS.Advancements.*;
import net.danh.rpgmythicc.NMS.Chats.*;
import net.danh.rpgmythicc.RPGMythicc;
import org.bukkit.Bukkit;

public class VersionChecker {
    private static ServerVersion serverVersion = ServerVersion.NOT_SUPPORTED;

    /**
     * Check the version of the server the plugin is running on.
     */
    public static void checkServerVersion() {
        String version;

        try {
            version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
//      System.out.println(version);
        } catch (ArrayIndexOutOfBoundsException versionNotSupported) {
            version = "UNKNOWN";
        }

        for (ServerVersion ver : ServerVersion.values()) {
            if (ver.getValue().contains(version)) {
                serverVersion = ver;
                return;
            }
        }
    }

    /**
     * Register the necessary classes depending on what server version was detected.
     */
    public static void registerClasses() {
        switch (serverVersion) {
            case VERSION_1_17 -> {
                RPGMythicc.get().setChatManager(new Chat_v1_17_R1());
                RPGMythicc.get().setAdvancementManager(new Advancement_v1_17_R1());
            }
            case VERSION_1_16_R3 -> {
                RPGMythicc.get().setChatManager(new Chat_v1_16_R3());
                RPGMythicc.get().setAdvancementManager(new Advancement_v1_16_R3());
            }
            case VERSION_1_16_R2 -> {
                RPGMythicc.get().setChatManager(new Chat_v1_16_R2());
                RPGMythicc.get().setAdvancementManager(new Advancement_v1_16_R2());
            }
            case VERSION_1_16_R1 -> {
                RPGMythicc.get().setChatManager(new Chat_v1_16_R1());
                RPGMythicc.get().setAdvancementManager(new Advancement_v1_16_R1());
            }
            case VERSION_1_15 -> {
                RPGMythicc.get().setChatManager(new Chat_v1_15_R1());
                RPGMythicc.get().setAdvancementManager(new Advancement_v1_15_R1());
            }
            case VERSION_1_14 -> {
                RPGMythicc.get().setChatManager(new Chat_v1_14_R1());
                RPGMythicc.get().setAdvancementManager(new Advancement_v1_14_R1());
            }
        }
    }

    public static boolean isSupportedVersion() {
        return serverVersion.isSupported();
    }

    public ServerVersion getServerVersion() {
        return serverVersion;
    }

}

package net.danh.rpgmythicc.Data;

import net.danh.rpgmythicc.PManager.Files;
import net.danh.rpgmythicc.RPGMythicc;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Level {

    static HashMap<String, Integer> level = new HashMap<>();

    public static int getLevelData(@NotNull Player p) {
        return Files.getdatafile().getInt("players." + p.getName() + ".Level");
    }

    public static int getLevel(@NotNull Player p) {
        if (isLoad(p)) {
            setLevel(p, Math.max(getLevelData(p), 1));
        }
        return level.get(p.getName() + "_level_");
    }

    public static boolean isLoad(@NotNull Player p) {
        return !level.containsKey(p.getName() + "_level_");
    }

    public static void setLevel(@NotNull Player p, Integer amount) {
        level.put(p.getName() + "_level_", Math.max(amount, 1));
    }

    public static void addLevel(@NotNull Player p, Integer amount) {
        level.replace(p.getName() + "_level_", getLevel(p) + amount);
        RPGMythicc.get().getChatManager().sendActionBar(p, Files.colorize("&a+ " + amount + "&e Mythicc Level"));
    }

    public static void removeLevel(@NotNull Player p, Integer amount) {
        if (getLevel(p) - amount > 1) {
            level.replace(p.getName() + "_level_", getLevel(p) - amount);
        } else {
            level.put(p.getName() + "_level_", 1);
        }
        RPGMythicc.get().getChatManager().sendActionBar(p, Files.colorize("&c- " + amount + "&e Mythicc Level"));
    }
}

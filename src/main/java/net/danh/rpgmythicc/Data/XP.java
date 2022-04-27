package net.danh.rpgmythicc.Data;

import net.danh.rpgmythicc.PManager.Files;
import net.danh.rpgmythicc.RPGMythicc;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class XP {

    private static final HashMap<String, Integer> xp = new HashMap<>();

    public static int getXPData(@NotNull Player p) {
        return Files.getdatafile().getInt("players." + p.getName() + ".XP");
    }

    public static int getXP(@NotNull Player p) {
        return xp.get(p.getName() + "_xp_");
    }

    public static void setXP(@NotNull Player p, Integer amount) {
        xp.put(p.getName() + "_xp_", Math.max(amount, 0));
    }

    public static void addXP(@NotNull Player p, Integer amount) {
        xp.replace(p.getName() + "_xp_", getXP(p) + amount);
        RPGMythicc.get().getChatManager().sendActionBar(p, Files.colorize("&a+ " + amount + "&6 Mythicc XP"));
        Data.checkLevelup(p);
    }

    public static void removeXP(@NotNull Player p, Integer amount) {
        if (getXP(p) - amount > 0) {
            xp.replace(p.getName() + "_xp_", getXP(p) - amount);
        } else {
            xp.put(p.getName() + "_xp_", 0);
        }
        RPGMythicc.get().getChatManager().sendActionBar(p, Files.colorize("&c- " + amount + "&6 Mythicc XP"));
    }
}

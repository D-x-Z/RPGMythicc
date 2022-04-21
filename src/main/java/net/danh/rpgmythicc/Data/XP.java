package net.danh.rpgmythicc.Data;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.danh.rpgmythicc.Manager.Files;
import net.danh.rpgmythicc.RPGMythicc;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class XP {

    private static final HashMap<String, Integer> xp = new HashMap<>();

    public static int getXPData(@NotNull Player p) {
        return Files.getdatafile().getInt("players." + p.getName() + ".xp");
    }

    public static int getXP(@NotNull Player p) {
        return xp.get(p.getName() + "_xp_");
    }

    public static void setXP(@NotNull Player p, Integer amount) {
        xp.put(p.getName() + "_xp_", Math.max(amount, 0));
    }

    public static void addXP(@NotNull Player p, Integer amount) {
        Hologram hologram = HologramsAPI.createHologram(RPGMythicc.get(), p.getLocation());
        hologram.appendTextLine(Files.colorize("&a+ " + amount + " RPGMythicc XP"));
        xp.replace(p.getName() + "_xp_", getXP(p) + amount);
        Data.checkLevelup(p);
    }

    public static void removeXP(@NotNull Player p, Integer amount) {
        if (getXP(p) - amount > 0) {
            xp.replace(p.getName() + "_xp_", getXP(p) - amount);
        } else {
            xp.put(p.getName() + "_xp_", 0);
        }
        Hologram hologram = HologramsAPI.createHologram(RPGMythicc.get(), p.getLocation());
        hologram.appendTextLine(Files.colorize("&c- " + amount + " RPGMythicc XP"));
    }

    public static void saveXPData(@NotNull Player p) {
        Files.getdatafile().set("players." + p.getName() + ".xp", getXP(p));
    }
}

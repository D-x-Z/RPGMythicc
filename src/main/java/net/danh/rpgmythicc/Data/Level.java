package net.danh.rpgmythicc.Data;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.danh.rpgmythicc.Manager.Files;
import net.danh.rpgmythicc.RPGMythicc;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Level {

    static HashMap<String, Integer> level = new HashMap<>();

    public static int getLevelData(@NotNull Player p) {
        return Files.getdatafile().getInt("players." + p.getName() + ".level");
    }

    public static int getLevel(@NotNull Player p) {
        return level.get(p.getName() + "_level_");
    }

    public static void setLevel(@NotNull Player p, Integer amount) {
        level.put(p.getName() + "_level_", Math.max(amount, 1));
    }

    public static void addLevel(@NotNull Player p, Integer amount) {
        Hologram hologram = HologramsAPI.createHologram(RPGMythicc.get(), p.getLocation());
        hologram.appendTextLine(Files.colorize("&a+ " + amount + " RPGMythicc Level"));
        level.replace(p.getName() + "_level_", getLevel(p) + amount);
    }

    public static void removeLevel(@NotNull Player p, Integer amount) {
        if (getLevel(p) - amount > 1) {
            level.replace(p.getName() + "_level_", getLevel(p) - amount);
        } else {
            level.put(p.getName() + "_level_", 1);
        }
        Hologram hologram = HologramsAPI.createHologram(RPGMythicc.get(), p.getLocation());
        hologram.appendTextLine(Files.colorize("&c- " + amount + " RPGMythicc Level"));
    }

    public static void saveLevelData(@NotNull Player p) {
        Files.getdatafile().set("players." + p.getName() + ".level", getLevel(p));
    }
}

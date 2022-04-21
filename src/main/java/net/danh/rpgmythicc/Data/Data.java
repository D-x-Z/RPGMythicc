package net.danh.rpgmythicc.Data;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import net.danh.rpgmythicc.Manager.Files;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Data {

    public static void save(Player p) {
        Level.saveLevelData(p);
        XP.saveXPData(p);
        Files.savedata();
    }

    public static void checkLevelup(Player p) {
        if (XP.getXP(p) >= Level.getLevel(p) * 100) {
            XP.setXP(p, 0);
            Level.addLevel(p, 1);
        }
    }

    public static void deleteIfOld(@NotNull Hologram hologram) {

        long tenMinutesMillis = 60 * 1000; // Ten minutes in milliseconds
        long elapsedMillis = System.currentTimeMillis() - hologram.getCreationTimestamp(); // Milliseconds elapsed from the creation of the hologram

        if (elapsedMillis > tenMinutesMillis) {
            hologram.delete();
        }
    }
}

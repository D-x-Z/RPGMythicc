package net.danh.rpgmythicc.Data;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import net.danh.rpgmythicc.Manager.Files;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Data {

    public static void save(@NotNull Player p) {
        Files.getdatafile().set("players." + p.getName() + ".Level", Level.getLevel(p));
        Files.getdatafile().set("players." + p.getName() + ".XP", XP.getXP(p));
        Files.getdatafile().set("players." + p.getName() + ".RankID", RankID.getRankID(p));
        Files.savedata();
    }

    public static void checkLevelup(Player p) {
        if (XP.getXP(p) >= Level.getLevel(p) * 100) {
            XP.setXP(p, 0);
            Level.addLevel(p, 1);
            if (Level.getLevel(p) % 10 == 0) {
                RankID.addRankID(p, 1);
            }
        }
    }

    public static void deleteIfOld(@NotNull Hologram hologram) {
        long elapsedMillis = System.currentTimeMillis() - hologram.getCreationTimestamp(); // Milliseconds elapsed from the creation of the hologram
        if (elapsedMillis >= 1000) {
            hologram.delete();
        }
    }
}

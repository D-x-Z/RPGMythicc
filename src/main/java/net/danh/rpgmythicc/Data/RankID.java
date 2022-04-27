package net.danh.rpgmythicc.Data;

import net.danh.rpgmythicc.PManager.Files;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RankID {

    private static final HashMap<String, Integer> RankID = new HashMap<>();

    public static int getRankIDData(@NotNull Player p) {
        return Files.getdatafile().getInt("players." + p.getName() + ".RankID");
    }

    public static int getRankID(@NotNull Player p) {
        if (isLoad(p)) {
            setRankID(p, Math.max(getRankIDData(p), 0));
        }
        return RankID.get(p.getName() + "_RankID_");
    }

    public static boolean isLoad(@NotNull Player p) {
        return !RankID.containsKey(p.getName() + "_RankID_");
    }

    public static void setRankID(@NotNull Player p, Integer id) {
        RankID.put(p.getName() + "_RankID_", Math.max(id, 0));
    }

    public static void addRankID(@NotNull Player p, Integer id) {
        RankID.replace(p.getName() + "_RankID_", getRankID(p) + id);
    }

    public static void removeRankID(@NotNull Player p, Integer id) {
        if (getRankID(p) - id > 0) {
            RankID.replace(p.getName() + "_RankID_", getRankID(p) - id);
        } else {
            RankID.put(p.getName() + "_RankID_", 0);
        }
    }
}

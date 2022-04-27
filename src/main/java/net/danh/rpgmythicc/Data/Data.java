package net.danh.rpgmythicc.Data;

import net.danh.rpgmythicc.PManager.Files;
import net.danh.rpgmythicc.RPGMythicc;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
            XP.removeXP(p, Level.getLevel(p) * 100);
            Level.addLevel(p, 1);
            RPGMythicc.get().getAdvancementManager().sendToast(new ItemStack(Material.EXPERIENCE_BOTTLE), p.getUniqueId(), "Chúc mừng bạn đã lên Mythicc Level " + Level.getLevel(p), "Level");
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 2);
            if (Level.getLevel(p) % 10 == 0) {
                String id = Files.getconfigfile().getString("SETTINGS.RANK.ID" + RankID.getRankID(p));
                if (id == null) {
                    id = Files.getconfigfile().getString("SETTINGS.RANK.ID0");
                }
                RankID.addRankID(p, 1);
                RPGMythicc.get().getAdvancementManager().sendToast(new ItemStack(Material.IRON_SWORD), p.getUniqueId(), "Chúc mừng bạn đạt được Mythicc ID " + id, "ID");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 2);
            }
        }
    }
}

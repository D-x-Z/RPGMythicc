package net.danh.rpgmythicc.Compatible;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.stat.type.DoubleStat;
import net.Indyuce.mmoitems.stat.type.ItemRestriction;
import net.danh.rpgmythicc.Data.RankID;
import net.danh.rpgmythicc.Manager.Files;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

public class MythiccID extends DoubleStat implements ItemRestriction {

    public MythiccID() {
        super("MYTHICC_ID", Material.EMERALD, "Required RPGMythicc ID",
                new String[]{"The ID your item needs", "in order to be used."}, new String[]{"all"});
    }


    @Override
    public boolean canUse(@NotNull RPGPlayer rpgPlayer, @NotNull NBTItem nbtItem, boolean b) {
        int Rank = nbtItem.getInteger("MMOITEMS_MYTHICC_ID");
        if (RankID.isUnload(rpgPlayer.getPlayer()) && RankID.getRankID(rpgPlayer.getPlayer()) < Rank && !rpgPlayer.getPlayer().hasPermission("RPGMythicc.admin")) {
            if (b) {
                rpgPlayer.getPlayer().sendTitle(Files.colorize("&c&lCảnh Báo"), Files.colorize("&3ID của bạn không thể để dùng vật phẩm này"), 10, 10, 10);
                rpgPlayer.getPlayer().playSound(rpgPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1.5f);
                /*                new StatModifier(rpgPlayer.getPlayer().getName(), "MYTHICC_LEVEL", 5 - Level.getLevel(rpgPlayer.getPlayer())).register(MMOPlayerData.get(rpgPlayer.getPlayer()));
                 */
            }
            return false;
        }
        return true;
    }
}

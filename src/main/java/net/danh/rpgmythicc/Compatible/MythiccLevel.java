package net.danh.rpgmythicc.Compatible;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.stat.type.DoubleStat;
import net.Indyuce.mmoitems.stat.type.ItemRestriction;
import net.danh.rpgmythicc.Data.Level;
import net.danh.rpgmythicc.Manager.Files;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

public class MythiccLevel extends DoubleStat implements ItemRestriction {

    public MythiccLevel() {
        super("MYTHICC_LEVEL", Material.EXPERIENCE_BOTTLE, "Required RPGMythicc Level",
                new String[]{"The level your item needs", "in order to be used."}, new String[]{"all"});
    }


    @Override
    public boolean canUse(@NotNull RPGPlayer rpgPlayer, @NotNull NBTItem nbtItem, boolean b) {
        int level = nbtItem.getInteger("MMOITEMS_MYTHICC_LEVEL");
        if (Level.isUnload(rpgPlayer.getPlayer()) && Level.getLevel(rpgPlayer.getPlayer()) < level && !rpgPlayer.getPlayer().hasPermission("RPGMythicc.admin")) {
            if (b) {
                rpgPlayer.getPlayer().sendTitle(Files.colorize("&c&lCảnh Báo"), Files.colorize("&3Cấp độ của bạn không đủ để dùng vật phẩm này"), 20, 10, 20);
                rpgPlayer.getPlayer().playSound(rpgPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1.5f);
                /*                new StatModifier(rpgPlayer.getPlayer().getName(), "MYTHICC_LEVEL", 5 - Level.getLevel(rpgPlayer.getPlayer())).register(MMOPlayerData.get(rpgPlayer.getPlayer()));
                 */
            }
            return false;
        }
        return true;
    }
}

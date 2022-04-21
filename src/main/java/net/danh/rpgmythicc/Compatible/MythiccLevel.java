package net.danh.rpgmythicc.Compatible;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.api.util.message.Message;
import net.Indyuce.mmoitems.stat.type.DoubleStat;
import net.Indyuce.mmoitems.stat.type.ItemRestriction;
import net.danh.rpgmythicc.Data.Level;
import org.bukkit.ChatColor;
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
        if (Level.getLevel(rpgPlayer.getPlayer()) < level && !rpgPlayer.getPlayer().hasPermission("RPGMythicc.admin")) {
            if (b) {
                Message.NOT_ENOUGH_LEVELS.format(ChatColor.RED).send(rpgPlayer.getPlayer());
                rpgPlayer.getPlayer().playSound(rpgPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1.5f);
            }
            return false;
        }
        return true;
    }
}

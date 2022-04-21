package net.danh.rpgmythicc.Event;

import net.danh.rpgmythicc.Data.Level;
import net.danh.rpgmythicc.Data.XP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class Join implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onJoin(@NotNull PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Level.setLevel(p, Level.getLevelData(p));
        XP.setXP(p, XP.getXPData(p));
    }
}

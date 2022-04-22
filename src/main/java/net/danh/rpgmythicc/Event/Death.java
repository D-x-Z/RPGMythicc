package net.danh.rpgmythicc.Event;

import net.danh.rpgmythicc.Data.XP;
import net.danh.rpgmythicc.Manager.Files;
import net.danh.rpgmythicc.RPGMythicc;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public class Death implements Listener {

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player k = p.getKiller();
        double money = RPGMythicc.economy.getBalance(p) / 20;
        int xp = XP.getXP(p) / 5;
        EconomyResponse economyResponse = RPGMythicc.economy.withdrawPlayer(p, money);
        XP.removeXP(p, xp);
        if (economyResponse.transactionSuccess()) {
            p.sendMessage(Files.colorize("&aBạn chết và mất 20% số tiền hiện có và 5% XP"));
        }
        if (k != null) {
            EconomyResponse economy = RPGMythicc.economy.depositPlayer(k, money);
            XP.addXP(k, xp);
            if (economy.transactionSuccess()) {
                k.sendMessage(Files.colorize("&aBạn nhận được và 20% số tiền và 5% XP từ &3" + p.getName()));
            }
        }
    }
}

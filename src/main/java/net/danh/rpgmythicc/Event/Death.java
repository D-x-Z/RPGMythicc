package net.danh.rpgmythicc.Event;

import net.danh.rpgmythicc.Data.XP;
import net.danh.rpgmythicc.PManager.Files;
import net.danh.rpgmythicc.RPGMythicc;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Death implements Listener {

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player k = p.getKiller();
        if (Files.getconfigfile().getBoolean("SETTINGS.DEATH.ENABLE")) {
            double money = RPGMythicc.economy.getBalance(p) / Files.getconfigfile().getInt("SETTINGS.DEATH.MONEY");
            int xp = XP.getXP(p) / Files.getconfigfile().getInt("SETTINGS.DEATH.XP");
            EconomyResponse economyResponse = RPGMythicc.economy.withdrawPlayer(p, money);
            XP.removeXP(p, xp);
            if (economyResponse.transactionSuccess()) {
                RPGMythicc.get().getChatManager().sendMessage(p, (Files.colorize(Objects.requireNonNull(Files.getlanguagefile().getString("DEATH.PLAYER"))
                        .replaceAll("%money%", String.format("%4.3f", money))
                        .replaceAll("%xp%", String.format("%,d", xp)))));
            }
            if (k != null) {
                EconomyResponse economy = RPGMythicc.economy.depositPlayer(k, money);
                XP.addXP(k, xp);
                if (economy.transactionSuccess()) {
                    RPGMythicc.get().getChatManager().sendMessage(k, Files.colorize(Objects.requireNonNull(Files.getlanguagefile().getString("DEATH.KILLER"))
                            .replaceAll("%money%", String.format("%4.3f", money))
                            .replaceAll("%xp%", String.format("%,d", xp))
                            .replaceAll("%player%", p.getName())));
                }
            }
        }
    }
}

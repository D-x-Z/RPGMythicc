package net.danh.rpgmythicc;

import net.Indyuce.mmoitems.MMOItems;
import net.danh.rpgmythicc.Compatible.MythiccID;
import net.danh.rpgmythicc.Compatible.MythiccLevel;
import net.danh.rpgmythicc.Compatible.PlaceholderAPI;
import net.danh.rpgmythicc.Data.Data;
import net.danh.rpgmythicc.Event.Death;
import net.danh.rpgmythicc.Event.Join;
import net.danh.rpgmythicc.Event.Quit;
import net.danh.rpgmythicc.Manager.AdvancementManager;
import net.danh.rpgmythicc.Manager.ChatManager;
import net.danh.rpgmythicc.PManager.Commands;
import net.danh.rpgmythicc.PManager.Files;
import net.danh.rpgmythicc.Utils.VersionChecker;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.logging.Level;

public final class RPGMythicc extends JavaPlugin implements Listener {

    public static Economy economy;
    private static RPGMythicc instance;
    private ChatManager chatManager;
    private AdvancementManager advancementManager;

    public static RPGMythicc get() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        VersionChecker.checkServerVersion();
        MMOItems.plugin.getStats().register(new MythiccLevel());
        MMOItems.plugin.getStats().register(new MythiccID());
    }

    @Override
    public void onEnable() {
        if (!initialSetupSuccessful()) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            this.setEnabled(false);
            return;
        }
        if (getServer().getPluginManager().getPlugin("MMOItems") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with MMOItems!");
            MMOItems.plugin.getLogger().log(Level.INFO, "Registered Mythicc Level Stats");
            MMOItems.plugin.getLogger().log(Level.INFO, "Registered Mythicc ID Stats");
        } else {
            getLogger().severe("*** MMOItems is not installed or not enabled. ***");
            getLogger().severe("*** This plugin will be disabled. ***");
            this.setEnabled(false);
            return;
        }
        if (getServer().getPluginManager().getPlugin("Vault") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with Vault!");
        } else {
            getLogger().severe("*** Vault is not installed or not enabled. ***");
            getLogger().severe("*** This plugin will be disabled. ***");
            this.setEnabled(false);
            return;
        }
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with PlaceholderAPI!");
            new PlaceholderAPI().register();
        }
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
        getServer().getPluginManager().registerEvents(new Death(), this);
        Objects.requireNonNull(getCommand("RPGMythicc")).setExecutor(new Commands());
        Files.createfiles();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : getServer().getOnlinePlayers()) {
                    p.setLevel(net.danh.rpgmythicc.Data.Level.getLevel(p));
                }
            }

        }.runTaskTimer(this, 20L, 20L);
    }

    @Override
    public void onDisable() {
        for (Player p : getServer().getOnlinePlayers()) {
            Data.save(p);
        }
        Files.saveconfig();
        Files.savelanguage();
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) economy = economyProvider.getProvider();
        return (economy != null);
    }

    private boolean initialSetupSuccessful() {
        if (!VersionChecker.isSupportedVersion()) {
            return false;
        }

        VersionChecker.registerClasses();
        return true;
    }

    public ChatManager getChatManager() {
        return chatManager;
    }

    public void setChatManager(ChatManager chatManager) {
        this.chatManager = chatManager;
    }


    public AdvancementManager getAdvancementManager() {
        return advancementManager;
    }

    public void setAdvancementManager(AdvancementManager advancementManager) {
        this.advancementManager = advancementManager;
    }
}

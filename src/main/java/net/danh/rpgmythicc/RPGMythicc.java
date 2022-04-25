package net.danh.rpgmythicc;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.Indyuce.mmoitems.MMOItems;
import net.danh.rpgmythicc.Compatible.MythiccID;
import net.danh.rpgmythicc.Compatible.MythiccLevel;
import net.danh.rpgmythicc.Compatible.PlaceholderAPI;
import net.danh.rpgmythicc.Data.Data;
import net.danh.rpgmythicc.Event.Death;
import net.danh.rpgmythicc.Event.Join;
import net.danh.rpgmythicc.Event.Quit;
import net.danh.rpgmythicc.Manager.Commands;
import net.danh.rpgmythicc.Manager.Files;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import preponderous.ponder.minecraft.bukkit.abs.PonderBukkitPlugin;
import preponderous.ponder.minecraft.bukkit.tools.EventHandlerRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;

public final class RPGMythicc extends PonderBukkitPlugin implements Listener {

    public static Economy economy;
    private static RPGMythicc instance;

    public static RPGMythicc get() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        MMOItems.plugin.getStats().register(new MythiccLevel());
        MMOItems.plugin.getStats().register(new MythiccID());
    }

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            this.setEnabled(false);
            return;
        }
        if (getServer().getPluginManager().getPlugin("MMOItems") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with MMOItems!");
            MMOItems.plugin.getLogger().log(Level.INFO,"Registered Mythicc Level Stats");
            MMOItems.plugin.getLogger().log(Level.INFO,"Registered Mythicc ID Stats");
        } else {
            getLogger().severe("*** MMOItems is not installed or not enabled. ***");
            getLogger().severe("*** This plugin will be disabled. ***");
            this.setEnabled(false);
            return;
        }
        if (getServer().getPluginManager().getPlugin("HolographicDisplays") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with HolographicDisplays!");
        } else {
            getLogger().severe("*** HolographicDisplays is not installed or not enabled. ***");
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
        Objects.requireNonNull(getCommand("RPGMythicc")).setExecutor(new Commands());
        registerEventHandlers();
        Files.createfiles();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : getServer().getOnlinePlayers()) {
                    p.setLevel(net.danh.rpgmythicc.Data.Level.getLevel(p));
                }
                for (Hologram hologram : HologramsAPI.getHolograms(RPGMythicc.get())) {
                    Data.deleteIfOld(hologram);
                }
            }

        }.runTaskTimer(this, 20L, 20L);
    }

    @Override
    public void onDisable() {
        for (Player p : getServer().getOnlinePlayers()) {
            Data.save(p);
        }
        for (Hologram hologram : HologramsAPI.getHolograms(RPGMythicc.get())) {
            Data.deleteIfOld(hologram);
        }
        Files.saveconfig();
        Files.savelanguage();
    }

    @Contract(" -> new")
    private @NotNull ArrayList<Listener> initializeListeners() {
        return new ArrayList<>(Arrays.asList(
                new Join(),
                new Death(),
                new Quit()
        ));
    }

    /**
     * Registers the event handlers of the plugin using Ponder.
     */
    private void registerEventHandlers() {
        ArrayList<Listener> listeners = initializeListeners();
        EventHandlerRegistry eventHandlerRegistry = new EventHandlerRegistry();
        eventHandlerRegistry.registerEventHandlers(listeners, this);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) economy = economyProvider.getProvider();
        return (economy != null);
    }
}

package net.danh.rpgmythicc.Manager;

import net.danh.rpgmythicc.Data.Level;
import net.danh.rpgmythicc.Data.XP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("RPGMythicc")) {
            if (sender.hasPermission("RPGMythicc.admin")) {
                if (args.length == 0) {
                    for (String help : Files.getlanguagefile().getStringList("HELP")) {
                        sender.sendMessage(Files.colorize(help));
                    }
                }
                if (args.length == 4) {
                    if (Bukkit.getPlayer(args[2]) != null) {
                        if (Integer.parseInt(args[3]) >= 0) {
                            if (args[1].equalsIgnoreCase("set")) {
                                if (args[0].equalsIgnoreCase("level")) {
                                    Level.setLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                                if (args[0].equalsIgnoreCase("xp")) {
                                    XP.setXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                if (args[0].equalsIgnoreCase("level")) {
                                    Level.addLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                                if (args[0].equalsIgnoreCase("xp")) {
                                    XP.addXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                if (args[0].equalsIgnoreCase("level")) {
                                    Level.removeLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                                if (args[0].equalsIgnoreCase("xp")) {
                                    XP.removeXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}

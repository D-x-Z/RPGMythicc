package net.danh.rpgmythicc.PManager;

import net.danh.rpgmythicc.Data.Level;
import net.danh.rpgmythicc.Data.RankID;
import net.danh.rpgmythicc.Data.XP;
import net.danh.rpgmythicc.RPGMythicc;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("RPGMythicc")) {
            if (sender.hasPermission("RPGMythicc.admin")) {
                if (args.length == 0) {
                    for (String help : Files.getlanguagefile().getStringList("HELP")) {
                        sender.sendMessage(Files.colorize(help));
                    }
                    return true;
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        Files.reloadfiles();
                        sender.sendMessage(Files.colorize("Reloaded"));
                        return true;
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
                                if (args[0].equalsIgnoreCase("rankid")) {
                                    RankID.setRankID(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                if (args[0].equalsIgnoreCase("level")) {
                                    Level.addLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                                if (args[0].equalsIgnoreCase("xp")) {
                                    XP.addXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                                if (args[0].equalsIgnoreCase("rankid")) {
                                    RankID.addRankID(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                if (args[0].equalsIgnoreCase("level")) {
                                    Level.removeLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                                if (args[0].equalsIgnoreCase("xp")) {
                                    XP.removeXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                                if (args[0].equalsIgnoreCase("rankid")) {
                                    RankID.removeRankID(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                }
                            }
                            return true;
                        }
                    }
                }
                if (args.length >= 4) {
                    if (Bukkit.getPlayer(args[2]) == null || Material.getMaterial(args[1].toUpperCase()) == null) {
                        return true;
                    }
                    ItemStack itemStack = new ItemStack(Objects.requireNonNull(Material.getMaterial(args[1].toUpperCase())));
                    UUID uuid = Objects.requireNonNull(Bukkit.getPlayer(args[2])).getUniqueId();
                    String title = String.join(" ", Arrays.asList(args).subList(3, args.length));
                    if (args[0].equalsIgnoreCase("c")) {
                        RPGMythicc.get().getAdvancementManager().sendChallengeToast(itemStack, uuid, title, "Custom");
                    }
                    if (args[0].equalsIgnoreCase("g")) {
                        RPGMythicc.get().getAdvancementManager().sendToast(itemStack, uuid, title, "Custom");
                    }
                    return true;
                }
            }
        }
        return false;
    }
}

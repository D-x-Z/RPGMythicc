package net.danh.rpgmythicc.Manager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface AdvancementManager {

    void sendToast(ItemStack item, UUID uuid, String title, String description);

    void sendToast(UUID uuid, String title, String description);

    void sendToast(Player player, String title, String description);

    void sendToast(String playerName, String title, String description);

    void sendGoalToast(ItemStack item, UUID uuid, String title, String description);

    void sendChallengeToast(ItemStack item, UUID uuid, String title, String description);


}

package net.danh.rpgmythicc.Manager;

import org.bukkit.entity.Player;

public interface ChatManager {

    void sendMessage(Player player, String msg);

    void sendActionBar(Player player, String msg);

}

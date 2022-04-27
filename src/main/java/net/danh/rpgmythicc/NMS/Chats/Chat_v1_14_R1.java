package net.danh.rpgmythicc.NMS.Chats;

import net.danh.rpgmythicc.Manager.ChatManager;
import net.danh.rpgmythicc.Utils.JSONUtils;
import net.minecraft.server.v1_14_R1.ChatMessageType;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Chat_v1_14_R1 implements ChatManager {

    @Override
    public void sendMessage(Player player, String msg) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(msg));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public void sendActionBar(Player player, String msg) {
        IChatBaseComponent baseComponent = IChatBaseComponent.ChatSerializer.a(JSONUtils.formatSimpleJSON(msg));
        PacketPlayOutChat packet = new PacketPlayOutChat(baseComponent, ChatMessageType.GAME_INFO);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}


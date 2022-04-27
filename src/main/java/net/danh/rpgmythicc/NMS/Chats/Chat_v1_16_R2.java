package net.danh.rpgmythicc.NMS.Chats;

import net.danh.rpgmythicc.Manager.ChatManager;
import net.danh.rpgmythicc.Utils.JSONUtils;
import net.minecraft.server.v1_16_R2.ChatMessageType;
import net.minecraft.server.v1_16_R2.IChatBaseComponent;
import net.minecraft.server.v1_16_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R2.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Chat_v1_16_R2 implements ChatManager {

    @Override
    public void sendMessage(Player player, String msg) {
        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(msg), ChatMessageType.CHAT, player.getUniqueId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public void sendActionBar(Player player, String msg) {
        IChatBaseComponent baseComponent = ChatSerializer.a(JSONUtils.formatSimpleJSON(msg));
        PacketPlayOutChat packet = new PacketPlayOutChat(baseComponent, ChatMessageType.GAME_INFO, player.getUniqueId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}


package net.danh.rpgmythicc.NMS.Chats;

import net.danh.rpgmythicc.Manager.ChatManager;
import net.danh.rpgmythicc.Utils.JSONUtils;
import net.minecraft.network.chat.ChatMessageType;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.chat.IChatBaseComponent.ChatSerializer;
import net.minecraft.network.protocol.game.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Chat_v1_17_R1 implements ChatManager {

    @Override
    public void sendMessage(Player player, String msg) {
        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(msg), ChatMessageType.a, player.getUniqueId());
        ((CraftPlayer) player).getHandle().b.sendPacket(packet);
    }

    @Override
    public void sendActionBar(Player player, String msg) {
        IChatBaseComponent baseComponent = ChatSerializer.a(JSONUtils.formatSimpleJSON(msg));
        PacketPlayOutChat packet = new PacketPlayOutChat(baseComponent, ChatMessageType.c, player.getUniqueId());
        ((CraftPlayer) player).getHandle().b.sendPacket(packet);
    }
}


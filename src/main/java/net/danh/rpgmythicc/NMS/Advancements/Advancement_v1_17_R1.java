package net.danh.rpgmythicc.NMS.Advancements;

import com.google.gson.JsonObject;
import net.danh.rpgmythicc.Enum.AdvancementBackground;
import net.danh.rpgmythicc.Manager.AdvancementManager;
import net.danh.rpgmythicc.PManager.Files;
import net.danh.rpgmythicc.Utils.JSONUtils;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.LootSerializationContext;
import net.minecraft.network.chat.IChatBaseComponent.ChatSerializer;
import net.minecraft.network.protocol.game.PacketPlayOutAdvancements;
import net.minecraft.resources.MinecraftKey;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Advancement_v1_17_R1 implements AdvancementManager {

    @Override
    public void sendToast(ItemStack item, UUID uuid, String title, String description) {
        Player player = Bukkit.getPlayer(uuid);

        MinecraftKey pluginKey = new MinecraftKey("ca.plugin", "notification");

        AdvancementRewards advRewards = new AdvancementRewards(0, new MinecraftKey[0], new MinecraftKey[0], null);

        Map<String, Criterion> advCriteria = new HashMap<>();
        String[][] advRequirements;

        advCriteria.put("for_free", new Criterion(new CriterionInstance() {

            @Override
            public JsonObject a(LootSerializationContext lootSerializationContext) {
                return null;
            }

            @Override
            public MinecraftKey a() {
                return new MinecraftKey("minecraft", "impossible");
            }
        }));

        ArrayList<String[]> fixedRequirements = new ArrayList<>();

        fixedRequirements.add(new String[]{"for_free"});

        advRequirements = Arrays.stream(fixedRequirements.toArray()).toArray(String[][]::new);

        AdvancementDisplay display = new AdvancementDisplay(CraftItemStack.asNMSCopy(item),
                ChatSerializer.a(JSONUtils.formatSimpleJSON(Files.colorize(title))),
                ChatSerializer.a(JSONUtils.formatSimpleJSON(Files.colorize(description))),
                new MinecraftKey(AdvancementBackground.SNOW.getValue()),
                AdvancementFrameType.c, true, true, true);
        Advancement advancement = new Advancement(pluginKey, null, display, advRewards, advCriteria, advRequirements);

        HashMap<MinecraftKey, AdvancementProgress> prg = new HashMap<>();

        AdvancementProgress advPrg = new AdvancementProgress();
        advPrg.a(advCriteria, advRequirements);
        advPrg.getCriterionProgress("for_free").b();
        prg.put(pluginKey, advPrg);

        PacketPlayOutAdvancements packet = new PacketPlayOutAdvancements(false, Collections.singletonList(advancement), new HashSet<>(), prg);
        ((CraftPlayer) player).getHandle().b.sendPacket(packet);

        HashSet<MinecraftKey> rm = new HashSet<>();
        rm.add(pluginKey);
        prg.clear();
        packet = new PacketPlayOutAdvancements(false, new ArrayList<>(), rm, prg);
        ((CraftPlayer) player).getHandle().b.sendPacket(packet);
    }

    @Override
    public void sendToast(UUID uuid, String title, String description) {

    }

    @Override
    public void sendToast(Player player, String title, String description) {

    }

    @Override
    public void sendToast(String playerName, String title, String description) {

    }

    @Override
    public void sendGoalToast(ItemStack item, UUID uuid, String title, String description) {

    }

    @Override
    public void sendChallengeToast(ItemStack item, UUID uuid, String title, String description) {
        Player player = Bukkit.getPlayer(uuid);

        MinecraftKey notName = new MinecraftKey("ca.plugin", "notification");

        AdvancementRewards advRewards = new AdvancementRewards(0, new MinecraftKey[0], new MinecraftKey[0], null);

        Map<String, Criterion> advCriteria = new HashMap<>();
        String[][] advRequirements;

        advCriteria.put("for_free", new Criterion(new CriterionInstance() {

            @Override
            public JsonObject a(LootSerializationContext lootSerializationContext) {
                return null;
            }

            @Override
            public MinecraftKey a() {
                return new MinecraftKey("minecraft", "impossible");
            }
        }));

        ArrayList<String[]> fixedRequirements = new ArrayList<>();

        fixedRequirements.add(new String[]{"for_free"});

        advRequirements = Arrays.stream(fixedRequirements.toArray()).toArray(String[][]::new);

        AdvancementDisplay display = new AdvancementDisplay(CraftItemStack.asNMSCopy(item),
                ChatSerializer.a(JSONUtils.formatSimpleJSON(Files.colorize(title))),
                ChatSerializer.a(JSONUtils.formatSimpleJSON(Files.colorize(description))),
                new MinecraftKey("minecraft:textures/block/dirt.png"),
                AdvancementFrameType.b, true, true, true);
        Advancement advancement = new Advancement(notName, null, display, advRewards, advCriteria, advRequirements);

        HashMap<MinecraftKey, AdvancementProgress> prg = new HashMap<>();

        AdvancementProgress advPrg = new AdvancementProgress();
        advPrg.a(advCriteria, advRequirements);
        advPrg.getCriterionProgress("for_free").b();
        prg.put(notName, advPrg);

        PacketPlayOutAdvancements packet = new PacketPlayOutAdvancements(false, Arrays.asList(advancement), new HashSet<>(), prg);
        ((CraftPlayer) player).getHandle().b.sendPacket(packet);
    }
}

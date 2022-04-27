package net.danh.rpgmythicc.Enum;

public enum AdvancementBackground {

    // Advancement Textures
    ADVENTURE("minecraft:textures/gui/advancements/backgrounds/adventure.png"),
    END("minecraft:textures/gui/advancements/backgrounds/end.png"),
    HUSBANDRY("minecraft:textures/gui/advancements/backgrounds/husbandry.png"),
    NETHER("minecraft:textures/gui/advancements/backgrounds/nether.png"),
    STONE("minecraft:textures/gui/advancements/backgrounds/stone.png"),

    // Block Textures
    BEDROCK("minecraft:textures/block/bedrock.png"),
    BEACON("minecraft:textures/block/beacon.png"),
    BRICKS("minecraft:textures/block/bricks.png"),
    CLAY("minecraft:textures/block/clay.png"),
    COBBLESTONE("minecraft:textures/block/cobblestone.png"),
    CONDUIT("minecraft:textures/block/conduit.png"),
    DIRT("minecraft:textures/block/dirt.png"),
    GLASS("minecraft:textures/block/glass.png"),
    GRAVEL("minecraft:textures/block/gravel.png"),
    ICE("minecraft:textures/block/ice.png"),
    LADDER("minecraft:textures/block/ladder.png"),
    OBSIDIAN("minecraft:textures/block/obsidian.png"),
    SAND("minecraft:textures/block/sand.png"),
    SANDSTONE("minecraft:textures/block/sandstone.png"),
    DARK_PRISMARINE("minecraft:textures/block/dark_prismarine.png"),
    SEA_LANTERN("minecraft:textures/block/sea_lantern.png"),
    SNOW("minecraft:textures/block/snow.png"),
    STONE_BLOCK("minecraft:textures/block/stone.png"),
    ;

    public String value;

    AdvancementBackground(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void fromNamespace(String string) {
        value = string;
    }

}

package net.danh.rpgmythicc.Compatible;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.rpgmythicc.Data.Level;
import net.danh.rpgmythicc.Data.RankID;
import net.danh.rpgmythicc.Data.XP;
import net.danh.rpgmythicc.PManager.Files;
import net.danh.rpgmythicc.RPGMythicc;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPI extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "RPGMythicc";
    }

    @Override
    public @NotNull String getAuthor() {
        return RPGMythicc.get().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return RPGMythicc.get().getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player p, @NotNull String identifier) {
        if (p == null) {
            return "";
        }
        if (identifier.equalsIgnoreCase("xp")) {
            return String.valueOf(XP.getXP(p));
        }
        if (identifier.equalsIgnoreCase("xp_format")) {
            return String.format("%,d", XP.getXP(p));
        }
        if (identifier.equalsIgnoreCase("max_xp_format")) {
            return String.format("%,d", Level.getLevel(p) * 100);
        }
        if (identifier.equalsIgnoreCase("level")) {
            return String.valueOf(Level.getLevel(p));
        }
        if (identifier.equalsIgnoreCase("level_format")) {
            return String.format("%,d", Level.getLevel(p));
        }
        if (identifier.equalsIgnoreCase("id")) {
            return String.valueOf(RankID.getRankID(p));
        }
        if (identifier.equalsIgnoreCase("id_format")) {
            return String.format("%,d", RankID.getRankID(p));
        }
        if (identifier.equalsIgnoreCase("rankid")) {
            if (Files.getconfigfile().getString("SETTINGS.RANK.ID" + RankID.getRankID(p)) != null) {
                return Files.getconfigfile().getString("SETTINGS.RANK.ID" + RankID.getRankID(p));
            }
            return Files.getconfigfile().getString("SETTINGS.RANK.ID0");
        }
        return null;
    }
}

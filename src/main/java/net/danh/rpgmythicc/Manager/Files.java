package net.danh.rpgmythicc.Manager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.danh.rpgmythicc.RPGMythicc.get;

public class Files {

    public final static char COLOR_CHAR = ChatColor.COLOR_CHAR;
    private static File dataFile;
    private static FileConfiguration data;

    public static void createfiles() {
        dataFile = new File(get().getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        data = new YamlConfiguration();

        try {
            data.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public static FileConfiguration getdatafile() {
        return data;
    }


    public static void savedata() {
        try {
            data.save(dataFile);
        } catch (IOException ignored) {
        }
    }

    // Colorize messages with preset colorcodes (&) and if using 1.16+, applies hex values via "&#hexvalue"
    public static @NotNull String colorize(String input) {

        input = ChatColor.translateAlternateColorCodes('&', input);
        if (input.startsWith("&#")) {
            input = translateHexColorCodes("&#", "", input);
        }
        return input;
    }

    public static @NotNull String translateHexColorCodes(String startTag, String endTag, String message) {

        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);

        while (matcher.find()) {

            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x" + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1) + COLOR_CHAR
                    + group.charAt(2) + COLOR_CHAR + group.charAt(3) + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5));

        }

        return matcher.appendTail(buffer).toString();
    }

}

package org.mcausc78.fastconsole;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.clip.placeholderapi.PlaceholderAPI;

public class TextUtils {
    public static boolean isEnabledPlaceholderAPI() {
        try {
            Class.forName("me.clip.placeholder.PlaceholderAPI");
            return true;
        } catch(ClassNotFoundException cnfe) {
            return false;
        }
    }
    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static String handle(String s, Player p) {
        return colorize(isEnabledPlaceholderAPI() ? PlaceholderAPI.setPlaceholders(p, s) : s);
    }
    public static String handle(String s, CommandSender cs) {
        return ((cs instanceof Player) ? handle(s, (Player)cs) : handle(s, (Player) null));
    }
}

package me.nerfin.heavyboxes;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class Utils {

    public static Entry plugin;

    public static void setPlugin(Entry plugin) {
        Utils.plugin = plugin;
    }

    public static void sTitle(Player p, String title, String subTitle) {
        p.sendTitle(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(title)), ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(subTitle)), 13, 13, 13);
    }

    public static void sMessage(Player p, String path) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(path)));
    }

    public static int getHbox(Player p) {
        return p.getPersistentDataContainer().get(NamespacedKey.fromString("hbox"), PersistentDataType.INTEGER);
    }

    public static long getHtime(Player p) {
        return p.getPersistentDataContainer().get(NamespacedKey.fromString("htime"),PersistentDataType.LONG);
    }

    public static void setHbox(Player p, int value) {
        p.getPersistentDataContainer().set(NamespacedKey.fromString("hbox"), PersistentDataType.INTEGER, value);
    }

    public static void setHtime(Player p) {
        p.getPersistentDataContainer().set(NamespacedKey.fromString("htime"), PersistentDataType.LONG, System.currentTimeMillis());
    }
}

package me.nerfin.heavyboxes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BoxData {

    private static Entry plugin;

    public static void setPlugin(Entry plugin) {
        BoxData.plugin = plugin;
    }

    private static String boxPermPlayer = "heavyboxes.player";
    private static String boxPermAdmin = "heavyboxes.admin";

    private String boxRgName = plugin.getConfig().getString("config.boxRegionName");
    private String boxRgSkladName = plugin.getConfig().getString("config.skladRegionName");
    private String boxRgZonaName = plugin.getConfig().getString("config.zonaRegionName");
    private String boxDeliveryMethod = plugin.getConfig().getString("config.boxDeliveryMethod");

    private String itemName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config.itemName"));
    private int countMoney = Integer.parseInt(plugin.getConfig().getString("config.money"));

    private Material boxMaterial = Material.valueOf(plugin.getConfig().getString("config.boxMaterial"));
    private Material skladMaterial = Material.valueOf(plugin.getConfig().getString("config.skladMaterial"));
    private Material itemMaterial = Material.valueOf(plugin.getConfig().getString("config.itemMaterial"));

    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(itemMaterial, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(getItemName());
        itemMeta.setLore(getItemLore());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public List<String> getItemLore() {
        List<String> itemLore = plugin.getConfig().getStringList("config.itemLore");
        for (int i = 0; i < itemLore.size(); i++) {
            itemLore.set(i, ChatColor.translateAlternateColorCodes('&', itemLore.get(i)));
        }
        return itemLore;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCountMoney() {
        return countMoney;
    }

    public String getBoxDeliveryMethod() {
        return boxDeliveryMethod;
    }

    public boolean checkPlayerPerm(Player p) {
        return p.hasPermission(boxPermPlayer);
    }

    public boolean checkAdminPerm(Player p) {
        return p.hasPermission(boxPermAdmin);
    }

    public Material getSkladMaterial() {
        return skladMaterial;
    }

    public Material getBoxMaterial() {
        return boxMaterial;
    }

    public String getBoxRgName() {
        return boxRgName;
    }

    public String getBoxRgZonaName() {
        return boxRgZonaName;
    }

    public String getBoxRgSkladName() {
        return  boxRgSkladName;
    }
}

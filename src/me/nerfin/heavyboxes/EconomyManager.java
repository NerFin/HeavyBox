package me.nerfin.heavyboxes;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyManager {

    private static Entry plugin;
    private static Economy e;

    public static void setPlugin(Entry plugin) {
        EconomyManager.plugin = plugin;
    }

    public static void init() {
        RegisteredServiceProvider<Economy> reg = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (reg != null) e = reg.getProvider();
        if (e == null) {
            plugin.getLogger().warning(" ");
            plugin.getLogger().warning("Плагин на экономику не найден.");
            plugin.getLogger().warning("Включите параметр 'useAlternativeCommand' чтобы избежать ошибок.");
            plugin.getLogger().warning(" ");
        }
    }

    public static void giveMoney(Player p) {
        BoxData bd = new BoxData();
        if (!bd.getUseAlternativeCommand())
            e.depositPlayer(p, bd.getCountMoney());
        else
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), bd.getAlternativeCommand().replace("{player}", p.getDisplayName()));
    }
}

package me.nerfin.heavyboxes;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyManager {

    private static Economy e;

    public static void init() {
        RegisteredServiceProvider<Economy> reg = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (reg != null) e = reg.getProvider();
    }

    public static void giveMoney(Player p) {
        BoxData bd = new BoxData();
        e.depositPlayer(p, bd.getCountMoney());
    }
}

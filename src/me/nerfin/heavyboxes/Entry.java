package me.nerfin.heavyboxes;

import me.nerfin.heavyboxes.commands.ReloadConfigCMD;
import me.nerfin.heavyboxes.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Entry extends JavaPlugin {

    @Override
    public void onEnable() {

        this.getLogger().warning("Плагин создал NerFin");

        EconomyManager.init();
        BoxData.setPlugin(this);
        Utils.setPlugin(this);

        getCommand("heavybox").setExecutor(new ReloadConfigCMD(this));

        Bukkit.getPluginManager().registerEvents(new InvClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MoveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DropItemEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new GamemodeEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CraftEvent(), this);

        this.saveDefaultConfig();

    }

    @Override
    public void onDisable() {
    }

    public Entry getInstance() {
        return this;
    }
}

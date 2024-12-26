package me.nerfin.heavyboxes.commands;

import me.nerfin.heavyboxes.BoxData;
import me.nerfin.heavyboxes.Entry;
import me.nerfin.heavyboxes.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfigCMD implements CommandExecutor {

    Entry plugin;

    public ReloadConfigCMD(Entry plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        BoxData bd = new BoxData();
        if (sender instanceof Player)
            if (!bd.checkAdminPerm((Player) sender)) {
                Utils.sMessage((Player) sender, "messages.noAdminPerm");
                return true;
            }
        if (args.length == 0) {
            sender.sendMessage("Перезагрузить плагин: /heavybox reload");
            return true;
        }
        if (args[0].equals("reload")) {
            plugin.reloadConfig();
            Utils.sMessage((Player) sender, "messages.pluginReloaded");
            return true;
        } else {
            sender.sendMessage("Перезагрузить плагин: /heavybox reload");
        }
        return true;
    }
}

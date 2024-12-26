package me.nerfin.heavyboxes.events;

import me.nerfin.heavyboxes.Utils;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().getPersistentDataContainer().get(NamespacedKey.fromString("hbox"), PersistentDataType.INTEGER) == null)
            Utils.setHbox(e.getPlayer(), 0);
        if (e.getPlayer().getPersistentDataContainer().get(NamespacedKey.fromString("htime"), PersistentDataType.LONG) == null)
            Utils.setHtime(e.getPlayer());
    }
}

package me.nerfin.heavyboxes.events;

import me.nerfin.heavyboxes.BoxData;
import me.nerfin.heavyboxes.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemEvent implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        BoxData bd = new BoxData();
        if (Utils.getHbox(e.getPlayer()) == 0)
            return;
        if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(bd.getItemName())) {
            Utils.sMessage(e.getPlayer(), "messages.dropItem");
            e.setCancelled(true);
        }
    }
}

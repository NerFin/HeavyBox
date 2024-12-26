package me.nerfin.heavyboxes.events;

import me.nerfin.heavyboxes.BoxData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvClickEvent implements Listener {

    @EventHandler
    public void onBoxCklick(InventoryClickEvent e) {
        BoxData bd = new BoxData();
        try {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(bd.getItemName())) {
                e.setCancelled(true);
            }
        } catch (NullPointerException err) {
            return;
        }
    }
}

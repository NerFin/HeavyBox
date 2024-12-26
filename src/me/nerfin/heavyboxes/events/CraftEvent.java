package me.nerfin.heavyboxes.events;

import me.nerfin.heavyboxes.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftEvent implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = ((Player) e.getWhoClicked()).getPlayer();
            if (Utils.getHbox(p) != 1) return;
            if (e.getInventory().getRecipe().getResult().getAmount() == 9) {
                e.setCancelled(true);
            }
        }
    }
}

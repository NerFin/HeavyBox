package me.nerfin.heavyboxes.events;

import me.nerfin.heavyboxes.BoxData;
import me.nerfin.heavyboxes.Utils;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.potion.PotionEffectType;

public class GamemodeEvent implements Listener {

    @EventHandler
    public void onCreative(PlayerGameModeChangeEvent e) {
        if (Utils.getHbox(e.getPlayer()) != 1)
            return;
        if (e.getNewGameMode().equals(GameMode.CREATIVE)) {
            BoxData bd = new BoxData();
            Utils.setHbox(e.getPlayer(), 0);
            e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
            e.getPlayer().getInventory().removeItem(bd.getItem());
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 15, 15);
            Utils.sMessage(e.getPlayer(), "messages.gamemode");
        }
    }
}

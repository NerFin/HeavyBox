package me.nerfin.heavyboxes.events;

import me.nerfin.heavyboxes.BoxData;
import me.nerfin.heavyboxes.EconomyManager;
import me.nerfin.heavyboxes.Region;
import me.nerfin.heavyboxes.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;


public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (Utils.getHbox(e.getPlayer()) == 0) return;
        BoxData bd = new BoxData();
        Player p = e.getPlayer();
        if (!Region.checkPlayerRg(p, bd.getBoxRgZonaName())) {
            //Удаление груза если игрок вышел из зоны
            Utils.setHbox(p, 0);
            p.removePotionEffect(PotionEffectType.SLOW);
            p.getInventory().removeItem(bd.getItem());
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 15, 15);
            Utils.sMessage(p, "messages.zona");
            return;
        }
        if (Region.checkPlayerRg(p, bd.getBoxRgSkladName())) {
            if (bd.getBoxDeliveryMethod().equalsIgnoreCase("OLD")) return;
            p.removePotionEffect(PotionEffectType.SLOW);
            p.getInventory().removeItem(bd.getItem());
            Utils.setHbox(p, 0);
            EconomyManager.giveMoney(p);
            Utils.sMessage(p, "messages.yesBox");
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 15, 15);
            Utils.sTitle(p, "messages.skladTitle", "messages.skladSubTitle");
            Utils.setHtime(p);
        }
    }
}

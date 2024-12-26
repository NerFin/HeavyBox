package me.nerfin.heavyboxes.events;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.nerfin.heavyboxes.*;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Set;

public class LClickEvent implements Listener {

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        BoxData bd = new BoxData();
        try {
            if (e.getClickedBlock().getBlockData().getMaterial() != bd.getBoxMaterial() &&
                    e.getClickedBlock().getBlockData().getMaterial() != bd.getSkladMaterial()) return;
        } catch (NullPointerException err) {
            return;
        }
        if (!bd.checkPlayerPerm(e.getPlayer())) {
            Utils.sMessage(e.getPlayer(), "messages.noPerm");
            return;
        }

        if ((System.currentTimeMillis() - Utils.getHtime(e.getPlayer())) < 1000)
            return;
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        Set<ProtectedRegion> prg =  Region.getPlayerRg(b);
        if (prg.toArray().length == 0) return;

        if (Region.checkPlayerRg(b, bd.getBoxRgName())) {
            if (Utils.getHbox(p) != 0) {
                Utils.sMessage(p, "messages.heavybox");
                Utils.setHtime(p);
                return;
            }
            if (p.getGameMode().equals(GameMode.CREATIVE)) {
                Utils.sMessage(p, "messages.gamemode");
                Utils.setHtime(p);
                return;
            }
            //Логика взятия блока

            p.getInventory().addItem(bd.getItem());
            Utils.setHbox(p, 1);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 1));

            Utils.sMessage(p, "messages.box");
            p.playSound(p.getLocation(), Sound.BLOCK_NETHER_GOLD_ORE_PLACE, 15, 15);
            Utils.sTitle(p, "messages.boxTitle", "messages.boxSubTitle");
            Utils.setHtime(p);

        } else if (Region.checkPlayerRg(b, bd.getBoxRgSkladName())) {
            if (bd.getBoxDeliveryMethod().equalsIgnoreCase("NEW")) return;
            if (Utils.getHbox(p) != 1) {
                Utils.sMessage(p, "messages.noBox");
                Utils.setHtime(p);
                return;
            }
            //Логика удаления блока когда игрок принес его на склад

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
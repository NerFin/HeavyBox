package me.nerfin.heavyboxes;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Set;

public class Region {

    public static Boolean checkPlayerRg(Block b, String rgId) {
        Set<ProtectedRegion> prg =  Region.getPlayerRg(b);

        for (int i = 0; i < prg.toArray().length; i++) {
            ProtectedRegion rg = (ProtectedRegion) prg.toArray()[i];
            if (rg.getId().equalsIgnoreCase(rgId)) return true;
        }
        return false;
    }

    public static Boolean checkPlayerRg(Player p, String rgId) {
        Set<ProtectedRegion> prg =  Region.getPlayerRg(p);

        for (int i = 0; i < prg.toArray().length; i++) {
            ProtectedRegion rg = (ProtectedRegion) prg.toArray()[i];
            if (rg.getId().equalsIgnoreCase(rgId)) return true;
        }
        return false;
    }

    public static Set<ProtectedRegion> getPlayerRg(Block b) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        World world = BukkitAdapter.adapt(b.getWorld());
        RegionManager regions = container.get(world);
        ApplicableRegionSet region = regions.getApplicableRegions(BlockVector3.at(bGetX(b), bGetY(b), bGetZ(b)));
        return region.getRegions();
    }

    public static Set<ProtectedRegion> getPlayerRg(Player p) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        World world = BukkitAdapter.adapt(p.getWorld());
        RegionManager regions = container.get(world);
        ApplicableRegionSet region = regions.getApplicableRegions(BlockVector3.at(pGetX(p), pGetY(p), pGetZ(p)));
        return region.getRegions();
    }

    private static int pGetX(Player p) {
        return p.getLocation().getBlockX();
    }

    private static int pGetY(Player p) {
        return p.getLocation().getBlockY();
    }

    private static int pGetZ(Player p) {
        return p.getLocation().getBlockZ();
    }

    private static int bGetX(Block b) {
        return b.getLocation().getBlockX();
    }

    private static int bGetY(Block b) {
        return b.getLocation().getBlockY();
    }

    private static int bGetZ(Block b) {
        return b.getLocation().getBlockZ();
    }

}

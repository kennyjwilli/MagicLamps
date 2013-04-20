package net.vectorgaming.magiclamps.listeners;

import net.vectorgaming.magiclamps.MagicLampsAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 *
 * @author Kenny
 */
public class BlockPlaceListener implements Listener
{
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        if(!player.hasPermission("magiclamps.place")) return;
        
        if(event.getBlock().getType() != Material.REDSTONE_LAMP_ON) return;
        
        MagicLampsAPI.createLamp(event.getBlock().getLocation());
    }
    
}

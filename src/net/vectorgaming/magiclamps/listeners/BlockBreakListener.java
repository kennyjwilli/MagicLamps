package net.vectorgaming.magiclamps.listeners;

import net.vectorgaming.magiclamps.MagicLampsAPI;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 *
 * @author Kenny
 */
public class BlockBreakListener implements Listener
{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        if(event.getBlock().getType() == Material.REDSTONE_LAMP_ON)
        {
            if(MagicLampsAPI.lampExists(event.getBlock().getLocation()))
            {
                MagicLampsAPI.deleteLamp(event.getBlock().getLocation());
            }
        }
    }
}

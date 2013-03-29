package net.vectorgaming.magiclamps.listeners;

import net.vectorgaming.magiclamps.MagicLampsAPI;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

/**
 *
 * @author Kenny
 */
public class RedstoneListener implements Listener
{
    @EventHandler
    public void onRedstone(BlockRedstoneEvent event)
    {
        if(event.getBlock().getType() == Material.REDSTONE_LAMP_ON)
        {
            if(MagicLampsAPI.lampExists(event.getBlock().getLocation()))
            {
                event.setNewCurrent(100);
            }
        }
    }
}

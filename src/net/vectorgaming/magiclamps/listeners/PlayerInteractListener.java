package net.vectorgaming.magiclamps.listeners;

import net.vectorgaming.magiclamps.MagicLampsAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author Kenny
 */
public class PlayerInteractListener implements Listener
{
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if(event.getClickedBlock().getType() == Material.REDSTONE_LAMP_ON || event.getClickedBlock().getType() == Material.REDSTONE_LAMP_OFF)
            {
                Block block = event.getClickedBlock();
                int x = block.getLocation().getBlockX();
                int y = block.getLocation().getBlockY();
                int z = block.getLocation().getBlockZ();
                String pos = x+" "+y+" "+z;
                
                if(MagicLampsAPI.getSLState(player.getName()))
                {
                    MagicLampsAPI.createLamp(block.getLocation());
                    block.setType(Material.REDSTONE_LAMP_ON);
                    MagicLampsAPI.setSLCreation(player.getName(), false);
                    player.sendMessage(ChatColor.AQUA+"Created lamp successfully!");
                    return;
                }
                
                if(MagicLampsAPI.getMLState(player.getName()))
                {
                    MagicLampsAPI.createLamp(block.getLocation());
                    block.setType(Material.REDSTONE_LAMP_ON);
                    player.sendMessage(ChatColor.GOLD+"Created lamp successfully!");
                    return;
                }
            }
        }
    }
}

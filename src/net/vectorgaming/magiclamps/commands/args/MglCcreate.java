package net.vectorgaming.magiclamps.commands.args;

import net.vectorgaming.magiclamps.MagicLampsAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class MglCcreate 
{
    public static void execute(CommandSender sender, String lbl, String[] args)
    {
        if(!sender.hasPermission("magiclamps.ccreate"))
        {
            sender.sendMessage(ChatColor.RED+"You don't have permission.");
            return;
        }
        if(args.length != 1)
        {
            sender.sendMessage(ChatColor.RED+"Usage: /mgl ccreate");
            return;
        }
        
        if(args.length == 1)
        {
            MagicLampsAPI.setMLCreation(sender.getName(), true);
            sender.sendMessage(ChatColor.GREEN+"Continuous lamp creation on. "+ChatColor.YELLOW+"Type /mgl"+ChatColor.GREEN+" off to turn off continuous mode.");
        }
    }
}

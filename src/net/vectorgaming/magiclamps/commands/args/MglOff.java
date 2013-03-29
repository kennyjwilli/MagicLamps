package net.vectorgaming.magiclamps.commands.args;

import net.vectorgaming.magiclamps.MagicLampsAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class MglOff 
{
    public static void execute(CommandSender sender, String lbl, String[] args)
    {
        if(!sender.hasPermission("magiclamps.off"))
        {
            sender.sendMessage(ChatColor.RED+"You don't have permission.");
            return;
        }
        
        if(args.length != 1)
        {
            sender.sendMessage(ChatColor.RED+"Usage: /mgl off");
            return;
        }
        
        if(args.length == 1)
        {
            MagicLampsAPI.setMLCreation(sender.getName(), false);
            sender.sendMessage(ChatColor.DARK_RED+"Continuous lamp creation off.");
        }
    }
}

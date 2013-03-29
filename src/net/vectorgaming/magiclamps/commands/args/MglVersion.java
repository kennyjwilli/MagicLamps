package net.vectorgaming.magiclamps.commands.args;

import net.vectorgaming.magiclamps.MagicLampsAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class MglVersion 
{
    public static void execute(CommandSender sender, String lbl, String[] args)
    {
        if(!sender.hasPermission("magiclamps.version"))
        {
            sender.sendMessage(ChatColor.RED+"You don't have permission.");
            return;
        }
        
        if(args.length == 1)
        {
            sender.sendMessage(ChatColor.ITALIC+"MagicLamps version "+MagicLampsAPI.getVersion());
        }
    }
}

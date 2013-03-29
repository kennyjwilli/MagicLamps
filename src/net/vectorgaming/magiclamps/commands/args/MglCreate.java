package net.vectorgaming.magiclamps.commands.args;

import net.vectorgaming.magiclamps.MagicLampsAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class MglCreate 
{
    public static void execute(CommandSender sender, String lbl, String[] args)
    {
        if(!sender.hasPermission("magiclamps.create"))
        {
            sender.sendMessage(ChatColor.RED+"You don't have permission.");
            return;
        }
        
        if(args.length != 1)
        {
            sender.sendMessage(ChatColor.RED+"Usage: /mgl create");
            return;
        }
        
        if(args.length == 1)
        {
            MagicLampsAPI.setSLCreation(sender.getName(), true);
            sender.sendMessage(ChatColor.GREEN+"Right click the lamp you always want to be on!");
        }
    }
}

package net.vectorgaming.magiclamps.commands.args;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class MglHelp 
{
    public static void execute(CommandSender sender, String lbl, String[] args)
    {
        if(!sender.hasPermission("magiclamps.help"))
        {
            sender.sendMessage(ChatColor.RED+"You don't have permission.");
            return;
        }
        sender.sendMessage(ChatColor.GREEN+"==========MagicLamps Help==========");
        sender.sendMessage(ChatColor.GOLD+"/mgl version"+ChatColor.BLUE+" Reloads the config file");
        sender.sendMessage(ChatColor.GOLD+"/mgl update"+ChatColor.BLUE+" Updates the plugin");
        sender.sendMessage(ChatColor.GOLD+"/mgl create"+ChatColor.BLUE+" Creates a lit redstone lamp");
        sender.sendMessage(ChatColor.GOLD+"/mgl ccreate"+ChatColor.BLUE+" Enables continuous lamp creation");
        sender.sendMessage(ChatColor.GOLD+"/mgl off"+ChatColor.BLUE+" Disables continuous lamp creation");
        sender.sendMessage(ChatColor.GOLD+"/mgl fix"+ChatColor.BLUE+" Updates the lamps.yml format to the v0.3 format");
    }
}

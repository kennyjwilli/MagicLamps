package net.vectorgaming.magiclamps.commands;

import net.vectorgaming.magiclamps.commands.args.MglCcreate;
import net.vectorgaming.magiclamps.commands.args.MglCreate;
import net.vectorgaming.magiclamps.commands.args.MglDynamic;
import net.vectorgaming.magiclamps.commands.args.MglFix;
import net.vectorgaming.magiclamps.commands.args.MglHelp;
import net.vectorgaming.magiclamps.commands.args.MglOff;
import net.vectorgaming.magiclamps.commands.args.MglVersion;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class MglCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args)
    {
        if(lbl.equalsIgnoreCase("mgl"))
        {
            if(args.length == 0)
            {
                sender.sendMessage(ChatColor.AQUA+"============MagicLamps============");
                sender.sendMessage("Type "+ChatColor.YELLOW+"/mgl help"+ChatColor.WHITE+" for a list of commands.");
                return true;
            }
            if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("create"))
                {
                    MglCreate.execute(sender, lbl, args);
                }else if(args[0].equalsIgnoreCase("ccreate")){
                    MglCcreate.execute(sender, lbl, args);
                }else if(args[0].equalsIgnoreCase("off")){
                    MglOff.execute(sender, lbl, args);
                }else if(args[0].equalsIgnoreCase("dynamic")){
                    MglDynamic.execute(sender, lbl, args);
                }else if(args[0].equalsIgnoreCase("update"))
                {
//                    if(!player.hasPermission("magiclamps.update"))
//                    {
//                        player.sendMessage(ChatColor.RED+"You don't have permission.");
//                        return true;
//                    }
//                    net.h31ix.updater.Updater updater = new net.h31ix.updater.Updater(this, "magiclamps", this.getFile(), net.h31ix.updater.Updater.UpdateType.NO_VERSION_CHECK, true);
//                    return true;
                }else if(args[0].equalsIgnoreCase("version"))
                {
                    MglVersion.execute(sender, lbl, args);
                }else if(args[0].equalsIgnoreCase("help"))
                {
                    MglHelp.execute(sender, lbl, args);
                    return true;
                }else if(args[0].equalsIgnoreCase("fix"))
                {
                    MglFix.execute(sender, lbl, args);
                    return true;
                }else
                {
                    sender.sendMessage(ChatColor.RED+"Error: Unrecognized argument. Type "+ChatColor.YELLOW+"/mgl help"+ChatColor.RED+" for a list of commands.");
                }
            }
            
            if(args.length > 1)
            {
                sender.sendMessage(ChatColor.RED+"Error: Too many arguments");
                return true;
            }
        }
        return false;
    }
}

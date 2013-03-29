package net.vectorgaming.magiclamps.commands.args;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.vectorgaming.magiclamps.MagicLampsAPI;
import net.vectorgaming.vchat.util.VConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.FileUtil;

/**
 *
 * @author Kenny
 */
public class MglFix 
{
    public static void execute(CommandSender sender, String lbl, String[] args)
    {
        if(!sender.hasPermission("magiclamps.fix"))
        {
            sender.sendMessage(ChatColor.RED+"You don't have permission.");
            return;
        }
        File lampsOld = new File(MagicLampsAPI.getPlugin().getDataFolder().getAbsolutePath()+File.separator+"lamps.yml");
        File lampsNew = new File(MagicLampsAPI.getPlugin().getDataFolder().getAbsolutePath()+File.separator+"lamps-old.yml");
        FileUtil.copy(lampsOld, lampsNew);
        lampsOld.delete();
        
        VConfig lamps = new VConfig(MagicLampsAPI.getPlugin().getDataFolder().getAbsolutePath(), "lamps-old.yml", MagicLampsAPI.getPlugin());
        VConfig lampsNewConfig = new VConfig(MagicLampsAPI.getPlugin().getDataFolder().getAbsolutePath(), "lamps.yml", MagicLampsAPI.getPlugin());
        
        boolean check = false;
        for(String s : lamps.getConfig().getKeys(false))
        {
            if(lamps.getConfig().contains(s+".world"))
            {
                check = true;
            }
        }
        
        if(!check)
        {
            sender.sendMessage(ChatColor.RED+"It looks as though your lamps.yml is already formatted properly. This command is for fixing the lamp format only.");
            return;
        }
        
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        ArrayList<String> worlds = new ArrayList<>();
        
        
        for(String s : lamps.getConfig().getKeys(false))
        {
            if(!worlds.contains(lamps.getConfig().getString(s+".world")))
                worlds.add(lamps.getConfig().getString(s+".world"));
        }
        
        int i = 0;
        
        for(String s : lamps.getConfig().getKeys(false))
        {
            for(String str : worlds)
            {
                if(lamps.getConfig().getString(s+".world").equalsIgnoreCase(str))
                {
                    if(!map.containsKey(str))
                    {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(s);
                        map.put(str, list);
                    }else
                    {
                        ArrayList<String> list = map.get(str);
                        list.add(s);
                        map.put(str, list);
                    }
                }
            }
            i++;
        }
        
        for(String s : map.keySet())
        {
            List<String> list = new ArrayList<>();
            for(String str : map.get(s))
            {
                list.add(str);
            }
            lampsNewConfig.getConfig().set(s, list);
        }
        lampsNewConfig.saveConfig();
        
        sender.sendMessage(ChatColor.GREEN+"Success! A total of "+i+" lamps have been converted to the new format.");
    }
}

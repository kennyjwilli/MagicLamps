package net.vectorgaming.magiclamps.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import net.vectorgaming.magiclamps.MagicLamps;
import net.vectorgaming.magiclamps.MagicLampsAPI;
import net.vectorgaming.vchat.util.VConfig;
import org.bukkit.Bukkit;

/**
 *
 * @author Kenny
 */
public class SLAPI 
{
    private static MagicLamps plugin;
    
    public SLAPI(MagicLamps instance)
    {
        plugin = instance;
    }
    
    /**
     * Saves all of the data created on the server to the proper YAML file.
     */
    public static void saveAll()
    {
        if(MagicLamps.configChecker)
        {
            Bukkit.getLogger().log(Level.INFO, "");
            return;
        }
        VConfig lamps = new VConfig(plugin.getDataFolder().getAbsolutePath(), "lamps.yml", plugin);
        HashMap<String,ArrayList<String>> map = MagicLampsAPI.getLampsMap();
        
        for(String s : map.keySet())
        {
            List<String> list = new ArrayList<>();
            for(String str : map.get(s))
            {
                list.add(str);
            }
            lamps.getConfig().set(s, list);
        }
        lamps.saveConfig();
    }
    
    /**
     * Loads all data from the MagicLamps data folder
     */
    public static void loadAll()
    {
        VConfig lamps = new VConfig(plugin.getDataFolder().getAbsolutePath(), "lamps.yml", plugin);
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        
        for(String s : lamps.getConfig().getKeys(false))
        {
            ArrayList<String> list = new ArrayList<>();
            for(String str : lamps.getConfig().getStringList(s))
            {
                list.add(str);
            }
            map.put(s, list);
        }
        MagicLampsAPI.setLampsMap(map);
    }
}

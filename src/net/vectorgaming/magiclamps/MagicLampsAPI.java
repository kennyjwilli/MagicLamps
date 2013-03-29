package net.vectorgaming.magiclamps;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Location;

/**
 *
 * @author Kenny
 */
public class MagicLampsAPI 
{
    private static MagicLamps plugin;
    
    private static HashMap<String,Boolean> singleLampCheck = new HashMap<>(); // {PlayerName, State}
    private static HashMap<String,Boolean> multiLampCheck = new HashMap<>(); // {PlayerName, State}
    private static HashMap<String, ArrayList<String>> lamps = new HashMap<>(); // {WorldName, Lamps}
    public MagicLampsAPI(MagicLamps instance)
    {
        plugin = instance;
    }
    
    public static void setSLCreation(String pname, boolean state)
    {
        singleLampCheck.put(pname, state);
    }
    
    public static boolean getSLState(String pname)
    {
        if(!singleLampCheck.containsKey(pname))
            return false;
        return singleLampCheck.get(pname);
    }
    
    public static void setMLCreation(String pname, boolean state)
    {
        multiLampCheck.put(pname, state);
    }
    
    public static boolean getMLState(String pname)
    {
        if(!multiLampCheck.containsKey(pname))
            return false;
        return multiLampCheck.get(pname);
    }
    
    public static void createLamp(Location location)
    {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        if(lamps.containsKey(location.getWorld().getName()))
        {
            ArrayList<String> locations = lamps.get(location.getWorld().getName());
            if(locations.contains(x+" "+y+" "+z))
                return;
            locations.add(x+" "+y+" "+z);
            lamps.put(location.getWorld().getName(), locations);
        }else
        {
            ArrayList<String> locations = new ArrayList<>();
            locations.add(x+" "+y+" "+z);
            lamps.put(location.getWorld().getName(), locations);
        }
    }
    
    public static void createLamp(String world, int x, int y, int z)
    {
        if(lamps.containsKey(world))
        {
            ArrayList<String> locations = lamps.get(world);
            if(locations.contains(x+" "+y+" "+z))
                return;
            locations.add(x+" "+y+" "+z);
            lamps.put(world, locations);
        }else
        {
            ArrayList<String> locations = new ArrayList<>();
            locations.add(x+" "+y+" "+z);
            lamps.put(world, locations);
        }
    }
    
    public static void deleteLamp(Location location)
    {
        if(!lamps.containsKey(location.getWorld().getName()))
            return;
        ArrayList<String> loc = lamps.get(location.getWorld().getName());
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        if(!loc.contains(x+" "+y+" "+z))
            return;
        loc.remove(x+" "+y+" "+z);
        lamps.put(location.getWorld().getName(), loc);
    }
    
    public static void deleteLamp(String world, int x, int y, int z)
    {
        if(!lamps.containsKey(world))
            return;
        ArrayList<String> loc = lamps.get(world);
        if(!loc.contains(x+" "+y+" "+z))
            return;
        loc.remove(x+" "+y+" "+z);
        lamps.put(world, loc);
    }
    
    public static boolean lampExists(Location location)
    {
        if(!lamps.containsKey(location.getWorld().getName()))
            return false;
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        for(String s : lamps.get(location.getWorld().getName()))
        {
            if(s.equalsIgnoreCase(x+" "+y+" "+z))
                return true;
        }
        return false;
    }
    
    public static boolean lampExists(String world, int  x, int y, int z)
    {
        if(!lamps.containsKey(world))
            return false;
        for(String s : lamps.get(world))
        {
            if(s.equalsIgnoreCase(x+" "+y+" "+z))
                return true;
        }
        return false;
    }
    
    public static String getVersion()
    {
        return plugin.getDescription().getVersion();
    }
    
    public static HashMap<String,ArrayList<String>> getLampsMap()
    {
        return lamps;
    }
    
    public static void setLampsMap(HashMap<String,ArrayList<String>> map)
    {
        lamps = map;
    }
    
    public static MagicLamps getPlugin()
    {
        return plugin;
    }
    
}

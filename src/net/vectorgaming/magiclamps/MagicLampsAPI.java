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
    
    /**
     * Toggles if a player can create a single lamp when the right click next
     * @param pname The name of the player
     * @param state True to enable; false to disable
     */
    public static void setSLCreation(String pname, boolean state)
    {
        singleLampCheck.put(pname, state);
    }
    
    /**
     * 
     * @param pname Player name
     * @return True if a player can create a lamp; false if they cannot
     */
    public static boolean getSLState(String pname)
    {
        if(!singleLampCheck.containsKey(pname))
            return false;
        return singleLampCheck.get(pname);
    }
    
    /**
     * Toggles if a player is in Continuous Lamp Creation mode
     * @param pname Player name
     * @param state True to enable; false to disable
     */
    public static void setMLCreation(String pname, boolean state)
    {
        multiLampCheck.put(pname, state);
    }
    
    /**
     *
     * @param pname Player name
     * @return True if player is in Continuous Lamp Creation mode; false if not
     */
    public static boolean getMLState(String pname)
    {
        if(!multiLampCheck.containsKey(pname))
            return false;
        return multiLampCheck.get(pname);
    }
    
    /**
     * Creates a wireless redstone lamp
     * @param location The location of the lamp
     */
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
    
    /**
     * Creates a wireless redstone lamp
     * @param world The world the lamp is in
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param z The Z coordinate
     */
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
    
    /**
     * Removes lamp from being continually enabled
     * @param location The location of the lamp
     */
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
    
    /**
     * Removes lamp from being continually enabled
     * @param world The world the lamp is in
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param z The Z coordinate
     */
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
    
    /**
     * Checks to see if a lamp exists at the location provided
     * @param location The location of the lamp
     * @return True if a lamp does exist; false if not
     */
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
    
    /**
     * Checks to see if a lamp exists at the location provided
     * @param world The world the lamp is in
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param z The Z coordinate
     * @return True if a lamp does exist; false if not
     */
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
    
    /**
     * 
     * @return The version of the plugin
     */
    public static String getVersion()
    {
        return plugin.getDescription().getVersion();
    }
    
    /**
     * This method should be used only in extreme circumstances. If you do not know what
     * you are doing you could lose all of your saved lamps.
     * @return The HashMap containing all of the lamps
     */
    public static HashMap<String,ArrayList<String>> getLampsMap()
    {
        return lamps;
    }
    
    /**
     * This method should be used only in extreme circumstances. If you do not know what
     * you are doing you could lose all of your saved lamps.
     * @param map A HashMap containing all of the lamps
     */
    public static void setLampsMap(HashMap<String,ArrayList<String>> map)
    {
        lamps = map;
    }
    
    /**
     * 
     * @return MagicLamps plugin
     */
    public static MagicLamps getPlugin()
    {
        return plugin;
    }
    
}

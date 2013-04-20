package net.vectorgaming.magiclamps;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import net.vectorgaming.magiclamps.commands.MglCommand;
import net.vectorgaming.magiclamps.listeners.*;
import net.vectorgaming.magiclamps.util.SLAPI;
import net.vectorgaming.vchat.util.VConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.FileUtil;

/**
 *
 * @author kennywilliams
 */
public class MagicLamps extends JavaPlugin implements Listener{
    ArrayList<String> a = new ArrayList();
    public static boolean update = false;
    public static String name = "";
    public static long size = 0;
    public static boolean configChecker = false;
    
    private MagicLampsAPI api;
    private SLAPI slapi;
    private PlayerInteractListener piLstn;
    private RedstoneListener rLstn;
    private BlockBreakListener bbLstn;
    private BlockPlaceListener bpLstn;
    private MglCommand mglCommand;

    @Override
    public void onEnable()
    {
        api = new MagicLampsAPI(this);
        slapi = new SLAPI(this);
        piLstn = new PlayerInteractListener();
        rLstn = new RedstoneListener();
        bbLstn = new BlockBreakListener();
        bpLstn = new BlockPlaceListener();
        mglCommand = new MglCommand();
        
        //Loads all lamps
        SLAPI.loadAll();
        
        //Creates config.yml
        createConfig();
        
        //Check for out of date config file
        configVersionCheck();
        
        registerEvents();
        
        //Check for mcstats.org reporting
        mcstats();
        
        //Check for auto-update feature
        updateCheck();
        
        if(checkConfigUpdate())
        {
            Bukkit.getLogger().log(Level.SEVERE, "---------------------------------------------------------------");
            Bukkit.getLogger().log(Level.SEVERE, "===========================MagicLamps===========================");
            Bukkit.getLogger().log(Level.SEVERE, "---------------------------------------------------------------");
            Bukkit.getLogger().log(Level.SEVERE, "Your current lamps.yml file is setup according to the old format. Run /mgl fix in-game to update the format.");
            Bukkit.getLogger().log(Level.SEVERE, "MagicLamps will not function properly until this command is ran.");
            Bukkit.getLogger().log(Level.SEVERE, "---------------------------------------------------------------");
            Bukkit.getLogger().log(Level.SEVERE, "===========================MagicLamps===========================");
            Bukkit.getLogger().log(Level.SEVERE, "---------------------------------------------------------------");
            configChecker = true;
        }
        
//        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                for(String s : config.getKeys(false))
//                {
//                    if(config.contains(s+".dynamic"))
//                    {
//                        World world = Bukkit.getWorld(config.getString(s+".world"));
//                        if(world.getTime() >= 0 && )
//                    }
//                }
//            }
//        }, 1200L);
    }
    
    @Override
    public void onDisable(){SLAPI.saveAll();}
    
    private void registerEvents()
    {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this.bbLstn, this);
        pm.registerEvents(this.rLstn, this);
        pm.registerEvents(this.piLstn, this);
        pm.registerEvents(this.bpLstn, this);
        pm.registerEvents(this, this);
        
        this.getCommand("mgl").setExecutor(this.mglCommand);
    }
    
    private void configVersionCheck()
    {
        if(!this.getConfig().getString("config-version").equalsIgnoreCase("0.3"))
        {
            Bukkit.getLogger().log(Level.SEVERE, "---------------------------------------------------------------");
            Bukkit.getLogger().log(Level.SEVERE, "===========================MagicLamps===========================");
            Bukkit.getLogger().log(Level.SEVERE, "---------------------------------------------------------------");
            Bukkit.getLogger().log(Level.SEVERE, "Incorrect config version. This check is in place to ensure the plugin will not break due to an out-of-date config.");
            Bukkit.getLogger().log(Level.SEVERE, "Creating a new config for you ...");
            File oldConfig = new File(this.getDataFolder().getAbsolutePath()+File.separator+"config.yml");
            File newConfig = new File(this.getDataFolder().getAbsolutePath()+File.separator+"config-old.yml");
            FileUtil.copy(oldConfig, newConfig);
            oldConfig.delete();
            this.saveDefaultConfig();
            Bukkit.getLogger().log(Level.SEVERE, "Success! Your old config has been saved to "+this.getDataFolder().getAbsolutePath()+File.separator+"config-old.yml");
            Bukkit.getLogger().log(Level.SEVERE, "---------------------------------------------------------------");
            Bukkit.getLogger().log(Level.SEVERE, "===========================MagicLamps===========================");
            Bukkit.getLogger().log(Level.SEVERE, "---------------------------------------------------------------");
        }
    }
    
    private void mcstats()
    {
        if(this.getConfig().getBoolean("mcstats")){
            try {
                org.mcstats.Metrics metrics = new org.mcstats.Metrics(this);
                metrics.start();
                Bukkit.getLogger().log(Level.INFO,"[MagicLamps] Info submitted to mcstats.org");
            } catch (IOException e) {
                Bukkit.getLogger().log(Level.WARNING,"MagicLamps wonders why you disabled this :(");
            }
        }
    }
    
    private void updateCheck()
    {      
        if(this.getConfig().getBoolean("auto-update"))
        {
            net.h31ix.updater.Updater updater = new net.h31ix.updater.Updater(this, "magiclamps", this.getFile(), net.h31ix.updater.Updater.UpdateType.NO_DOWNLOAD, false);
            if(updater.getResult() == net.h31ix.updater.Updater.UpdateResult.UPDATE_AVAILABLE){
                name = updater.getLatestVersionString(); 
                size = updater.getFileSize();
                a.add(ChatColor.YELLOW+"An update is available: " + MagicLamps.name + "(" + MagicLamps.size + " bytes)");
                //a.add(ChatColor.YELLOW+"Type "+ChatColor.WHITE+"/mgl update"+ChatColor.YELLOW+" to update.");
            }
        }
    }
    
    public void createConfig()
    {
        //Creates the config file ..
        File file = new File(getDataFolder()+File.separator+"config.yml"); 
        if(!file.exists()){
            Bukkit.getLogger().log(Level.INFO,"[MagicLamps] Creating default config file ...");
            saveDefaultConfig();
            Bukkit.getLogger().log(Level.INFO,"[MagicLamps] Config created successfully!");
        }
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        //Message informing player about update when they join
        if(!player.hasPermission("magiclamps.update.info")){
            return;
        }
        if(a.size() > 0){
            player.sendMessage(a.get(0));
            //player.sendMessage(a.get(1));
        }
    }
    
    private static boolean checkConfigUpdate()
    {
        VConfig lamps = new VConfig(MagicLampsAPI.getPlugin().getDataFolder().getAbsolutePath(), "lamps.yml", MagicLampsAPI.getPlugin());
        
        for(String s : lamps.getConfig().getKeys(false))
        {
            if(lamps.getConfig().contains(s+".world"))
            {
                return true;
            }
        }
        return false;
    }
}
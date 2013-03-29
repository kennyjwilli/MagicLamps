package net.vectorgaming.vchat.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Kenny
 */
public class VConfig 
{
    private final String fileName;
    private final String path;
    private final Plugin plugin;
    
    private File configFile;
    private FileConfiguration fileConfig;
    
    public VConfig(String path, String fileName, Plugin plugin)
    {
        if(plugin == null)
        {
            throw new IllegalArgumentException("Plugin cannot be null!");
        }
        
        
        this.path = path;
        this.fileName = fileName;
        this.plugin = plugin;
    }
    
    public void reloadConfig()
    {
        if(configFile == null)
        {
            configFile = new File(path, fileName);
        }
        fileConfig = YamlConfiguration.loadConfiguration(configFile);
        
        InputStream defConfigStream = plugin.getResource(fileName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            fileConfig.setDefaults(defConfig);
        }
    }
    
    public FileConfiguration getConfig()
    {
        if(fileConfig == null)
        {
            this.reloadConfig();
        }
        return fileConfig;
    }
    
    public void saveConfig()
    {
        if(fileConfig == null || configFile == null)
        {
            return;
        }else
        {
            try
            {
                this.getConfig().save(configFile);
            }catch(IOException ex)
            {
                plugin.getLogger().log(Level.SEVERE, "Ecountered error while saving config to "+configFile, ex);
            }
        }
    }
    
    public void saveDefaultConfig()
    {
        if(configFile == null)
        {
            this.plugin.saveResource(fileName, false);
        }
    }
}

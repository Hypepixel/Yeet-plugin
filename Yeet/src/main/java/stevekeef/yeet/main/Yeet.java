package stevekeef.yeet.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Yeet extends JavaPlugin
{
	private File customConfigFile;
	private FileConfiguration customConfig;
	
	// Fired when plugin is first enabled
    @Override
    public void onEnable()
    {
    	createCustomConfig();
    	
    	// Register our command "yeet" (set an instance of your command class as executor)
        this.getCommand("yeet").setExecutor(new CommandKit());
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable()
    {
    	
    }
    
    public FileConfiguration getCustomConfig()
    {
        return this.customConfig;
    }
    
    public File getCustomConfigFile()
    {
    	return this.customConfigFile;
    }
    
    private void createCustomConfig()
    {
        customConfigFile = new File(getDataFolder(), "yeet.yml");
        if (!customConfigFile.exists())
        {
            customConfigFile.getParentFile().mkdirs();
            saveResource("yeet.yml", false);
         }

        customConfig= new YamlConfiguration();
        
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}

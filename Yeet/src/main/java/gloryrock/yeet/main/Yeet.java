package gloryrock.yeet.main;

import org.bukkit.plugin.java.JavaPlugin;

public class Yeet extends JavaPlugin
{
	// Fired when plugin is first enabled
    @Override
    public void onEnable()
    {
    	loadConfig();
    	
    	// Register our command "yeet" (set an instance of your command class as executor)
        this.getCommand("yeet").setExecutor(new CommandKit());
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable()
    {
    	
    }
    
    private void loadConfig()
    {
    	getServer().getConsoleSender().sendMessage("[Yeet] Loading config");
    	
    	getConfig().options().copyDefaults(true);
    	saveConfig();
    	
    	getServer().getConsoleSender().sendMessage("[Yeet] Loaded config");
    }
}

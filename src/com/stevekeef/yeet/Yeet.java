package com.stevekeef.yeet;
import org.bukkit.plugin.java.JavaPlugin;

public class Yeet extends JavaPlugin
{
	// Fired when plugin is first enabled
    @Override
    public void onEnable()
    {
    	// Register our command "yeet" (set an instance of your command class as executor)
        this.getCommand("yeet").setExecutor(new CommandExec());
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable()
    {
    	
    }
}

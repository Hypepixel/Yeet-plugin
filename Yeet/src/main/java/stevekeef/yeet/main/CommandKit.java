package stevekeef.yeet.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class CommandKit implements CommandExecutor
{
	private File customConfigFile;
	private FileConfiguration config;
	
	// This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	if (sender instanceof Player)
    	{
    		customConfigFile = Yeet.getPlugin(Yeet.class).getCustomConfigFile();
    		config = Yeet.getPlugin(Yeet.class).getCustomConfig();
            Player player = (Player) sender;
            
            if (player.getInventory().contains(Material.DIAMOND) == false)
            {
            	if (config.contains(player.getUniqueId().toString()) == false)
            	{
	            // Create a new ItemStack (type: diamond)
	            ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
	
	            // Give the player our items (comma-seperated list of all ItemStack)
	            player.getInventory().addItem(diamond);
	            
	            // Add message
	            player.sendMessage("Yeet!");
	            
	            // Player sound-effect
	            player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
	            
	            config.set("Data.player", player.getUniqueId().toString());
	            
	            try {
					config.save(customConfigFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	}
            	else
            	{
            		Bukkit.getConsoleSender().sendMessage("You've already yeeted!");
            	}
            }
        }
    	else
    	{
    		// Send error handling to the entity
    		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You need to be a player to yeet");
    		
    		return false;
    	}
    	
    	// If the player (or console) uses our command correct, we can return true
        return true;
    }
}

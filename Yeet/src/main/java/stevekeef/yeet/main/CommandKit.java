package stevekeef.yeet.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class CommandKit implements CommandExecutor
{
	private Plugin plugin = Yeet.getPlugin(Yeet.class);
	
	private int count = 0;
	
	// This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	if (sender instanceof Player)
    	{
            Player player = (Player) sender;
            
            Calendar calendarNow = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            if (plugin.getConfig().getString("users." + player.getUniqueId() + ".date") != null)
            {
            	Calendar calendarLoaded = Calendar.getInstance();
            	
            	try
            	{
					calendarLoaded.setTime(dateFormat.parse(plugin.getConfig().getString("users." + player.getUniqueId() + ".date")));
				}
            	catch (ParseException e)
            	{
					e.printStackTrace();
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + e.toString());
				}
            	
				int deltaHours = calendarNow.get(Calendar.HOUR) - calendarLoaded.get(Calendar.HOUR);
            		
            	if (plugin.getConfig().getString("users." + player.getUniqueId() + ".count") != null)
            	{
            		if (deltaHours >= 1)
            		{
            			// Upgrade the yeet
	            		count = plugin.getConfig().getInt("users." + player.getUniqueId() + ".count");
	            		itemEvent(player, count);
	            		count++;
	            		
	            		saveUser(player, dateFormat.format(calendarNow.getTime()), count);
            		}
            		else
            		{
            			// On cooldown
            			player.sendMessage("Cooldown! Wait an hour to flex more!");
            			Bukkit.getConsoleSender().sendMessage(Integer.toString(deltaHours));
            		}
            	}
            	else
            	{
            		// Yeet for the first time today
            		itemEvent(player, count);
            		count = 1;
            		
            		saveUser(player, dateFormat.format(calendarNow.getTime()), count);
            	}
            }
            else
            {
            	// Yeet for the first time
        		itemEvent(player, count);
	            count = 1;
	            
	            saveUser(player, dateFormat.format(calendarNow.getTime()), count);
            }
        }
    	else
    	{
    		// Send error handling to the entity
    		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You need to be a player to yeet");
    	}
    	
    	// If the player (or console) uses our command correct, we can return true
        return true;
    }
    
    private void itemEvent(Player player, int itemCount)
    {
        switch(itemCount)
        {
        	case 0:
        		ItemStack iron = new ItemStack(Material.IRON_INGOT, 1);
        		player.getInventory().addItem(iron);
        		player.sendMessage("Yeet!");
        		player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
        		Bukkit.broadcastMessage(player.getDisplayName() + " just flexed!");
        	break;
        	case 1:
        		ItemStack gold = new ItemStack(Material.GOLD_INGOT, 1);
			   player.getInventory().addItem(gold);
			   player.sendMessage("Yeet!");
			   player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
			   Bukkit.broadcastMessage(player.getDisplayName() + " just flexed!");
		      break;
        	case 2:
        		ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
			   player.getInventory().addItem(diamond);
			   player.sendMessage("Yeet!");
			   player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
			   Bukkit.broadcastMessage(player.getDisplayName() + " just flexed!");
		      break;
        	default :
			   player.sendMessage("You are already flexing!");
			   break;
        }
    }
    
    private void saveUser(Player player, CharSequence date, int itemCount)
    {
    	// Set and save user
        plugin.getConfig().set("users." + player.getUniqueId() + ".date", date);
        plugin.getConfig().set("users." + player.getUniqueId() + ".count", itemCount);
        plugin.saveConfig();
    }
}

package stevekeef.yeet.main;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Command;

public class CommandKit implements CommandExecutor
{
	// This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	if (sender instanceof Player)
    	{
            Player player = (Player) sender;
            
            if (player.getInventory().contains(Material.DIAMOND) == false)
            {
	            // Create a new ItemStack (type: diamond)
	            ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
	
	            // Give the player our items (comma-seperated list of all ItemStack)
	            player.getInventory().addItem(diamond);
            }
        }
    	else
    	{
    		return false;
    	}
    	
    	// If the player (or console) uses our command correct, we can return true
        return true;
    }
}

package me.Sanhak.Convertor.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.Sanhak.Convertor.Configurations.Configuration;
import me.Sanhak.Convertor.Methods.Methods;
import me.Sanhak.Convertor.Methods.PayMethod;

public class Listeners implements Listener 
{
	

	

	 		@EventHandler(priority = EventPriority.HIGH)
		   public void CheckOfTitle(final BlockPlaceEvent e)
	 		{
		      Block b = e.getBlock();
		      e.setCancelled(false);
		      if (e.getBlock().getType() == Material.LAPIS_BLOCK) 
		      {
		    	  Location Blocloc = b.getLocation();
		    	  Methods.Title_Lapis(new Location(Blocloc.getWorld(), Blocloc.getBlockX() + 0.5D, Blocloc.getBlockY() - 0.5D, Blocloc.getBlockZ() + 0.5D));
		      }
		      if (e.getBlock().getType() == Material.GOLD_BLOCK) 
		      {
		    	  Location Blocloc = b.getLocation();
		    	  Methods.Title_Gold(new Location(Blocloc.getWorld(), Blocloc.getBlockX() + 0.5D, Blocloc.getBlockY() - 0.5D, Blocloc.getBlockZ() + 0.5D));
		      }
		      
	 		}
	
	
	//
	 		 @EventHandler
	 		   public void onLapisLevel(PlayerInteractEvent e) {
	 		      Action action = e.getAction();
	 		      Player player = e.getPlayer();
	 		      if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_BLOCK)) {
	 		         Block b = e.getClickedBlock();
	 		         if (b.getType() == Material.valueOf(Configuration.getConfig().getString("Exp-Block.ExChange-Block"))) {
	 		            if (PayMethod.pay(player, Material.valueOf(Configuration.getConfig().getString("Exp-Block.ExChange-Material")), Configuration.getConfig().getInt("Exp-Block.ExChange-Amount")))
	 		            {
	 		               player.playSound(player.getLocation(), Sound.LEVEL_UP, 5.0F, 1.0F);
	 		               int level = player.getLevel();
	 		 	          	player.setLevel(level + Configuration.getConfig().getInt("Exp-Block.Level-Get"));	 	        
	 		            
	 		            } else {
	 		               player.sendMessage(Methods.f("&cYou don't have enough redstone to complete the exchange."));
	 		               player.getWorld().playSound(player.getLocation(), Sound.ITEM_BREAK, 5.5F, 5.5F);
	 		            }
	 		         }
	 		      }

	 		   }
	 		 
	 		@EventHandler
	 		   public void onGoldExchange(PlayerInteractEvent e) {
	 		      Action action = e.getAction();
	 		      Player player = e.getPlayer();
	 		      if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_BLOCK)) {
	 		         Block b = e.getClickedBlock();
	 		         if (b.getType() == Material.GOLD_BLOCK) 
	 		         {
	 		            if (PayMethod.pay(player, Material.REDSTONE, 64)) {
	 		               player.playSound(player.getLocation(), Sound.LEVEL_UP, 5.0F, 1.0F);
	 		               player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.GOLD_INGOT, 1)});
	 		            
	 		            } else {
	 		               player.sendMessage(Methods.f("&cYou don't have enough redstone to complete the exchange."));
	 		               player.getWorld().playSound(player.getLocation(), Sound.ITEM_BREAK, 5.5F, 5.5F);
	 		            }
	 		         }
	 		      }

	 		   }
	
	 			
		
	
	
	
	
	
	
	
	
	
}

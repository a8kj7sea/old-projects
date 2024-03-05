package MMMMM;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		//Normal set on block without sneaking !!
		Material type = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1, p.getLocation().getZ()).getBlock().getType();
		if (type == Material.GOLD_BLOCK) {
			if (p.isSneaking()) {
			if (p.hasPermission("tpppp.adddmin")) {
				p.teleport(new Location(p.getWorld() , 125,45,-209));
			}
		 }
		}
	}
	
	@EventHandler
	public void colorsign(SignChangeEvent e) {
		e.setLine(0, ChatColor.translateAlternateColorCodes('&', e.getLine(0)));
		e.setLine(1, ChatColor.translateAlternateColorCodes('&', e.getLine(1)));
		e.setLine(2, ChatColor.translateAlternateColorCodes('&', e.getLine(2)));
		e.setLine(3, ChatColor.translateAlternateColorCodes('&', e.getLine(3)));
	}
	
}

package listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import enums.TrailParticlesEnum;
import main.MainClass;
import objects.Trail;

public class PlayerChatListener implements Listener {

	


	@EventHandler
	public void onPlayerChatting(AsyncPlayerChatEvent event) {
		if (event.getMessage().equalsIgnoreCase("!hearts")) {
			MainClass.getMainClass().getTrailsManager().addPlayer(event.getPlayer(), new Trail(TrailParticlesEnum.HEART));
		} else if (event.getMessage().equalsIgnoreCase("!smoke")) {
			MainClass.getMainClass().getTrailsManager().addPlayer(event.getPlayer(), new Trail(TrailParticlesEnum.SMOKE));
		} else if (event.getMessage().equalsIgnoreCase("!clouds")) {
			MainClass.getMainClass().getTrailsManager().addPlayer(event.getPlayer(), new Trail(TrailParticlesEnum.CLOUD));
		}else if (event.getMessage().equalsIgnoreCase("!chat")) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.chat("!hearts");
			}
		}
	}

}

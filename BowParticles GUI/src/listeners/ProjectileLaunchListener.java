package listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import main.MainClass;

public class ProjectileLaunchListener implements Listener {

	

	@EventHandler
	public void onPlayerShootArrow(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() != null && e.getEntity().getShooter() instanceof Player
				&& e.getEntity() instanceof Arrow) {
			Player p = (Player) e.getEntity().getShooter();
			if (MainClass.getMainClass().getTrailsManager().hasTrail(p)) {
				MainClass.getMainClass().getTrailsManager().addArrow((Arrow) e.getEntity());
			}
		}
	}

}

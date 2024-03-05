package manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import main.MainClass;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import objects.Trail;

public class TrailsManager {

	private Map<Player, Trail> playerwhoSeletedTrail = new HashMap<>();
	private Set<Arrow> arrowsList = new HashSet<>();

	public TrailsManager() {

	}

	public void displayParticle(EnumParticle enumParticle) {
		for (Arrow arrow : this.arrowsList) {
			if (arrow.isDead() || arrow.isOnGround() || arrow == null) {
				this.arrowsList.remove(arrow);
				arrow.remove();
				continue;
			} else {

				particle(arrow.getLocation(), enumParticle);

			}
		}
	}
	
	
	  private void particle(Location loc , EnumParticle enumParticle) {
		    PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(enumParticle, true, (float)loc.getX(), 
		        (float)loc.getY(), (float)loc.getZ(), 0.0F, 0.0F, 0.0F, 0.0F, 15, null);
		    for (Player p : Bukkit.getOnlinePlayers())
		      (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet); 
		  }

	public boolean hasTrail(Player player) {
		return playerwhoSeletedTrail.containsKey(player);
	}

	public Trail getTrail(Player player) {
		if (!playerwhoSeletedTrail.containsKey(player))
			return null;
		return playerwhoSeletedTrail.get(player);
	}

	public void addPlayer(Player player, Trail trail) {
		if (playerwhoSeletedTrail.containsKey(player)) {
			playerwhoSeletedTrail.replace(player, trail);
		} else {
			playerwhoSeletedTrail.put(player, trail);
		}
	}

	public void removePlayer(Player player) {
		if (!playerwhoSeletedTrail.containsKey(player))
			return;
		playerwhoSeletedTrail.remove(player);
	}

	public void addArrow(Arrow arrow) {
		if (arrowsList.contains(arrow))
			return;
		arrowsList.add(arrow);
	}

	public void removeArrow(Arrow arrow) {
		if (!arrowsList.contains(arrow))
			return;
		arrowsList.remove(arrow);
	}

	public Set<Arrow> getArrowsList() {
		return this.arrowsList;
	}

	public Map<Player, Trail> getPlayerwhoSeletedTrail() {
		return this.playerwhoSeletedTrail;
	}

}

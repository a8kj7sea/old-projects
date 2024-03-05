package main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HeartParticlesPlugin extends JavaPlugin implements Listener {

    private final Map<UUID, HeartParticleCosmetic> cosmetics = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("HeartParticlesPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        for (HeartParticleCosmetic cosmetic : cosmetics.values()) {
            cosmetic.remove();
        }
        cosmetics.clear();
        getLogger().info("HeartParticlesPlugin has been disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        addCosmetic(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        removeCosmetic(player);
    }

    private void addCosmetic(Player player) {
        if (!cosmetics.containsKey(player.getUniqueId())) {
            cosmetics.put(player.getUniqueId(), new HeartParticleCosmetic(player));
        }
    }

    private void removeCosmetic(Player player) {
        UUID uuid = player.getUniqueId();
        if (cosmetics.containsKey(uuid)) {
            cosmetics.get(uuid).remove();
            cosmetics.remove(uuid);
        }
    }

    private static class HeartParticleCosmetic {

        private final ArmorStand armorStand;
        private final BukkitRunnable task;
        private double angle = 0.0;
        private final Player player;

        public HeartParticleCosmetic(Player player) {
            this.player = player;
            this.armorStand = createArmorStand(player);
            this.task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (!player.isOnline()) {
                        remove();
                        return;
                    }
                    angle += 0.1;
                    Location location = player.getLocation().add(0, 1.8, 0).add(getCircleOffset(angle));
                    particle(location);
                }
            };
            this.task.runTaskTimer(HeartParticlesPlugin.getPlugin(HeartParticlesPlugin.class), 1L, 1L);
        }

        public void remove() {
            if (armorStand != null) {
                armorStand.remove();
            }
            task.cancel();
        }

        private static ArmorStand createArmorStand(Player player) {
            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.setCustomName(player.getName() + "'s HeartParticles");
            armorStand.setCustomNameVisible(true);
            player.setPassenger(armorStand);
            return armorStand;
        }

        private static Vector getCircleOffset(double angle) {
            double x = Math.cos(angle) * 1.0;
            double z = Math.sin(angle) * 1.0;
            return new Vector(x, 0.0, z);
        }
    }
    
    private static void particle(Location loc ) {
	    PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.HEART, true, (float)loc.getX(), 
	        (float)loc.getY(), (float)loc.getZ(), 0.1F, 0.1F, 0.1F, 0.0F, 1, null);
	    for (Player p : Bukkit.getOnlinePlayers())
	      (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet); 
	  }
}












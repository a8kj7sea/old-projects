package main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import listeners.PlayerChatListener;
import listeners.ProjectileLaunchListener;
import manager.TrailsManager;
import tasks.TrailsTask;
@Deprecated
public class MainClass extends JavaPlugin {

	private static MainClass mainClass;
	private TrailsManager trailsManager;

	public static MainClass getMainClass() {
		return mainClass;
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
		getServer().getPluginManager().registerEvents(new ProjectileLaunchListener(), this);
		mainClass = this;
		trailsManager = new TrailsManager();
		Bukkit.getScheduler().runTaskTimer(this, new TrailsTask(), 0, 1);
	}

	
	public TrailsManager getTrailsManager() {
		return this.trailsManager;
	}
}

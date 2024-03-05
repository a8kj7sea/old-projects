package me.sanhak.ticketbot.spigot.main;

import org.bukkit.plugin.java.JavaPlugin;

import me.sanhak.ticketbot.discord.startmethods.StartMethods;
import me.sanhak.ticketbot.spigot.configuration.Configuration;

public class Main extends JavaPlugin {

	public static Main m;
	public Configuration c;
	public static Main getMain() {
		return m;
	}
	
	@Override
	public void onEnable() {
		c = new Configuration("config.yml", this, true);
		if (Configuration.getConfig().getString("Ticket-Bot.Token") != null) {
			StartMethods.Start();
		}
		
		m = this;
	}
	
}

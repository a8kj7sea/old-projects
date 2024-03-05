package me.sanhak.ticketbot.discord.startmethods;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.security.auth.login.LoginException;

import me.sanhak.ticketbot.spigot.configuration.Configuration;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class StartMethods {

	public static void Start() {
		JDABuilder b = JDABuilder.createDefault(Configuration.getConfig().getString("Ticket-Bot.Token"));
		b.enableIntents(GatewayIntent.GUILD_MEMBERS);

		try {
			b.build();
		} catch (LoginException var2) {
			var2.printStackTrace();
		}

	}

	public static String GetTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return formatter.format(date);
	}

	public static long Epoch() {
		long time = System.currentTimeMillis();
		return time;
	}
}
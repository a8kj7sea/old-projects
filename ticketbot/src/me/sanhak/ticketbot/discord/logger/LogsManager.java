package me.sanhak.ticketbot.discord.logger;

import me.sanhak.ticketbot.discord.backup.BackupManager;
import me.sanhak.ticketbot.spigot.configuration.Configuration;
import net.dv8tion.jda.api.entities.TextChannel;

public class LogsManager {
	
	public static TextChannel getTextChannel() {
		if (BackupManager.getBackupGuild() == null) return null;
		if (BackupManager.getBackupGuild().getTextChannelById(Configuration.getConfig().getString("Ticket-Bot.Logs-Channel-ID")) == null) return null;	
		TextChannel tx = BackupManager.getBackupGuild().getTextChannelById(Configuration.getConfig().getString("Ticket-Bot.Logs-Channel-ID"));
		return tx;
	}

}

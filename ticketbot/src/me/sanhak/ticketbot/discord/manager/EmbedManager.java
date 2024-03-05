package me.sanhak.ticketbot.discord.manager;

import java.awt.Color;

import me.sanhak.ticketbot.spigot.configuration.Configuration;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

public class EmbedManager {
	
	public static EmbedBuilder CreateEmbed() {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(Configuration.getConfig().getString("Embeds.Create-Embed.Title"));
		eb.setDescription(Configuration.getConfig().getString("Embeds.Create-Embed.Description"));
		eb.setColor(Color.getColor(Configuration.getConfig().getString("Embeds.Create-Embed.Color").toUpperCase()));
		return eb;
	}

	public static EmbedBuilder TicketEmbed(Member m) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(Configuration.getConfig().getString("Embeds.Ticket-Embed.Title")
				.replace("%user_name%", m.getUser().getName())
				.replace("%user_tag%", m.getUser().getAsTag())
				.replace("%user_id%", m.getUser().getId())
				.replace("%user_as_mention%", m.getUser().getAsMention())
				.replace("%user_avatar%", m.getUser().getAvatarUrl())
				.replace("%mention_role%", RoleManager.getMentionRole()));
		eb.setDescription(Configuration.getConfig().getString("Embeds.Ticket-Embed.Description")
				.replace("%user_name%", m.getUser().getName())
				.replace("%user_tag%", m.getUser().getAsTag())
				.replace("%user_id%", m.getUser().getId())
				.replace("%user_as_mention%", m.getUser().getAsMention())
				.replace("%user_avatar%", m.getUser().getAvatarUrl())
				.replace("%mention_role%", RoleManager.getMentionRole()));
		eb.setColor(Color.getColor(Configuration.getConfig().getString("Embeds.Ticket-Embed.Color").toUpperCase()));
		return eb;
	}
	
	public static EmbedBuilder LogsEmbed(String title , String Description) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(title);
		eb.setDescription(Description);
		eb.setColor(Color.RED);
		return eb;
	}
	
	
	
}
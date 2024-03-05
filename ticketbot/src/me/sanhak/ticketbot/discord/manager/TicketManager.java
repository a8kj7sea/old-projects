package me.sanhak.ticketbot.discord.manager;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import me.sanhak.ticketbot.discord.buttons.ButtonsManager;
import me.sanhak.ticketbot.discord.logger.LogsManager;
import me.sanhak.ticketbot.spigot.configuration.Configuration;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;

public class TicketManager {

	public static TextChannel c;

	@SuppressWarnings("deprecation")
	public static void createaTicket(Member member, MessageChannel channel, Category category, Guild g) {
		if (category == null) {
			channel.sendMessage(Configuration.getConfig().getString("Messages.Not-Found-Category")).queue();
		} else {
			category.createTextChannel(member.getUser().getName() + "-ticket")
					.addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null)
					// .addPermissionOverride(g.getRoleById(""), null,
					// EnumSet.of(Permission.VIEW_CHANNEL))
					.queue((ticket) -> {
						channel.sendMessageFormat(Configuration.getConfig().getString("Messages.Create-Ticket"), ticket)
								.queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
						ticket.sendMessage(EmbedManager.TicketEmbed(member).build())
								.setActionRow(ButtonsManager.getTicketButtons()).queue();
						LogsManager.getTextChannel()
								.sendMessage(EmbedManager.LogsEmbed("Ticket Created !",
										"Ticket : <#" + ticket.getId() + ">" + "\n Created by : "
												+ member.getAsMention() + "\n Time : <t:"
												+ System.currentTimeMillis() / 1000 + ">")
										.build())
								.queue();
					});

		}
	}

}

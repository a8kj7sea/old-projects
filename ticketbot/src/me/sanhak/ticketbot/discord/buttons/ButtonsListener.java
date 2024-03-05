package me.sanhak.ticketbot.discord.buttons;

import java.util.concurrent.TimeUnit;

import me.sanhak.ticketbot.discord.backup.BackupManager;
import me.sanhak.ticketbot.discord.logger.LogsManager;
import me.sanhak.ticketbot.discord.manager.EmbedManager;
import me.sanhak.ticketbot.discord.manager.ListManager;
import me.sanhak.ticketbot.discord.manager.RoleManager;
import me.sanhak.ticketbot.discord.manager.TicketManager;
import me.sanhak.ticketbot.spigot.configuration.Configuration;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ButtonsListener extends ListenerAdapter {

	@SuppressWarnings("deprecation")
	public void onButtonClick(ButtonClickEvent e) {
		Role r = e.getGuild().getRoleById(RoleManager.getRoleID());
		if (e.getButton().getId().equalsIgnoreCase("close")) {
			if (ListManager.staff_list.contains(e.getMember().getId()) && RoleManager.hasRole(e.getMember(), r)) {
				BackupManager.Start(e.getTextChannel());
				e.getTextChannel().sendMessage("The Ticket will removed after 15 seconds !")
						.queue(m -> m.getTextChannel().delete().queueAfter(15, TimeUnit.SECONDS));
				LogsManager.getTextChannel()
				.sendMessage(EmbedManager.LogsEmbed("Ticket Closed !",
						"Ticket : <#" + e.getChannel().getId() + ">" + "\n Closed by : "
								+ e.getUser().getAsMention() + "\n Time : <t:"
								+ System.currentTimeMillis() / 1000 + ">")
						.build())
				.queue();
			} else {
				e.getTextChannel().sendMessage(Configuration.getConfig().getString("Messages.No-Permissions")
						.replace("%m%", e.getUser().getAsMention()))
						.queue(m -> m.delete().queueAfter(3, TimeUnit.SECONDS));
			}

		}

		if (e.getButton().getId().equalsIgnoreCase("claim")) {
			for (MessageEmbed me : e.getMessage().getEmbeds()) {
				EmbedBuilder eb = new EmbedBuilder(me);
				eb.setTitle(me.getTitle());
				eb.setDescription(me.getDescription() + "\n Claim : " + e.getUser().getAsMention());
				eb.setColor(me.getColor());
				LogsManager.getTextChannel()
						.sendMessage(EmbedManager.LogsEmbed("Ticket Claimed !",
								"Ticket : <#" + e.getChannel().getId() + ">" + "\n Claimed by : "
										+ e.getUser().getAsMention() + "\n Time : <t:"
										+ System.currentTimeMillis() / 1000 + ">")
								.build())
						.queue();
			}
		}

		if (e.getButton().getId().equalsIgnoreCase("create")) {
			if (!ListManager.getBLackList().contains(e.getMember().getId())) {
				TicketManager.createaTicket(e.getMember(), e.getChannel(),
						e.getGuild().getCategoryById(Configuration.getConfig().getString("Ticket-Bot.Category-ID")),
						e.getGuild());

			}
		} else {
			e.getTextChannel().sendMessage(Configuration.getConfig().getString("Messages.Black-List").replace("%blm%",
					e.getUser().getAsMention())).queue(m -> m.delete().queueAfter(3, TimeUnit.SECONDS));
		}

	}
}
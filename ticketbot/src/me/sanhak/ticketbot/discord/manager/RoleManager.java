package me.sanhak.ticketbot.discord.manager;

import java.util.List;

import me.sanhak.ticketbot.spigot.configuration.Configuration;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class RoleManager {
	
	public static String getRoleID() {
		return Configuration.getConfig().getString("Ticket-Bot.Mention-Role-ID");
	}

	public static String getMentionRole() {
		return getRoleID() == null ? "Not Found The Mention Role ID !" : "<@&" + getRoleID() + ">";
	}

	public static boolean hasRole(Member member, Role role) {
		List<Role> memberRoles = member.getRoles();
		return memberRoles.contains(role);
	}
	
}

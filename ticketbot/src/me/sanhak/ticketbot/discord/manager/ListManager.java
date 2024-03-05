package me.sanhak.ticketbot.discord.manager;

import java.util.List;

import me.sanhak.ticketbot.spigot.configuration.Configuration;

public class ListManager {

	public static List<String> black_list = Configuration.getConfig().getStringList("Black-List");
	public static List<String> staff_list = Configuration.getConfig().getStringList("Staff-List");

	public static List<String> getBLackList() {
		return black_list == null ? null : black_list;
	}

	public static void addMemberIDtoBlackList(String member_ID) {
		List<String> list = getBLackList();
		if (list != null) {
			if (!list.contains(member_ID)) {
				list.add(member_ID);
			}
		}
	}

	public static void removeMemberIDfromBlackList(String member_ID) {
		List<String> list = getBLackList();
		if (list != null) {
			if (list.contains(member_ID)) {
				list.remove(member_ID);
			}
		}

	}

	public static void removeallfromblacklist() {
		List<String> list = getBLackList();
		if (list != null) {
			list.clear();
		}
	}

	public static List<String> getStaffList() {
		return staff_list == null ? null : staff_list;
	}

	public static void addMemberIDtoStaffList(String member_ID) {
		List<String> list = getStaffList();
		if (list != null) {
			if (!list.contains(member_ID)) {
				list.add(member_ID);
			}
		}
	}

	public static void removeMemberIDfromStaffList(String member_ID) {
		List<String> list = getStaffList();
		if (list != null) {
			if (list.contains(member_ID)) {
				list.remove(member_ID);
			}
		}

	}

	public static void removeallfromStaffList() {
		List<String> list = getStaffList();
		if (list != null) {
			list.clear();
		}
	}

}

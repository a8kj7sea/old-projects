package me.sanhak.ticketbot.discord.backup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import me.sanhak.ticketbot.discord.logger.LogsManager;
import me.sanhak.ticketbot.spigot.configuration.Configuration;
import me.sanhak.ticketbot.spigot.main.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class BackupManager {

	public static JDA j;
	public static File f;

//	public static void checkondir() {
//		String path = Main.getMain().getDataFolder().getAbsolutePath() + "\\BackupFiles";
//		File file = new File(path);
//		if (!file.exists() || !file.isDirectory()) {
//			file.mkdir();
//			System.out.println("Create Backup Folder done !");
//		}
//	}

	public static Guild getBackupGuild() {
		Guild g = j.getGuildById(Configuration.getConfig().getString("Backup-Guild-ID"));
		if (Configuration.getConfig().getString("Backup-Guild-ID") != null && g != null) {
			return g;
		} else {
			return null;
		}
	}

	public static void Start(TextChannel c) {
		List<Message> messageList = new ArrayList<>();
		try {
			messageList = c.getIterableHistory().takeAsync(1000)
					.thenApply(list -> list.stream().collect(Collectors.toList())).get();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}

		Collections.reverse(messageList);

		for (Message message : messageList) {
			SimpleType(message, c.getName());
		}
	}

	public static void SimpleType(Message message, String channel) {
		String name = channel + ".html";
		Path pathToFile = Paths.get(Main.getMain().getDataFolder().getAbsolutePath() + "\\BackupFiles\\" + name);

		if (!(pathToFile.toFile().exists())) {
			try {
				Files.createFile(pathToFile);
				f = pathToFile.toFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		try (BufferedWriter writer = Files.newBufferedWriter(pathToFile, StandardOpenOption.APPEND)) {
			// writer.write(message.getContentDisplay() + System.lineSeparator());
			String format = message.getTimeCreated().format(DateTimeFormatter.ISO_OFFSET_TIME) + " "
					+ message.getAuthor().getAsTag() + " > " + message.getContentRaw();
			writer.write("<!DOCTYPE html>" + System.lineSeparator());
			writer.write("<head>" + System.lineSeparator());
			writer.write("	<meta charset=\"utf-8\">" + System.lineSeparator());
			writer.write("	<title>" + name + "</title>" + System.lineSeparator());
			writer.write("</head>" + System.lineSeparator());
			writer.write("" + System.lineSeparator());
			writer.write("<body>" + System.lineSeparator());
			writer.write("	<p>" + format + "</p>" + System.lineSeparator());
			writer.write("<hr>" + System.lineSeparator());
			writer.write("</body>" + System.lineSeparator());
			writer.write("" + System.lineSeparator());
			writer.write("</html>" + System.lineSeparator());
			writer.write("" + System.lineSeparator());
		} catch (IOException ioe) {
			System.err.format("IOException: %s%n", ioe);
		}
		if (f != null) {
			TextChannel newone = getBackupGuild().createTextChannel(channel).complete();
			newone.sendMessage(channel + " Backup file").addFile(f, f.getName());
			LogsManager.getTextChannel().sendMessage("**[logs]** Backup has been created to " + channel + " successfully !").queue();
		}
	}
}
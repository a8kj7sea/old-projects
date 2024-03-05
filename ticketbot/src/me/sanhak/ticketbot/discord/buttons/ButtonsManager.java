package me.sanhak.ticketbot.discord.buttons;

import java.util.ArrayList;
import java.util.List;

import me.sanhak.ticketbot.spigot.configuration.Configuration;
import net.dv8tion.jda.api.interactions.components.Button;

public class ButtonsManager {
   public static List<Button> getTicketButtons() {
      List<Button> buttons = new ArrayList<Button>();
      buttons.add(Button.danger("close", Configuration.getConfig().getString("Buttons.close-ticket")));
      buttons.add(Button.success("claim", Configuration.getConfig().getString("Buttons.claim-ticket")));
      return buttons;
   }

   public static List<Button> getCreateTicketButtons() {
      List<Button> buttons = new ArrayList<Button>();
      buttons.add(Button.success("create", Configuration.getConfig().getString("Buttons.create-ticket")));
      return buttons;
   }
}
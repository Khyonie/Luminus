package com.perceus.luminus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import coffee.khyonieheart.api.NotNull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Luminus
{
	private static JDA botInstance;

	public static void main(String[] args)
	{
		String token;
		File tokenFile = new File("token.txt");
		if (!tokenFile.exists())
		{
			System.out.println("Fatal: token file not found");
			return;
		}

		try (Scanner scanner = new Scanner(tokenFile)) { // Open a scanner to read token.txt
			token = scanner.nextLine();
		} catch (FileNotFoundException e) { 
			// This realistically should not fire, but if it somehow does
			return;
		}

		// Check if token.txt is empty
		if (token.length() == 0)
		{
			System.out.println("Fatal: token file is empty");
			return;
		}

		// Create the bot using the JDABuilder
		botInstance = JDABuilder.createDefault(token)
			.addEventListeners(new EventHandler()) // Add an event handler so our bot can actually do something
			.setActivity(Activity.watching("the decline of humanity"))
			.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES)
			.build();

		// Finally, log bot in
		try {
			System.out.println("Attempting to log bot in...");
			botInstance.awaitReady();
			System.out.println("Bot should be ready.");
		} catch (InterruptedException e) {
			botInstance.shutdownNow();
			e.printStackTrace();
		}

		new Terminal().start();
	}

	@NotNull
	public static JDA getBot()
	{
		return botInstance;
	}
}

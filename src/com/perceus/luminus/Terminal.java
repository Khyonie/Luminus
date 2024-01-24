package com.perceus.luminus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import coffee.khyonieheart.api.Arrays;
import coffee.khyonieheart.api.NotNull;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;

/**
 * Handler for commands in the host's terminal.
 */
public class Terminal
{
	private static Thread runningThread;

	/**
	 * Starts the terminal handler.
	 */
	public void start()
		throws IllegalStateException
	{
		if (runningThread != null)
		{
			throw new IllegalStateException("Cannot start two concurrent instances of the terminal reader");
		}

		runningThread = new Thread(() -> {
			try (Scanner scanner = new Scanner(System.in))
			{
				List<String> history = new ArrayList<>();
				String input;
				loop: while (true)
				{
					System.out.print("> ");
					input = scanner.nextLine();
					history.add(input);
					String[] split = input.split(" ");

					if (split.length == 1)
					{
						if (!handleInput(split[0].toLowerCase(), new String[0], history))
						{
							break loop;
						}
						continue;
					}

					if (!handleInput(split[0].toLowerCase(), java.util.Arrays.copyOfRange(split, 1, split.length), history))
					{
						break loop;
					}
				}
			}

			Luminus.getBot().shutdown();
		});

		runningThread.start();
	}

	/**
	 * Handles input from the terminal.
	 * 
	 * @param command The command being executed
	 * @param args All provided arguments
	 * @param history Command history
	 *
	 * @return Whether to not stop this terminal handler and subsequently terminate the bot
	 */
	private boolean handleInput(
		@NotNull String command,
		@NotNull String[] args,
		@NotNull List<String> history
	) {
		try {
			return switch (command)
			{
				case "stop" -> {
					System.out.println("\tAttempting to exit...");
					yield false;
				}
				case "exit" -> {
					System.out.println("\tAttempting to exit...");
					yield false;
				}
				case "history" -> {
					for (String s : history)
					{
						System.out.println("» " + s);
					}

					yield true;
				}
				case "broadcast" -> {
					if (Luminus.getBot().getGuilds().isEmpty())
					{
						System.out.println("\tBot is not connected to any guilds.");
						yield true;
					}

					if (args.length == 0)
					{
						System.out.println("\tbroadcast | broadcast <message>");
						System.out.println("\t- Sends a message to the #general channel of all connected servers.");
						yield true;
					}

					String message = Arrays.toString(args, " ", null);
					System.out.println("\tBroadcasting message \"" + message + "\"");
					for (Guild guild : Luminus.getBot().getGuilds())
					{
						for (GuildChannel channel : guild.getChannels(false))
						{
							if (!channel.getName().equals("general"))
							{
								continue;
							}

							if (channel instanceof TextChannel textChannel)
							{
								System.out.println("\tBroadcasting to channel #" + channel.getName() + " in guild " + guild.getName());
								textChannel.sendMessage(message).submit();
							}
						}
					}

					yield true;
				}
				default -> {
					System.out.println("\tNo such terminal command \"" + command + "\"");
					yield true;
				}
			};
		} catch (Exception e) {
			return true;
		}
	}
}

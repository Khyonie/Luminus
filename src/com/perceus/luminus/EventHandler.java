package com.perceus.luminus;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;

public class EventHandler extends ListenerAdapter
{
	@Override
	public void onMessageReceived(
		MessageReceivedEvent event
	) {
		if (event.getAuthor().isBot())
		{
			// Ignore bot messages
			return;
		}

		MessageCreateAction result = event.getChannel().sendMessage(event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
		result.submit();
	}
}

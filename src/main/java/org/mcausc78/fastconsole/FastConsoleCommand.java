package org.mcausc78.fastconsole;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FastConsoleCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender.hasPermission("fastconsole.fastconsole"))) {
			FastConsole
					.getInstance()
					.getConfig()
					.getStringList("language.commands.fastconsole.no-permission")
					.forEach(s ->
							sender.sendMessage(TextUtils.handle(s, sender))
					);
			return true;
		}
		if(args.length <= 0) {
			FastConsole
					.getInstance()
					.getConfig()
					.getStringList("language.commands.fastconsole.usage")
					.forEach(s ->
							sender.sendMessage(TextUtils.handle(s, sender))
					);
		}
		switch(args[0]) {
			case "help":
				FastConsole
					.getInstance()
					.getConfig()
					.getStringList("language.commands.fastconsole.help")
					.forEach(s ->
						sender.sendMessage(TextUtils.handle(s, sender))
					);
				break;
			case "reload":
				FastConsole.getInstance().reloadConfig();
				FastConsole
						.getInstance()
						.getConfig()
						.getStringList("language.commands.fastconsole.config-reloaded")
						.forEach(s ->
								sender.sendMessage(TextUtils.handle(s, sender))
						);
				break;
			default:
				FastConsole
						.getInstance()
						.getConfig()
						.getStringList("language.commands.fastconsole.unknown-subcommand")
						.forEach(s ->
								sender.sendMessage(TextUtils.handle(s, sender))
					);
				return true;
		}
		return true;
	}
}

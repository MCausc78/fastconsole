package org.mcausc78.fastconsole;

import org.bukkit.command.CommandSender;

import java.util.List;

public class PlayerUtils {
	// disallow create instance
	private PlayerUtils() {}
	public static boolean canUseConsole(CommandSender cs, AccessMode am) {
		switch(am) {
			case LIST:
				return ((cs != null && FastConsole
						.getInstance()
						.getConfig()
						.getStringList("commands.console.players").contains(cs.getName())));
			case PERMISSION:
				return ((cs != null &&
						cs.hasPermission("fastconsole.commands.console")));
			case PERMISSION_AND_LIST:
				return canUseConsole(cs, AccessMode.PERMISSION) && canUseConsole(cs, AccessMode.LIST);
			case PERMISSION_OR_LIST:
				return canUseConsole(cs, AccessMode.PERMISSION) || canUseConsole(cs, AccessMode.LIST);
			case NONE:
				return true;
			case UNKNOWN:
			default:
				return false;
		}
	}
	public static boolean canUseConsole(CommandSender cs) {
		return canUseConsole(cs, FastConsole.getMode());
	}
	public static List<String> getMessages(AccessMode am) {
		switch(am) {
			case PERMISSION_OR_LIST: return FastConsole
					.getInstance()
					.getConfig()
					.getStringList("language.commands.console.access-denied.permission-or-list");
			case PERMISSION_AND_LIST: return FastConsole
					.getInstance()
					.getConfig()
					.getStringList("language.commands.console.access-denied.permission-and-list");
			case PERMISSION: return FastConsole
					.getInstance()
					.getConfig()
					.getStringList("language.commands.console.access-denied.permission");
			case LIST: return FastConsole
					.getInstance()
					.getConfig()
					.getStringList("language.commands.console.access-denied.list");
			default: return FastConsole
					.getInstance()
					.getConfig()
					.getStringList("language.commands.console.access-denied.none");
		}
	}
}

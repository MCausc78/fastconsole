package org.mcausc78.fastconsole;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConsoleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            FastConsole
                    .getInstance()
                    .getConfig()
                    .getStringList("language.commands.console.only-for-players")
                    .forEach(s ->
                            sender.sendMessage(TextUtils.handle(s, sender)
                                    .replace("$label$", label)
                                    .replace("$cmd.name$", command.getName())
                            )
                    );
            return true;
        }
        if(!(PlayerUtils.canUseConsole(sender))) {
            PlayerUtils.getMessages(FastConsole.getMode())
                .forEach(s ->
                        sender.sendMessage(TextUtils.handle(s
                                .replace("$label$", label)
                                .replace("$cmd.name$", command.getName()), sender))
                );
            return true;
        }
        if(args.length <= 0) {
            FastConsole
                    .getInstance()
                    .getConfig()
                    .getStringList("language.commands.console.usage")
                    .forEach(s ->
                            sender.sendMessage(TextUtils.handle(s
                                    .replace("$label$", label)
                                    .replace("$cmd.name$", command.getName()), sender))
                    );
            return true;
        }
        boolean result = Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                String.join(" ", args)
        );
        FastConsole
                .getInstance()
                .getConfig()
                .getStringList("language.commands.console.executed")
                .forEach(s ->
                    sender.sendMessage(TextUtils.handle(s
                                    .replace("$label$", label)
                                    .replace("$cmd.name$", command.getName())
                                    .replace("$result.bool$", Boolean.toString(result)), sender)
                    )
                );
        return true;
    }
}

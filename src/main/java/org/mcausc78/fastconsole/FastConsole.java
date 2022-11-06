package org.mcausc78.fastconsole;

import org.bukkit.plugin.java.JavaPlugin;

public final class FastConsole extends JavaPlugin {
    private static FastConsole instance;
    @Override
    public void onEnable() {
        this.getCommand("console")
                .setExecutor(new ConsoleCommand());
        this.getCommand("fastconsole")
                .setExecutor(new FastConsoleCommand());
        instance = this;
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
    }

    public static FastConsole getInstance() {
        return instance;
    }
    public static AccessMode getMode() {
        String mode = (FastConsole
                .getInstance()
                .getConfig()
                .getString("commands.console.access-mode", "UNKNOWN").toUpperCase());
        switch(mode) {
            case "PERMISSION":
                return AccessMode.PERMISSION;
            case "PERMISSION_AND_LIST":
                return AccessMode.PERMISSION_AND_LIST;
            case "LIST":
                return AccessMode.LIST;
            case "PERMISSION_OR_LIST":
                return AccessMode.PERMISSION_OR_LIST;
            case "NONE":
                return AccessMode.NONE;
            case "UNKNOWN":
            default:
                System.err.printf("[%s] Unknown mode \"%s\"\n", getInstance().getDescription().getFullName(), mode);
                return AccessMode.UNKNOWN;
        }
    }
}

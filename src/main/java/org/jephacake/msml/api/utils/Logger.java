package org.jephacake.msml.api.utils;

import org.bukkit.plugin.java.JavaPlugin;

public class Logger {

    public static java.util.logging.Logger logger;

    public static void setLogger(JavaPlugin plugin) {
        logger = plugin.getLogger();
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warning(message);
    }

    public static void severe(String message) {
        logger.severe(message);
    }

    public static void fine(String message) {
        logger.fine(message);
    }

    public static void finer(String message) {
        logger.finer(message);
    }

    public static void finest(String message) {
        logger.finest(message);
    }

    public static void config(String message) {
        logger.config(message);
    }

    public static void log(java.util.logging.Level level, String message) {
        logger.log(level, message);
    }

}

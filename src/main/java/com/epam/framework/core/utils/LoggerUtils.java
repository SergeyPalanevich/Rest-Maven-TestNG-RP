package com.epam.framework.core.utils;

import com.epam.reportportal.message.ReportPortalMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;

import java.io.File;
import java.io.IOException;

public class LoggerUtils {

    private static final Logger LOGGER = LogManager.getRootLogger();

    private LoggerUtils() {
    }

    public static void error(String string) {
        LOGGER.error(string);
    }

    public static void error(String string, Throwable throwable) {
        LOGGER.error(string, throwable);
    }

    public static void debug(String string) {
        LOGGER.debug(string);
    }

    public static void info(File file, String message){
        try {
            LOGGER.info(new ObjectMessage(new ReportPortalMessage(file, message)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void info(String message) {
        LOGGER.info(message);
    }
}

package models;

import java.util.logging.Logger;

public class LoggerProject {
    private static Logger log = Logger.getLogger(LoggerProject.class.getName());

    public static void setLogs(String className, String msg) {
        log.info(String.format("%s : %s", className, msg));
    }

    public static void printWhatDone(String obj, String msg) {
        System.out.println(String.format("%s : %s", obj, msg));
    }
}

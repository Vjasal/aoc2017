package com.vjasal.aoc.common;

import java.util.logging.Logger;

public class LoggerFactory {

    public static Logger getLogger(Class loggingClass) {
        return Logger.getLogger(loggingClass.getName());
    }
}

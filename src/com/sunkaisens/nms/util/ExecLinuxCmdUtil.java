package com.sunkaisens.nms.util;

import org.apache.log4j.Logger;

public class ExecLinuxCmdUtil {
	
	private static Logger logger = Logger.getLogger(ExecLinuxCmdUtil.class);
	public static void exec(String cmd) {
        Process process = null;
        String[] cmds = {"/bin/bash","-c",cmd};
        try {
            process = new ProcessBuilder(cmds).redirectErrorStream(true).start();
            process.waitFor();
        } catch (Exception e) {
        	logger.error("runtime.exec cmd: " + cmd + " failed", e);
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }
}

package com.sunkaisens.nms.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecLinuxShellUtil {
	public static void execShell(String shellString){
        System.out.println("将要执行的shell语句是:"+shellString);
        try{
            Process process=Runtime.getRuntime().exec(shellString);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line="";
            while((line = input.readLine()) != null){
                System.out.println(line);
            }
            input.close();
            int exitValue=process.waitFor();
            if(0!=exitValue){
                System.err.println("call shell failed.errorcodeis:"+exitValue);
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
         
    }
}

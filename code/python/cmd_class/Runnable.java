
package com.code.python.cmd_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Runnable {
     public static List cmdRun(String File_name, String output_lable) throws IOException {
        List<String> words_list = new ArrayList<>();
        ProcessBuilder builder = new ProcessBuilder("python", System.getProperty("user.dir") + "/src/python_code/" + File_name + ".py");
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader readers = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        String[] words = null;
        String line;

        int count = 0;

        while ((line = reader.readLine()) != null) {
            words = line.split(" ");
            for (String word : words) {
                words_list.add(word);
                if (word.equals("" + output_lable)) //Search for the given word
                {
//                    temp = count;
                }
                count++;
            }
        }
        return words_list;
    }
     public static void main(String[] args) {
         try {
             System.out.println(new Runnable().cmdRun("", ""));
         } catch (IOException ex) {
             Logger.getLogger(Runnable.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

}

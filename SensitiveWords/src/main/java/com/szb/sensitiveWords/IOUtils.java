package com.szb.sensitiveWords;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 石致彬
 * @since 2021-09-11 19:43
 */
public class IOUtils {
    public static List<String> ans = new ArrayList<>();
    public static List<String> readText(String src)  {
        File file = new File(src);
        FileReader fileReader = null;
        BufferedReader br = null;
        List<String> strings = null;
        try {
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str;
            int i = 0;
            strings = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                str = str.toLowerCase();
                strings.add(i,str);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return strings;
    }

    public static void addAns(String word, int line, String substring) {
        ans.add("Line"+line+": "+"<"+word+"> "+substring);
    }

    public static void writeAns(String desc) {
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        try {
            File file = new File(desc);
            fileWriter = new FileWriter(file,true);
            bw = new BufferedWriter(fileWriter);
            bw.write("Total: " + ans.size());
            bw.newLine();
            for (String str : ans) {
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
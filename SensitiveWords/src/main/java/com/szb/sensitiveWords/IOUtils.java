package com.szb.sensitiveWords;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 进行IO操作的工具类
 * @author 石致彬
 * @since 2021-09-11 19:43
 */
public class IOUtils {
    public static List<String> ans = new ArrayList<>();

    /**
     * 根据文件绝对路径读入文件
     * @param src
     * @return
     */
    public static List<String> readText(String src)  {
        File file = new File(src);
        FileInputStream fis;
        InputStreamReader fileReader = null;
        BufferedReader br = null;
        List<String> strings = null;
        try {
            fis = new FileInputStream(file);
            fileReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
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


    /**
     * 保存格式化的答案
     * @param word 敏感词
     * @param line 行号
     * @param substring 原文中的包含敏感词的字符串
     */
    public static void addAns(String word, int line, String substring) {
        ans.add("Line"+line+": "+"<"+word+"> "+substring);
    }

    /**
     * 根据输出绝对路径输出文件
     * @param desc
     */
    public static void writeAns(String desc) {
        BufferedWriter bw = null;
        FileOutputStream fos;
        OutputStreamWriter fileWriter = null;
        try {
            File file = new File(desc);
            fos = new FileOutputStream(file);
            fileWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
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

package com.szb.sensitiveWords;


import java.util.List;


/**
 * 启动类
 * @author 石致彬
 * @since 2021-09-09 18:10
 */
public class Main {
    public static void main(String[] args)  {
        //初始化汉字拆分的词典
        Dictionary.init();
        //从命令行读取参数
        String srcWords = args[0];
        String srcText = args[1];
        String desc = args[2];


        DfaSensitiveWords dfaSensitiveWords = new DfaSensitiveWords();
        //1.读入敏感词文件并添加到敏感词库中
//        String srcWords = "D:\\SensitiveWords\\src\\main\\resources\\words.txt";
//        String srcText = "D:\\SensitiveWords\\src\\main\\resources\\org.txt";
        List<String> words = IOUtils.readWords(srcWords);
        dfaSensitiveWords.init(words);

        //2.读入待测文件
        List<String> lines = IOUtils.readText(srcText);
        for (int i = 0; i < lines.size(); i++) {
            dfaSensitiveWords.filter1(lines.get(i),i+1);
        }

        //3.输出文件
//        String desc = "D:\\SensitiveWords\\src\\main\\resources\\ans.txt";
        IOUtils.writeAns(desc);
    }
}

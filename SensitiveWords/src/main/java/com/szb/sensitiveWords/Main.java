package com.szb.sensitiveWords;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;
import com.github.houbb.sensitive.word.api.IWordResultHandler;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import com.github.houbb.sensitive.word.support.result.WordResultHandlers;
import org.junit.Assert;

/**
 * @author 石致彬
 * @since 2021-09-09 18:10
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DfaSensitiveWords dfaSensitiveWords = new DfaSensitiveWords();
        //1.读入敏感词文件并添加到敏感词库中
        String srcWords = "D:\\SensitiveWords\\src\\main\\resources\\words.txt";
        String srcText = "D:\\SensitiveWords\\src\\main\\resources\\org.txt";
        List<String> words = IOUtils.readText(srcWords);
        dfaSensitiveWords.init(words);
        List<String> lines = IOUtils.readText(srcText);
        for (int i = 0; i < lines.size(); i++) {
            dfaSensitiveWords.filter(lines.get(i),i+1);
        }
        String desc = "D:\\SensitiveWords\\src\\main\\resources\\ans.txt";
        IOUtils.writeAns(desc);
    }
}

package com.szb.sensitiveWords;

import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词库
 * @author 石致彬
 * @since 2021-09-14 18:39
 */
@SuppressWarnings("all")
public class SensitiveWords {
    public static List<String> words;
    public static List<String> wordsList;

//    public static List<String> strings = new ArrayList<>();//将原来的字符串中每个字符存起来

//    public static List<String> splits = new ArrayList<>();

    /**
     * 添加拆分汉字的敏感词库
     * @param str
     */
    public static void addWords(String str) {
        words = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        List<String> splits = new ArrayList<>();
        String string;
        for (int i = 0; i < str.length(); i++) {
            string = "" + str.charAt(i);
            strings.add(i,string);
        }
        for (int i = 0; i < strings.size(); i++) {
            if (Dictionary.dict.containsKey(strings.get(i))) {
                splits.add(i,Dictionary.dict.get(strings.get(i)));
            } else {
                splits.add(i,strings.get(i));
            }
        }
        wordsList = new ArrayList<>();
        breakWord(strings,splits,0);
        for (String s : wordsList) {
            Dictionary.brokenWordToWord.put(s,str);
        }

    }

    /**
     * 添加部首拼音敏感词 如 氵去che仑gong力
     * @param str
     */
    public static void addWordsBSPY(String str) {
        words = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        List<String> splits = new ArrayList<>();
        List<String> pyList = new ArrayList<>();
        String string;
        for (int i = 0; i < str.length(); i++) {
            string = "" + str.charAt(i);
            strings.add(i,string);
        }
        int j = 0;
        for (int i = 0; i < strings.size(); i++) {
            if (Dictionary.dict.containsKey(strings.get(i))) {
//                splits.add(j++,Dictionary.dict.get(strings.get(i)));
                String s = Dictionary.dict.get(strings.get(i));
                String s1 = s.charAt(0) + "";
                splits.add(j++,s1);
                String s2 = s.charAt(1) + "";
                splits.add(j++,s2);
            } else {
                splits.add(j++,strings.get(i));
            }
        }
        for (int i = 0; i < splits.size(); i++) {
            pyList.add(i, PinyinHelper.toPinyin(splits.get(i), PinyinStyleEnum.NORMAL));
        }
        wordsList = new ArrayList<>();
        breakWord(splits,pyList,0);
        for (String s : wordsList) {
            Dictionary.brokenWordToWord.put(s,str);
        }

    }

    /**
     * 添加拼音的敏感词库
     * @param str
     */
    public static void addWordsPY(String str) {
        words = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        List<String> splits = new ArrayList<>();
        String string;
        for (int i = 0; i < str.length(); i++) {
            string = "" + str.charAt(i);
            strings.add(i,string);
        }
        for (int i = 0; i < strings.size(); i++) {
            splits.add(i, PinyinHelper.toPinyin(strings.get(i), PinyinStyleEnum.NORMAL));
        }
        wordsList = new ArrayList<>();
        breakWord(strings,splits,0);
        for (String s : wordsList) {
            Dictionary.brokenWordToWord.put(s,str);
        }

    }

    /**
     * 添加首字母的敏感词库
     * @param str
     */
    public static void addWordsSZM(String str) {
        words = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        List<String> splits = new ArrayList<>();
        String string;
        for (int i = 0; i < str.length(); i++) {
            string = "" + str.charAt(i);
            strings.add(i,string);
        }
        for (int i = 0; i < strings.size(); i++) {
            splits.add(i, PinyinHelper.toPinyin(strings.get(i), PinyinStyleEnum.FIRST_LETTER));
        }
        wordsList = new ArrayList<>();
        breakWord(strings,splits,0);
        for (String s : wordsList) {
            Dictionary.brokenWordToWord.put(s,str);
        }

    }

    /**
     * 增加拼音+首字母的敏感词
     * @param str
     */
    public static void addWordsPYSZM(String str) {
        words = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        List<String> splits = new ArrayList<>();
        List<String> pyList = new ArrayList<>();
        String string;
        for (int i = 0; i < str.length(); i++) {
            string = "" + str.charAt(i);
            strings.add(i,string);
        }
        for (int i = 0; i < strings.size(); i++) {
            splits.add(i, PinyinHelper.toPinyin(strings.get(i), PinyinStyleEnum.FIRST_LETTER));
            pyList.add(i, PinyinHelper.toPinyin(strings.get(i), PinyinStyleEnum.NORMAL));
        }
        wordsList = new ArrayList<>();
        breakWord(pyList,splits,0);
        for (String s : wordsList) {
            Dictionary.brokenWordToWord.put(s,str);
        }

    }


    public static void breakWord(List<String> strings, List<String> splits,int t) {
        if (t > strings.size() - 1) {
            StringBuilder ans = new StringBuilder();
            for (String word : words) {
                ans.append(word);
            }
            String s = ans.toString();
//            System.out.println(s);
            wordsList.add(s);
        } else {
            for (int i = 0; i <= 1; i++) {
                if (i == 0) {
                    words.add(t,strings.get(t));
                    if (words.size() > strings.size()) {
                        words.remove(t);
                        words.set(t,strings.get(t));
                    }
                } else  {
                    words.add(t,splits.get(t));
                    if (words.size() > splits.size()) {
                        words.remove(t);
                        words.set(t,splits.get(t));
                    }
                }
                breakWord(strings,splits,t+1);
            }
        }
    }
}

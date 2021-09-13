package com.szb.sensitiveWords;


import com.github.houbb.opencc4j.util.ZhConverterUtil;

import java.util.*;


/**
 * DFA操作类
 * @author 石致彬
 * @since 2021-09-09 18:15
 */
@SuppressWarnings("all")
public class DfaSensitiveWords implements ISensitiveWords {

    private final static String END_MARK_KEY = "end";


    private String sensitive = ""; // 用于记录原文中的敏感词
    public static int total = 0;
    public HashMap wordsMap;

    @Override
    public void init(Collection<String> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            throw new IllegalArgumentException("The param of keywords cannot be empty");
        }
        wordsMap= new HashMap(keywords.size());
        for (String keyword : keywords) {
            if (keyword == null) {
                continue;
            }
            Map nowMap = wordsMap;
            char[] chars = keyword.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char keyChar = chars[i];
                Object wordMap = nowMap.get(keyChar);

                if (wordMap != null) {
                    //如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                } else {
                    //不存在则，则构建一个map，同时将end设置为false，因为他不是最后一个
                    Map<String, Object> newWorMap = new HashMap<>(4);
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if (i == chars.length - 1) {
                    //最后一个
                    nowMap.put(END_MARK_KEY, true);
                }
            }
        }
    }


    /**
     * 进行敏感词过滤
     * @param word      文本
     * @param line      行号
     */
    public void filter(String word,int line) {
        //去除特殊符号，转换为小写，繁体转简体
        String str = word.replaceAll("([^\\u4e00-\\u9fc2A-Za-z0-9])", " ");
        str = str.toLowerCase();
        String simple = ZhConverterUtil.toSimple(str);

        //循环进行DFA检测
        for (int i = 0; i < word.length(); i++) {
            this.sensitive = "";
            if (word.charAt(i) == ' ') {
                continue;
            }
            int check = check(simple, i);
            if (check > i) {
                IOUtils.addAns(this.sensitive, line, word.substring(i, check));
                total++;
            }
        }

    }

    /**
     * 检查文字中是否包含敏感字符
     *
     * @param words      文本
     * @param beginIndex 开始位置
     * @return 如果存在，则返回敏感词字符的长度，不存在返回-1
     */
    private int check(String words, int beginIndex) {
        char beginChar = words.charAt(beginIndex);
        if (beginChar == ' ')
            return -1;
        boolean flag = false;
        Map nowMap = wordsMap;
        int i;
        for (i = beginIndex; i < words.length(); i++) {
            char word = words.charAt(i);
            if (word == ' ' || beginChar >= 'a' && beginChar <= 'z' && word >= '0' && word <= '9' ) {
                continue;
            }
            nowMap = (Map) nowMap.get(word);
            if (nowMap == null) { //该字符不在敏感词库中直接退出循环
                break;
            }
            this.sensitive += word; //是敏感词的字符，先进行拼接
            flag = Boolean.TRUE.equals(nowMap.get(END_MARK_KEY));
            if (flag){
                i++;
                break;
            }
        }
        return flag ? i : -1;
    }
}



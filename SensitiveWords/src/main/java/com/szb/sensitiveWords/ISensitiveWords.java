package com.szb.sensitiveWords;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface ISensitiveWords {

    /**
     * 初始化敏感词库
     *
     * @param keywords 所有敏感词
     */
    void init(Collection<String> keywords);

    /**
     * 添加敏感词
     *
     * @param keyword 待添加的敏感词
     */
    void add(String keyword);

    /**
     * 检查敏感词
     *
     * @param words 待检查文本
     * @return 是否包含敏感词
     */
    boolean contain(String words);

    /**
     * 检查第一个敏感词
     *
     * @param words 待检查文本
     * @return 检查到的第一个敏感词
     */
    Optional<String> first(String words);

    /**
     * 所有包含的敏感词
     *
     * @param words 待检查文本
     * @return 敏感词集合
     */
    Set<String> all(String words);
}


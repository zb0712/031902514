package com.szb.sensitiveWords;

import java.util.Collection;


public interface ISensitiveWords {

    /**
     * 初始化敏感词库
     *
     * @param keywords 所有敏感词
     */
    void init(Collection<String> keywords);



}


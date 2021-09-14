import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;
import com.szb.sensitiveWords.DfaSensitiveWords;
import com.szb.sensitiveWords.Dictionary;
import com.szb.sensitiveWords.IOUtils;
import com.szb.sensitiveWords.SensitiveWords;

import java.util.List;

/**
 * @author 石致彬
 * @since 2021-09-14 18:49
 */
public class Test {
    public static void main(String[] args) {
        Dictionary.init();
        List<String> list = IOUtils.readWords("D:\\\\SensitiveWords\\\\src\\\\main\\\\resources\\\\words.txt");
        System.out.println(list);
        DfaSensitiveWords dfaSensitiveWords = new DfaSensitiveWords();
        dfaSensitiveWords.init(list);
//        String s = PinyinHelper.toPinyin("法轮功", PinyinStyleEnum.NORMAL);
        System.out.println(dfaSensitiveWords.wordsMap);
//        System.out.println(s);
    }
}

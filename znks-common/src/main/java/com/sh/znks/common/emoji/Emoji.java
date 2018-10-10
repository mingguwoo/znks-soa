package com.sh.znks.common.emoji;

import com.github.binarywang.java.emoji.EmojiConverter;

/**
 * Created by wuminggu on 2018/9/20.
 */
public class Emoji {
    private static EmojiConverter emojiConverter = EmojiConverter.getInstance();

    // 将表情编码转换成可读的表情字符
    public static String emojiConverterUnicodeStr(String emojiStr){
        String result = emojiConverter.toUnicode(emojiStr);
        return result;
    }

    // 将带有表情的字符转换为表情编码（存入数据库）
    public static String emojiConverterToAlias(String str){
        String result=emojiConverter.toAlias(str);
        return result;
    }
}

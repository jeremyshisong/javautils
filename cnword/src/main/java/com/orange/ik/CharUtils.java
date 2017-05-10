package com.orange.ik;

/**
 * Created by meijiangze on 2016/9/26.
 */
public class CharUtils {
    /**
     * 全角转半角
     * @param input
     * @return
     */
    public static String regularize(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);
            }
        }
        String returnString = new String(c);

        return returnString;
    }
}

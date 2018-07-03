package learn_to_code.java_se_api;

import org.apache.commons.lang3.StringEscapeUtils;

import java.net.URLEncoder;

/** */
public class JunkCode {
    public static void main(String[] args) throws Exception {
        String str = "особый-русский-数据应用-текст";

        StringBuilder builder = new StringBuilder();
        for(char ch: str.toCharArray()) {
            if(ch >= 0x20 && ch <= 0x7E) {
                builder.append(ch);
            } else {
                builder.append(String.format("%%u%04X", (int)ch));
            }
        }

        String result = builder.toString();
        System.out.println(result);
    }
}

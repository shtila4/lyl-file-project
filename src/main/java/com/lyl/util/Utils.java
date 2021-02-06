package com.lyl.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
@SuppressWarnings("all")
public class Utils {

    //在线预览
    public static String reading(String bookUrl) throws IOException {
        //字符输入流
        FileReader fr = null;
        String text = "";
        try {
            fr = new FileReader(new File(bookUrl));
            char[] chars = new char[1024];
            while (fr.read(chars) != -1) {
                //如果多次读取到chars中需制定chars的起始位置和偏移量，因为每次把字符读取到chars中是覆盖了上次的chars，若不指定长度，会产生多于字符
                text += new String(chars, 0, chars.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return text;
        }
    }

}



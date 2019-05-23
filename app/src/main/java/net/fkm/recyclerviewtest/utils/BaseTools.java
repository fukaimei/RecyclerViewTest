package net.fkm.recyclerviewtest.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * 基础工具类
 */
public class BaseTools {

    /**
     * 获取Assets目录的json文件
     *
     * @return json data
     */
    public static String getAssetsJson(Context context, String fileName) {
        InputStream input = null;
        try {
            input = context.getAssets().open(fileName);
            String json = convertStreamToString(input);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * input 流转换为字符串
     *
     * @param is
     * @return
     */
    private static String convertStreamToString(InputStream is) {
        String s = null;
        try {
            Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
            if (scanner.hasNext()) {
                s = scanner.next();
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}

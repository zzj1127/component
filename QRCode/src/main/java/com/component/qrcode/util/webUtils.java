package com.component.qrcode.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class webUtils {
    public static void  setText(String path, String SearchStr){
        List<String> pathlist = getFile(path,SearchStr);
        File file = new File("C:\\Users\\Zzj\\Desktop\\read.txt");
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file,false);
            for(String value:pathlist){
                outputStream.write(value.getBytes());
            }

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //搜索
    public static List<String> getFile(String path, String SearchStr) {
        File file = new File(path);
        List<String> resultList = new ArrayList<>();
        if (file.exists()) {
            String s = file.toString();
            /* 读取数据 */
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(new File(s)), "UTF-8"));
                String lineTxt = null;
                int i=1;
                while ((lineTxt = br.readLine()) != null) {
                    if (lineTxt.toLowerCase().contains(SearchStr.toLowerCase())) {
                        resultList.add("在【" + s + "】文件中找到了" + SearchStr +"在第"+i+"行\n");

                    }
                    i++;
                }
                br.close();
            } catch (Exception e) {
                // TODO: handle exception
                System.err.println("读取文件错误 :" + e);
            }
        }
        return resultList;
    }


}

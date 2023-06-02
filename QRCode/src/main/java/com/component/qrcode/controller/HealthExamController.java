package com.component.qrcode.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;

/**
 * @Author ZHZJ
 * @create 2022/11/7 15:09
 */
@RestController
@RequestMapping("/health/check")
public class HealthExamController {
    @RequestMapping(value = "/status", method = {RequestMethod.HEAD, RequestMethod.GET})
    public boolean checkStatus(HttpServletRequest request, HttpServletResponse response) {
        String ipAddresses = request.getHeader("X-Forwarded-For");
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        System.out.println(ip);
        if("".equals(ip)){

        }
        try {
            String jsonFile = readJsonFile("/home/zzj/bin/test.json");
//            String jsonFile = readJsonFile("F:\\data\\test.json");
            JSONObject jsonObject = JSON.parseObject(jsonFile);
            Integer interval =Integer.valueOf(jsonObject.getString("interval"));
            Boolean sqlCheckFlag =Boolean.valueOf(jsonObject.getString("sqlCheckFlag"));
              if(sqlCheckFlag){
                 System.out.println(sqlCheckFlag);
              }
            return true;
        } catch (Exception e) {
            return true;
        }
    }
    public static String readJsonFile(String jsonFilePath) {
        String jsonStr = "";
        try {
            File jsonFile = new File(jsonFilePath);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }

            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

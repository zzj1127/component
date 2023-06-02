package com.component.qrcode.controller;

import com.component.qrcode.util.QrCodeUtils;
import com.component.qrcode.util.ReadCodeUtils;
import com.google.zxing.NotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/QRCode")
public class QRCodeController {
    //生成二维码
    @RequestMapping("/createCode")
    public void createCode(String content, HttpServletRequest request, HttpServletResponse response) {
        StringBuffer url = request.getRequestURL();
        try {
            OutputStream os = response.getOutputStream();
            String context ="username：zzj;password：zhang12589";
            //从配置文件读取需要生成二维码的连接
//            String requestUrl = GraphUtils.getProperties("requestUrl");
            //requestUrl:需要生成二维码的连接，logoPath：内嵌图片的路径，os：响应输出流，needCompress:是否压缩内嵌的图片
            QrCodeUtils.encode(context,"","zzj", os, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //识别二维码
    @RequestMapping("identifyCode")
    public Map identifyCode(String path) throws NotFoundException, IOException {
//        path = "C:\\Users\\Windows\\Desktop\\111.jpg";
//        File file = new File(path);
//        BufferedImage image = ImageIO.read(file);
//        Map map = ReadCodeUtils.identifyCode(image);
        Map map = new HashMap();

        for(int a=0;a<1000000;a++){
            System.out.println("1");
            map.put("aa",a);
        }
        return map;
    }
    public static String generatePassword (int length) {
        // 最终生成的密码
        String password = "";
        Random random = new Random();
        for (int i = 0; i < length; i ++) {
            // 随机生成0或1，用来确定是当前使用数字还是字母 (0则输出数字，1则输出字母)
            int charOrNum = random.nextInt(2);
            if (charOrNum == 1) {
                // 随机生成0或1，用来判断是大写字母还是小写字母 (0则输出小写字母，1则输出大写字母)
                int temp = random.nextInt(2) == 1 ? 65 : 97;
                password += (char) (random.nextInt(26) + temp);
            } else {
                // 生成随机数字
                password += random.nextInt(10);
            }
        }
        return password;
    }

}

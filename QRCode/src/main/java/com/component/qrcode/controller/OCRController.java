package com.component.qrcode.controller;

import com.component.qrcode.util.QrCodeUtils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;

@RestController
@RequestMapping("/OCR")
public class OCRController {
    @RequestMapping("/doOCRFromFile")
    public void createCode(String content, HttpServletRequest request, HttpServletResponse response) {
//        String path = "C:\\Users\\Windows\\Desktop\\Capture001.png";
//
//
//        File file = new File(path);
//        ITesseract instance = new Tesseract();
//
//        //设置训练库的位置
//        instance.setDatapath("F:\\testApp");
//
//        //chi_sim ：简体中文， eng    根据需求选择语言库
//        instance.setLanguage("chi_sim");
//        String result = null;
//        try {
//            long startTime = System.currentTimeMillis();
//            result =  instance.doOCR(file);
//            long endTime = System.currentTimeMillis();
//            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("result: ");
//        System.out.println(result);


    }
}

package com.component.qrcode.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class OCRUtils {
    public static String doOCRFromFile(File imageFile, String lang) throws Exception {
        ITesseract instance = new Tesseract();
        instance.setDatapath("F:\\testApp"); //指定语言库目录
        instance.setTessVariable("user_defined_dpi", "300");
        instance.setLanguage(lang);
        String result = instance.doOCR(imageFile);
        return result;
    }
}

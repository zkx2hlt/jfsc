package com.cntomrrow.project.modules.utils;

import java.util.Random;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午6:53
 */


public class Tools {
    private static final String BASIC = "123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

    public static String randomNumber(int  num){
        char[] basicArray = BASIC.toCharArray();
        Random random = new Random();
        char[] result = new char[num];
        for (int i = 0; i < result.length; i++) {
            int index = random.nextInt(100) % (basicArray.length);
            result[i] = basicArray[index];
        }
        return String.valueOf(result);
    }

}

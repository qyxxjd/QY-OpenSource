package com.qy.main.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

public class AssetsUtil
{
	/**
	 * 读取assets下的文本文件
	 * @param asset 文件名称
	 * @param context
	 * @return
	 */
	public static String getAssetString(String asset, Context context) {  
        BufferedReader bufferedReader = null;  
        try {  
            bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(asset)));  
            String line = null;  
            StringBuilder builder = new StringBuilder();  
            while (null != (line = bufferedReader.readLine())) {  
                builder.append(line).append("\n");  
            }  
            return builder.toString();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            if (null != bufferedReader) {  
                try {  
                    bufferedReader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            bufferedReader = null;  
        }  
        return "";  
    }
}

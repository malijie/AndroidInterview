package com.android.interview.opt_memery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.interview.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 对比String +字符串拼接与使用StringBuilder之间的性能差距
 * Created by malijie on 2017/11/13.
 */

public class StringBuilderVsStringActivity extends Activity{
    private int column = 100;
    private int row = 200;
    private int[][] matrix = new int[row][column];
    String result = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        init();

//        result = testAddString();
        result = testStringBuilder();
        Log.d("MLJ","result=" + result);
    }

    private String testAddString() {
        String temp = "";
        Log.d("MLJ","string add begin");
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                temp = temp + matrix[i][j] ;
                temp = temp + ",";
            }
        }
        return temp;
    }

    private String testStringBuilder(){
        StringBuilder sb = new StringBuilder();
        Log.d("MLJ","stringBuilder begin");
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                sb = sb.append(matrix[i][j]) ;
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private void init() {
        Random random = new Random();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                matrix[i][j] = random.nextInt(10);
            }
        }
    }
}

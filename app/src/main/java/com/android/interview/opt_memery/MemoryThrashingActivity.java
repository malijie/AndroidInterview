package com.android.interview.opt_memery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.interview.R;

import java.util.Random;

/**
 * Created by malijie on 2017/11/16.
 */

public class MemoryThrashingActivity extends Activity{
    private static final int length = 99999;
    private static final int column = 100;

    private int[][] test = new int[][]{};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_thrashing);

        Random r = new Random();
        String str[] = new String [length];
        for(int i=0;i<column;i++){
            for(int j=0;j<length;j++){
                str[j] = String.valueOf(r.nextInt());
            }
        }
    }
}

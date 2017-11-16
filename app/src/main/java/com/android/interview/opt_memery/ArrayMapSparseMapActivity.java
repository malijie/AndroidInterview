package com.android.interview.opt_memery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import com.android.interview.R;

import java.util.HashMap;

/**
 * 内存优化，ArrayMap与SparseMap对比HashMap
 * 结论：
 * 1. 查找效率上，只要不是倒序查找，sparseMap与hashMap差不多
 * 2. 内存上，sparseMap比hashmap要节省一些
 * Created by malijie on 2017/11/13.
 */

public class ArrayMapSparseMapActivity extends Activity{
    private static int SIZE = 99999;
    private SparseArray<String> sparseArray = new SparseArray<>();
    private ArrayMap arrayMap = new ArrayMap();
    private HashMap<Integer,String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sparse_array);
        Log.d("MLJ","begin init");

        initHashMap();
        Log.d("MLJ","initHashMap finish");

//      initSparseArray();
//      Log.d("MLJ","initSparseArray finish");

//        initArrayMap();
//      Log.d("MLJ","initArrayMap finish");


    }

    private void initHashMap() {
        Log.d("MLJ","begin init HashMap");
        for(int i=0;i<SIZE;i++){
            hashMap.put(i,i+"");
        }
        Log.d("MLJ","end hash map init,memory=" + Runtime.getRuntime().totalMemory());
    }

    private void initSparseArray() {
        Log.d("MLJ","begin init SparseArray");
        for(int i=0;i<SIZE;i++){
            sparseArray.put(i,i+"");
        }
        Log.d("MLJ","end sparse array init,memory=" + Runtime.getRuntime().totalMemory());
    }

    private void initArrayMap(){
        Log.d("MLJ","begin init ArrayMap");
        for(int i=0;i<SIZE;i++){
            arrayMap.put(i,i+"");
        }
        Log.d("MLJ","array map init,memory=" + Runtime.getRuntime().totalMemory());

    }
}

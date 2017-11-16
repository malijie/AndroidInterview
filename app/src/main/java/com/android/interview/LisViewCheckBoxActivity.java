package com.android.interview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.interview.bean.TestInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by malijie on 2017/10/19.
 */

public class LisViewCheckBoxActivity extends Activity{
    private ListView lv = null;
    private List<TestInfo> testInfos = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_cb);

        lv = findViewById(R.id.id_lv);
        for(int i=0;i<20;i++){
            TestInfo testInfo = new TestInfo();
            testInfo.setChecked(false);
            testInfo.setIndex(i);
            testInfos.add(testInfo);
        }

        lv.setAdapter(new CBAdapter(testInfos,this));
    }

    private List<Map<String,Boolean>> checkMapList = new ArrayList<>();

    private class CBAdapter extends BaseAdapter{

        private List<TestInfo> testInfoList = new ArrayList<>();
        private LayoutInflater inflater = null;
        private Context mContext = null;

        public CBAdapter(List<TestInfo> testInfos,Context context){
            this.testInfoList = testInfos;
            this.mContext = context;
            inflater = LayoutInflater.from(context);

            for(int i=0;i<testInfoList.size();i++){
                Map<String,Boolean> checkMap = new HashMap<>();
                checkMap.put(i + "",testInfoList.get(i).isChecked());
                checkMapList.add(checkMap);
            }
        }
        @Override
        public int getCount() {
            return testInfoList.size();
        }

        @Override
        public Object getItem(int i) {
            return testInfoList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.lv_item,null);
                holder.cb = convertView.findViewById(R.id.item_cb);
                holder.text = convertView.findViewById(R.id.item_text);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    Map<String,Boolean> checkMap = checkMapList.get(position);
                        checkMap.put("" + position,isChecked);
                }
            });

            holder.cb.setChecked(checkMapList.get(position).get("" + position));
            holder.text.setText(position + "");

            Log.d("MLJ","checkMapList=" + checkMapList);
            return convertView;
        }

        protected class ViewHolder{
            public CheckBox cb = null;
            public TextView text = null;
        }

    }
}

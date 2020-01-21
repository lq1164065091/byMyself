package lq.studay.day03;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lq.studay.R;

public class Main3Activity extends Activity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_demo01);
        List<Map<String, ?>> data_ = DataConverter.ObjToDataMap(Arrays.asList(
                new DataObj("1","4",getDataDir().getPath()+"/test.jpg"),
                new DataObj("1","5",getDataDir().getPath()+"/test.jpg"),
                new DataObj("1","6",getDataDir().getPath()+"/test.jpg")),
                DataObj.class);

        System.out.println(new DataObj("1", "6", getDataDir().getPath() + "test.jpg").toString());

        BaseAdapter baseAdapter = new BaseAdapter(getApplicationContext(), R.layout.item_rv_demo01,data_, new String[]{"tv1","tv2","iv1"}, new int[]{R.id.tv_demo01,R.id.tv_demo02,R.id.iv_demo01},BaseAdapter.BIND_BY_FILE_PATH);
        baseAdapter.setOnClickEvent(new ClickEvent(),R.id.tv_demo01);
        recyclerView.setAdapter(baseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));





    }




    class ClickEvent implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            System.out.println("123");
        }
    }


}







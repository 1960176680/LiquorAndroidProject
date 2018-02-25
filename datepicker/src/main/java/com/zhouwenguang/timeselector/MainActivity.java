package com.zhouwenguang.timeselector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhouwenguang.timeselector.widget.TimeSelector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private String currentDate;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.button);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeSelector timeSelector = new TimeSelector(MainActivity.this, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        currentDate=time;
                        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                        try {
//                            Date Adate=format.parse(currentDate);
                            Date Bdate=format.parse(time);
//                    int startAndEnd= Util.getGapCount(Bdate,new Date());
//                    if (startAndEnd>15){
//                        ToastTools.showLazzToast("最多只能查询15天前的记录");
//                        return;
//                    }
                            button.setText(time);
//                            queryUploadData(time);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },"2017-01-01 00:00", "2022-12-31 00:00");

                timeSelector.show();
            }
        });
    }
}

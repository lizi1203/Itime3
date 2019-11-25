package com.example.itime;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;

import static com.example.itime.MainActivity.RESULT_UPDATE;

public class TimeAllActivity extends AppCompatActivity {
    Button buttonReturn;
    Button buttonDelete;
    Button buttonShare;
    Button buttonUpdate;
    Context context=this;
    TextView titleTextView2,descriptionTextView2;
    TextView datetextview,counttextview;
    private MyCount mc;

    int count;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 3:
                if (resultCode == RESULT_OK) {
                    int position = data.getIntExtra("position", 0);
                    String title = data.getStringExtra("title");
                    String description = data.getStringExtra("description");
                    titleTextView2.setText(title);
                    descriptionTextView2.setText(description);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_all);

        buttonReturn=findViewById(R.id.button_return);
        buttonDelete=findViewById(R.id.button_delete);
        buttonShare=findViewById(R.id.button_share);
        buttonUpdate=findViewById(R.id.button_update);
        titleTextView2=findViewById(R.id.title_text_view2);
        descriptionTextView2=findViewById(R.id.description_text_view2);
        datetextview=findViewById(R.id.data_text_view);
        counttextview=findViewById(R.id.count_text_view);

        final Intent intent=getIntent();
        final String title=intent.getStringExtra("Title");
        String date=intent.getStringExtra("Date");
        final String description=intent.getStringExtra("Description");
        final int position=intent.getIntExtra("position", 0);
        int count=intent.getIntExtra("count", 0);

        titleTextView2.setText(title);
        descriptionTextView2.setText(description);
        datetextview.setText(date);

        mc = new MyCount(count*1000, 1000);

        mc.start();
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent();
                intent.putExtra("title", titleTextView2.getText().toString().trim());
                intent.putExtra("description", descriptionTextView2.getText().toString().trim());
                intent.putExtra("position", position);
                setResult(RESULT_UPDATE, intent);
                finish();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(context)
                        .setMessage("Delete this moment?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent1=new Intent();
                                intent1.putExtra("position", position);
                                setResult(RESULT_OK, intent1);
                                TimeAllActivity.this.finish();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(TimeAllActivity.this,CreatNewActivity.class);
                intent2.putExtra("title", title);
                intent2.putExtra("description", description);
                startActivityForResult(intent2,3);
            }
        });


    }

    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override

        public void onFinish() {
            counttextview.setText("finish");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            long days=millisUntilFinished / (1000 * 60 * 60 * 24 );
            long hours=millisUntilFinished / (1000*60*60) -days*24;
            long minutes=millisUntilFinished / (1000*60)-days*24*60-hours*60;
            long seconds=millisUntilFinished / (1000)-days*24*60*60-hours*60*60-minutes*60;
            counttextview.setText( days+ " DAYS "+
                    +hours + " HRS "+minutes+" MNIS "+seconds+" SECS");

            }
    }

}

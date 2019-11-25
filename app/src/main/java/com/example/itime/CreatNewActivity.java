package com.example.itime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CreatNewActivity extends AppCompatActivity {
    ListView listView;
    private ArrayList<ChooseItem> chooseItems;
    Button buttonReturn;
    Button buttonOk;
    EditText editTitle;
    EditText editDescription;
    ChooseAdapter chooseAdapter;
    EditText editTextTitle;
    EditText editTextDescription;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_new);
        buttonReturn=findViewById(R.id.button_return);
        buttonOk=findViewById(R.id.button_ok);
        editTitle=findViewById(R.id.edit_text_title);
        editDescription=findViewById(R.id.edit_text_description);
        listView=findViewById(R.id.list_view_choose);
        editTitle=findViewById(R.id.edit_text_title);
        editDescription=findViewById(R.id.edit_text_description);
        imageView=findViewById(R.id.image2);

        Init();
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String description=intent.getStringExtra("description");
        final int position=intent.getIntExtra("position",0);
        if(title!=null) {
            editTitle.setText(title);
        }
        if(description!=null) {
            editDescription.setText(description);
        }

        chooseAdapter = new ChooseAdapter(CreatNewActivity.this, R.layout.choose_item, chooseItems);
        listView.setAdapter(chooseAdapter);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("title", editTitle.getText().toString().trim());
                intent.putExtra("description", editDescription.getText().toString().trim());
                intent.putExtra("position", position);
                setResult(RESULT_OK, intent);
                Log.d("bookTitle", editTitle.getText().toString());
                CreatNewActivity.this.finish();
            }
        });
    }


    private void Init()
    {
        chooseItems=new ArrayList<>();
        chooseItems.add(new ChooseItem("Date  ", "Long press to use calculator",
                R.drawable.calendar_icon));
        chooseItems.add(new ChooseItem("Period","None                      ",R.drawable.period_icon));
        chooseItems.add(new ChooseItem("Image ","                          ",R.drawable.image_icon));
        chooseItems.add(new ChooseItem("Stick ","                          ",R.drawable.stick_icon));
    }
}
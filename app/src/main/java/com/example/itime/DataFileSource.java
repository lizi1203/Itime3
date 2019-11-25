package com.example.itime;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataFileSource {
    Context context;

    public DataFileSource(Context context) {
        this.context = context;
    }

    public ArrayList<TimeItem> getItems() {
        return items;
    }

    private ArrayList<TimeItem> items = new ArrayList<>();

    public void save() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream
                    (context.openFileOutput("serializable.txt", Context.MODE_PRIVATE));
            outputStream.writeObject(items);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TimeItem> load() {

        try {
            ObjectInputStream inputStream = new ObjectInputStream
                    (context.openFileInput("serializable.txt"));
            items = (ArrayList<TimeItem>) inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}

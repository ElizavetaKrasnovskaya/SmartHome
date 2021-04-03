package com.bsuir.smarthome.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsuir.smarthome.R;
import com.bsuir.smarthome.adapter.RoomAdapter;
import com.bsuir.smarthome.adapter.SingleRoomAdapter;
import com.bsuir.smarthome.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailsActivity extends AppCompatActivity {

    private List<Room> roomList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SingleRoomAdapter mAdapter;
    private Room room = RoomAdapter.getSelectedRoom();
    private TextView temperatureTextView;
    private TextView humidityTextView;
    private TextView name;

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new SingleRoomAdapter(roomList, getApplicationContext(), room);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        temperatureTextView = findViewById(R.id.temperature);
        humidityTextView = findViewById(R.id.humidity);
        name = findViewById(R.id.name);
        name.setText(room.getName());
        temperatureTextView.setText(room.getTemperature().toString());
        humidityTextView.setText(room.getHumidity().toString());

        prepareRoomData();

    }

    private void prepareRoomData() {
        Room room = new Room("Light");
        roomList.add(room);
//        room = new Room("2", "Fan");
//        roomList.add(room);
//        room = new Room("1", "Air Conditioner");
//        roomList.add(room);
//        room = new Room("2", "Table Light");
//        roomList.add(room);
//        room = new Room("1", "Stand Fan");
//        roomList.add(room);
//        room = new Room("2", "Footer Light");
//        roomList.add(room);
//        room = new Room("1", "TV");
//        roomList.add(room);

        mAdapter.notifyDataSetChanged();
    }

    public void onBackClicked(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}

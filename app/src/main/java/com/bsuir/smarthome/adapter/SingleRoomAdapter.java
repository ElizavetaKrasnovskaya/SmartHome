package com.bsuir.smarthome.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bsuir.smarthome.R;
import com.bsuir.smarthome.model.Room;
import com.bsuir.smarthome.utils.SwitchButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SingleRoomAdapter extends RecyclerView.Adapter<SingleRoomAdapter.MyViewHolder> {

    Context context;
    private List<Room> roomList;
    private Room roomOfFlat;
    private DatabaseReference ref;

    public SingleRoomAdapter(List<Room> roomList, Context context, Room roomOfFlat) {
        this.roomList = roomList;
        this.context = context;
        this.roomOfFlat = roomOfFlat;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_room_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.title.setText(room.getName());
        if(roomOfFlat.getLed() == 1){
            holder.switchButton.setChecked(true);
        }
        holder.switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(roomOfFlat.getLed() == 0){
                    roomOfFlat.setLed((long) 1);
                }else{
                    roomOfFlat.setLed((long)0);
                }
                    ref = FirebaseDatabase.getInstance().getReference();
                    ref.child(roomOfFlat.getName()).child("led").setValue(roomOfFlat.getLed());
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public CardView cardView;
        public SwitchButton switchButton;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            switchButton = view.findViewById(R.id.checkbox);
            cardView = view.findViewById(R.id.card_view);
        }
    }


}
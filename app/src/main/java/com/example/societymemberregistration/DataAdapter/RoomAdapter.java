package com.example.societymemberregistration.DataAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.societymemberregistration.R;
import com.example.societymemberregistration.dataModel.Block;
import com.example.societymemberregistration.dataModel.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    public interface OnItemClickListener {
        void OnItemClick(Block model,int pos);
    }

    Context context;
    List<Room> roomList;

    public RoomAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_recyclerview_item, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Room room = roomList.get(position);
        holder.roomNameTextView.setText(room.getRoomName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked On " + room.getRoomName() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }



    public class RoomViewHolder extends RecyclerView.ViewHolder{

        TextView roomNameTextView;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomNameTextView = itemView.findViewById(R.id.txtRoom);
        }
    }
}

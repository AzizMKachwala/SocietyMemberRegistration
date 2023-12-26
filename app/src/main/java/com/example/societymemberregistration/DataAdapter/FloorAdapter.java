package com.example.societymemberregistration.DataAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.societymemberregistration.R;
import com.example.societymemberregistration.dataModel.Floor;
import com.example.societymemberregistration.dataModel.Room;

import java.util.List;

public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.FloorViewHolder> {

    Context context;
    private List<Floor> floorList;

    public FloorAdapter(Context context, List<Floor> floorList) {
        this.context = context;
        this.floorList = floorList;
    }

    @NonNull
    @Override
    public FloorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.floor_recyclerview_item, parent, false);
        return new FloorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FloorViewHolder holder, int position) {
        Floor floor = floorList.get(position);
        holder.floorNameTextView.setText(floor.getFloorName());

        RoomAdapter roomAdapter = new RoomAdapter(context, floor.getRooms());
        holder.roomRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        holder.roomRecyclerView.setAdapter(roomAdapter);
    }

    @Override
    public int getItemCount() {
        return floorList.size();
    }

    public class FloorViewHolder extends RecyclerView.ViewHolder{

        TextView floorNameTextView;
        RecyclerView roomRecyclerView;

        public FloorViewHolder(@NonNull View itemView) {
            super(itemView);

            floorNameTextView = itemView.findViewById(R.id.txtFloor);
            roomRecyclerView = itemView.findViewById(R.id.roomRecyclerView);
        }
    }
    public void updateData(List<Floor> newFloorList) {
        floorList.clear();
        floorList.addAll(newFloorList);
        notifyDataSetChanged();
    }
}

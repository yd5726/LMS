package com.example.lms_kmj.tt_recv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms_kmj.R;

import java.util.ArrayList;

public class TTAdapter extends RecyclerView.Adapter<TTAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<TT_DTO> list;

    public TTAdapter(LayoutInflater inflater, ArrayList<TT_DTO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_tt_recv, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        for(int i=0; i<list.size(); i++) {
            TT_DTO tt_dto = list.get(i);
            String date = tt_dto.getDate();
            String time = tt_dto.getTime();
            String context = tt_dto.getContext();
            holder.tt_recv_date.setText(date);
            holder.tt_recv_time.setText(time);
            holder.tt_recv_content.setText(context);
        }
    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tt_recv_date, tt_recv_time, tt_recv_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tt_recv_date = itemView.findViewById(R.id.tt_recv_date);
            tt_recv_time = itemView.findViewById(R.id.tt_recv_time);
            tt_recv_content = itemView.findViewById(R.id.tt_recv_content);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}

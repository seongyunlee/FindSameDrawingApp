package edu.skku.cs.findsamedrawing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.skku.cs.findsamedrawing.Model.Record;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private ArrayList<Record> records= new ArrayList<>();
    private RankingViewModel viewModel;


    public RankingAdapter(RankingViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_record,parent,false);
        RankingAdapter.ViewHolder vh =  new RankingAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_rank.setText(Integer.toString(position+1));
        holder.tv_time.setText(records.get(position).time);
        holder.tv_name.setText(records.get(position).name);


    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rank;
        TextView tv_name;
        TextView tv_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_rank=itemView.findViewById(R.id.tv_rank);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_time=itemView.findViewById(R.id.tv_time);
        }
    }
    public void setRecords(ArrayList<Record> records){
        this.records=records;
        notifyDataSetChanged();
    }
}

package com.example.piyal.spycalculator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by piyal on 12/29/2017.
 */
public class MemberView extends RecyclerView.Adapter<MemberView.RecyclerViewHolder> {
    List<SmsModel> mealTableModellist;
    Context context;

    public void setOnItemClickListner(OnItemClickListner listner) {
        this.listner = listner;
    }

    private OnItemClickListner listner;

    public interface OnItemClickListner{

        void OnItemClick(int position);
        void OnItemEdit(int position);
        void OnItemdelete(int position);

    }


    public MemberView(Context context, List<SmsModel> mealTableModellist) {
        this.mealTableModellist = mealTableModellist;
        this.context = context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.member_view,null);
        return new RecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        StringBuilder sb = new StringBuilder();
        sb.append("Contact Name: " + mealTableModellist.get(position).getCalltime());
        sb.append(System.getProperty("line.separator"));
        sb.append("Phone number: " + mealTableModellist.get(position).getPhone_number());
        sb.append(System.getProperty("line.separator"));
        sb.append("Call duration: " + mealTableModellist.get(position).getCall_duration()+".00 min(s)");
        sb.append(System.getProperty("line.separator"));
        sb.append("Call type: " + mealTableModellist.get(position).getCalltype());
        sb.append(System.getProperty("line.separator"));
        sb.append("Call date: " + new Date(Long.valueOf(mealTableModellist.get(position).getCalldate())));
        holder.nametx.setText(sb.toString());
    }
    @Override
    public int getItemCount() {
        return  mealTableModellist.size();
    }
    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView nametx;
        ImageView edit;
        ImageView delete;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nametx=itemView.findViewById(R.id.nametx);
        }
    }
}

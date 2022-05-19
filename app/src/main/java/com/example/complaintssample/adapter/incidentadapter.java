package com.example.complaintssample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.complaintssample.R;
import com.example.complaintssample.domain.addtoreportdomain;

import java.util.List;

public class incidentadapter extends RecyclerView.Adapter<incidentadapter.incidentViewHolder> {
	Context context;
	List<addtoreportdomain> list;

	public incidentadapter(Context context, List<addtoreportdomain> list) {
		this.context = context;
		this.list = list;
	}

	@NonNull
	@Override
	public incidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view= LayoutInflater.from(context).inflate(R.layout.incident,parent,false);
		return new incidentViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull incidentViewHolder holder, int position) {
		holder.type.setText(list.get(position).getType());
		holder.date.setText(list.get(position).getDate());
		holder.recommend.setText(list.get(position).getRecommendations());
		holder.steps.setText(list.get(position).getSteps());
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	public  final class  incidentViewHolder extends RecyclerView.ViewHolder{
		public TextView type,date,recommend,steps;

		public incidentViewHolder(@NonNull View itemView) {
			super(itemView);
			type=itemView.findViewById(R.id.intype);
			date=itemView.findViewById(R.id.indate);
			recommend=itemView.findViewById(R.id.inrecommend);
			steps=itemView.findViewById(R.id.instep);
		}
	}
}

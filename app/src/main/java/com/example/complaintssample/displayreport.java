package com.example.complaintssample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.complaintssample.adapter.incidentadapter;
import com.example.complaintssample.domain.addtoreportdomain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class displayreport extends AppCompatActivity {
	RecyclerView recyclerView;
	incidentadapter adapter;
	DatabaseReference reference;
	List<addtoreportdomain> mylist;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_displayreport);

		recyclerView=findViewById(R.id.incidentrecycer);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		reference= FirebaseDatabase.getInstance().getReference("Incidents").child("bets");
		mylist=new ArrayList<>();
		eventchange();

	}

	public void  eventchange (){
		reference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				for (DataSnapshot dataSnapshot :snapshot.getChildren()){
					addtoreportdomain domain=dataSnapshot.getValue(addtoreportdomain.class);
					mylist.add(domain);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
		adapter=new incidentadapter(displayreport.this,mylist);
		recyclerView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

}
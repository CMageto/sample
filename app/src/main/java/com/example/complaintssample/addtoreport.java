package com.example.complaintssample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class addtoreport extends AppCompatActivity {
	EditText type,steps,recommend;
	public String itype,isteps,irecommend,saveCurrentDate,saveCurrentTime,RandomKey;
	Button submit;
	ProgressDialog loadingBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addtoreport);
		submit=findViewById(R.id.submit);
		type=findViewById(R.id.type);
		steps=findViewById(R.id.steps);
		recommend=findViewById(R.id.recommend);

		loadingBar=new ProgressDialog(this);
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				validateData();
			}
		});

	}
	public void validateData(){
		itype=type.getText().toString();
		isteps=steps.getText().toString();
		irecommend=recommend.getText().toString();

		if (TextUtils.isEmpty(itype)){
			Toast.makeText(this, "incident type is needed", Toast.LENGTH_SHORT).show();
		}
		else if (TextUtils.isEmpty(isteps)){
			Toast.makeText(this, "steps taken to resolve is needed", Toast.LENGTH_SHORT).show();
		}
		else if (TextUtils.isEmpty(irecommend)){
			Toast.makeText(this, "recommendation is needed", Toast.LENGTH_SHORT).show();
		}
		else {
			storeData();
		}
	}
	public void storeData(){
		loadingBar.setTitle("Adding the data");
		loadingBar.setMessage("please wait as we add the data");
		loadingBar.setCanceledOnTouchOutside(false);
		loadingBar.show();
		Calendar calendar = Calendar.getInstance();
		DatabaseReference reference;

		SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
		saveCurrentDate = currentDate.format(calendar.getTime());

		SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
		saveCurrentTime = currentTime.format(calendar.getTime());

		RandomKey = saveCurrentDate + saveCurrentTime;
		HashMap <String,Object> map=new HashMap<>();
		map.put("type",itype);
		map.put("steps",isteps);
		map.put("recommendations",irecommend);
		map.put("date",RandomKey);
		reference= FirebaseDatabase.getInstance().getReference().child("Incidents");
		reference.child(itype).child(RandomKey).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
			@Override
			public void onComplete(@NonNull Task<Void> task) {
				if (task.isSuccessful()){
					loadingBar.dismiss();
					Toast.makeText(addtoreport.this, "detalis is added successfully", Toast.LENGTH_SHORT).show();
					startActivity(new Intent(addtoreport.this,MainActivity.class));
				}
				else {
					loadingBar.dismiss();
					String message= task.getException().toString();
					Toast.makeText(addtoreport.this, "Error:"+message, Toast.LENGTH_SHORT).show();
					startActivity(new Intent(addtoreport.this,MainActivity.class));
				}
			}
		});
	}

}
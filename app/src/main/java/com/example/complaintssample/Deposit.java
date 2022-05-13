package com.example.complaintssample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.complaintssample.domain.persondetails;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Deposit extends AppCompatActivity {
	EditText phone,code;
	Button check;
	FirebaseDatabase db;
	DatabaseReference reference;
	String pphone,ccode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_deposit);
		phone=findViewById(R.id.depositphone);
		code=findViewById(R.id.depositcode);
		check=findViewById(R.id.depositbutton);

		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				db=FirebaseDatabase.getInstance();
				reference=db.getReference("Deposit");

				//store data

				persondetails details= new persondetails(pphone,ccode);


			}
		});


	}
	public void validate(){
		pphone=phone.getText().toString().trim();
		ccode=code.getText().toString().trim();
		reference=FirebaseDatabase.getInstance().getReference("deposit");
		Query check =reference.orderByChild("phone").equalTo(pphone);
		check.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				if (snapshot.exists()){
					String codee= snapshot.child("phone").child("code").getValue(String.class);
					if (codee.equals(ccode)){
						//the data exists


					}
					else {
						//code error
					}
				}
				else {
					//phone incorrect
				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}
}
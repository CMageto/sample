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
	ProgressDialog loadingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_deposit);
		phone=findViewById(R.id.depositphone);
		code=findViewById(R.id.depositcode);
		check=findViewById(R.id.depositbutton);
		loadingBar = new ProgressDialog(this);

		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				db=FirebaseDatabase.getInstance();
				reference=db.getReference("Deposit");
				validate();
			}
		});
	}
	public void validate(){
		pphone=phone.getText().toString().trim();
		ccode=code.getText().toString().trim();

		if(TextUtils.isEmpty(pphone)){
			Toast.makeText(this, "Please enter the phone number...", Toast.LENGTH_SHORT).show();
		} else if (TextUtils.isEmpty(ccode)) {
			Toast.makeText(this, "Please enter your code...", Toast.LENGTH_SHORT).show();
		}
		else
		{
			loadingBar.setTitle("Checking details");
			loadingBar.setMessage("Please wait, while we are checking the details.");
			loadingBar.setCanceledOnTouchOutside(false);
			loadingBar.show();
			checkdetails(pphone,ccode);
		}
	}
	private void checkdetails(final String pphone, final String ccode)
	{


		final DatabaseReference reference1;
		reference1 = FirebaseDatabase.getInstance().getReference();
		reference1.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				if (dataSnapshot.child("deposits").child(pphone).exists()){

					persondetails details= dataSnapshot.child("deposits").child(pphone).getValue(persondetails.class);

					if (details.getPhone().equals(pphone))
					{
						if (details.getCode().equals(ccode))
						{
							Toast.makeText(Deposit.this, "details exist...", Toast.LENGTH_SHORT).show();
							loadingBar.dismiss();
						}
						else {
							Toast.makeText(Deposit.this, "The code does not exist", Toast.LENGTH_SHORT).show();
							loadingBar.dismiss();
						}
					}
				}
				else {
					Toast.makeText(Deposit.this, "Phone number doesnt exist", Toast.LENGTH_SHORT).show();
					loadingBar.dismiss();
				}
			}
			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
	}
}
package com.example.complaintssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	Button deposit,withdraw,bets,generate;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		deposit=findViewById(R.id.deposit);
		withdraw=findViewById(R.id.withdraw);
		bets=findViewById(R.id.bets);
		textView=findViewById(R.id.iadd);
		generate=findViewById(R.id.button);


		deposit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, Deposit.class));
			}
		});
		withdraw.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
					startActivity(new Intent(MainActivity.this,withdraw.class));
			}
		});
		bets.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
					startActivity(new Intent(MainActivity.this,bets.class));
			}
		});
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this,addtoreport.class));
			}
		});
		generate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this,displayreport.class));
			}
		});


	}
}
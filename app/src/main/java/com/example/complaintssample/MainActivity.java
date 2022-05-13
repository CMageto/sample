package com.example.complaintssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
	Button deposit,withdraw,bets;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		deposit=findViewById(R.id.deposit);
		withdraw=findViewById(R.id.withdraw);
		bets=findViewById(R.id.bets);


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


	}
}
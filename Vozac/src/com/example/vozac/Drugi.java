package com.example.vozac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Drugi extends Activity {

	private String username = null;
	private String password = null;
	private String idKorisnika = null;
	private String idVozila = null;
	private String idLinija = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drugi);
		
		Intent in = getIntent();
		username = in.getStringExtra("username");
		password = in.getStringExtra("password");
		idKorisnika = in.getStringExtra("idKorisnika");
		idVozila = in.getStringExtra("idVozila");
		idLinija = in.getStringExtra("idLinija");
		
		final Button kvar = (Button) findViewById (R.id.kvar);
		final Button odjava = (Button) findViewById (R.id.odjava);
		final Button smjer = (Button) findViewById (R.id.smjer);
		final TextView user = (TextView) findViewById (R.id.username);
		final TextView pass = (TextView) findViewById (R.id.password);
		
		user.setText(username);
		pass.setText(password);

        kvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent (Drugi.this, Kvar.class);
				startActivity(i);	
			}
		});
        
        odjava.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent (Drugi.this, Login.class);
				startActivity(i);
			}
		});
		
        smjer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.print("Promjena smjera");
			}
		});
		
		
	}
}

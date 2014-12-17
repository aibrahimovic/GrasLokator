package com.example.vozac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vozac.AsyncTaskKlase.UcitajVozaca;
import com.example.vozac.Klase.Vozac;

public class Login extends Activity {

	private Vozac vozac;
	public EditText username, sifra;
	public TextView status;
	private static int Status;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        final Button pocni = (Button) findViewById (R.id.prijava);
        username = (EditText) findViewById(R.id.username);
        sifra = (EditText) findViewById(R.id.sifra);
        status = (TextView) findViewById(R.id.textView1);
        final ImageView iv = (ImageView)findViewById(R.id.slika);
        iv.setImageResource(R.drawable.ic_launcher);
    
        vozac = new Vozac();
        vozac.setId(" ");
                
        username.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getStatus().setVisibility(View.GONE);
			}	
        });
        sifra.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getStatus().setVisibility(View.GONE);
			}	
        });

       // UcitajVozaca uv = new UcitajVozaca(this);
        pocni.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				vozac.setUsername(username.getText().toString());
				vozac.setSifra(sifra.getText().toString());
				Log.d("iz Logina", vozac.getUsername());
				
				if (username.getText().toString().equals(" ") || sifra.getText().toString().equals(" ")) {
					status.setVisibility(View.VISIBLE);
				}
				
				//new UcitajVozaca(getApplicationContext()).execute(vozac);
				new UcitajVozaca(Login.this).execute(vozac);
				//uv.execute(vozac);

			}
		});
        
    }
    
    public void postaviStatus () {
    	getStatus().setVisibility(View.VISIBLE);
    }

	public TextView getStatus() {
		return status;
	}
	public void setStatus(TextView status) {
		this.status = status;
	}
}

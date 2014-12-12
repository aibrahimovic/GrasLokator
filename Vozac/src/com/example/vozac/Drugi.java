package com.example.vozac;

import com.example.vozac.AsyncTaskKlase.DeleteVoznja;
import com.example.vozac.AsyncTaskKlase.PostVoznja;
import com.example.vozac.Klase.Voznja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
	private String brojLinije = null;
	private String smjer1 = null;
	private String smjer2 = null;
	private Voznja voznja = new Voznja();
	public TextView trenutni_smjer;
	private String idVoznje = null;
	Postavke p;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drugi);
	
		Intent in = getIntent();
		
		username = in.getStringExtra("username");
		password = in.getStringExtra("password");
		idKorisnika = in.getStringExtra("idKorisnika");
		idVozila = in.getStringExtra("idVozila");
		idVoznje = in.getStringExtra("idVoznje");
		idVoznje = String.valueOf(76);
		idLinija = in.getStringExtra ("idLinija");
		brojLinije = in.getStringExtra("brojLinije");
		smjer1 = in.getStringExtra("smjer1");
		smjer2 = in.getStringExtra("smjer2");
		
		Log.d("username u Drugi", username);
		Log.d("password u Drugi", password);
		Log.d("idKorisnika u Drugi", idKorisnika);
		Log.d("idVoznje u Drugi", idVoznje);
		
		voznja.setUsername(username);
		voznja.setPassword(password);
	
		
		final Button kvar = (Button) findViewById (R.id.kvar);
		final Button odjava = (Button) findViewById (R.id.odjava);
		final Button smjer = (Button) findViewById (R.id.smjer);
		final TextView trenutni_user = (TextView) findViewById (R.id.username);
		final TextView trenutni_broj = (TextView) findViewById (R.id.brojVozila);
		final TextView trenutni_linija = (TextView) findViewById (R.id.brojLinije);
		trenutni_smjer = (TextView) findViewById (R.id.smjerKojiVozi);
		

		trenutni_user.setText("Korisnièko ime: " + username);
		trenutni_broj.setText("Broj vozila: " + idVozila);
		trenutni_linija.setText("Linija: " + brojLinije);
		trenutni_smjer.setText("Smjer: " + smjer1);

		
		final DeleteVoznja dv = new DeleteVoznja (this);
        kvar.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
			dv.execute(username, password, idVoznje);
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
		
        final DeleteVoznja dv2 = new DeleteVoznja(this);
        final PostVoznja pv2 = new PostVoznja(this);
        smjer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				trenutni_smjer.setText("Smjer: " + smjer2);
				String pomocni = smjer2;
				smjer2 = smjer1;
				smjer1 = pomocni;
				
				dv2.execute(username, password, idVoznje);
				
				zapocniVoznju();
				pv2.execute(voznja);
			}
		});
	}
	
	public void zapocniVoznju() {
		voznja.setIdKorisnika(Integer.valueOf(idKorisnika));
		voznja.setIdVozila(Integer.valueOf(idVozila));
		voznja.setIdLinije(Integer.valueOf(idLinija));
		voznja.setBrojLinije(Integer.valueOf(brojLinije));
		voznja.setUsername(username);
		voznja.setPassword(password);
		voznja.setSmjer1(smjer1);
		voznja.setSmjer2(smjer2);
		
		
	}
}

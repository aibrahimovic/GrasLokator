package com.example.vozac;

import com.example.vozac.AsyncTaskKlase.PostVoznja;
import com.example.vozac.AsyncTaskKlase.PutVoznja;
import com.example.vozac.Klase.Voznja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Kvar extends Activity {

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
	private String lat = null;
	private String lon = null;
	private String id1 = null;
	private String id2 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kvar);
		
		Intent in6 = getIntent();
		
		username = in6.getStringExtra("username");
		password = in6.getStringExtra("password");
		idKorisnika = in6.getStringExtra("idKorisnika");
		idVozila = in6.getStringExtra("idVozila");
		idVoznje = in6.getStringExtra("idVoznje");
		idLinija = in6.getStringExtra ("idLinija");
		brojLinije = in6.getStringExtra("brojLinije");
		smjer1 = in6.getStringExtra("smjer1");
		smjer2 = in6.getStringExtra("smjer2");
		lat = in6.getStringExtra("lat");
		lon = in6.getStringExtra("lon");
		id1 = in6.getStringExtra("id1");
		id2 = in6.getStringExtra("id2");
		
		final Kvar aktivnost = this;
		
		final Button kvar = (Button) findViewById (R.id.kvar);
		
		//final PutVoznja putV2 = new PutVoznja (this);
		
		
		final PostVoznja pv3 = new PostVoznja(this);
        kvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				zapocniVoznju();
				pv3.execute(voznja);
				//putV2.execute(username, password, idVoznje, lat, lon);
				
				Intent in7 = new Intent (Kvar.this, Drugi.class);
				
				in7.putExtra("username", username);
				in7.putExtra("password", password);
				in7.putExtra("idKorisnika", idKorisnika);
				in7.putExtra("idVoznje", idVoznje);
				in7.putExtra("idVozila", idVozila);
				in7.putExtra("idLinija", idLinija);
				in7.putExtra("lat", lat);
				in7.putExtra("lon", lon);	
				in7.putExtra("brojLinije", brojLinije);
				in7.putExtra("smjer1", smjer1);
				in7.putExtra("smjer2", smjer2);
				in7.putExtra("status", "kvar");
				in7.putExtra("id1", id1);
				in7.putExtra("id2", id2);

				in7.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				
				Log.d("username u Kvar", username);
				Log.d("password u Kvar", password);
				Log.d("idKorisnika u Kvar", idKorisnika);
				Log.d("idVoznje u Kvar", idVoznje);
				
				startActivity(in7);	
			}
		});
        
        
        
	}
	public void zapocniVoznju() {
		voznja.setIdVoznje(idVoznje);
		voznja.setIdKorisnika(Integer.valueOf(idKorisnika));
		voznja.setIdVozila(Integer.valueOf(idVozila));
		voznja.setIdLinija(String.valueOf(idLinija));	
		voznja.setBrojLinije(Integer.valueOf(brojLinije));
		voznja.setUsername(username);
		voznja.setPassword(password);
		voznja.setSmjer1(smjer1);
		voznja.setSmjer2(smjer2);
		voznja.setLat(lat);
		voznja.setLon(lon);	
		voznja.setId1(id1);
		voznja.setId2(id2);
	}
	
}

package com.example.vozac;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.vozac.AsyncTaskKlase.DeleteVoznja;
import com.example.vozac.AsyncTaskKlase.PostVoznja;
import com.example.vozac.AsyncTaskKlase.PutVoznja;
import com.example.vozac.Klase.Voznja;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
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
	private String lat = null;
	private String lon = null;
	Postavke p;
	private Handler mHandler = new Handler();
	public ArrayList<String> idijevi = new ArrayList<String>();
	public String id1 = null;
	public String id2 = null;
	static private Location mojaLokacija;
	
	Handler handler = new Handler();
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drugi);

		mojaLokacija = dajLokaciju(true);
		lat =  String.valueOf(mojaLokacija.getLatitude());
		lon = String.valueOf(mojaLokacija.getLongitude());

		Intent in = getIntent();	
		Intent in7 = getIntent();
		
		if (in7.getStringExtra("status") == "kvar") {
			username = in7.getStringExtra("username");
			password = in7.getStringExtra("password");
			idKorisnika = in7.getStringExtra("idKorisnika");
			idVozila = in7.getStringExtra("idVozila");
			idVoznje = in7.getStringExtra("idVoznje");
			idLinija = in7.getStringExtra ("idLinija");
			brojLinije = in7.getStringExtra("brojLinije");
			smjer1 = in7.getStringExtra("smjer1");
			smjer2 = in7.getStringExtra("smjer2");
			//lat = in7.getStringExtra("lat");
			//lon = in7.getStringExtra("lon");
			id1 = in7.getStringExtra("id1");
			id2 = in7.getStringExtra("id2");
		}
		
		else {
			username = in.getStringExtra("username");
			password = in.getStringExtra("password");
			idKorisnika = in.getStringExtra("idKorisnika");
			idVozila = in.getStringExtra("idVozila");
			idVoznje = in.getStringExtra("idVoznje");
			idLinija = in.getStringExtra ("idLinija");
			brojLinije = in.getStringExtra("brojLinije");
			smjer1 = in.getStringExtra("smjer1");
			smjer2 = in.getStringExtra("smjer2");
			//lat = in.getStringExtra("lat");
			//lon = in.getStringExtra("lon");
			id1 = in.getStringExtra("id1");
			id2 = in.getStringExtra("id2");
		}
		
		Log.d("idijevi koji su dosli pravi", idLinija);
		Log.d("idijevi koji su dosli", id1);
		Log.d("idijevi koji su dosli", id2);
		
		PutVoznja putV = new PutVoznja (this);
		putV.execute(username, password, idVoznje, lat, lon);
		
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
			Intent in6 = new Intent (Drugi.this, Kvar.class);

			in6.putExtra("username", username);
			in6.putExtra("password", password);
			in6.putExtra("idKorisnika", idKorisnika);
			in6.putExtra("idVoznje", idVoznje);
			in6.putExtra("idVozila", idVozila);
			in6.putExtra("idLinija", idLinija);
			in6.putExtra("lat", lat);
			in6.putExtra("lon", lon);	
			in6.putExtra("brojLinije", brojLinije);
			in6.putExtra("smjer1", smjer1);
			in6.putExtra("smjer2", smjer2);
			in6.putExtra("id1", id1);
			in6.putExtra("id2", id2);

			in6.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(in6);
		
			}
		});
        
        final DeleteVoznja dv3 = new DeleteVoznja (this);
        odjava.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dv3.execute(username, password, idVoznje);
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
				
				idLinija = id2;
				String pomocni2 = id2;
				id2 = id1;
				id1 = pomocni2;
				
				dv2.execute(username, password, idVoznje);
				zapocniVoznju();
				pv2.execute(voznja);
				
				Log.d("trenutni id", idLinija);
			}
		});
	}
	
	public void zapocniVoznju() {
		voznja.setIdVoznje(" ");
		voznja.setIdKorisnika(Integer.valueOf(idKorisnika));
		voznja.setIdVozila(Integer.valueOf(idVozila));
		voznja.setIdLinije(Integer.valueOf(idLinija));
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
	
	public Location dajLokaciju(boolean enabledProvidersOnly) {
		LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		Location location = null;
		List<String> providers = manager.getProviders(enabledProvidersOnly);

		for (String provider : providers) {
			location = manager.getLastKnownLocation(provider);
			if (location != null) {
				mojaLokacija = location;
				Log.d("iz logina lokacija", String.valueOf(mojaLokacija.getLatitude()));
				Log.d("iz logina lokacija", String.valueOf(mojaLokacija.getLongitude()));

				return location;
			}
		}
		return null;
	}
	
}

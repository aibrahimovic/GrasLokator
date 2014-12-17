package com.example.vozac;

import java.util.ArrayList;
import java.util.List;

import com.example.vozac.AsyncTaskKlase.GetBrojVozila;
import com.example.vozac.AsyncTaskKlase.GetLinije;
import com.example.vozac.AsyncTaskKlase.GetSmjer;
import com.example.vozac.AsyncTaskKlase.GetTipovi;
import com.example.vozac.AsyncTaskKlase.PostVoznja;
import com.example.vozac.Klase.Voznja;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Postavke extends Activity {
	
	public static Location mojaLokacija = null;
	
	private String[] brojevi;
	private String[] linije;
	private String[] smjerovi;
	private Login login;
	public Spinner tip, broj, linija, smjer;
	private ArrayList<String> tipovi2 = new ArrayList<String> ();
	public ArrayList<String> idLinije = new ArrayList<String>();
	String[] tipovi = null;
	
	private String username = null;
	private String password = null;
	private String idKorisnika = null;
	private String idVozila = null;
	private String idLinija = null;
	public String brojLinije = null;
	public String smjer1 = null;
	public String smjer2 = null;
	public double lat = 0;
	public double lon = 0;
	
	
	private Voznja voznja = new Voznja();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_postavke);
		
		Intent i = getIntent();
		username = i.getStringExtra("username");
		password = i.getStringExtra("password");
		idKorisnika = i.getStringExtra("idKorisnika");
		
		Log.d("ucitani username", username);
		Log.d("ucitani pass", password);
		Log.d("ucitani id", idKorisnika);
		
		final Button pocni = (Button) findViewById (R.id.vozi);
		tip = (Spinner) findViewById (R.id.tip);
		broj = (Spinner) findViewById (R.id.broj);
		linija = (Spinner) findViewById (R.id.linija);
		smjer = (Spinner) findViewById (R.id.smjer);
		
		final TextView t1 = (TextView) findViewById (R.id.textView1);
		final TextView t2 = (TextView) findViewById (R.id.textView2);
		final TextView t3 = (TextView) findViewById (R.id.textView3);
		final TextView t4 = (TextView) findViewById (R.id.textView4);
		
		final View l1 = (View) findViewById (R.id.linija1);
		final View l2 = (View) findViewById (R.id.linija2);
		final View l3 = (View) findViewById (R.id.linija3);
		final View l4 = (View) findViewById (R.id.linija4);
		
	    GetTipovi gp = new GetTipovi(this); 
	    gp.execute(username, password);
	    
	    final GetBrojVozila gbv = new GetBrojVozila(this);
	    tip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
	        @Override
	        public void onItemSelected(AdapterView adapter, View v, int i, long lng) {              
	        	String b = tip.getSelectedItem().toString();
	        	String indeks = String.valueOf(tip.getSelectedItemPosition());
	        	Log.d("indesk pozicijie", indeks);
	        	if (!b.equals(" ")) {
		        	t4.setVisibility(View.VISIBLE);
		        	l4.setVisibility(View.VISIBLE);
		        	broj.setVisibility(View.VISIBLE);
		        	
		        	Log.d("indeks tipa koji saljem", indeks);
		        	gbv.execute(username, password, indeks);
	        	}
	        }    
	        @Override
	        public void onNothingSelected(AdapterView arg0) { }
	    }); 
	    
	    final GetLinije gl = new GetLinije(this);
	    broj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
	        @Override
	        public void onItemSelected(AdapterView adapter, View v, int i, long lng) {              
	        	
	        	String t = tip.getSelectedItem().toString();
	        	String indeks = String.valueOf(tip.getSelectedItemPosition());
	        	Log.d("indeks iz postavki", indeks);
	        	
	        	String b = broj.getSelectedItem().toString();
	        	idVozila = b;
	        	Log.d ("vazno idVozila", idVozila);
	        	
	        	if (!b.equals(" ")) {
		        	t2.setVisibility(View.VISIBLE);
		        	l2.setVisibility(View.VISIBLE);
		        	linija.setVisibility(View.VISIBLE);
		        	gl.execute(username, password, indeks);	
	        	}
	        }    
	        @Override
	        public void onNothingSelected(AdapterView arg0) { }
	    }); 
	    

	    final GetSmjer gs = new GetSmjer(this);
	    linija.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
	        @Override
	        public void onItemSelected(AdapterView adapter, View v, int i, long lng) {              
	        	
	        	String b = linija.getSelectedItem().toString();
	        	int indeks = linija.getSelectedItemPosition();
	        	idLinija = idLinije.get(indeks); 
	        	Log.d ("broj Linije iz postavki", b);
	        	brojLinije = b;
	        	
	        	if (!b.equals(" ")) {
		        	t3.setVisibility(View.VISIBLE);
		        	l3.setVisibility(View.VISIBLE);
		        	smjer.setVisibility(View.VISIBLE);
		        	gs.execute(username, password, b);
	        	}
	        }    
	        @Override
	        public void onNothingSelected(AdapterView arg0) { }
	    });
	    
	    smjer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
	        @Override
	        public void onItemSelected(AdapterView adapter, View v, int i, long lng) {              
	        	String b = smjer.getSelectedItem().toString();
	        	if (!b.equals(" ")) {
	        		pocni.setVisibility(View.VISIBLE);
	        	}
	        	smjer1 = b;
	        	int indeks_s1 = smjer.getSelectedItemPosition();
	        	if (indeks_s1 == 1) smjer2 = (String) smjer.getItemAtPosition(2);
	        	else smjer2 = (String) smjer.getItemAtPosition(1);
	        }    
	        @Override
	        public void onNothingSelected(AdapterView arg0) { }
	    });
	    
	    final Intent in5 = new Intent(this, Drugi.class);        
	    final PostVoznja pv = new PostVoznja(this);
		pocni.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				zapocniVoznju();
				pv.execute(voznja);
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
		//lat = mojaLokacija.getLatitude();
		//lon = mojaLokacija.getLongitude();
		voznja.setLat(lat);
		voznja.setLon(lon);
		Log.d("lat2", String.valueOf(lat));
		Log.d("lon2", String.valueOf(lon));		
	}
	
	public String[] getLinije() {
		return linije;
	}

	public void setLinije(String[] linije) {
		this.linije = linije;
	}
	
	public void dodajTip (String t) {
		getTipovi2().add(t);
	}

	public ArrayList<String> getTipovi2() {
		return tipovi2;
	}
	
	public void setTipovi2(ArrayList<String> tipovi2) {
		this.tipovi2 = tipovi2;
	}
	public String [] dajSmjerove (Spinner sp) {
		String [] s = new String[2];
		s[0] = (String) sp.getItemAtPosition(0);
		s[1] = (String) sp.getItemAtPosition(1);
		return s;
	}
}


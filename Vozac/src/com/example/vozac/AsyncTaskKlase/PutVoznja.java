package com.example.vozac.AsyncTaskKlase;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.vozac.Drugi;
import com.example.vozac.Klase.Voznja;

public class PutVoznja extends AsyncTask<String, Void, String> {

	private Voznja v;
	private Drugi activity;

	private String username = null;
	private String password = null;
	private String lat = null;
	private String lon = null;
	private String idKorisnika = null;
	private String idVozila = null;
	private String idLinije = null;
	public String brojLinije = null;
	public String smjer1 = null;
	public String smjer2 = null;
	public String id;
	public String idVoznje = null;
	private Handler mHandler = new Handler();
	static private Location mojaLokacija;
	
	public PutVoznja(Drugi b) {
		this.activity = b;
	}
	
	int status;
	

	@Override
	protected String doInBackground(String... params) {
				
		username = params[0];
		password = params[1];
		idVoznje = params[2];
		lat = params[3];
		lon = params[4];
		
		mojaLokacija = dajLokaciju(true);
		String pom1, pom2;
		pom1 = String.valueOf(mojaLokacija.getLatitude());
		pom2 = String.valueOf(mojaLokacija.getLongitude());
		
		lat = pom1;
		lon = pom2;

		try {
			Log.d("stop status u doInBackground", activity.stop);
			if (activity.stop.equals("0")) {
				Log.d("iz put", "put");
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httpput = new HttpPost("http://farisc.comlu.com/Voznje.php");		
				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	
				nameValuePairs.add(new BasicNameValuePair("korisnickoIme", username));
				nameValuePairs.add(new BasicNameValuePair("sifra", password));
				nameValuePairs.add(new BasicNameValuePair("lat", lat));
				nameValuePairs.add(new BasicNameValuePair("lon", lon));
				nameValuePairs.add(new BasicNameValuePair("idVoznje", idVoznje ));
				httpput.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				
				HttpResponse response = httpclient.execute(httpput);
				status = response.getStatusLine().getStatusCode();
				
				return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			}
			else {
				Log.d("usao u else u doInBackground", activity.stop);
				activity.stop = "1";
				//this.cancel(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

	@Override
	protected void onPostExecute(String response) {

		final PutVoznja p = new PutVoznja(activity);
		try {
			Log.d("stop status u onPOst", activity.stop);
			if (activity.stop.equals("0")) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						p.execute(username, password, idVoznje, lat, lon);
						}
					}, 10000);
			}
			else {
				activity.stop = "1";
				this.cancel(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Location dajLokaciju(boolean enabledProvidersOnly) {
		LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		Location location = null;
		List<String> providers = manager.getProviders(enabledProvidersOnly);

		
		for (String provider : providers) {
			location = manager.getLastKnownLocation(provider);
			if (location != null) {
				mojaLokacija = location;
				return location;
			}
		}
		return null;
	}
}


	

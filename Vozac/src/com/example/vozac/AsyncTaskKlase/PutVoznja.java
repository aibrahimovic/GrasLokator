package com.example.vozac.AsyncTaskKlase;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.vozac.Klase.Voznja;

public class PutVoznja extends AsyncTask<String, Void, String> {

	private Voznja v;
	private Context activity;

	private String username = null;
	private String password = null;
	private Double lat = null;
	private Double lon = null;
	private String s_lat = null;
	private String s_lon = null;
	private String idKorisnika = null;
	private String idVozila = null;
	private String idLinije = null;
	public String brojLinije = null;
	public String smjer1 = null;
	public String smjer2 = null;
	public String id;
	public String idVoznje = null;
	private Handler mHandler = new Handler();
	
	public PutVoznja(Context a) {
		this.activity = a;
	}

	@Override
	protected String doInBackground(String... params) {
		Log.d("info", "Usao u doInBackground u put");
		username = params[0];
		password = params[1];
		idVoznje = params[2];
		s_lat = params[3];
		s_lon = params[4];
		
		try {
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpPut httpput = new HttpPut("http://farisc.comlu.com/Voznje.php");		
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			nameValuePairs.add(new BasicNameValuePair("korisnickoIme", username));
			nameValuePairs.add(new BasicNameValuePair("sifra", password));
			nameValuePairs.add(new BasicNameValuePair("lat", s_lat));
			nameValuePairs.add(new BasicNameValuePair("lon", s_lon));
			nameValuePairs.add(new BasicNameValuePair("idVoznje ", idVoznje ));
			httpput.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				
			HttpResponse response = httpclient.execute(httpput);
			return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String response) {
		
		Log.d("info", "Usao u onPostExecute u put");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}
}
	

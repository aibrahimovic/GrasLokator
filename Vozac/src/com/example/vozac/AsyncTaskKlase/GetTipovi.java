package com.example.vozac.AsyncTaskKlase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.vozac.Drugi;
import com.example.vozac.Login;
import com.example.vozac.Postavke;
import com.example.vozac.Klase.Vozilo;

public class GetTipovi extends AsyncTask <String, Void, String> {

	private Postavke activity;
	private String naziv;

	public GetTipovi(Postavke a)
    {
        this.activity = a;
    }
	
	@Override
	protected String doInBackground(String... params) {
		
		Log.d("info", "Usao u doInBackground getTipovi");
		String username = params[0];
		String password = params[1];
		Log.d("info2", "Usao u doInBackground getTipovi");
		try {
			String url = "http://farisc.comlu.com/TipoviVozila.php";	
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url + "?korisnickoIme=" + username + "&password=" +password);
			HttpResponse response = httpClient.execute(httpGet);
			Log.d("response", url + "?korisnickoIme=" + username + "&password=" +password);	
			return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);		
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String response) {
		
		Log.d("info", "Usao u onPostExecute u tipovima");
		ArrayList<String> pomocna = new ArrayList<String> ();
				
		try {
			final JSONArray jsonObj = new JSONObject(response).getJSONArray("tipoviVozila");
		
			pomocna.add(" ");
			for (int i=0; i<jsonObj.length(); i++) {
				naziv = jsonObj.getJSONObject(i).getString("naziv");
				Log.d("info nazivi", naziv);
				pomocna.add(naziv);
			}
			activity.setTipovi2(pomocna);
			Log.d("iz getTipovi broj", String.valueOf(pomocna.size()));
			
			String [] tipovi = new String[pomocna.size()];
		    tipovi = pomocna.toArray(tipovi);
			
			ArrayAdapter adapter1 = new ArrayAdapter (activity, android.R.layout.simple_spinner_item, tipovi);
			activity.tip.setAdapter(adapter1);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
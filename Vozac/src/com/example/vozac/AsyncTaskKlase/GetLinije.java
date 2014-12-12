package com.example.vozac.AsyncTaskKlase;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.vozac.Postavke;
import com.example.vozac.Klase.Vozilo;

public class GetLinije extends AsyncTask <String, Void, String> {

	private Postavke activity;
	private String li;

	public GetLinije (Postavke a)
    {
        this.activity = a;
    }
	
	@Override
	protected String doInBackground(String... params) {
		
		Log.d("info", "Usao u doInBackground linije");
		String username = params[0];
		String password = params[1];
		String tip = params[2];
		
		try {
			String url = "http://farisc.comlu.com/Linije.php";	
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url + "?korisnickoIme=" + username + "&password=" +password + "&tipVozila=" +tip);
			HttpResponse response = httpClient.execute(httpGet);
			return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);		
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
	protected void onPostExecute(String response) {
		
		Log.d("info", "Usao u onPostExecute u linijama");
		ArrayList<String> brojevi = new ArrayList<String> ();
			
			try {
				final JSONArray jsonObj = new JSONObject(response).getJSONArray("linije");
				brojevi.add(" ");
				activity.idLinije.add(" ");
				for (int i=0; i<jsonObj.length(); i++) {
					li = jsonObj.getJSONObject(i).getString("brojLinije");	
					String id = jsonObj.getJSONObject(i).getString("idLinije");	
					brojevi.add(li);
					activity.idLinije.add(id);
					Log.d ("linija", li);
				}

				String [] brojeviVozila = new String[brojevi.size()];
				brojeviVozila = brojevi.toArray(brojeviVozila);
				
			    ArrayAdapter adapter2 = new ArrayAdapter(activity, android.R.layout.simple_spinner_item, brojeviVozila);
			    activity.linija.setAdapter(adapter2);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
	}
}

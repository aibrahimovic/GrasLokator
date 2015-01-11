package com.example.vozac.AsyncTaskKlase;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;

import com.example.vozac.Drugi;
import com.example.vozac.Postavke;
import com.example.vozac.Klase.Voznja;


public class PostVoznja extends AsyncTask<Voznja, Void, String> {

	private Voznja v;
	private Context activity;

	private String username = null;
	private String password = null;
	private String lat = null;
	private String lon = null;
	private String idKorisnika = null;
	private String idVozila = null;
	private String idLinija = null;
	public String brojLinije = null;
	public String smjer1 = null;
	public String smjer2 = null;
	public String id;
	public String idVoznje = " ";
	public String id1 = null;
	public String id2 = null;
	
	public PostVoznja(Context a) {
		this.activity = a;
	}

	
	@Override
	protected String doInBackground(Voznja... params) {
		Log.d("info", "Usao u doInBackground u voznji");
		v = params[0];
		username = v.getUsername();
		password = v.getPassword();
		idVoznje = v.getIdVoznje();
		
		Log.d("idVOznje nakon klika iz kvar", idVoznje);
		
		idKorisnika = String.valueOf(v.getIdKorisnika());
		idVozila = String.valueOf(v.getIdVozila());
		idLinija = String.valueOf(v.getIdLinija());
		brojLinije = String.valueOf(v.getBrojLinije());
		smjer1 = v.getSmjer1();
		smjer2 = v.getSmjer2();
		lat = v.getLat();
		lon = v.getLon();
		id1 = v.getId1();
		id2 = v.getId2();

		
		try {
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://farisc.comlu.com/Voznje.php");
			
			Log.d("idVoznje iz post sada", idVoznje);
			
			if (idVoznje != " ") {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
				nameValuePairs.add(new BasicNameValuePair("korisnickoIme", username));
				nameValuePairs.add(new BasicNameValuePair("sifra", password));
				//nameValuePairs.add(new BasicNameValuePair("lat", lat));
				//nameValuePairs.add(new BasicNameValuePair("lon", lon));
				//nameValuePairs.add(new BasicNameValuePair("idKorisnika", idKorisnika));
				//nameValuePairs.add(new BasicNameValuePair("idLinije", idLinija));
				//nameValuePairs.add(new BasicNameValuePair("idVozila", idVozila));
				nameValuePairs.add(new BasicNameValuePair("idVoznje", idVoznje));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				
				HttpResponse response = httpclient.execute(httppost);
				return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			}
		
			else {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);
				nameValuePairs.add(new BasicNameValuePair("korisnickoIme", username));
				nameValuePairs.add(new BasicNameValuePair("sifra", password));
				nameValuePairs.add(new BasicNameValuePair("lat", lat));
				nameValuePairs.add(new BasicNameValuePair("lon", lon));
				nameValuePairs.add(new BasicNameValuePair("idKorisnika", idKorisnika));
				nameValuePairs.add(new BasicNameValuePair("idLinije", idLinija));
				nameValuePairs.add(new BasicNameValuePair("idVozila", idVozila ));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				
				
				HttpResponse response = httpclient.execute(httppost);
				return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String response) {
		
		
		Log.d("info", "Usao u onPostExecute voznje");
		try {
			Log.d("u postu", response);
			
			final String jsonObj = new JSONObject(response).getString("idVoznje");
			id = jsonObj;
			id = id.replace("<!-- Hosting24 Analytics Code -->", "");
			id = id.replace("<script type=\"text/javascript\" src=\"http://stats.hosting24.com/count.php\"></script>", "");
			id = id.replace("<!-- End Of Analytics Code -->", "");
			
			Intent in = new Intent(activity, Drugi.class);
			in.putExtra("username", username);
			in.putExtra("password", password);
			in.putExtra("idKorisnika", idKorisnika);
			in.putExtra("idVoznje", id);
			in.putExtra("idVozila", idVozila);
			in.putExtra("idLinija", idLinija);
			in.putExtra("lat", lat);
			in.putExtra("lon", lon);	
			in.putExtra("brojLinije", brojLinije);
			in.putExtra("smjer1", smjer1);
			in.putExtra("smjer2", smjer2);
			in.putExtra("id1", id1);
			in.putExtra("id2", id2);
	
			in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			activity.startActivity(in);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
	}	
}
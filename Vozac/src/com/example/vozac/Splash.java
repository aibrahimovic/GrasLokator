package com.example.vozac;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class Splash extends Activity implements LocationListener {
	
	public static Location mojaLokacija = null;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		final Intent i = new Intent(this, Login.class);
		mHandler.postDelayed (new Runnable() {
			public void run() {
				//dajLokaciju(true);
				startActivity(i);
			}
		}, 3000);
	}
	
	private Location dajLokaciju(boolean enabledProvidersOnly) {
		LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		Location location = null;
		List<String> providers = manager.getProviders(enabledProvidersOnly);

		for (String provider : providers) {
			location = manager.getLastKnownLocation(provider);
			if (location != null) {
				mojaLokacija = location;
				Intent intent = new Intent(this, Login.class);
				startActivity(intent);
				return location;
			}
		}
		prikaziIzbornik();

		return null;
	}

	private void prikaziIzbornik() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		builder.setMessage("Aplikacija Vas ne može locirati!");
		builder.setPositiveButton("Osvježi", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dajLokaciju(false);
			}
		});
		builder.setNegativeButton("Postavke", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
				dajLokaciju(false);
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.setCancelable(false);
		dialog.show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mHandler.postDelayed(new Runnable() {
			public void run() {
				//dajLokaciju(false);
			}
		}, 3000);
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}

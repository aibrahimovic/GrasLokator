package com.example.vozac.Klase;

public class Linija {
	
	private int idLinije;
	private int brojLinije;
	private String smjer;
	private String tipVozila;
	
	
	public int getBrojLinije() {
		return brojLinije;
	}
	public void setBrojLinije(int brojLinije) {
		this.brojLinije = brojLinije;
	}
	public String getSmjer() {
		return smjer;
	}
	public void setSmjer(String smjer) {
		this.smjer = smjer;
	}
	public int getIdLinije() {
		return idLinije;
	}
	public void setIdLinije(int idLinije) {
		this.idLinije = idLinije;
	}
	public String getTipVozila() {
		return tipVozila;
	}
	public void setTipVozila(String tipVozila) {
		this.tipVozila = tipVozila;
	}
}

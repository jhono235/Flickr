package com.example.android.flickrapp.model.listResults;

public class Response{
	private String stat;
	private Photos photos;

	public void setStat(String stat){
		this.stat = stat;
	}

	public String getStat(){
		return stat;
	}

	public void setPhotos(Photos photos){
		this.photos = photos;
	}

	public Photos getPhotos(){
		return photos;
	}
}

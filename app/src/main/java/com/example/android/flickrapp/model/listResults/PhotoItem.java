package com.example.android.flickrapp.model.listResults;

public class PhotoItem{
	private String owner;
	private String server;
	private int ispublic;
	private int isfriend;
	private int farm;
	private String id;
	private String secret;
	private String title;
	private int isfamily;

	public void setOwner(String owner){
		this.owner = owner;
	}

	public String getOwner(){
		return owner;
	}

	public void setServer(String server){
		this.server = server;
	}

	public String getServer(){
		return server;
	}

	public void setIspublic(int ispublic){
		this.ispublic = ispublic;
	}

	public int getIspublic(){
		return ispublic;
	}

	public void setIsfriend(int isfriend){
		this.isfriend = isfriend;
	}

	public int getIsfriend(){
		return isfriend;
	}

	public void setFarm(int farm){
		this.farm = farm;
	}

	public int getFarm(){
		return farm;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSecret(String secret){
		this.secret = secret;
	}

	public String getSecret(){
		return secret;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setIsfamily(int isfamily){
		this.isfamily = isfamily;
	}

	public int getIsfamily(){
		return isfamily;
	}
}

package com.example.android.flickrapp.model.listResults;

import java.util.List;

public class Photos{
	private int perpage;
	private String total;
	private String pages;
	private List<PhotoItem> photo;
	private int page;

	public void setPerpage(int perpage){
		this.perpage = perpage;
	}

	public int getPerpage(){
		return perpage;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setPages(String pages){
		this.pages = pages;
	}

	public String getPages(){
		return pages;
	}

	public void setPhoto(List<PhotoItem> photo){
		this.photo = photo;
	}

	public List<PhotoItem> getPhoto(){
		return photo;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}
}
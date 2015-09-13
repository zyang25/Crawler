package com.charles.gs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UrlPool {
	
	LinkedList<String> urlset;
	ArrayList<String> lastlist;
	HashSet<String> urlhash;

	UrlPool(String url){
		urlset = new LinkedList<String>();
		lastlist = new ArrayList<String>();
		urlhash = new HashSet<String>();
		urlset.add(url);
//		urlset.add("www.alibaba.com/us");
//		urlset.add("www.jason-ji.com");
//		urlset.add("http://www.alibaba.com/");
//		urlset.add("https://en.wikipedia.org/wiki/Alibaba_Group");
//		urlset.add("http://www.alibabagroup.com/");
	}
	
	
	public void AddUrl(String url){
		if(urlhash.add(url))
			urlset.add(url);
	}
	
	public void AddToLastTirePool(String url){
		if(urlhash.add(url))
			lastlist.add(url);
	}
	
	public boolean SoloUrl(){
		
		return false;
	}
	
	public String ParseUrl(String url){
		String regex = 
				"(http://|https://){1}[\\d\\-a-zA-Z]+(\\.[\\d\\-a-zA-Z]+)*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(url);
		if (m.find()) {
			return m.group().split("//")[1];
        }
		return null;
	}
	
	public void PrintUrlFromlist(){
		for(String u1 : urlset)
			System.out.println("Tire: "+u1);
		
		for(String u2 : lastlist)
			System.out.println("LastTire: "+ u2);
		
	}
	
	public void PrintUrlFromHashSet(){
		for(String u : urlhash)
			System.out.println(u);
		
	}
	
	public void WriteListToTxt(WriteTxt wt,int tire, int seed){
		for(String u1 : urlset){
			wt.WriteUrl(u1, tire, seed);
		}
		if(!lastlist.isEmpty())
			for(String u2:lastlist)
				wt.WriteUrl(u2, 4, seed);
	}
	

	public LinkedList<String> getUrlset() {
		return urlset;
	}

	public void setUrlset(LinkedList<String> urlset) {
		this.urlset = urlset;
	}
	
	public HashSet<String> getUrlhash() {
		return urlhash;
	}

	public void setUrlhash(HashSet<String> urlhash) {
		this.urlhash = urlhash;
	}
	
	public String getUrl(){
		if(!urlset.isEmpty())
			return urlset.pop();
		else
			return null;
	}
	public boolean isEmpty(){
		if(urlset.isEmpty())
			return true;
		else
			return false;
	}
	public int size(){
		return urlset.size();
	}
	
}

package com.charles.gs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UrlPool {
	
	LinkedList<String> urlset;
	HashSet<String> urlhash;

	UrlPool(){
		urlset = new LinkedList<String>();
		urlhash = new HashSet<String>();
//		urlset.add("www.alibaba.com/us");
		urlset.add("www.jason-ji.com");
//		urlset.add("http://www.alibaba.com/");
//		urlset.add("https://en.wikipedia.org/wiki/Alibaba_Group");
//		urlset.add("http://www.alibabagroup.com/");
	}
	
	public void AddUrl(String url){
		if(urlhash.add(url))
			urlset.add(url);
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
	
	public void PrintUrl(){
		for(String u : urlset)
			System.out.println(u);
		
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

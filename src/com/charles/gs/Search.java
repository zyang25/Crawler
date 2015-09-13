package com.charles.gs;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
 
//1.ajax.google
//2.reg
//3.Jsoup return all the url
//4.Single thread crawler
//5.Runnable use thread to search the url



public class Search implements Runnable{
 
	static URL url;
	static LinkedList<String> urlset;
	public UrlPool urlpool;
	public static int tire = 0;
	public static int tireamount = 0;
	public static long count = 0;
	
	public URL getURL(){
		return url;
	}
	
	Search(UrlPool up){
		this.urlpool = up;
	}
	
	public static void main(String[] args) throws IOException {
 
//		String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
//		String query = "alibaba";
//		String charset = "UTF-8";
//		urlset = new HashSet<String>();
//		
//		url = new URL(address + URLEncoder.encode(query, charset));
//		Reader reader = new InputStreamReader(url.openStream(), charset);
//		GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
// 
//		int total = results.getResponseData().getResults().size();
//		System.out.println("total: "+total);
// 
//		// Show title and URL of each results
//		for(int i=0; i<=total-1; i++){
//			urlset.add(results.getResponseData().getResults().get(i).getUrl());
//			System.out.println("Title: " + results.getResponseData().getResults().get(i).getTitle());
//			System.out.println("URL: " + results.getResponseData().getResults().get(i).getUrl() + "\n");
// 
//		}
		
		//UrlPool up = new UrlPool();
		//up.PrintUrl();
		
		
		UrlPool up = new UrlPool();
		Search search = new Search(up);
		
		Thread t1 = new Thread(search);
		Thread t2 = new Thread(search);
		Thread t3 = new Thread(search);
		Thread t4 = new Thread(search);
		t1.start();
		//t2.start();
		//t3.start();
	}
	
	public void SearchUrl(){
		Document doc = null;
		if(!urlpool.isEmpty()){
			try {				
				String url = urlpool.getUrl();
				System.out.println("");
				System.out.println("Searching..  "+ url);
				doc = Jsoup.connect("http://"+ url).ignoreHttpErrors(true).get();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				if(urlpool.ParseUrl(link.toString())!=null){
					urlpool.AddUrl(urlpool.ParseUrl(link.toString()));
				}
			}
			if(tireamount==0&&count==0)
				tireamount = urlpool.size();
		urlpool.PrintUrl();
		}else
			System.out.println("All url is reached");
	}
	
	
	@Override
	public synchronized void run() {
		
		while(!urlpool.isEmpty()){
			SearchUrl();
			count++;
			System.out.println("Tire = "+tire+" Count = "+count+" Tireamount = "+tireamount);
			if(count == tireamount){
				tireamount =0;
				count = 0;
				tire++;
				if(tire==1)
					break;
			}
		}
		
		System.out.println("Done");
	}
	
}
 
 

class GoogleResults{
 
    private ResponseData responseData;
    public ResponseData getResponseData() { return responseData; }
    public void setResponseData(ResponseData responseData) { this.responseData = responseData; }
    public String toString() { return "ResponseData[" + responseData + "]"; }
 
    static class ResponseData {
        private List<Result> results;
        public List<Result> getResults() { return results; }
        public void setResults(List<Result> results) { this.results = results; }
        public String toString() { return "Results[" + results + "]"; }
    }
 
    static class Result {
        private String url;
        private String title;
        public String getUrl() { return url; }
        public String getTitle() { return title; }
        public void setUrl(String url) { this.url = url; }
        public void setTitle(String title) { this.title = title; }
        public String toString() { return "Result[url:" + url +",title:" + title + "]"; }
    }
}
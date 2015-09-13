package com.charles.gs;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JsoupSearch{
	JsoupSearch(){
	}
	public void SearchUrl(UrlPool up){
		Document doc = null;
		try {
			doc = Jsoup.connect(up.getUrl()).get();
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			if(up.ParseUrl(link.toString())!=null){
				up.AddUrl(up.ParseUrl(link.toString()));
			}
		}
		up.PrintUrl();
	}
}

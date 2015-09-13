package com.charles.gs;

import java.io.FileWriter;
import java.io.IOException;

public class WriteTxt {
	
	FileWriter fw;
	
	WriteTxt(){
		try {
			fw = new FileWriter("url.txt");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void WriteUrl(String url, int tire, int seed){
		try {
			fw.write(url+" Depth: "+tire+" Seed: "+ seed);
			fw.write("\n");
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Close(){
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.ksubaku.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiQuery {

	private static StringBuffer IMDB = new StringBuffer("http://www.omdbapi.com/?y=&plot=short&r=json");
	private static StringBuffer SPOTIFY = new StringBuffer("https://api.spotify.com/v1/search?type=album");

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static void main(String args[]) throws IOException, JSONException{
		String api = System.getProperty("api");
		String searchString = System.getProperty("searchString");
		if(api.equalsIgnoreCase("imdb") && !searchString.equals("")){
			System.out.println(searchString +" movies in "+api+" api:");
			searchString = searchString.replace(" ", "+");
			IMDB = IMDB.append("&s="+searchString);
			JSONObject json = readJsonFromUrl(IMDB.toString());
			//System.out.println(json.toString());	
			JSONArray array = json.getJSONArray("Search");
			for(int i = 0 ; i < array.length() ; i++){
				System.out.println(array.getJSONObject(i).getString("Year")+"\t"+array.getJSONObject(i).getString("Title"));

			}
		}
		else if(api.equalsIgnoreCase("spotify") && !searchString.equals("")){
			System.out.println(searchString +"\'s albums in "+api+" api:");
			searchString = searchString.replace(" ", "+");
			SPOTIFY = SPOTIFY.append("&q="+searchString);
			JSONObject json = readJsonFromUrl(SPOTIFY.toString());
			//System.out.println(json.toString());	
			JSONArray array = json.getJSONObject("albums").getJSONArray("items");
			for(int i = 0 ; i < array.length() ; i++){
				System.out.println(array.getJSONObject(i).getString("type")+"\t"+array.getJSONObject(i).getString("name"));

			}
		}
		else{
			System.out.println("API not supported!");
		} 
	}
}

package fr.univavignon.pokedex.imp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonResourceProvider {
	public static JsonElement getJsonFrom(String Address) throws IOException{
		URL url = new URL(Address);
	    HttpURLConnection request = (HttpURLConnection) url.openConnection();
	    JsonParser jp = new JsonParser(); //from gson
	    JsonElement obj = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
	    return obj;
	}
}

package com.in28minutes.rest.webservices.restfulwebservices;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class SampleDemo implements Runnable {

	private static final String URL = "https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=";
	// private static final String URL =
	// "https:en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=";
	private Thread t;
	private String threadName;

	public SampleDemo(String threadName) {
		this.threadName = threadName;
	}

	public void run() {
		while (true) {
			System.out.println(threadName);
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public synchronized void methodA(int i, String msg) {
		System.out.println(Integer.toString(i));
		System.out.println(msg);
	}

	public void methodB(int i, String msg) {
		synchronized (this) {
			System.out.println(Integer.toString(i));
			System.out.println(msg);
		}
	}

	public static JSONObject readJsonFromUrl(String url) {
		try (InputStream is = new URL(url).openStream()) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
			String jsonText = sb.toString();
			return new JSONObject(jsonText);
		} catch (Exception e) {
			System.out.println("Exception reading json from url: " + e.getMessage());
			return null;
		}
	}

	public static int getTopicCount(String topic) {
		int count = 0;
		JSONObject json = readJsonFromUrl(URL + topic);

		if (null != topic && null != json) {
			JSONObject parse = json.getJSONObject("parse");
			JSONObject text = parse.getJSONObject("text");
			String textValue = text.getString("*");
			count = textValue.split(topic, -1).length - 1;
		}
		return count;
	}

	public static void main(String[] args) {
		int result = getTopicCount("pizza");
		System.out.println("getTopicCount --> " + result);
		/*
		 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); String
		 * encodedString = encoder.encode("user"); System.out.println("encodedString: "
		 * + encodedString);
		 */
	}

}

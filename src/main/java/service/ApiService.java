package service;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import property.ApiKey;
import property.ApiProperties;
import singleton.OkHttpSingleton;

public class ApiService {
	
	private final WifiService wifiService;
	
	public ApiService() {
		this.wifiService = new WifiService();
	}
	
	public void close() {
		wifiService.close();
	}
	
	public int callAllOpenApi() {
		int start = 1, end = 1000, res = 0, total = 0;
		
		wifiService.open();
		
		System.out.println("Inserting Wifi Data...");
		while ((res = callOpenApi(start, end)) > 0) {
			start += 1000;
			end += 1000;
			
			total = res;
		}
		
		System.out.println("Insert Complete!");
		wifiService.close();
		
		return total;
	}
	
	public int callOpenApi(int startIndex, int endIndex) {
		int result = 0;
		
		OkHttpClient client = OkHttpSingleton.getInstance();
		Request request = new Request.Builder()
				.url(getURLString(startIndex, endIndex))
				.build();
		
		try (Response response = client.newCall(request).execute()) {
			String responseString = response.body().string();
			String resultString = ParseService.parseXmlLine(
					responseString, 
					"list_total_count"
					);
			
			result = resultString.equals("") ? 0 : Integer.parseInt(resultString);
			
			if (result > 0) {
				wifiService.callDbInsert(responseString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return result;
	}

	String getURLString(int start, int end) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(ApiProperties.DOMAIN);
		sb.append(ApiKey.APIKEY);
		sb.append(ApiProperties.SUFFIX);
		sb.append(start).append("/").append(end);
		
		return sb.toString();
	}
	
}

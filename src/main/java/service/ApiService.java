package service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import domain.Wifi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
		
		while ((res = callOpenApi(start, end)) > 0) {
			start += 1000;
			end += 1000;
			
			total = res;
		}
		
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
		sb.append(ApiProperties.APIKEY);
		sb.append(ApiProperties.SUFFIX);
		sb.append(start).append("/").append(end);
		
		return sb.toString();
	}
	
}

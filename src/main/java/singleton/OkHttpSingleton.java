package singleton;

import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;

@NoArgsConstructor
public class OkHttpSingleton {

	private static final OkHttpClient INSTANCE = new OkHttpClient();
	
	public static OkHttpClient getInstance() {
		return INSTANCE;
	}
}

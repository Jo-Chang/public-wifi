import domain.Wifi;
import service.ApiService;
import service.DbService;

public class Main {
	public static void main(String[] args) {
		DbService dbService = new DbService();
		dbService.initHistoryTable();
		
//		ApiService apiService = new ApiService();
//		apiService.callOpenApi(1, 1);
//		
//		System.out.println(dbService.getPublicWifi());
	}
}

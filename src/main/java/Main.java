import domain.Wifi;
import service.ApiService;
import service.DbService;

public class Main {
	public static void main(String[] args) {
		DbService dbService = new DbService();
		
		dbService.initWifiTable();
		dbService.initHistoryTable();
		dbService.initBookmarkTable();
		dbService.initWifiBookmarkTable();
		
	}
}

package domain;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class WifiBookmarkDto {
	
	private int id;
	private String bookmarkName;
	private String wifiMgrNo;
	private String wifiName;
	private Timestamp createTs;

	@Builder
	public WifiBookmarkDto(int id, String bookmarkName, String wifiMgrNo, String wifiName, Timestamp createTs) {
		this.id = id;
		this.bookmarkName = bookmarkName;
		this.wifiMgrNo = wifiMgrNo;
		this.wifiName = wifiName;
		this.createTs = createTs;
	}

}

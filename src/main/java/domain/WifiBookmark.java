package domain;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class WifiBookmark {
	
	private int id;
	private int bookmarkId;
	private String wifiMgrNo;
	private Timestamp createTs;

	@Builder
	public WifiBookmark(int id, int bookmarkId, String wifiMgrNo, Timestamp createTs) {
		this.id = id;
		this.bookmarkId = bookmarkId;
		this.wifiMgrNo = wifiMgrNo;
		this.createTs = createTs;
	}

}

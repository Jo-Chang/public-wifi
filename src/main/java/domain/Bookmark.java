package domain;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Bookmark {
	
	private int id;
	private String name;
	private int bmOrder;
	private Timestamp createTs;
	private Timestamp updateTs;

	@Builder
	public Bookmark(int id, String name, int bmOrder, Timestamp createTs, Timestamp updateTs) {
		this.id = id;
		this.name = name;
		this.bmOrder = bmOrder;
		this.createTs = createTs;
		this.updateTs = updateTs;
	}
	
}

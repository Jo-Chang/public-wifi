package domain;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class History {
	
	private int id;
	private Double lat;
	private Double lnt;
	private Timestamp viewTs;
	
	@Builder
	public History(int id, Double lat, Double lnt, Timestamp viewTs) {
		super();
		this.id = id;
		this.lat = lat;
		this.lnt = lnt;
		this.viewTs = viewTs;
	}
	
}

package domain;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Wifi {

	private String mgrNo;
	private String wrdofc;
	private String mainNm;
	private String adres1;
	private String adres2;
	private String instlFloor;
	private String instlTy;
	private String instlMBy;
	private String svcSe;
	private String cmcwr;
	private Integer cnstcYear;
	private String inoutDoor;
	private String remars3;
	private Double lat;
	private Double lnt;
	private Timestamp workDttm;
	private Double distance;
	
}

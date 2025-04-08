package service;

import java.sql.Timestamp;

import domain.Wifi;

public class ParseService {

	public static Wifi parseXmlToWifi(String str) {
		Wifi wifi = new Wifi();
		String tag = "";
		
		tag = "X_SWIFI_MGR_NO";
		wifi.setMgrNo(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_WRDOFC";
		wifi.setWrdofc(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_MAIN_NM";
		wifi.setMainNm(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_ADRES1";
		wifi.setAdres1(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_ADRES2";
		wifi.setAdres2(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_INSTL_FLOOR";
		wifi.setInstlFloor(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_INSTL_MBY";
		wifi.setInstlMBy(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_INSTL_TY";
		wifi.setInstlTy(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_SVC_SE";
		wifi.setSvcSe(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_CMCWR";
		wifi.setCmcwr(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_CNSTC_YEAR";
		wifi.setCnstcYear(Integer.parseInt(parseXmlLine(str, tag)));
		
		tag = "X_SWIFI_INOUT_DOOR";
		wifi.setInoutDoor(parseXmlLine(str, tag));
		
		tag = "X_SWIFI_REMARS3";
		wifi.setRemars3(parseXmlLine(str, tag));
		
		tag = "LAT";
		wifi.setLat(Double.parseDouble(parseXmlLine(str, tag)));
		
		tag = "LNT";
		wifi.setLnt(Double.parseDouble(parseXmlLine(str, tag)));
		
		tag = "WORK_DTTM";
		wifi.setWorkDttm(Timestamp.valueOf(parseXmlLine(str, tag)));
		
		return wifi;
	}
	
	static String parseXmlLine(String str, String tag) {
		if (!str.contains(String.format("<%s>", tag))) return "";
		
		return str.substring(
				str.indexOf(String.format("<%s>", tag)) + tag.length() + 2,
				str.indexOf(String.format("</%s>", tag))
				);
	}
	
}

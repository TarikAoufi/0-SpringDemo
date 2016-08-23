package fr.aoufi.annuaire.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	
	public static String dateToString(Date dDate, String sFormat)  
	{
		String sDate = "";
		SimpleDateFormat sdf;
		try {
			sdf = new SimpleDateFormat(sFormat);
			sDate = sdf.format(dDate);
		}
		catch (Exception e){
			sDate = "";
		}
		return sDate;
	}
	
	public static Date stringToDate(String sDate, String sFormat)  
	{
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		Date maDate;
		try {
			maDate = sdf.parse(sDate);
		} catch (ParseException e) {
			// le mois = mois - 1   <=>    0 => janvier
			maDate = new GregorianCalendar(1900,00,01).getTime();
		}
		return maDate;
	} 

}

package javaee.loja.converter;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Calendar.class)
public class CalendarConverter implements Converter{
	
	private DateTimeConverter converterTime = new DateTimeConverter();
	
	public CalendarConverter() {
		converterTime.setPattern("dd/MM/yyyy");
		converterTime.setTimeZone(TimeZone.getTimeZone("America/Brasilia"));
	}
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent uComponent, String dataStr) {
		Date data = (Date) converterTime.getAsObject(context, uComponent, dataStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent uComponent, Object dataObj) {
		if(dataObj == null){
			return null;
		}
		Calendar calendar = (Calendar) dataObj;
		return converterTime.getAsString(context, uComponent, calendar.getTime());
	}
}

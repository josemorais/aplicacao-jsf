package br.com.junior.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = LocalDateTimeConverter.ID)
public class LocalDateTimeConverter extends DateTimeConverter {

	public static final String ID = "br.com.junior.uteis.LocalDateTimeConverter";

//	@Override
//	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
//		// TODO Auto-generated method stub
//		Date date = null;
//		LocalDateTime localDateTime = null;
//
//		Object obj = super.getAsObject(arg0, arg1, arg2);
//
//		if (obj instanceof Date) {
//			date = (Date) obj;
//			Instant instant = Instant.ofEpochMilli(date.getTime());
//			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//			return localDateTime;
//		} else {
//			throw new IllegalArgumentException(String.format(
//					"value=%s Não foi possível converter LocalDateTime, resultado super.getAsObject=%s", arg2, obj));
//		}
//	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		if (arg2 == null)
			return super.getAsString(arg0, arg1, arg2);
		if (arg2 instanceof LocalDateTime) {
			LocalDateTime localDateTime = (LocalDateTime) arg2;
			Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
			Date date = Date.from(instant);
				return super.getAsString(arg0, arg1, date);
		} else {
			throw new IllegalArgumentException(String.format("value=%s não é um LocalDateTime", arg2));
		}
	}

}

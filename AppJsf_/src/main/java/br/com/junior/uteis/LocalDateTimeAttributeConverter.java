package br.com.junior.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	/*
	 * Converte LocalDateTime em TimesTamp (non-Javadoc)
	 * 
	 * @see
	 * javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.
	 * Object)
	 */
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime arg0) {
		if (arg0 != null)
			return Timestamp.valueOf(arg0);
		return null;
	}

	/*
	 * Converte TimesTamp em LocalDateTime (non-Javadoc)
	 * 
	 * @see
	 * javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.
	 * Object)
	 */
	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp arg0) {
		if (arg0 != null)
			return arg0.toLocalDateTime();
		return null;
	}

}

package com.richard.brewer.config.format;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Locale;

import org.springframework.format.Formatter;

public abstract class TemporalFormatter<T extends Temporal> implements Formatter<T> {
	
	@Override
	public String print(T  temporal, Locale locale) {
		return geDateTimeFormatter(locale).format(temporal);
	}

	@Override
	public T parse(String text, Locale locale) throws ParseException {
		return parse(text, geDateTimeFormatter(locale));
	}
	
	private DateTimeFormatter geDateTimeFormatter(Locale locale) {
		return DateTimeFormatter.ofPattern(pattern(locale));
	}

	public abstract String pattern(Locale locale);
	public abstract T parse(String text, DateTimeFormatter formatter);
}

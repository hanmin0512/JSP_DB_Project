package db;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class cal_date {
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
    String format_now = now.format(formatter);
	
	public void cal_day() {
		
	}
	
	public void cal_time() {
		
	}
}

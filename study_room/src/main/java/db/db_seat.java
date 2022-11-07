package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_seat {
	Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String db_url = "jdbc:oracle:thin:@203.247.239.1:1521:myora";
    String db_id = "reservation";
    String db_pw = "reservation2022";
    String driver = "oracle.jdbc.driver.OracleDriver";
    String id,sc_number;
    String sql;
    LinkedList<String> date_list = new LinkedList();
    boolean connect_confirm;
    boolean inDataSuccessed = false;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
    String format_now = now.format(formatter);
    

	public void db_connection() {
		try{
		 	Class.forName(driver);
		 	conn = DriverManager.getConnection(db_url, db_id, db_pw);
			connect_confirm = true;
		 	System.out.print("connected db");
			
				
			
		 } catch(Exception e){
		 	e.printStackTrace();
		 	System.out.print("error");
		 }
		//return conn;
	}
	
	
	public void db_close() throws SQLException {
		try{
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs!=null) rs.close();
			 if(pstmt !=null) pstmt.close();
			 if(conn != null) conn.close();
		}
	}
	
	public LinkedList<String> db_confirm_list(int num) {
		sql = "SELECT * FROM seat WHERE dayof = \'"+format_now+"\'and seat_num = "+num+"";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rs.previous();
				while(rs.next()) {
					date_list.add(rs.getString("timeof"));
				}
				
			}else {
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error");
		}
		return date_list;
	}
	
	public void insertInto(int table_num, String sc_number, String dayof, String timeof) {
		if( overlap(table_num,dayof,timeof)) {
			System.out.println("jinip");
			sql = "INSERT INTO seat VALUES ("+table_num+", '"+sc_number+"', '"+dayof+"', '"+ timeof+"')";
			try{
				pstmt = conn.prepareStatement(sql);
				int r = pstmt.executeUpdate();
				if(r>0) {
					inDataSuccessed = true;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("jinip fail");
	}
	
	public boolean overlap(int table_num, String dayof, String timeof) {
		sql = "SELECT * from seat WHERE seat_num = "+table_num+" and dayof = '"+dayof+"' and timeof = '"+timeof+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return false;
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}

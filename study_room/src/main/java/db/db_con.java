package db;


import java.sql.Connection;
import java.util.LinkedList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_con {
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
    
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
    String format_now = now.format(formatter);
    
    boolean new_data;
    boolean connect_confirm = false;
    
    
    public db_con(String id, String sc_number) {
    	this.id = id;
    	this.sc_number = sc_number;
    }
	public void db_connection() {
		try{
		 	Class.forName(driver);
		 	conn = DriverManager.getConnection(db_url, db_id, db_pw);
			connect_confirm = true;
		 	System.out.print("connected db");
			//return conn;
				
			
		 } catch(Exception e){
		 	e.printStackTrace();
		 	System.out.print("error");
		 	//return null;
		 }
	}

		
	
	
	public void db_insert(String id, String sc_number) {
		sql = "INSERT INTO sc_user VALUES(?,?)";
		if(connect_confirm && new_data) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, sc_number);
				int r = pstmt.executeUpdate();
				if(r>0){
				//out.println(f.base());
					System.out.print(this.id);
				}else{
				System.out.println("err");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
			 try{
				 	if(pstmt !=null) pstmt.close();
				 	if(conn != null) conn.close();
			 	}catch(Exception e){
				 	e.printStackTrace();
			 	}
			}
		 
		}
	}
	
	public void db_update() {
		
	}
	
	
	
	
	public void db_indata() {
		sql = "SELECT * FROM sc_user where sc_number = \'"+this.sc_number+"\' ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				new_data = false;
			}else {
				new_data = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public ResultSet db_lookup() {
		sql = "SELECT * FROM seat WHERE sc_number = \'"+sc_number+"\'";
		try {
			pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs= pstmt.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rs;
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
	
	public LinkedList<String> db_confirm_list(String table, int num) {
		String number = Integer.toString(num);
		sql = "SELECT * FROM"+table+" WHERE dayof = \'"+format_now+"\' seat_num = "+num+"";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				date_list.add(rs.getString("dayof"));
				
			}else {
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error");
		}
		return date_list;
	}
}
	
	

	

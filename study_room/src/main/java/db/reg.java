package db;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;

import db.form;
import db.db_con;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class reg
 */
@WebServlet("/reg")
public class reg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean C;
    form f = new form();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
    
    String db_url = "jdbc:oracle:thin:@203.247.239.1:1521:myora";
    String db_id = "reservation";
    String db_pw = "reservation2022";
    String driver = "oracle.jdbc.driver.OracleDriver";
    String day_all;
    Date day_user;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
    String format_now = now.format(formatter);
    //LocalDate kor = LocalDate.now(ZoneId,of("Asia/Seoul"));
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		out.println(f.base());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=UTF-8"); 
		
		PrintWriter out = response.getWriter();
		
		String id = URLDecoder.decode(request.getParameter("username"),"utf8");
		String sc_number = URLDecoder.decode(request.getParameter("sc_num"),"utf8");
		
		db_con db = new db_con(id, sc_number);
		db.db_connection();
		db.db_indata();
		try {
			rs=db.db_lookup();
			if(!db.new_data) {
				out.print(f.myPage(id, sc_number));
				out.print("<div style='width:50%;'>");
				out.print("<table class ='table table-striped' style ='margin-top:2%;'");
				out.print("<tr><td>좌석 번호</td><td>학번</td><td>예약 날짜</td><td>예약 시간</td><td>update</td></tr>");
				
				while(rs.next()) {
					out.print("<tr>");
					out.print("<td>");
					out.print(rs.getInt("seat_num"));
					out.print("</td>");
					out.print("<td>");
					out.print(rs.getString("sc_number"));
					out.print("</td>");
					out.print("<td>");
					day_all = rs.getString("dayof");
					out.print(day_all.substring(0,10));
					out.print("</td>");
					out.print("<td>");
					out.print(rs.getString("timeof"));
					out.print("</td>");
					out.print("<td>");
					out.print("<input type=button value = '수정'></input>");
					out.print("</td>");
					out.print("</tr>");
				}
				out.print("</table></div>");
			}else{
				out.println(f.joinus());
				//out.print("you are not joinus");
			}
			db.db_close();
		 } catch(Exception e){
		 	e.printStackTrace();
		 	out.print("error");
		 }finally{
			 try{
				 if(rs!=null) rs.close();
				 if(pstmt !=null) pstmt.close();
				 if(conn != null) conn.close();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }


		
		

		
		
	}

}

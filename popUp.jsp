<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "db.Form" %>
    <%@ page import ="java.sql.*" %>
    <%@ page import = "db.Seat" %>
    <%@ page import = "java.util.LinkedList" %>
    <%@ page import = "java.time.LocalDate, java.time.LocalTime, java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
    <!-- this page is popup page -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% 
	Form f = new Form();

	
	String m=":00";
	LinkedList<String> date_base = new LinkedList<String>();
	String id = request.getParameter("username");
	String sc_number = request.getParameter("sc_num");
	String format_now,time_now;
	
	int table_num = Integer.parseInt(request.getParameter("reser"));
	LocalTime nowTime = LocalTime.now();
	LocalDate now = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
	format_now = now.format(formatter);
	time_now = nowTime.format(formatTime);
	
	if(id == null && sc_number == null){
		out.print(join_tag());
	}else{
		date_base.add("09:00");
		for(int i=10; i<=24; i++){
			String time = Integer.toString(i)+m;
			date_base.add(time);
		}
		
	}
	
		
%>

<%!
	public String join_tag(){
		String s = "<center><a href = 'index.jsp'>login</a></center>";
		return s;
	}

%>

</head>
<body>

	popup page 입니다<br>
	<% out.print(f.myPage(id,sc_number));%>
	<% 
	Seat ds = new Seat();
	ds.db_connection();
	//out.print(ds.connect_confirm);
	
	LinkedList<String> search = new LinkedList<String>();
	search = ds.db_confirm_list(table_num);
	//out.print(ds.connect_confirm);
	//out.print(search);
	ds.db_close();
		
		

		if(search != null){
			for(String s:search){
				if(date_base.contains(s)){
					date_base.remove(s);			
				}
			}
		}
		else
		{
		 System.out.println("데이터베이스 x");
			
		}
		
		/*
		for(String t:date_base){
			if(t.compareTo(time_now) < 1 ){
				out.print(t);
			}
		}
		*/
		int size = date_base.size();
		for(int i=date_base.size()-1; i>= 0; i--){
			if( date_base.get(i).compareTo(time_now) < 1 ){
				date_base.remove(date_base.get(i));
			}
		}
		
		out.print("<p class='text-left'>예약 가능한 시간 </p>");
		out.print("<div class=\"position-absolute start-0 translate-left\" style=\"width:50%;\">");
		for(String s_s: date_base){
			out.print("<form action='Reservation' method='post'>");
			out.print("<input type = 'hidden' name = 'table_num' value='"+table_num+"' >");
			out.print("<input type = 'hidden' name = 'sc_num' value='"+sc_number+"' >");
			//out.print("<input type = 'hidden' name = 'dayof' value='"+format_now+"' >");
			out.print("<input type = 'hidden' name = 'timeof' value='"+s_s+"' >");
			out.print("<input type = 'hidden' name = 'username' value='"+id+"' >");
			out.print(s_s+"<button>신청</button><br>");
			out.print("</form>");
		}
		out.print("</div>");
		//out.print(time_now);
		String table_number = "table"+table_num+".jpg";
	%>
	
	
	<div class="position-absolute start-50 translate-right" style="width:50%;">
			<img src = "<%=table_number %>" style = "width:70%; height:70%">
	</div>
</body>
</html>
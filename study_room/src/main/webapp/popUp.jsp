<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "db.form" %>
    <%@ page import = "db.db_con" %>
    <%@ page import ="java.sql.*" %>
    <%@ page import = "db.db_seat" %>
    <%@ page import = "java.util.LinkedList" %>
    <%@ page import = "java.time.LocalDate, java.time.LocalTime, java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
    <!-- this page is popup page -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% 
	form f = new form();
	
	String m=":00";
	LinkedList<String> date_base = new LinkedList();
	String id = request.getParameter("username");
	String sc_number = request.getParameter("sc_num");
	String format_now;
	
	int table_num = Integer.parseInt(request.getParameter("reser"));
	
	LocalDate now = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
	format_now = now.format(formatter);
	
	if(id == null && sc_number == null){
		out.print(join_tag());
	}else{
		
		for(int i=9; i<=24; i++){
			String time = Integer.toString(i)+m;
			date_base.add(time);
		}
		
		
		
		/*
		for(String s : date_base){
			out.print(s);
		}
		*/
		
	}
	
		
%>

<%!
public LinkedList<String> search_list(int num) throws SQLException {
	db_seat db = new db_seat();
	db.db_connection();
	LinkedList<String> date_list = db.db_confirm_list(num);
	db.db_close();
	
	return date_list;
	}
	
	public String join_tag(){
		String s = "<center><a href = 'index.jsp'>login</a></center>";
		return s;
	}

%>

</head>
<body>
<script>
alert("<%=id%>"+"<%=table_num%>");
</script>
	popup page 입니다<br>
	<% out.print(f.base());%>
	<% 
		db_seat ds = new db_seat();
		ds.db_connection();

		LinkedList<String> search = search_list(table_num);
		if(search != null){
			for(String s:search){
				if(date_base.contains(s)){
					date_base.remove(s);
					
				}else{
		
				}
		}
		}
		for(String s_s: date_base){
			out.print("<form action='Reservation' method='post'>");
			out.print("<input type = 'hidden' name = 'table_num' value='"+table_num+"' >");
			out.print("<input type = 'hidden' name = 'sc_num' value='"+sc_number+"' >");
			out.print("<input type = 'hidden' name = 'dayof' value='"+format_now+"' >");
			out.print("<input type = 'hidden' name = 'timeof' value='"+s_s+"' >");
			out.print("<input type = 'hidden' name = 'username' value='"+id+"' >");
			out.print(s_s+"<button>신청</button><br>");
			out.print("</form>");
		}
		
	%>
</body>
</html>
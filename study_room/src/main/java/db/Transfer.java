package db;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Transfer {
	HttpServletRequest request;
	HttpServletResponse response;
	public Transfer(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
	}
	
	
	public void in_data(String name, String sc_number) {
		this.request.setAttribute("name", name);
		this.request.setAttribute("sc_number", sc_number);
	}
	
	public void page(String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher(path);
		dispatcher.forward(this.request,this.response);
	}

}

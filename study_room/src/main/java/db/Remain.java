package db;

import db.form;
import db.db_con;

import java.io.IOException;
import java.io.PrintWriter;
import db.Transfer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Remain
 */
@WebServlet("/Remain")
public class Remain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	form f = new form();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); 
		Transfer tf = new Transfer(request, response);
		String seat_num = request.getParameter("reser");
		//String name = request.getParameter("username");
		//String sc_number = request.getParameter("sc_num");
		PrintWriter out = response.getWriter();
		request.setAttribute("reser",seat_num);
		//tf.in_data(name, sc_number);
		tf.page("popUp.jsp");
		//out.print(f.base());
		
		
	}

}

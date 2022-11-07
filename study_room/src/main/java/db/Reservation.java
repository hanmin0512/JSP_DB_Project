package db;

import db.Transfer;

import db.db_seat;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reservation
 */
@WebServlet("/Reservation")
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reservation() {
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
		
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		String sc_number = request.getParameter("sc_num");
		String dayof = request.getParameter("dayof");
		String timeof = request.getParameter("timeof");
		String name =request.getParameter("username");
		
		db_seat ds = new db_seat();
		ds.db_connection();
		ds.insertInto(table_num, sc_number, dayof, timeof);
		if(ds.inDataSuccessed) {
			tf.page("reg");
		}else {
			tf.page("index.jsp");
		}
		
	}

}

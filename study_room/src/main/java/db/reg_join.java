package db;

import db.Transfer;
import db.db_con;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;

import db.form;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class reg_join
 */
@WebServlet("/reg_join")
public class reg_join extends HttpServlet {
	private static final long serialVersionUID = 1L;
	form f = new form();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reg_join() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Transfer tf = new Transfer(request, response);
		
		String id = URLDecoder.decode(request.getParameter("username"),"utf8");
		String sc_number = URLDecoder.decode(request.getParameter("sc_num"),"utf8");
		
		db_con db = new db_con(id, sc_number);
		
		db.db_connection();
		db.db_indata();
		
		
		
		out.print(f.base());
		if(db.new_data) {
			// successed join user goto goto (index.jsp)login page
			db.db_insert(id, sc_number);
			tf.page("reg");
		}else {
			// already joiner goto successed login page
			
			tf.page("index.jsp");
		}
		out.print("</body></html>");
		try {
			db.db_close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void page(String path,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request,response);
	}

}

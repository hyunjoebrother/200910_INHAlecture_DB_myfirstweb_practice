package examples;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class JDBCservlet
 */
@WebServlet("/JDBCservlet")
public class JDBCservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JDBCservlet() {
        super();
        
        System.out.println("JDBCservlet 생성!!");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("doGet()호출!");
			response.setContentType("text/html; charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<h1> Hello World!!!</h1>");
			
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				String url = "jdbc:mysql://localhost/dbdesign";
				
				conn = DriverManager.getConnection(url, "dbuser", "dbuser123!");
				System.out.println("연결 성공!!~");
				
				stmt = conn.createStatement();
				
				String sql = "SELECT name, studentnumber, class, major FROM student";
				
				rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					String name = rs.getString(1);
					int num = rs.getInt(2);
					String className = rs.getString(3);
					String majorName = rs.getNString(4);
					out.println("<h1>" + name + " " + num + " " + className + " " + majorName + "</h1>");
				}
			}
			
			catch( ClassNotFoundException e)
			{
				System.out.println("드라이버 로딩 실패");
			}
			
			catch( SQLException e)
			{
				System.out.println("에러" + e);
			}
			
			finally
			{
				try
				{
					if( conn != null && !conn.isClosed())
					{
						conn.close();
					}
					
					if( stmt != null && !stmt.isClosed())
					{
						stmt.close();
					}
					
					if( rs != null && !rs.isClosed())
					{
						rs.close();
					}
				}
				catch( SQLException e)
				{
					e.printStackTrace();
				}
			}
			
			out.close();	
	}
}

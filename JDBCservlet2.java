package examples;

import java.io.IOException;

/* PrintWriter�� import���ش� */
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* java.sql�� import���ش� */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class JDBCservlet
 */
@WebServlet("/JDBCservlet2")
public class JDBCservlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JDBCservlet2() {
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
			//out.println("<h1> Hello World!!!</h1>");
			
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				// 1. ����̹� loading
				// ����̹� �������̽��� ������ Ŭ������ loading
				// Ŭ���� �̸��� mysql�̶� oracle �� �� ������ ���� �ٸ���
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				
				// 2. �����ϱ� Connection
				// MySQL ������ ���� Connection ��ü�� �����Ѵ�
				// ����̺� �Ŵ������� Connection ��ü�� �޶�� ��û
				String url = "jdbc:mysql://localhost/dbHomeWork";
				
				conn = DriverManager.getConnection(url, "hyunjoe", "mei831");
				System.out.println("연결 성공!!~");
				
				// 3. Query ������ ���� Statement ��ü ����
				stmt = conn.createStatement();
				
				
				// 4. SQL Query �ۼ�
				//    1) JDBC���� Query�� �ۼ��� ���� ����Ŭ���� ���� �ۼ��Ѵ�
				//    2) SELECT �� �� *���� ��� column�� �������� �ͺ��� ������ column���� ���� �������
				String sql = "SELECT studentnumber, name, major, grade, major2, startdate, email FROM student";
				
				
				// 5. Query ����
				// ���ڵ���� ResultSet ��ü�� �߰��Ѵ�.
				rs = stmt.executeQuery(sql);
				
				
				// 6. ������ ����ϱ�
				while(rs.next())
				{	
					// ���ڵ��� column�� �迭�� �޸� 0�� �ƴ϶� 1���� �����Ѵ�
					// db���� �������� data type�� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�
					int studentnumber = rs.getInt(1);
					String name = rs.getString(2);
					String major = rs.getString(3);
					int grade = rs.getInt(4);
					String major2 = rs.getString(5);
					String startdate = rs.getString(6);
					String email = rs.getString(7);
					
					out.println("<h1>" + studentnumber + " , " + name + " , " + major + " , " + 
					grade + " , " + major2 + " , " + startdate + " , " + email + "</h1>");
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

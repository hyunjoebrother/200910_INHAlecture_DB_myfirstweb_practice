package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NameServlet
 */
@WebServlet("/NameServlet")
public class NameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
			
		String name = request.getParameter("myname");

		out.println("<h1> Helllo " + name + "!!!</h1>");
		out.close();
	
		}
}
/* JSP (JAVA Server Page) : HTML ���� JAVA�� �־� ���������� �������� ����  �����ϴ� ��� */
/* 						   - client ��û�� �������� �����ϰ�, ������ html�� ���� */


/* Servlet : JAVA �� ���ø����̼��� ������� �� ������ ó���� �ϴ� ���α׷��� ���� */
/*			- �����α׷��ֿ��� client�� ��û�� ó���ϰ�, �� ����� �ٽ� client���� �����ϴ� Ŭ���� */
/* 		    - WAS�� �����ϴ� JAVA Ŭ���� */


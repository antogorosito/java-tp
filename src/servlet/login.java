package servlet;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlUsuario;
import entidades.Usuario;

/**
 * Servlet implementation class login
 */
@WebServlet({ "/login", "/Login" })
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String u=request.getParameter("usuario");
		String c=request.getParameter("clave");
		
		CtrlUsuario cu= new CtrlUsuario();
		
		Usuario usuario= cu.getOne(u,c);
		if(usuario!=null)
		{
			request.getRequestDispatcher("menu.jsp").forward(request, response);
			request.getSession().setAttribute("usuario", usuario);
		}
		else
		{
			PrintWriter out= response.getWriter();
			out.println("<script type=\"text/javascript\">");
			 out.println("alert('Usuario o Clave incorrecto');");
			 out.println("location='login.jsp';");
			 out.println("</script>");
		}
	}

}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import entidades.*;
import negocio.*;

/**
 * Servlet implementation class altaSocio
 */
@WebServlet({ "/altaSocio", "/altasocio" })
public class altaSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaSocio() {
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
		
		String op=request.getParameter("op");
		if(op.equals("Registrar"))
		{
			String n=request.getParameter("nombre");
			String a=request.getParameter("apellido");
			String d=request.getParameter("domicilio");
			String e=request.getParameter("email");
			String t=request.getParameter("telefono");
			String dni=request.getParameter("dni");		
			CtrlSocio cs=new CtrlSocio();
			Socio  s=cs.getOne(dni);
			if(s==null)
			{
				Socio socio=new Socio(a,n,e,d,t,dni);
				cs.add(socio);
				int tipo=1; 
				Usuario u=new Usuario(dni,a,tipo);
				CtrlUsuario cu=new CtrlUsuario();
				cu.add(u);
				
				PrintWriter out= response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Nuevo socio y usuario registrado');");
				out.println("location='WEB-INF/lib/altaSocio.jsp';");
				out.println("</script>");
			}
			else 
			{
			
				PrintWriter out= response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Ya existe un socio con el dni');");
				out.println("location='WEB-INF/lib/altaSocio.jsp';");
				out.println("</script>");			
			}
		}
		
		if(op.equals("Cancelar"))
		{
			request.getRequestDispatcher("WEB-INF/lib/menu.jsp").forward(request, response);
		}
	}

}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import entidades.Socio;
import negocio.CtrlSocio;

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
		// TODO Auto-generated method stub
		String n=request.getParameter("nombre");
		String a=request.getParameter("apellido");
		String d=request.getParameter("domicilio");
		String e=request.getParameter("email");
		String t=request.getParameter("telefono");
		String dni=request.getParameter("dni");
		
		CtrlSocio cs=new CtrlSocio();
		boolean  rta=cs.getOne(dni);
		if(rta==false)
		{
		Socio socio=new Socio(a,n,e,d,t,dni);
		cs.add(socio);
		
		PrintWriter out= response.getWriter();
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Nuevo socio registrado');");
		out.println("location='altaSocio.jsp';");
		out.println("</script>");
		}
		else 
		{
			PrintWriter out= response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Ya existe un socio con el dni');");//FALTA MOSTRAR EL ID DE SOCIO
			out.println("location='altaSocio.jsp';");
			out.println("</script>");
			
		}
		
		
	}

}

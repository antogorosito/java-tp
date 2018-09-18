package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.*;
import negocio.*;

/**
 * Servlet implementation class devoluciones
 */
@WebServlet({ "/devoluciones", "/Devoluciones" })
public class devoluciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devoluciones() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String op = request.getParameter("opc");
		if(op.equals("Buscar"))
		{
			if (request.getParameter("idEjemplar") == "") 
			{
				
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Id vacio');");
				out.println("location='devoluciones.jsp';");
				out.println("</script>");
			} 
			else
			{
				int id = Integer.parseInt(request.getParameter("idEjemplar"));
				CtrlEjemplar ce = new CtrlEjemplar();
				Ejemplar e = ce.getOne(id); // veo si existe el id
				if (e == null )
				{
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Id ejemplar incorrecto');");
					out.println("location='devoluciones.jsp';");
					out.println("</script>");
				} 
				else 
				{
					CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
					LineaDePrestamo lp=clp.getOne(id);//me fijo si esta pendiente de devolucion
					if (lp == null)
					{
						PrintWriter out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('El id ingresado no esta pendiente de devolucion');");
						out.println("location='devoluciones.jsp';");
						out.println("</script>");
					}
					else
					{
						
						HttpSession session = request.getSession();
						
						CtrlSocio cs=new CtrlSocio();
						Socio s=cs.getOne(lp.getSocio().getIdSocio());
						request.setAttribute("socio",s);	
						
						session.setAttribute("lineaPre",lp);
						request.getRequestDispatcher("/devuelto.jsp").forward(request, response);
					}
				}
			}
			
		}//termina el buscar
			
	}
		
		
	

}

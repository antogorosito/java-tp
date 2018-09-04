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

import com.mysql.cj.Session;

import negocio.*;
import entidades.*;
/**
 * Servlet implementation class agregar
 */
@WebServlet("/agregar")
public class agregar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public agregar() {
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
	
		int id=Integer.parseInt(request.getParameter("idEjemplar"));
		CtrlEjemplar ce=new CtrlEjemplar();
		Ejemplar e=ce.getOne(id);
		
		
		if(e==null)
		{
			PrintWriter out= response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Id ejemplar incorrecto');");
			out.println("location='agregar.jsp';");
			out.println("</script>");
		}
		else
		{
			//buscar si esta en otro prestamo de otra persona sin devolver aun
			if(ce.existe(id) ==true)
			{
				PrintWriter out= response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('ejemplar no disponible');");
				out.println("location='agregar.jsp';");
				out.println("</script>");
			}
			else
			{
				CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
				HttpSession session= request.getSession();
				Socio s=(Socio)session.getAttribute("socio");
				boolean rta= clp.getOne(s.getIdSocio(), id);
				if (rta==true)
				{
					PrintWriter out= response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Posee otros ejemplares del mismo libro ya prestados');");
					out.println("location='agregar.jsp';");
					out.println("</script>");
				}
				else 
				{
					//que cree el prestamo la primera vez y las siguientes solo agregue
					
				//VEEEEEEEEEEEEEEEEEEEER
					
					Prestamo ap=(Prestamo)session.getAttribute("prestamo");
					if (ap==null)
					{
					/* creo el prestamo*/	
						Prestamo p=new Prestamo(s);
						CtrlPrestamo cp=new CtrlPrestamo();
						cp.add(p);
						Prestamo pp2=cp.getOne(p);
						session.setAttribute("prestamo",pp2);				
					}
				
				/*debo buscar si el libro ya esta en el prestamo y si no esta dsp agregar la linea*/
					
					CtrlPrestamo cp=new CtrlPrestamo();//ver

					Prestamo pp=cp.getOne(s);//ver

					LineaDePrestamo lp= new LineaDePrestamo(s,pp,e);
					boolean resultado=clp.buscarLinea(lp);
					if(resultado==true)
					{
						PrintWriter out= response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('ya esta en el prestamo');");
						out.println("location='agregar.jsp';");
						out.println("</script>");
					}
					else
					{
						clp.add(lp);
					//devolver TODAS LAS LINEAS DEL PRESTAMO
						ArrayList<LineaDePrestamo> lineas = clp.getAll(pp);
						request.setAttribute("lineas", lineas );
					//	buscar minima cantidad de dias en las lineas de prestamo
						int dias=clp.minimoDias(pp);
					
						request.getSession().setAttribute("dias",dias);
						request.getRequestDispatcher("/agregar.jsp").forward(request, response);
					}
				}
			}
			
		
		}
		
	}

}

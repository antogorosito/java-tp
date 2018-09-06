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
						cp.add(p); //agrego el prestamo a la bd
						Prestamo pp=cp.getOne(p); // obtengo el prestamo, ya que antes no lo tenia con id.
						session.setAttribute("prestamo",pp);				
					}
				
				/*debo buscar si el libro ya esta en el prestamo y si no esta dsp agregar la linea*/
					
					CtrlPrestamo cp=new CtrlPrestamo();//ver 

					Prestamo pp=cp.getOne(s);//ver // obtengo el prestamo, ya que antes no lo tenia con id.(cdo entro mas de una vez)

					LineaDePrestamo lp= new LineaDePrestamo(s,pp,e);
					boolean resultado=clp.buscarLinea(lp); // me fijo si la linea de prestamo ya existe
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
						int c=(Integer)session.getAttribute("cantPosible"); // fijarse que no se supere la cantidad permitida de libros prestados
						if(c<=0) 
						{
							String msj="No se pueden agregar mas libros";
							request.getSession().setAttribute("error",msj);
						}
						else 
						{
							clp.add(lp); // agrego la linea de prestamo a la bd
							c=c-1;
							request.getSession().setAttribute("cantPosible",c);

						}	
							ArrayList<LineaDePrestamo> lineas = clp.getAll(pp);//devolver TODAS LAS LINEAS DEL PRESTAMO
							request.setAttribute("lineas", lineas );
					
							int dias=clp.minimoDias(pp);//	buscar minima cantidad de dias en las lineas de prestamo
					
							request.getSession().setAttribute("dias",dias);
							request.getRequestDispatcher("/agregar.jsp").forward(request, response);
						
				
						
					}
				}
			}
			
		
		}
		
	}

}
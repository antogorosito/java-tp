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
 * Servlet implementation class devueltoVarios
 */
@WebServlet("/devueltoVarios")
public class devueltoVarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devueltoVarios() {
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
		String op = request.getParameter("opp");
		if(op.equals("Agregar"))
		{
			if(request.getParameter("idEjemplar1") == "" && request.getParameter("idEjemplar2") == "" && request.getParameter("idEjemplar3") == "" && request.getParameter("idEjemplar4") == "" && request.getParameter("idEjemplar5") == "") // si estan todos vacios 
			{
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Todos los id estan vacios');");
				out.println("location='devueltoVarios.jsp';");
				out.println("</script>");
			}  
			else 
			{
				HttpSession session = request.getSession();
				// me tengo que fijar si alguno de los input esta vacio
				
				ArrayList<Integer> arreglo=new ArrayList<Integer>();
				
				if(request.getParameter("idEjemplar1")!="")
				{
					request.setAttribute("id1", request.getParameter("idEjemplar1"));
					arreglo.add(Integer.parseInt(request.getParameter("idEjemplar1")));
				}
				if(request.getParameter("idEjemplar2")!="")
				{
					request.setAttribute("id2", request.getParameter("idEjemplar2"));
					arreglo.add(Integer.parseInt(request.getParameter("idEjemplar2")));
				}
				if(request.getParameter("idEjemplar3")!="")
				{
					request.setAttribute("id3", request.getParameter("idEjemplar3"));
					arreglo.add(Integer.parseInt(request.getParameter("idEjemplar3")));
				}
				if(request.getParameter("idEjemplar4")!="")
				{
					request.setAttribute("id4", request.getParameter("idEjemplar4"));
					arreglo.add(Integer.parseInt(request.getParameter("idEjemplar4")));
				}
				if(request.getParameter("idEjemplar5")!="")
				{
					request.setAttribute("id5", request.getParameter("idEjemplar5"));
					arreglo.add(Integer.parseInt(request.getParameter("idEjemplar5")));
				}
				String error="No existen los id: ";
				String error2="No estan pendientes en devolucion los id: ";
			
				ArrayList<LineaDePrestamo> lineas=new ArrayList<LineaDePrestamo>();
			
				for(Integer i:arreglo) 
				{
					CtrlEjemplar ce = new CtrlEjemplar();
					Ejemplar e = ce.getOne(i);
					if(e==null)
					{
						error=error +i +" " ;
					
						request.setAttribute("error", error);
					}
					else 
					{
						CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
						LineaDePrestamo lp=clp.getOne(i);
						if(lp==null)
						{
							error2=error2+i+" ";
							request.setAttribute("error2", error2);
						}
						else
						{
														
							lineas.add(lp);
						}
					}
				}
				if(request.getAttribute("error")==null && request.getAttribute("error2")==null) // estan pendientes de devolucion y existen los id
				{
				
					request.setAttribute("lineas",lineas);
					request.getRequestDispatcher("/devueltoVariosRegistrar.jsp").forward(request, response);
					
				}
				request.getRequestDispatcher("/devueltoVarios.jsp").forward(request, response);
			} // fin else e que esten todos vacios
				
		}//fin agregar
		if(op.equals("Cancelar"))
		{
			request.getRequestDispatcher("/devoluciones.jsp").forward(request, response);
		}
	}

}

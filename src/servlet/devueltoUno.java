package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.*;
import entidades.*;

/**
 * Servlet implementation class devueltoUno
 */
@WebServlet("/devueltoUno")
public class devueltoUno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devueltoUno() {
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
		request.getSession().setAttribute("errorDevUno",null);
		String op = request.getParameter("opc");
		if(op.equals("Agregar"))
		{
			
				int id = Integer.parseInt(request.getParameter("idEjemplar"));
				CtrlEjemplar ce = new CtrlEjemplar();
				Ejemplar e = ce.getOne(id); // veo si existe el id
				if (e == null )
				{
					String msj = "No existe el ejemplar con el id "+id;
					request.getSession().setAttribute("errorDevUno", msj);
					request.getRequestDispatcher("WEB-INF/lib/devueltoUno.jsp").forward(request, response);
				
				} 
				else 
				{
					CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
					LineaDePrestamo lp=clp.getOne(id);//me fijo si esta pendiente de devolucion
					if (lp == null)
					{
						String msj = "El ejemplar "+id+" no esta pendiente de devolucion";
						request.getSession().setAttribute("errorDevUno", msj);
						request.getRequestDispatcher("WEB-INF/lib/devueltoUno.jsp").forward(request, response);
						
					}
					else
					{
						request.getSession().setAttribute("errorDevUno",null);
						HttpSession session = request.getSession();
										
						session.setAttribute("lineaPre",lp);
						request.getRequestDispatcher("WEB-INF/lib/devueltoUnoRegistrar.jsp").forward(request, response);
					}
				}
			
			
		}//termina el agregar
		if(op.equals("Cancelar"))
		{
			request.getSession().setAttribute("errorDevUno",null);
			request.getRequestDispatcher("WEB-INF/lib/menu.jsp").forward(request, response);
		}
	
		
		

	}//fin fucnion

}



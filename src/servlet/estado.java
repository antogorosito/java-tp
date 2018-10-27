package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.*;
import negocio.*;

/**
 * Servlet implementation class estado
 */
@WebServlet("/estado")
public class estado extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public estado()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CtrlSocio cs=new CtrlSocio();
	 	ArrayList<Socio> sociosInhabilitar = cs.getAllAInhabilitar();
	 	request.getSession().setAttribute("sociosInhabilitar",sociosInhabilitar );
	 	ArrayList<Socio> sociosHabilitar = cs.getAllAHabilitar();
	 	request.getSession().setAttribute("sociosHabilitar",sociosHabilitar );
	 	request.getRequestDispatcher("WEB-INF/lib/estado.jsp").forward(request, response);
		
		
	}

}

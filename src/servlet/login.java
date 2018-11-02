package servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.*;
import util.AppDataException;
import entidades.*;

/**
 * Servlet implementation class login
 */
@WebServlet({ "/login", "/Login" })
public class login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() 
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
			
		try 
		{
		String u=request.getParameter("usuario");
		String c=request.getParameter("clave");
		CtrlUsuario cu= new CtrlUsuario();
		
		Usuario usuario= cu.getOne(u,c);
	
		
			HttpSession session = request.getSession(); 
			session.setAttribute("usuario", usuario);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		catch(AppDataException ape)
		{
			request.setAttribute("errorLogin",ape.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		catch (Exception e) {
			
		}
	
		
		
	}

}

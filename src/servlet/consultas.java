package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.*;
import entidades.*;


/**
 * Servlet implementation class consultas
 */
@WebServlet("/consultas")
public class consultas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public consultas() {
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
		
		String t=request.getParameter("titulo");
		CtrlEjemplar ce=new CtrlEjemplar();
		ArrayList<Ejemplar> ejemplares=ce.buscar(t);
		request.getSession().setAttribute("titulo",t);
		request.getSession().setAttribute("listaejemplares", ejemplares);
		
		request.getRequestDispatcher("/consultas.jsp").forward(request, response);
		
		
		
	}

}

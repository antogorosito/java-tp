package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import entidades.Libro;
import negocio.CtrlLibro;

/**
 * Servlet implementation class altaLibro
 */
@WebServlet("/altaLibro")
public class altaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaLibro() {
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
		String op=request.getParameter("op");
		
		if(op.equals("Registrar")) 
		{
			String t=request.getParameter("titulo");
			String i=request.getParameter("ISBN");
			int n=Integer.parseInt(request.getParameter("nroEdicion"));
			String d=request.getParameter("fechaEdicion").toString();
			int m=Integer.parseInt(request.getParameter("cantDiasMaxPrestamo"));
			CtrlLibro cs=new CtrlLibro();
			Libro  l=cs.getOne(i);
			if(l==null)
			{
				l=new Libro(t,i,n,d,m);
				cs.add(l);		
				request.getSession().setAttribute("Libro", l);
				request.getRequestDispatcher("WEB-INF/lib/altaEjemplar.jsp").forward(request, response);
			}
			else 
			{
			
				PrintWriter out= response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Ya existe un libro con ese ISBN');");
				out.println("location='WEB-INF/lib/altaLibro.jsp';");
				out.println("</script>");
			
			}
		}//fin if
		if(op.equals("Cancelar"))
		{
			request.getRequestDispatcher("WEB-INF/lib/menu.jsp").forward(request, response);
		}
	}
}



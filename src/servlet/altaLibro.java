package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		String t=request.getParameter("titulo");
		String i=request.getParameter("ISBN");
		String n=request.getParameter("nroEdicion");
		String f=request.getParameter("fechaEdicion");
		String m=request.getParameter("cantDiasMaxPrestamo");
				
		CtrlLibro cs=new CtrlLibro();
		Libro  l=cs.getOne(i);
		if(l==null)
		{
		//Libro libro=new Libro(t,i,n,f,m);
		//cs.add(libro);
		
		PrintWriter out= response.getWriter();
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Nuevo libro registrado');");
		out.println("location='altaLibro.jsp';");
		out.println("</script>");
		}
		else 
		{
			/*JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
	        JOptionPane.showMessageDialog(null, "EL ISBN del libro es: " + s.getISBNLibro());*/
			
			PrintWriter out= response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Ya existe un libro con ese ISBN');");//FALTA MOSTRAR EL ID DE SOCIO
			out.println("location='altaLibro.jsp';");
			out.println("</script>");
			
		}
	}
}



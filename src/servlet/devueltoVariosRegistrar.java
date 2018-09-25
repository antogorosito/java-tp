package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.LineaDePrestamo;
import entidades.PoliticaSancion;
import entidades.Sancion;
import entidades.Socio;
import negocio.CtrlLineaDePrestamo;
import negocio.CtrlPoliticaSancion;
import negocio.CtrlSancion;
import negocio.CtrlSocio;

/**
 * Servlet implementation class devueltoVariosRegistrar
 */
@WebServlet("/devueltoVariosRegistrar")
public class devueltoVariosRegistrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devueltoVariosRegistrar() {
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
		String op = request.getParameter("opcion");
		HttpSession session = request.getSession();
		if(op.equals("Registrar"))
		{
	
			ArrayList<LineaDePrestamo> lineasPrestamos=(ArrayList<LineaDePrestamo>)session.getAttribute("lineas");
		
			
			CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
			//verificar lo de la fecha. si esta ok cambio solo la linea 
			// sino cambiar tmb estado del socio
			
			
		
			ArrayList<LineaDePrestamo> ll=new ArrayList<LineaDePrestamo>();// para guardar las lineas con sancion
		
			for(LineaDePrestamo l:lineasPrestamos)
			{
				java.util.Date fecha = new  java.util.Date();
				DateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
				String fechaActu=Formato.format(fecha);
			 	String fechadev=Formato.format(l.getPrestamo().getFechaADevolver());
			 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try 
				{
					java.util.Date date1 = sdf.parse(fechaActu);
					java.util.Date date2=sdf.parse(fechadev);	// devuelve <0 si actual es menor a devolver, devuelve 0 si son iguales,devuelve >0 si actual es mayor a devolver
					if (date1.compareTo(date2) < 0 || date1.compareTo(date2)==0 )
					{
						clp.update(l);
							//msj 
			        }else if (date1.compareTo(date2) > 0) {              //VEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEER
				        												
				        	 //diferencia entre la fecha que habia que devolverla y hoy
				            int diasDif=(int)((date1.getTime()-date2.getTime())/86400000); 
				            		
				            
				            CtrlSocio cs=new CtrlSocio();
				            boolean est=false;//inhabilito
				            Socio s=l.getSocio();
				            cs.update(s, est);
				            
				            //obtengo tiempo de sancino
				         CtrlPoliticaSancion cps=new CtrlPoliticaSancion();
				            PoliticaSancion ps=cps.getOne(diasDif);
				            if(ps==null) // por si son mas dias de atraso que los que tienen la bd, lo sanciono por el maximo de dias cargado.
				            {
				            	ps=cps.getMax();
				            }
				    
				            ll.add(l);
				      //      CtrlSancion css=new CtrlSancion();
				        //    Sancion sa=new Sancion(ps.getDiasDeSancion(),l.getSocio());
				          //  css.add(sa);
				            //clp.update(l,sa);

				        //msj
				        }  
					} catch (ParseException e) {
						
						e.printStackTrace();
					}		
		  
			}//fin for
			
		// CREAR LA SANCION, DEPENDE DEL NRO DE PRESTAMO
			//los ejemplares del mismo prestamo, tienen misma sancion
			
		for(LineaDePrestamo li:ll)
		{
			
		 	
		
		 	
		//	clp.update(li,sa);
			
		}//fin for2
		
		
		}
		if(op.equals("Cancelar"))
		{
			request.getRequestDispatcher("/devoluciones.jsp").forward(request, response);
		}
	}

}

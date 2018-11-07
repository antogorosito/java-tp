package servlet;

import java.io.IOException;
import java.sql.Date;
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

import entidades.*;
import negocio.*;
import util.AppDataException;
import util.Emailer;

/**
 * Servlet implementation class devolverRegistrar
 */
@WebServlet("/devolverRegistrar")
public class devolverRegistrar extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devolverRegistrar()
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String op=request.getParameter("op");
		boolean band=false;
		Date fec=null;
		if(op.equals("Registrar"))
		{
			ArrayList<LineaDePrestamo> lista=new ArrayList<LineaDePrestamo>();
			CtrlLineaDePrestamo clp=new CtrlLineaDePrestamo();
			String[] lin=request.getParameterValues("chk");
			if(lin!=null)
			{
				try 
				{
					for(int i=0;i<lin.length;i++) //obtengo las lineas de prestamos seleccionadas
					{
						LineaDePrestamo l=clp.obtener(Integer.parseInt(lin[i]));
						lista.add(l);
					}
					
					for(LineaDePrestamo ll:lista)
					{
						java.util.Date fecha = new  java.util.Date();
						DateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
						String fechaActu=Formato.format(fecha);
						String fechadev=Formato.format(ll.getPrestamo().getFechaADevolver());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						
						java.util.Date date1 = sdf.parse(fechaActu);
						java.util.Date date2=sdf.parse(fechadev);
						// 	devuelve <0 si actual es menor a devolver 
						//  devuelve 0 si son iguales. 
						//  devuelve >0 si actual es mayor a devolver
						if (date1.compareTo(date2) < 0 || date1.compareTo(date2)==0 )
						{
							clp.update(ll);
						}
						else if (date1.compareTo(date2) > 0) 
						{
							band=true;
							int diasDif=(int)((date1.getTime()-date2.getTime())/86400000);  
							CtrlSocio cs=new CtrlSocio();
							boolean est=false;
							Socio s=ll.getPrestamo().getSocio();
							cs.update(s, est); 
							CtrlPoliticaSancion cps=new CtrlPoliticaSancion();
							PoliticaSancion ps=cps.getOne(diasDif);
							if(ps==null) // por si son mas dias de atraso que los que tienen la bd, lo sanciono por el maximo de dias cargado.
							{
								ps=cps.getMax();
							}
							CtrlSancion css=new CtrlSancion();
							Sancion sa=css.getOne(ll);
							if(sa==null)
							{
								sa=new Sancion(ps.getDiasDeSancion(),ll.getPrestamo().getSocio());
								css.add(sa);
								fec=sa.getFechaSancionHasta();
								
							}
							clp.update(ll,sa);
						}
					} 
					if(band=true)
					{
						HttpSession session = request.getSession();
						Socio s = (Socio) session.getAttribute("socioD");
						String msj="Se informa que se ha registrado la devolucion de los ejemplares.\n Se lo sanciono hasta la fecha: "+fec+".\n Atte. Biblioteca Rosario";
						Emailer.getInstance().send(s.getEmail(),"Confirmacion devolucion de prestamo",msj);
					}
					int nro=5;
					request.getSession().setAttribute("lineasD",null);
					request.getSession().setAttribute("socioD",null);
					request.setAttribute("opc",nro);	
					request.getRequestDispatcher("WEB-INF/lib/mensaje.jsp").forward(request, response);
				}
				catch(AppDataException ape)
				{
					request.setAttribute("error",ape.getMessage());
					request.getRequestDispatcher("WEB-INF/lib/devolverRegistrar.jsp").forward(request, response);
				}
				catch (Exception e) 
				{
					request.setAttribute("error",e.getMessage());
					request.getRequestDispatcher("WEB-INF/lib/devolverRegistrar.jsp").forward(request, response);
				}
			}
			else
			{
				String msj="No se selecciono ningun libro para devolver";
				request.setAttribute("error",msj);
				request.getRequestDispatcher("WEB-INF/lib/devolverRegistrar.jsp").forward(request, response);
			}
		}
		if(op.equals("Cancelar"))
		{
			request.getSession().setAttribute("lineasD",null);
			request.getSession().setAttribute("socioD",null);
			request.getRequestDispatcher("WEB-INF/lib/devolver.jsp").forward(request, response);
		}
	}

}


package com.emergentes.controlador;

import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletControlador", urlPatterns = {"/ServletControlador"})
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    HttpSession ses = request.getSession();
      
      if(ses.getAttribute("listaprod") == null){
          ArrayList<Producto> listap = new ArrayList<Producto>();
          ses.setAttribute("listaprod", listap);
      }
      ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listaprod");
      
      String opcion = request.getParameter("opcion");
      String opcn = (opcion != null) ? request.getParameter("opcion") : "view";
      
      Producto ob1 = new Producto();
      int id, pos;
      
      
     switch (opcn){
         case "nuevo"://Insertar nuevo registro
         request.setAttribute("producto", ob1);
         request.getRequestDispatcher("editar.jsp").forward(request, response);
         break;
         
         case "editar" : //modificar registro
         id = Integer.parseInt(request.getParameter("id"));
         pos = buscarIndice(request, id);
         ob1= lista.get(pos);
         request.setAttribute("producto", ob1);
         request.getRequestDispatcher("editar.jsp").forward(request, response);
         break;
         
         case "eliminar": //Modificar registro
            pos = buscarIndice(request, Integer.parseInt(request.getParameter("id")));
            lista.remove(pos);
            ses.setAttribute("listaprod", lista);
            response.sendRedirect("index.jsp");
            break;
         case "view":
             response.sendRedirect("index.jsp");
     }   
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     HttpSession ses = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listaprod");
        
        Producto obj1 = new Producto();
        
        obj1.setId(Integer.parseInt(request.getParameter("id")));
        obj1.setDescripcion(request.getParameter("descripcion"));
        obj1.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        obj1.setPrecio(Double.parseDouble(request.getParameter("precio")));
        obj1.setCategoria(request.getParameter("categoria"));
        
        
        int idpr = obj1.getId();
        
        if(idpr == 0){
            int ultID;
            ultID = ultimoId(request);
            obj1.setId(ultID);
            lista.add(obj1);
        }else{
            lista.set(buscarIndice(request, idpr),obj1);
        }
        ses.setAttribute("listaprod", lista);
        response.sendRedirect("index.jsp");
    }
       private int ultimoId(HttpServletRequest request){
           HttpSession ses = request.getSession();
           ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listaprod");
           
           int idaux = 0;
           
           for(Producto item: lista){
               idaux = item.getId();
           }
           return idaux + 1;
       }
        
       private int buscarIndice(HttpServletRequest request, int id){
          HttpSession ses = request.getSession();
           ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listaprod");
           
           int i=0;
           if(lista.size() > 0){
               while(i < lista.size()){
                   if(lista.get(i).getId()== id){
                       break;
                   }else{
                       i++;
                   }
               }
           }
       return i;
    }
    }




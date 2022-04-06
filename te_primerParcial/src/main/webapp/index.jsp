<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Producto"%>

<%
   ArrayList<Producto> listaap = (ArrayList<Producto>) session.getAttribute("listaprod");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Productos</h1>
          <table border="1">
            <tr>
                <th>
                <p> PRIMER PARCIAL TEM - 742</p>
                <p> Nombre: Karla Odeth Angulo Callisaya </p>
                <p>Carnet: 7089614 L.P. </p>
                </th>
            </tr>
        </table>
        <br><br>
            <a href="ServletControlador?opcion=nuevo">Nuevo Producto</a>
        <br><br>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            <%
                if(listaap != null){
                for(Producto item :listaap){
                %>
                <tr>
                    <td><%= item.getId()%></td>
                    <td><%= item.getDescripcion()%></td>
                    <td><%= item.getCantidad()%></td>
                    <td><%= item.getPrecio()%></td>
                    <td><%= item.getCategoria()%></td>
                    <td><a href="ServletControlador?opcion=editar&id=<%= item.getId()%>">Editar</a></td>
                     <td><a href="ServletControlador?opcion=eliminar&id=<%= item.getId()%>"
                            onclick='return confirm("Esta seguro de eliminar el Producto ?");'>Eliminar</a></td>
                </tr>
                <%
                    }
                  }
               %>
        </table>
    </body>
</html>
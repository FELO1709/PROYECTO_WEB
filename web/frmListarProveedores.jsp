<%@page import="Entidades.*"%>
<%@page import="LogicaNegocio.*"%>
<%@page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Proveedores</title>
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css'>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.4/css/tether.min.css'>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/style.css">
        <!-- Demo CSS -->
        <link rel="stylesheet" href="css/demo.css">
    </head>

    <body>
        <header>             
            <div id="wrapper">
                <div class="overlay">

                    <!-- Sidebar -->
                    <nav class="navbar navbar-inverse fixed-top" id="sidebar-wrapper" role="navigation">
                        <ul class="nav sidebar-nav">
                            <div class="sidebar-header">
                                <div class="sidebar-brand">
                                    <a href="index.html">Menú</a></div></div>
                            <li><a href="index.html"></i> Inicio</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle"  data-toggle="dropdown">Facturas <span class="caret"></span></a>
                                <ul class="dropdown-menu animated fadeInLeft" role="menu">
                                    <li><a href="Frm_Facturar.jsp?txtnumFactura=-1">Nueva Factura</a></li>
                                    <li><a href="frmListarFacturas.jsp">Registro de Facturas</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle"  data-toggle="dropdown">Pagos <span class="caret"></span></a>
                                <ul class="dropdown-menu animated fadeInLeft" role="menu">
                                    <li><a href="">Nuevo Pago</a></li>
                                    <li><a href="frmRegistroPagos.jsp">Transacciones</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle"  data-toggle="dropdown">Reportes <span class="caret"></span></a>
                                <ul class="dropdown-menu animated fadeInLeft" role="menu">
                                    <li><a href="frmResumenVentasMes.jsp">Mensual</a></li>
                                    <li><a href="frmResumenPersonalizado.jsp">Personalizado</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle"  data-toggle="dropdown">Autores <span class="caret"></span></a>
                                <ul class="dropdown-menu animated fadeInLeft" role="menu">
                                    <div class="dropdown-header">Mantenimientos</div>
                                    <li><a href="frmListarClientes.jsp">Clientes</a></li>
                                    <li><a href="frmListarVendedores.jsp">Vendedores</a></li>
                                    <li><a href="frmListarProveedores.jsp">Proveedores</a></li>
                                    <li><a href="frmListarEmpresas.jsp">Empresas</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle"  data-toggle="dropdown">Inventario <span class="caret"></span></a>
                                <ul class="dropdown-menu animated fadeInLeft" role="menu">
                                    <div class="dropdown-header">Mantenimientos</div>
                                    <!--li><a href="frmListarMovimientos.jsp">Movimientos</a></li-->
                                    <li><a href="frmListarProductos.jsp">Productos</a></li>
                                    <li><a href="frmListarMarcas.jsp">Marcas</a></li>
                                    <li><a href="frmListarCategorias.jsp">Categorías</a></li>
                                </ul>
                            </li>
                            <li><a href="frmContacto.jsp">Contacto</a></li>
                        </ul>
                    </nav>
                    <!-- /#sidebar-wrapper -->
                </div>
                <!-- Page Content -->
                <div id="page-content-wrapper">
                    <button type="button" class="hamburger animated fadeInLeft is-closed" data-toggle="offcanvas">
                        <span class="hamb-top"></span>
                        <span class="hamb-middle"></span>
                        <span class="hamb-bottom"></span>
                    </button>
                    </header>
                    <div class="container">
                        <div class="row">
                            <div class="card-header">
                                <h1>Proveedores</h1>
                            </div>                   
                            <br>

                            <!-- El formulario se va a cargar a si mismo -->    
                            <form action="frmListarProveedores.jsp" method="post">
                                <div class="form-group">
                                    <div class="input-group">
                                        <input type="text" id="txtnombre" name="txtnombre" value="" 
                                               placeholder="Buscar por Nombre" class="form-control"/>
                                        &nbsp;&nbsp;
                                        <input type="submit" id="btnbuscar" name="btnbuscar" value="Buscar" 
                                               class="btn btn-primary"/><br><br>
                                    </div> 
                                </div> 
                            </form>
                            <hr>
                            <table class="table">
                                <thead>
                                    <tr id="titulos">
                                        <th>Código</th>
                                        <!--th>Cód E</th-->
                                        <th>Empresa</th>
                                        <th>Cédula</th>
                                        <th>Nombre</th>
                                        <th>1er Apellido</th>
                                        <th>2do Apellido</th>
                                        <th>Teléfono</th>
                                        <th>Correo</th>
                                        <th>Dirección</th>
                                        <th>Estado</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <!--Hacer una consulta a la bd para obtener la lista de clientes
                                        mediante código JAVA--> 
                                    <% 
                                        String nombre = "";
                                        String condicion = "";
                                        //si el txtnombre no está vacio
                                        if(request.getParameter("txtnombre")!=null){
                                            nombre = request.getParameter("txtnombre");
                                            condicion = "NOMBRE LIKE '%" + nombre + "%'";
                                        }
                                        BLProveedores logica = new BLProveedores();
                                        List<EntidadProveedor> datos;
                                        datos = logica.ListarProveedores(condicion);
            
                                        //for para llenar la tabla
                                        for (EntidadProveedor registro:datos){
                                    %>
                                    <tr>
                                        <% int codigo=registro.getCodProveedor();%>
                                        <td><%=codigo %></td>
                                        <!--td></td-->
                                        <td><%=registro.getNombreEmpresa()%></td>
                                        <td><%=registro.getCedula()%></td>
                                        <td><%=registro.getNombre()%></td>
                                        <td><%=registro.getApellido1()%></td>
                                        <td><%=registro.getApellido2()%></td><!-- comment -->
                                        <td><%=registro.getTelefono()%></td><!-- comment -->
                                        <td><%=registro.getCorreo()%></td>
                                        <td><%=registro.getDireccion()%></td>
                                        <td><%=registro.getEstado()%></td>
                                        <!--Columna para los botones -->
                                        <td>
                                            <!--Botón para modificar -->
                                            <a href="frmProveedores.jsp?idCrearModificar=<%=codigo%>"> <i id="edit" class="fas fa-user-edit"></i> </a> |
                                            <!--Botón para Eliminar -->
                                            <a href="EliminarProveedores?idEliminar=<%=codigo%>"> <i id="delete" class="fas fa-trash-alt"></i> </a> |
                                        </td>
                                    </tr>
                                    <%}%><!--Para cerrar el for --> 
                                </tbody>
                            </table>
                        </div>

                        <a href="frmProveedores.jsp?idCrearModificar=-1"> Agregar un nuevo Proveedor </a> |
                        <a href="frmListarProveedores.jsp"> Actualizar </a> |
                        <a href="index.html"> Regresar al Inicio </a> 
                        
                    </div>
                    <footer class="credit">&copy; Hecho por Luis Felipe Miranda Rojas </footer>            
                    <script src='https://code.jquery.com/jquery-3.3.1.slim.min.js'></script>
                    <script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js'></script>
                    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js'></script>
                    <script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.4/js/tether.min.js'></script>
                    <script  src="js/script.js"></script>
                    </body>
                    </html>

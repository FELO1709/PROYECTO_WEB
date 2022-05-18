<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="LogicaNegocio.BLCategorias"%>
<%@page import="Entidades.EntidadCategoria"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mantenimiento de Categorías</title>
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
                            <li><a href="index.html">Inicio</a></li>
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
                            <div class="col-md-8 mx-auto">
                                <div class="card-header">
                                    <h1>Mantenimiento de Categorías</h1>
                                </div>

                                <%
                                    String id = request.getParameter("idCrearModificar");
                                    int codigo = Integer.parseInt(id);
                                    EntidadCategoria categoria;
                                    BLCategorias logica = new BLCategorias();

                                    if (codigo > 0) {
                                        // Si el cliente existe, lo obtiene enviándole una
                                        // CONDICIÓN al método que obtiene un registro:
                                        categoria = logica.ObtenerRegistro("COD_CATEGORIA=" + id);
                                    } else {
                                        // Sino, crea uno nuevo
                                        categoria = new EntidadCategoria();
                                    }
                                %>

                                <form action="CrearModificarCategoria" method="post" id="form_AgregarModificar">                 
                                    <div class="form-group">
                                        <%if (codigo > 0) {%>
                                        <!-- Si el cliente existe, mostrará la etiqueta y el ID, deshabilitado para que no se pueda editar -->
                                        <label for="txtCodigo" class="control-label">Código</label>
                                        <input type="number" id="txtCodigo" name="txtCodigo" value="<%=categoria.getCodCategoria()%>" readonly class="form-control"/><br>
                                        <%} else {%>
                                        <!-- Sino, el campo ID se le asigna -1 y no se muestra en pantalla -->
                                        <input type="hidden" id="txtCodigo" name="txtCodigo" value="-1"/><br>
                                        <%}%>
                                    </div>
                                    <!-- form-group para los controles de Nombre -->
                                    <div class="form-group">
                                        <label for="txtNombre" class="control-label">Nombre</label>
                                        <input type="text" id="txtNombre" name="txtNombre" value="<%=categoria.getNombreCategoria()%>" class="form-control"/><br>
                                    </div>

                                    <!-- form-group para los BOTONES de guardar y regresar  -->
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input type="submit" id="btnGuardar" value="Guardar" class="btn btn-primary"/> &nbsp;&nbsp;
                                            <input type="button" id="btnRegresar" value="Regresar" onclick="location.href = 'frmListarCategorias.jsp'" class="btn btn-secondary"/>
                                        </div>
                                    </div>

                                </form>

                            </div> <!-- clase que crea las 6 columnas -->

                        </div> <!-- class row, div de la fila -->

                    </div> <!-- class container -->

                    <!-- Agregamos las referencias a Bootstrap, jquery y jquery-validation -->
                    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
                    <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
                    <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
                    <script  src="js/script.js"></script>
                    <script>
                                                // Cuando el documento está listo
                                                $(document).ready(function () {

                                                    $("#form_AgregarModificar").validate({
                                                        // Reglas que deseamos personalizar:
                                                        rules: {
                                                            txtNombre: {required: true, maxlength: 80}
                                                        },
                                                        // Mensajes que deseamos personalizar: 
                                                        messages: {
                                                            txtNombre: "El campo de Nombre es obligatorio (max 80 caracteres)",
                                                        },
                                                        errorElement: 'span'
                                                    });
                                                });
                    </script>

                    </body>
                    </html>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="LogicaNegocio.*"%>
<%@page import="Entidades.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mantenimiento de Vendedores</title>
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
                                    <h1>Mantenimiento de Vendedores</h1>
                                </div>

                                <%
                                    String id = request.getParameter("idCrearModificar");
                                    int codigo = Integer.parseInt(id);
                                    EntidadVendedor vendedor;
                                    BLVendedores logica = new BLVendedores();

                                    if (codigo > 0) {
                                        // Si el cliente existe, lo obtiene enviándole una
                                        // CONDICIÓN al método que obtiene un registro:
                                        vendedor = logica.ObtenerRegistro("COD_VENDEDOR=" + id);
                                    } else {
                                        // Sino, crea uno nuevo
                                        vendedor = new EntidadVendedor();
                                    }
                                %>

                                <form action="CrearModificarVendedor" method="post" id="form_AgregarModificar">                 
                                    <div class="form-group">
                                        <%if (codigo > 0) {%>
                                        <!-- Si el cliente existe, mostrará la etiqueta y el ID, deshabilitado para que no se pueda editar -->
                                        <label for="txtCodigo" class="control-label">Código</label>
                                        <input type="number" id="txtCodigo" name="txtCodigo" value="<%=vendedor.getCodVendedor()%>" readonly class="form-control"/><br>
                                        <%} else {%>
                                        <!-- Sino, el campo ID se le asigna -1 y no se muestra en pantalla -->
                                        <input type="hidden" id="txtCodigo" name="txtCodigo" value="-1"/><br>
                                        <%}%>
                                    </div>
                                 
                                    
                                    <!-- form-group para los controles de Cédula -->
                                    <div class="form-group">
                                        <label for="txtCedula" class="control-label">Cédula</label>
                                        <input type="text" id="txtCedula" name="txtCedula" value="<%=vendedor.getCedula()%>" class="form-control"/><br>
                                    </div>

                                    <!-- form-group para los controles de Nombre -->
                                    <div class="form-group">
                                        <label for="txtNombre" class="control-label">Nombre</label>
                                        <input type="text" id="txtNombre" name="txtNombre" value="<%=vendedor.getNombre()%>" class="form-control"/><br>
                                    </div>

                                    <!-- form-group para los controles de apellido 1 -->
                                    <div class="form-group">
                                        <label for="txtApellido1" class="control-label">1er Apellido</label>
                                        <input type="text" id="txtApellido1" name="txtApellido1" value="<%=vendedor.getApellido1()%>" class="form-control"/><br>
                                    </div><!-- comment -->

                                    <!-- form-group para los controles de apellido 2 -->
                                    <div class="form-group">
                                        <label for="txtApellido2" class="control-label">2do Apellido</label>
                                        <input type="text" id="txtApellido2" name="txtApellido2" value="<%=vendedor.getApellido2()%>" class="form-control"/><br>
                                    </div>

                                    <!-- form-group para los controles de Teléfono -->
                                    <div class="form-group">
                                        <label for="txtTelefono" class="control-label">Teléfono</label>
                                        <input type="text" id="txtTelefono" name="txtTelefono" value="<%=vendedor.getTelefono()%>" class="form-control" placeholder="00-00-00-00"/><br>
                                    </div>

                                    <!-- form-group para los controles de Correo -->
                                    <div class="form-group">
                                        <label for="txtCorreo" class="control-label">Correo</label>
                                        <input type="text" id="txtCorreo" name="txtCorreo" value="<%=vendedor.getCorreo()%>" class="form-control"/><br>
                                    </div>

                                    <!-- form-group para los controles de Dirección -->
                                    <div class="form-group">
                                        <label for="txtDireccion" class="control-label">Dirección</label>
                                        <input type="text" id="txtDireccion" name="txtDireccion" value="<%=vendedor.getDireccion()%>" class="form-control"/><br>
                                    </div>

                                    <!-- form-group para los controles de Dirección -->
                                    <div class="form-group">
                                        <label for="txtEstado" class="control-label">Estado</label>
                                        <input type="text" id="txtEstado" name="txtEstado" value="<%=vendedor.getEstado()%>" class="form-control"><br> 
                                    </div>

                                    <!-- form-group para los BOTONES de guardar y regresar  -->
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input type="submit" id="btnGuardar" value="Guardar" class="btn btn-primary"/> &nbsp;&nbsp;
                                            <input type="button" id="btnRegresar" value="Regresar" onclick="location.href = 'frmListarVendedores.jsp'" class="btn btn-secondary"/>
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
                                                            txtCedula: {required: true, minlength: 9 maxlength: 15},
                                                            txtNombre: {required: true, maxlength: 80},
                                                            txtApellido1: {required: true, maxlength: 80},
                                                            txtApellido2: {required: true, maxlength: 80},
                                                            txtTelefono: {required: true, minlength: 8, maxlength: 20},
                                                            txtCorreo: {required: true, maxlength: 50},
                                                            txtDireccion: {required: true, maxlength: 80},
                                                            txtEstado: {maxlength: 20}

                                                        },
                                                        // Mensajes que deseamos personalizar: 
                                                        messages: {
                                                            txtCedula: "El campo Cédula es obligatorio (mínimo 9 caracteres, máximo 15)",
                                                            txtNombre: "El campo de Nombre es obligatorio (max 80 caracteres)",
                                                            txtApellido1: "El campo de Apellido1 es obligatorio (max 80 caracteres)",
                                                            txtApellido2: "El campo de Apellido2 es obligatorio (max 80 caracteres)",
                                                            txtTelefono: "El campo Teléfono es obligatorio (mínimo 8 caracteres, máximo 20)",
                                                            txtCorreo: "El campo Correo es obligatorio (max 50 caracteres)",
                                                            txtDireccion: "El campo de Dirección es obligatorio (max 80 caracteres)",
                                                            txtEstado: "(max 20 caracteres)"
                                                        },
                                                        errorElement: 'span'
                                                    });
                                                });
                    </script>

                    </body>
                    </html>


<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="LogicaNegocio.*"%>
<%@page import="Entidades.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mantenimiento de Productos</title>
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css'>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.4/css/tether.min.css'>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>
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
                                    <h1>Mantenimiento de Productos</h1>
                                </div>

                                <%
                                    String id = request.getParameter("idCrearModificar");
                                    int codigo = Integer.parseInt(id);
                                    EntidadProducto producto;
                                    BLProductos logica = new BLProductos();

                                    if (codigo > 0) {
                                        // Si el cliente existe, lo obtiene enviándole una
                                        // CONDICIÓN al método que obtiene un registro:
                                        producto = logica.ObtenerRegistro("COD_PRODUCTO=" + id);
                                    } else {
                                        // Sino, crea uno nuevo
                                        producto = new EntidadProducto();
                                    }
                                %>

                                <form action="CrearModificarProducto" method="post" id="form_AgregarModificar">                 
                                    <div class="form-group">
                                        <%if (codigo > 0) {%>
                                        <!-- Si el cliente existe, mostrará la etiqueta y el ID, deshabilitado para que no se pueda editar -->
                                        <label for="txtCodigo" class="control-label">Código</label>
                                        <input type="number" id="txtCodigo" name="txtCodigo" value="<%=producto.getCodProducto()%>" readonly class="form-control"/><br>
                                        <%} else {%>
                                        <!-- Sino, el campo ID se le asigna -1 y no se muestra en pantalla -->
                                        <input type="hidden" id="txtCodigo" name="txtCodigo" value="-1"/><br>
                                        <%}%>
                                    </div>
                                    <div class="form-group">
                                        <%if (codigo > 0) {%>
                                        <div class="input-group">
                                            <input type="hidden" id="txtCodCategoria" name="txtCodCategoria" value="<%=producto.getCodCategoria()%>"
                                                   readonly="" class="form-control">
                                            <input type="text" id="txtNombreCategoria" name="txtNombreCategoria" 
                                                   value="<%=producto.getNombreCategoria()%>" readonly="" class="form-control" 
                                                   placeholder="Seleccione una categoría">&nbsp;&nbsp;
                                                <a id="btnbuscar" class="btn btn-success" data-toggle="modal" 
                                               data-target="#buscarCategoria"><i class="fas fa-search"></i></a> 
                                        </div>
                                        <%} else {%>
                                        <div class="input-group">
                                            <input type="hidden" id="txtCodCategoria" name="txtCodCategoria" value="<%=producto.getCodCategoria()%>"
                                                   readonly="" class="form-control">
                                            <input type="text" id="txtNombreCategoria" name="txtNombreCategoria" 
                                                   value="<%=producto.getNombreCategoria()%>" readonly="" class="form-control" 
                                                   placeholder="Seleccione una categoría">&nbsp;&nbsp;
                                            <a id="btnbuscar" class="btn btn-success" data-toggle="modal" 
                                               data-target="#buscarCategoria"><i class="fas fa-search"></i></a> 
                                        </div>
                                        <%}%>           
                                    </div>

                                    <div class="form-group">
                                        <%if (codigo > 0) {%>
                                        <div class="input-group">
                                            <input type="hidden" id="txtCodMarca" name="txtCodMarca" value="<%=producto.getCodMarca()%>"
                                                   readonly="" class="form-control">
                                            <input type="text" id="txtNombreMarca" name="txtNombreMarca" 
                                                   value="<%=producto.getNombreMarca()%>" readonly="" class="form-control" 
                                                   placeholder="Seleccione una marca">&nbsp;&nbsp;
                                        </div>
                                        <%} else {%>
                                        <div class="input-group">
                                            <input type="hidden" id="txtCodMarca" name="txtCodMarca" value="<%=producto.getCodMarca()%>"
                                                   readonly="" class="form-control">
                                            <input type="text" id="txtNombreMarca" name="txtNombreMarca" 
                                                   value="<%=producto.getNombreMarca()%>" readonly="" class="form-control" 
                                                   placeholder="Seleccione una marca">&nbsp;&nbsp;
                                            <a id="btnbuscar" class="btn btn-success" data-toggle="modal" 
                                               data-target="#buscarMarca"><i class="fas fa-search"></i></a> 
                                        </div>
                                        <%}%>           
                                    </div>
                                    
                                    <!-- form-group para los controles de Nombre -->
                                    <div class="form-group">
                                        <label for="txtNombre" class="control-label">Nombre</label>
                                        <input type="text" id="txtNombre" name="txtNombre" value="<%=producto.getNombre()%>" class="form-control"/><br>
                                    </div>

                                    <!-- form-group para los controles de Descripción -->
                                    <div class="form-group">
                                        <label for="txtDescripcion" class="control-label">Descripción</label>
                                        <input type="text" id="txtDescripcion" name="txtDescripcion" value="<%=producto.getDescripcion()%>" class="form-control"/><br>
                                    </div><!-- comment -->

                                    <!-- form-group para los controles de Existencia -->
                                    <div class="form-group">
                                        <label for="txtExistencia" class="control-label">Existencia</label>
                                        <input type="text" id="txtExistencia" name="txtExistencia" value="<%=producto.getExistencia()%>" class="form-control"/><br>
                                    </div>

                                    <!-- form-group para los controles de Precio de Compra -->
                                    <div class="form-group">
                                        <label for="txtPrecioCompra" class="control-label">Precio de Compra</label>
                                        <input type="text" id="txtPrecioCompra" name="txtPrecioCompra" value="<%=producto.getPrecioCompra()%>" class="form-control" placeholder="00-00-00-00"/><br>
                                    </div>

                                    <!-- form-group para los controles de Precio de Venta -->
                                    <div class="form-group">
                                        <label for="txtPrecioVenta" class="control-label">Precio de Venta</label>
                                        <input type="text" id="txtPrecioVenta" name="txtPrecioVenta" value="<%=producto.getPrecioVenta()%>" class="form-control"/><br>
                                    </div>

                                    <!-- form-group para los controles de Impuesto -->
                                    <div class="form-group">
                                        <label for="txtImpuesto" class="control-label">Impuesto</label>
                                        <input type="text" id="txtImpuesto" name="txtImpuesto" value="<%=producto.getImpuesto()%>" class="form-control"/><br>
                                    </div>

                                    <!-- form-group para los controles de Borrado -->
                                    <div class="form-group">
                                        <label for="txtBorrado" class="control-label">Borrado</label>
                                        <input type="number" id="txtBorrado" name="txtBorrado" value="<%=producto.getBorrado()%>" class="form-control"><br> 
                                    </div>

                                    <!-- form-group para los BOTONES de guardar y regresar  -->
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input type="submit" name="Guardar" id="btnGuardar" value="Guardar" class="btn btn-primary">
                                            <input type="button" id="btnRegresar" value="Regresar" onclick="location.href = 'frmListarProductos.jsp'" class="btn btn-secondary"/>
                                        </div>
                                    </div>

                                </form>

                            </div> <!-- clase que crea las 6 columnas -->

                        </div> <!-- class row, div de la fila -->

                    </div> <!-- class container -->

                    <!-- MODAL DE CATEGORÍAS -->
                    <div class="modal" id="buscarCategoria" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 id="tituloVentana">Buscar Categoría</h5>
                                    <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                            onclick="LimpiarC()">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <!-- TABLA DE CATEGORÍAS -->
                                    <table id="tablaCategorias">
                                        <thead>
                                            <tr>
                                                <th> Código |</th>
                                                <th> Nombre      |</th>
                                                <th> Seleccionar |</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                BLCategorias logicaCategorias = new BLCategorias();
                                                List<EntidadCategoria> datosCategorias;
                                                datosCategorias = logicaCategorias.ListarCategorias("");
                                                for (EntidadCategoria registroC : datosCategorias) {
                                            %>
                                            <tr>
                                                <%
                                                    int codCategoria = registroC.getCodCategoria();
                                                    String nombreCategoria = registroC.getNombreCategoria();
                                                %>
                                                <td><%= codCategoria%></td>
                                                <td><%= nombreCategoria%></td>
                                                <td>
                                                    <a href="#" data-dismiss="modal"
                                                       onClick="SeleccionarCategoria('<%=codCategoria%>', '<%=nombreCategoria%>');">Seleccionar</a>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="LimpiarC()">
                                        Cancelar
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>              

                    <!-- MODAL DE MARCAS -->
                    <div class="modal" id="buscarMarca" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 id="tituloVentana">Buscar Marca</h5>
                                    <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                            onclick="LimpiarM()">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <!-- TABLA DE MARCAS -->
                                    <table id="tablaMarcas">
                                        <thead>
                                            <tr>
                                                <th> Código |</th>
                                                <th> Nombre      |</th>
                                                <th> Seleccionar |</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                BLMarcas logicaMarcas = new BLMarcas();
                                                List<EntidadMarca> datosMarcas;
                                                datosMarcas = logicaMarcas.ListarMarcas("");
                                                for (EntidadMarca registroM : datosMarcas) {
                                            %>
                                            <tr>
                                                <%
                                                    int codMarca = registroM.getCodMarca();
                                                    String nombreMarca = registroM.getNombreMarca();
                                                %>
                                                <td><%= codMarca%></td>
                                                <td><%= nombreMarca%></td>
                                                <td>
                                                    <a href="#" data-dismiss="modal"
                                                       onClick="SeleccionarMarca('<%=codMarca%>', '<%=nombreMarca%>');">Seleccionar</a>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="LimpiarM()">
                                        Cancelar
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>   
                                        
                                        
                    <!-- Agregamos las referencias a Bootstrap, jquery y jquery-validation -->
                    <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
                    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
                    <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
                    <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
                    <script src="lib/DataTables/dataTables.min.js" type="text/javascript"></script>
                    <script src="lib/DataTables/DataTables-1.10.21/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
                    <script  src="js/script.js"></script>
                    <script>
                                        // Cuando el documento está listo
                                        $(document).ready(function () {


                                            $('#tablaCategorias').dataTable({
                                                "lenghtMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                                "language": {
                                                    "info": "Página _PAGE_ de _PAGES_",
                                                    "infoEmpty": "No existen registros disponibles",
                                                    "zeroRecords": "No se encuentran registros",
                                                    "search": "Buscar",
                                                    "infoFiltered": "",
                                                    "lenghtMenu": "Mostrar _MENU_ Registros",
                                                    "paginate": {
                                                        "first": "Primero",
                                                        "last": "Último",
                                                        "next": "Siguiente",
                                                        "previous": "Anterior"
                                                    }
                                                }
                                            });
                                            
                                            $('#tablaMarcas').dataTable({
                                                "lenghtMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                                "language": {
                                                    "info": "Página _PAGE_ de _PAGES_",
                                                    "infoEmpty": "No existen registros disponibles",
                                                    "zeroRecords": "No se encuentran registros",
                                                    "search": "Buscar",
                                                    "infoFiltered": "",
                                                    "lenghtMenu": "Mostrar _MENU_ Registros",
                                                    "paginate": {
                                                        "first": "Primero",
                                                        "last": "Último",
                                                        "next": "Siguiente",
                                                        "previous": "Anterior"
                                                    }
                                                }
                                            });

                                            $("#form_AgregarModificar").validate({
                                                // Reglas que deseamos personalizar:
                                                rules: {
                                                    txtCedula: {required: true, minlength: 8, maxlength: 20},
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

                                        function LimpiarC() {
                                            $("#txtCodCategoria").val("");
                                            $("#txtNombreCategoria").val("");
                                        }
                                        
                                         function LimpiarM() {
                                            $("#txtCodMarca").val("");
                                            $("#txtNombreMarca").val("");
                                        }

                                        function SeleccionarCategoria(codCategoria, nombreCategoria) {
                                            $("#txtCodCategoria").val(codCategoria);
                                            $("#txtNombreCategoria").val(nombreCategoria);
                                        }
                                        
                                        function SeleccionarMarca(codMarca, nombreMarca) {
                                            $("#txtCodMarca").val(codMarca);
                                            $("#txtNombreMarca").val(nombreMarca);
                                        }

                    </script>

                    </body>
                    </html>

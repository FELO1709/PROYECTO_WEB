<%@page import="java.util.*"%>
<%@page import="Entidades.*"%>
<%@page import="LogicaNegocio.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css'>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.4/css/tether.min.css'>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/> 
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/> 
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/> 
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/style.css">
        <title>Facturación</title>

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
                </div>
        </header>

        <div class="container">  <!-- container y card-header son clases de BOOTSTRAP -->
            <div class="row">
                <div class="col-md-8 mx-auto">
                    <div class="card-header">
                        <h1>Facturación</h1>

                    </div>

                    <div class="container"> 
                        <div class="row">
                            <div class="col-10"><hl></h1></div>
                        </div>

                        <%
                            int numFactura = -1;
                            double total = 0;
                            EntidadEncabezadoF EntidadFactura;
                            BLEncabezados logicaFactura = new BLEncabezados();
                            BLDetalles logicaDetalle = new BLDetalles();
                            List<EntidadDetalle> DatosDetalles = null;
                            if (request.getParameter("txtnumFactura") != null && Integer.parseInt(request.getParameter("txtnumFactura")) != -1) {
                                numFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
                                EntidadFactura = logicaFactura.ObtenerRegistro("Num_Factura=" + numFactura);
                                DatosDetalles = logicaDetalle.ListarDetalles("Num_Factura=" + numFactura);
                            } else {
                                EntidadFactura = new EntidadEncabezadoF();
                                EntidadFactura.setNumFactura(-1);
                                Date fecha = new Date();
                                java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                                EntidadFactura.setFechaFactura(fechasql);
                            }
                        %>    
                        <br/>

                        <!-- Formulario para los datos de la factura --> 
                        <form action="Facturar" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <p>
                                        <label><input type="radio" name="tipofactura" value="CREDITO"> CRÉDITO</label><br>
                                        <label><input type="radio" name="tipofactura" value="CONTADO"> CONTADO</label><br>
                                    </p>
                                    <p>
                                        <input type="button" value="DEFINIR" style="background-color: #28a745; color: #fff" onclick="capturar()">
                                        <input type="reset" value="QUITAR" style="background-color: #ff004c; color: #fff">
                                    </p>
                                </div>
                            </div>
                            <script>
                                function capturar() {
                                    var resultado = "";
                                    var porNombre = document.getElementsByName("tipofactura");
                                    for (var i = 0; i < porNombre.length; i++) {
                                        if (porNombre[i].checked) {
                                            resultado = porNombre[i].value;
                                        }
                                        document.getElementById("txtTipoFactura").value = resultado;
                                    }
                                    document.addEventListener('DOMContentLoaded', function () {
                                        let inputs = document.querySelector('inpur[type="radio"]');
                                        for (let i = 0; i < porNombre.length; i++) {
                                            inputs[i].addEventListener('change', function () {
                                                if (this.checked) {
                                                    document.getElementById("txtTipoFactura").value = this.value;
                                                }
                                            });
                                        }

                                    });
                                }


                            </script>
                            <div class="form-group float-right">

                                <div class="input-group">
                                    <label for="txtnumFactura" class="form-control">Num. Factura</label> 
                                    <input type="text" id="txtnumFactura" name="txtnumFactura" value="<%=EntidadFactura.getNumFactura()%>"
                                           readonly class="form-control"/> 
                                </div>                    

                                <div class="input-group">
                                    <label for="txtFechaFactura" class="form-control">Fecha</label>
                                    <input type="text" id="txtFechaFactura" name="txtFechaFactura" readonly value="<%=EntidadFactura.getFechaFactura()%>"
                                           required class="datepicker form-control"/> 
                                </div>
                                <div class="input-group">
                                    <label for="txtTipoFactura" class="form-control">Tipo Factura</label>
                                    <input type="text" id="txtTipoFactura" name="txtTipoFactura" readonly value="<%=EntidadFactura.getTipoFactura()%>"
                                           required class="form-control">
                                </div>               
                            </div> 
                            <br/> 
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="number" id="txtIdVendedor" name="txtIdVendedor" value="<%=EntidadFactura.getCodVendedor()%>"
                                           readonly="" class="form-control col-1">&nbsp;&nbsp;
                                    <input type="text" id="txtNombreVendedor" name="txtNombreVendedor" 
                                           value="<%=EntidadFactura.getNombreVendedor()%>" readonly="" class="form-control" 
                                           placeholder="Nombre">&nbsp;&nbsp;
                                    <a id="btnbuscarv" class="btn btn-success" data-toggle="modal" 
                                       data-target="#buscarVendedor"><i class="fas fa-search"></i></a> 
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="number" id="txtIdCliente" name="txtIdCliente" value="<%=EntidadFactura.getCodCliente()%>"
                                           readonly="" class="form-control col-1">&nbsp;&nbsp;
                                    <input type="text" id="txtCedula" name="txtCedula" 
                                           value="<%=EntidadFactura.getCedulaCliente()%>" readonly="" class="form-control" 
                                           placeholder="Cédula">&nbsp;&nbsp;
                                    <input type="text" id="txtNombreCliente" name="txtNombreCliente" 
                                           value="<%=EntidadFactura.getNombreCliente()%>" readonly="" class="form-control" 
                                           placeholder="Nombre">&nbsp;&nbsp;
                                    <a id="btnbuscarc" class="btn btn-success" data-toggle="modal" 
                                       data-target="#buscarCliente"><i class="fas fa-search"></i></a> 
                                </div>
                            </div>
                            <hr/> <!-- Inicia el detalle de factura --> 
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="hidden" id="txtIdProducto" name="txtIdProducto" value="" readonly="" class="form-control"/> 
                                    <input type="text" id="txtdescripcion" name="txtdescripcion" value="" class="form-control" readonly
                                           placeholder="Seleccione un producto"/>&nbsp;&nbsp; 
                                    <a id="btnBuscarP" class="btn btn-success" data-toggle="modal" data-target="#buscarProducto">
                                        <i class="fa fa-search"></i></a>&nbsp;&nbsp; 
                                    <input type="number" id="txtcantidad" name="txtcantidad" value="" class="form-control"
                                           placeholder="Cantidad"/> &nbsp;&nbsp; 
                                    <input type="number" id="txtprecio" readonly = "true" name="txtprecio" value="" class="form-control"
                                           placeholder="Precio"/>&nbsp;&nbsp;
                                    <input type="number" id="txtexistencia" readonly name="txtexistencia" value="" class="form-control"
                                           placeholder="Existencia"/>
                                </div> 
                            </div> 
                            <br/> 

                            <div class="form-group">                                                                       
                                <input type="submit" name="Guardar" id="BtnGuardar" value="Agregar y Guardar" class="btn btn-primary"/>
                            </div> 
                        </form> 

                        <hr/>   
                        <!-- Mostrar detalle de factura --> 
                        <h5>Detalle de Factura</h5>
                        <table id="DetalleFactura" class="table"> 
                            <thead> 
                                <tr>
                                    <th>Código</th> 
                                    <th>Descripción</th> 
                                    <th>Cantidad</th> 
                                    <th>Precio</th> 
                                    <th>Subtotal</th>
                                    <th>Eliminar </th> 
                                </tr> 
                            </thead>


                            <tbody>
                                <% if (DatosDetalles != null) {
                                        Double totalNeto = 0.0;
                                        for (EntidadDetalle registroDetalle : DatosDetalles) {
                                %>
                                <tr>
                                    <%
                                        int numfactura = registroDetalle.getNumFactura();
                                        int codigop = registroDetalle.getCodProducto();
                                        String descripcion = new String(registroDetalle.getNombreProducto().getBytes("ISO-8859-1"), "UTF-8");
                                        int cantidad = registroDetalle.getCantidadProd();
                                        double precioV = registroDetalle.getPrecio();
                                        total += (cantidad * precioV);
                                        totalNeto = totalNeto + total;
                                    %>
                                    <td><%= codigop%></td> 
                                    <td><%= descripcion%></td> 
                                    <td><%= cantidad%></td> 
                                    <td><%= precioV%></td>
                                    <td><%= cantidad * precioV%></td> 
                                    <td>
                                        <a href="EliminarDetalle?idproducto=<%=codigop%>&idfactura=<%=numfactura%>">
                                            <i class="fas fa-trash-alt"></i></a> 
                                    </td> 
                                </tr>

                                <%
                                        } // cierre de for 
                                    } // cierre del if
%>
                            </tbody> 
                        </table> 

                        <div class="float-right mr-xl-5">
                            <p class="text-danger h5" >Subtotal</p> 
                        </div> 
                        <form action="AgregarTotal" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <label for="txtDescuento" class="form-control col-2">Descuento</label>
                                    <input type="text" id="txtDescuento" name="txtDescuento" value=""
                                           class="form-control col-3">
                                    <input type="button" id="BtnDescuento" value="Aplicar"
                                           accept="" onclick="restarDescuento();"
                                           accesskey=""class="btn btn-warning"/>&nbsp; &nbsp;
                                    <input type="text" id="txtSubtotal" name="txtSubtotal" value="<%= total%>"
                                           readonly=""  class="form-control">
                                </div>    
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <label for="txtTotal" class="form-control col-12">TOTAL</label> 
                                    <input type="text" id="txtTotal" name="txtTotal" readonly=""  class="form-control" value="<%=EntidadFactura.getTotal()%>">&nbsp; &nbsp;
                                    <input type="submit" name="AgregarTotal" id="BtnAgregarTotal" value="Agregar Total" class="btn btn-primary"/>

                                </div>
                            </div>

                        </form>
                                    <input type="button"  id="BtnCancelar" value="Realizar Facturacion"
                               onclick="location.href = 'CancelarFactura?txtnumFactura=' +<%= EntidadFactura.getNumFactura()%>"
                               class="btn btn-success"/>&nbsp; &nbsp; 

                        <script type="text/javascript">
                                   function restarDescuento() {
                                       var desc = document.getElementById("txtDescuento").value;
                                       var subTotal = document.getElementById("txtSubtotal").value;
                                       var porcDesc = desc / 100;
                                       var montoDesc = subTotal * porcDesc;
                                       var total = subTotal - montoDesc;


                                       document.getElementById("txtTotal").value = total;
                                   }
                        </script>
                        <br><br>
                        <%
                            //mensaje generado en en servlets facturas
                            if (request.getParameter("msgFac") != null) {
                                out.print("<p class='text-danger'>" + new String(request.getParameter("msgFac").getBytes("ISO-8859-1"), "UTF-8") + "</p>");
                            }
                        %>    
                    </div> <!-- container principal -->


                    <!-- Modal de clientes --> 
                    <div class="modal" id="buscarCliente" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
                        <div class="modal-dialog" role="document"> 
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 id="tituloVentaja">Buscar cliente</h5>
                                    <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                            onclick="Limpiar()"> 
                                        <span aria-hidden="true">&times;</span>
                                    </button> 
                                </div> 
                                <div class="modal-body">
                                    <!-- tabla de clientes --> 
                                    <table id="tablaClientes">
                                        <thead> 
                                            <tr>
                                                <th>Código</th>
                                                <th>Nombre</th>
                                                <th>Cédula</th>
                                                <th>Seleccionar</th> 
                                            </tr> 
                                        </thead>



                                        <tbody>
                                            <%
                                                BLClientes logicaClientes = new BLClientes();
                                                List<EntidadCliente> datosClientes;
                                                datosClientes = logicaClientes.ListarClientes("");
                                                for (EntidadCliente registroC : datosClientes) {
                                            %>
                                            <tr> 
                                                <%int codigoCliente = registroC.getCodCliente();
                                                    String nombreCliente = registroC.getNombre();
                                                    String cedulaCliente = registroC.getCedula();%>
                                                <td><%= codigoCliente%></td>
                                                <td><%= nombreCliente%></td> 
                                                <td><%= cedulaCliente%></td> 
                                                <td>
                                                    <a href="#" data-dismiss="modal"
                                                       onclick="SeleccionarCliente('<%=codigoCliente%>', '<%= nombreCliente%>', '<%= cedulaCliente%>');">Seleccionar</a> 
                                                </td> 
                                            </tr>
                                            <%} %>
                                        </tbody> 
                                    </table> 
                                </div> <!-- modal body --> 
                                <div class="modal-footer"> 
                                    <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="Limpiar()">
                                        Cancelar 
                                    </button> 
                                </div> 
                            </div> <!-- modal content -->
                        </div> <!-- modal dialog -->
                    </div> <!-- modal --> 


                    <!-- Modal de PRODUCTO -->
                    <div class="modal" id="buscarProducto" tabindex="1" role="dialog" aria-labelledby="tituloVentana"> 
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 id="tituloVentana">Buscar producto</h5>
                                    <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                            onclick="LimpiarProducto()"> 
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <!-- tabla de clientes -->
                                    <table id="tablaProductos">
                                        <thead> 
                                            <tr>
                                                <th>Código</th> 
                                                <th>Descripción</th>
                                                <th>Precio</th>
                                                <th>Seleccionar</th>
                                            </tr> 
                                        </thead>

                                        <tbody>

                                            <%
                                                BLProductos logicaProductos = new BLProductos();
                                                List<EntidadProducto> datosProductos;
                                                datosProductos = logicaProductos.ListarProductos("");
                                                for (EntidadProducto registroP : datosProductos) {
                                            %>
                                            <tr>
                                                <%int codigoProducto = registroP.getCodProducto();
                                                    String nombreProducto = registroP.getNombre();
                                                    double precio = registroP.getPrecioVenta();
                                                    double existencia = registroP.getExistencia();
                                                %>
                                                <td><%= codigoProducto%></td> 
                                                <td><%= nombreProducto%></td>
                                                <td><%= precio%></td>
                                                <td>
                                                    <a href="#" data-dismiss="modal"
                                                       onclick="SeleccionarProducto('<%=codigoProducto%>',
                                                                       '<%= nombreProducto%>', '<%= precio%>', '<%= existencia%>');">Seleccionar</a>
                                                </td> 
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table> 
                                </div> <!-- modal body -->
                                <div class="modal-footer">
                                    <button class="btn btn-warning" type="button" data-dismiss="modal"
                                            onclick="LimpiarProducto()"> Cancelar
                                    </button> 
                                </div> 
                            </div> <!-- modal content --> 
                        </div> <!-- mnodal dialog --> 
                    </div> <!-- modal -->

                    <!--MODAL DE VENDEDORES -->
                    <div class="modal" id="buscarVendedor" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 id="tituloVentana">Buscar vendedor</h5>
                                    <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                            onclick="LimpiarV()">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <!-- TABLA DE VENDEDORES -->
                                    <table id="tablaVendedores">
                                        <thead>
                                            <tr>
                                                <th>Código</th>
                                                <th>Nombre</th>
                                                <th>Seleccionar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                BLVendedores logicaVendedores = new BLVendedores();
                                                List<EntidadVendedor> datosVendedores;
                                                datosVendedores = logicaVendedores.ListarVendedores("");
                                                for (EntidadVendedor registroV : datosVendedores) {
                                            %>
                                            <tr>
                                                <%
                                                    int codigoVendedor = registroV.getCodVendedor();
                                                    String nombreVendedor = registroV.getNombre();
                                                %>
                                                <td><%= codigoVendedor%></td>
                                                <td><%= nombreVendedor%></td>
                                                <td>
                                                    <a href="#" data-dismiss="modal"
                                                       onclick="SeleccionarVendedor('<%=codigoVendedor%>', '<%=nombreVendedor%>');">Seleccionar</a>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="LimpiarV()">
                                        Cancelar
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a href="frmListarFacturas.jsp" class="btn btn-secondary" style="background-color: red">Regresar</a>
                    <!-- Scripts requeridos --> 
                    <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
                    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
                    <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
                    <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
                    <script src="lib/DataTables/datatables.min.js" type="text/javascript"></script>
                    <script src="lib/DataTables/DataTables-1.10.21/js/dataTables.bootstrap4.min.js" type="text/javascript"></script> 
                    <script  src="js/script.js"></script>
                    <script>
                                        //cuando el documento este listo 
                                        //Cargue las siguientes funciones
                                        $(document).ready(function () {
                                            //mostrar calendario 
                                            $('.datepicker').datepicker({
                                                format: 'yyyy-mm-dd',
                                                autoclose: true,
                                                language: 'es'
                                            });
                                            //hacer que la lista de clientes se comporte como un datatable 
                                            // Configurar la tabla clientes del modal 
                                            $('#tablaProductos').dataTable({
                                                "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                                "language": {
                                                    "info": "Página_PAGE_de_PAGES_ ",
                                                    "infoEmpty": "No existen Registros disponibles",
                                                    "zeroRecords": "No se encuentran registros",
                                                    "search": "Buscar",
                                                    "infoFiltered": "",
                                                    "lengthMenu": "Mostrar_MENU_ Registros",
                                                    "paginate": {
                                                        "first": "Primero",
                                                        "last": "Último",
                                                        "next": "Siguiente",
                                                        "previous": "Anterior"
                                                    }
                                                }
                                            });

                                            // Configurar la tabla clientes del modal 
                                            $('#tablaClientes').dataTable({
                                                "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                                "language": {
                                                    "info": "Página_PAGE_de_PAGES_ ",
                                                    "infoEmpty": "No existen Registros disponibles",
                                                    "zeroRecords": "No se encuentran registros",
                                                    "search": "Buscar",
                                                    "infoFiltered": "",
                                                    "lengthMenu": "Mostrar_MENU_ Registros",
                                                    "paginate": {
                                                        "first": "Primero",
                                                        "last": "Último",
                                                        "next": "Siguiente",
                                                        "previous": "Anterior"
                                                    }
                                                }
                                            });

                                            //Configura la tabla productos del modal
                                            $('#tablaVendedores').dataTable({
                                                "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                                "language": {
                                                    "info": "Página _PAGE_ de _PAGES_",
                                                    "infoEmpty": "No existen Registros disponibles",
                                                    "zeroRecords": "No se encuentran registros",
                                                    "search": "Buscar",
                                                    "infoFiltered": "",
                                                    "lengthMenu": "",
                                                    "paginate": {
                                                        "first": "Primero",
                                                        "last": "Último",
                                                        "next": "Siguiente",
                                                        "previous": "Anterior"
                                                    }
                                                }
                                            });
                                        });


                                        //seleccionar cliente 
                                        // estas funciones se llaman con un evento desde un modal 
                                        function SeleccionarCliente(idCliente, nombreCliente, cedulaCliente) {
                                            $("#txtIdCliente").val(idCliente);
                                            $("#txtNombreCliente").val(nombreCliente);
                                            $("#txtCedula").val(cedulaCliente);
                                        }

                                        //seleccionar vendedor 
                                        // estas funciones se llaman con un evento desde un modal 
                                        function SeleccionarVendedor(idVendedor, nombreVendedor) {
                                            $("#txtIdVendedor").val(idVendedor);
                                            $("#txtNombreVendedor").val(nombreVendedor);
                                        }

                                        //seleccionar producto 
                                        function SeleccionarProducto(idProducto, Descripcion, Precio, Existencia) {
                                            $("#txtIdProducto").val(idProducto);
                                            $("#txtdescripcion").val(Descripcion);
                                            $("#txtprecio").val(Precio);
                                            $("#txtexistencia").val(Existencia);
                                            $("#txtcantidad").focus();
                                        }

                                        // seleccionar cliente 
                                        function LimpiarC() {
                                            $("#txtIdCliente").val("");
                                            $("#txtNombreCliente").val("");
                                        }

                                        // seleccionar cliente 
                                        function LimpiarV() {
                                            $("#txtIdVendedor").val("");
                                            $("#txtNombreVendedor").val("");
                                        }

                                        //seleccionar producto 
                                        function LimpiarProducto() {
                                            $("#txtIdProducto").val("");
                                            $("#txtdescripcion").val("");
                                            $("#txtprecio").val("");
                                            $("#txtexistencia").val("");
                                        }
                    </script> 
                    </body> 
                    </html>
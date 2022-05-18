<%@page import="java.util.*"%>
<%@page import="Entidades.*"%>
<%@page import="LogicaNegocio.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
        <title>Resumen Personalizado</title>

        <!-- Demo CSS -->
        <link rel="stylesheet" href="css/demo.css">
    </head>

    <body id="body">
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

                    <%
                        int numFactura = -1;
                        double total = 0;
                        EntidadEncabezadoF factura;
                        BLEncabezados logicaFactura = new BLEncabezados();
                        BLDetalles logicaDetalle = new BLDetalles();
                        List<EntidadDetalle> DatosDetalles = null;
                        if (request.getParameter("txtnumFactura") != null && Integer.parseInt(request.getParameter("txtnumFactura")) != -1) {
                            numFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
                            factura = logicaFactura.ObtenerRegistro("Num_Factura=" + numFactura);
                            DatosDetalles = logicaDetalle.ListarDetalles("Num_Factura=" + numFactura);
                        } else {
                            factura = new EntidadEncabezadoF();
                            factura.setNumFactura(-1);
                            Date fecha = new Date();
                            java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                            factura.setFechaFactura(fechasql);

                        }

                    %>
                    <!-- El formulario se va a cargar a si mismo -->    

                    <form action="frmResumenPersonalizado.jsp" method="post">
                        <div class="form-group m-xl-5 pl-xl-5">
                            <div class="input-group col-10">          
                                <input type="text" id="txtFechaP" name="txtFechaP" value="" 
                                       placeholder="Seleccione la fecha inicial" class="datepicker"/>&nbsp; &nbsp;
                                <input type="text" id="txtFechaS" name="txtFechaS" value="" 
                                       placeholder="Seleccione la fecha final" class="datepicker"/>&nbsp; &nbsp;     
                                <input type="number" id="txtCodVendedor" name="txtCodVendedor" value="<%=factura.getCodVendedor()%>"
                                       readonly="" class="form-control col-1">&nbsp;&nbsp;
                                <input type="text" id="txtNombreVendedor" name="txtNombreVendedor" 
                                       value="<%=factura.getNombreVendedor()%>" readonly="" class="form-control" 
                                       placeholder="Nombre">&nbsp;&nbsp;
                                <a id="btnbuscarv" class="btn btn-success" data-toggle="modal" 
                                   data-target="#buscarVendedor"><i class="fas fa-search"></i></a> &nbsp;&nbsp;
                                <input type="submit" id="btnbuscar" name="btnBuscar" value="Buscar" 
                                       class="btn btn-success col-2 ml-4"/><br><br>
                            </div>
                        </div> 
                    </form>
                    <hr>
                    <div id="container" class="container">
                        <div class="row">
                            <div class="card-header">
                                <h1>Resumen Personalizado</h1>
                            </div>                        
                            <br>
                            <div class="form-group col-5">
                                <label for="txtMontoMes" class="control-label">Monto de Ventas</label>
                                <input type="text" id="txtMontoMes" name="txtMontoMes" class="form-control" readonly="" placeholder="0.0"/><br>
                            </div>
                            <table class="table">
                                <thead>
                                    <tr id="titulos">
                                        <th>N° Factura</th>
                                        <th>Vendedor</th>
                                        <th>Fecha</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <!--Hacer una consulta a la bd para obtener la lista de clientes
                                        mediante código JAVA--> 
                                    <%
                                        String fechaP = "";
                                        String fechaS = "";
                                        int codVendedor = 0;
                                        String condicion = "";

                                        if (request.getParameter("txtFechaP") != null
                                                && !request.getParameter("txtFechaP").equals("")
                                                && request.getParameter("txtFechaS") != null
                                                && !request.getParameter("txtFechaS").equals("")
                                                && request.getParameter("txtCodVendedor") != null
                                                && !request.getParameter("txtCodVendedor").equals("")) {
                                            fechaP = request.getParameter("txtFechaP");
                                            fechaS = request.getParameter("txtFechaS");
                                            codVendedor =  Integer.parseInt(request.getParameter("txtCodVendedor"));
                                            
                                            condicion = condicion + " EXISTS(SELECT COD_VENDEDOR FROM ENCABEZADO_FACTURA WHERE COD_VENDEDOR = '" + codVendedor + "')" + " AND FECHA BETWEEN '" + fechaP + "' and '" + fechaS + "'";

                                        }
                                        BLEncabezados logica = new BLEncabezados();
                                        List<EntidadEncabezadoF> datos;
                                        datos = logica.ListarRegistros(condicion);

                                        for (EntidadEncabezadoF registro : datos) {
                                    %>
                                    <tr>
                                        <% int codigo = registro.getNumFactura();%>
                                        <td><%=codigo%></td>
                                        <td><%=registro.getNombreVendedor()%></td>
                                        <td><%=registro.getFechaFactura()%></td>
                                    </tr>
                                    <%}%><!--Para cerrar el for --> 
                                </tbody>
                            </table>
                        </div>
                        <br>


                        <a href="frmResumenPersonalizado.jsp"> Actualizar </a> |
                        <a href="index.html"> Regresar al Inicio </a> 

                    </div>
                    <script>
                        function imprimirPagina() {
                            var body = document.getElementById('body').innerHTML;
                            var tbl = document.getElementById('container').innerHTML;
                            document.getElementById('body').innerHTML = tbl;

                            window.print();
                            document.getElementById('body').innerHTML = body;
                        }
                    </script>

                    <div style="text-align: center;"> 
                        <button class="btn btn-dark" id="btnCrearPdf" onclick="imprimirPagina();">Imprimir</button>
                    </div>
                    <footer class="credit">&copy; Hecho por Luis Felipe Miranda Rojas </footer> 

                    <!-- MODAL DE VENDEDORES -->
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
                    <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
                    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
                    <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
                    <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
                    <script src="lib/DataTables/datatables.min.js" type="text/javascript"></script>
                    <script src="lib/DataTables/DataTables-1.10.21/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
                    <script  src="js/script.js"></script>
                    </body>
                    <script>
                                        $('.datepicker').datepicker({
                                            format: 'yyyy-mm-dd',
                                            autoclose: true,
                                            language: 'es'
                                        });

                                        $('#tablaVendedores').dataTable({
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

                                        function SeleccionarVendedor(idVendedor, nombreVendedor) {
                                            $("#txtCodVendedor").val(idVendedor);
                                            $("#txtNombreVendedor").val(nombreVendedor);
                                        }
                    </script>
                    </html>

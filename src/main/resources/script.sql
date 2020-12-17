DROP TABLE Cuentas IF EXISTS;
CREATE TABLE Cuentas (
   idCuenta int NOT NULL AUTO_INCREMENT,
   fechaAltaCuenta DATE,
   fechaBajaCuenta DATE,
   esActiva BOOLEAN,
   nombreUsuCuenta VARCHAR(20),
   passUsuCuenta VARCHAR(20),
   rol VARCHAR(15),
   PRIMARY KEY (idCuenta)
);

DROP TABLE DatosPersonales IF EXISTS;
CREATE TABLE DatosPersonales (
    idDatosPersonales int NOT NULL AUTO_INCREMENT,
    nombreCompleto VARCHAR(60) NOT NULL,
  apellido VARCHAR(60) NOT NULL,
    dni INT,
    telefono VARCHAR(15),
    email VARCHAR(25),
    calle VARCHAR(25),
    altura VARCHAR(5),
    piso VARCHAR(2),
    dpto VARCHAR(4),
    localidad VARCHAR(25),
    PRIMARY KEY (idDatosPersonales)
);

DROP TABLE Usuarios IF EXISTS;
CREATE TABLE Usuarios (
    idUsuario INT NOT NULL AUTO_INCREMENT,
    idCuenta INT ,
    idDatosPersonales INT,
    PRIMARY KEY (idUsuario),
    FOREIGN KEY (idCuenta) REFERENCES Cuentas (idCuenta),
    FOREIGN KEY (idDatosPersonales) REFERENCES DatosPersonales(idDatosPersonales)
);

DROP TABLE Clientes IF EXISTS;
CREATE TABLE Clientes (
    idCliente INT NOT NULL AUTO_INCREMENT,
    fechaAltaCliente DATE ,
    idDatosPersonales INT,
    PRIMARY KEY (idCliente),
    FOREIGN KEY (idDatosPersonales) REFERENCES DatosPersonales(idDatosPersonales)
);

DROP TABLE Turnos IF EXISTS;
CREATE TABLE Turnos (
   idTurno INT NOT NULL AUTO_INCREMENT,
   idCliente INT,
   fechaCanceladoTurno DATE,
   fechaAltaTurno DATE NOT NULL,
   fechProgramadaTurno DATE NOT NULL,
   nombreCliente VARCHAR (40) NOT NULL,
   apellidoCliente VARCHAR (40) NOT NULL,
   dniCliente INT NOT NULL,
   telefonoCliente VARCHAR(40),
   emailCliente VARCHAR(40),
   PRIMARY KEY (idTurno)
);

DROP TABLE FichaTecnicaVehiculo IF EXISTS;
CREATE TABLE FichaTecnicaVehiculo (
   idFichaTecnicaVehiculo INT NOT NULL AUTO_INCREMENT,
   nroChasis INT UNIQUE NOT NULL,
   nroMotor INT UNIQUE NOT NULL,
   kilometraje INT,
   marca VARCHAR (20),
   modelo INT,
   color VARCHAR (15),
   combustion VARCHAR (15),
   descripcion VARCHAR (60),
   patente VARCHAR (10),
   PRIMARY KEY (idFichaTecnicaVehiculo)
);

DROP TABLE VehiculoConOrdenesDeTrabajo IF EXISTS;
CREATE TABLE VehiculoConOrdenesDeTrabajo (
   idVehiculoConOT INT NOT NULL AUTO_INCREMENT,
   idFichaTecnicaVehiculo INT,
   idCliente INT NOT NULL,
   kilometrajeGarantia INT,
   aseguradora VARCHAR (20),
   nroPolizaSeguro INT,
   patenteVehiculo VARCHAR (10),
   idVehiculo INT,
   PRIMARY KEY (idVehiculoConOT),
   FOREIGN KEY (idFichaTecnicaVehiculo) REFERENCES FichaTecnicaVehiculo (idFichaTecnicaVehiculo),
   FOREIGN KEY (idCliente) REFERENCES Clientes (idCliente)
);

DROP TABLE TipoTrabajo IF EXISTS;
CREATE TABLE TipoTrabajo (
  idTipoTrabajo INT NOT NULL AUTO_INCREMENT,
  descripcionTrabajo VARCHAR(10) UNIQUE,
  PRIMARY KEY (idTipoTrabajo)
);

DROP TABLE OrdenesDeTrabajo IF EXISTS;
CREATE TABLE OrdenesDeTrabajo (
  idOT INT NOT NULL AUTO_INCREMENT,
  tipoTrabajo VARCHAR(20) NOT NULL,
  idUsuAlta INT NOT NULL,
  idVehiculoOt INT NOT NULL,
  fechaAltaOt DATE,
  trabajoSolicitado VARCHAR (60),
  trabajoSujerido VARCHAR (60),
  fechaEntregadoVehiculo DATE,
  PRIMARY KEY (idOT),
  FOREIGN KEY (idUsuAlta) REFERENCES Usuarios (idUsuario),
  FOREIGN KEY (idVehiculoOt) REFERENCES VehiculoConOrdenesDeTrabajo (idVehiculoConOT)
);

DROP TABLE Facturas IF EXISTS;
CREATE TABLE Facturas (
  idFactura INT AUTO_INCREMENT,
  idOT INT,
  fechaDeAlta DATE,
  fechaDeCierrePorPago DATE,
  PRIMARY KEY(idFactura),
  total DOUBLE,
  estado VARCHAR(10),
   FOREIGN KEY (idOT) REFERENCES OrdenesDeTrabajo(idOT),
   FOREIGN KEY (idCliente) REFERENCES Clientes (idCliente),
  dni INT,
  idCliente INT,
  FOREIGN KEY (dni) REFERENCES DatosPersonales(dni)
);

DROP TABLE Emisores IF EXISTS;
CREATE TABLE Emisores (
  idEmisor INT NOT NULL AUTO_INCREMENT,
  nombreEmisor VARCHAR (20),
  PRIMARY KEY (idEmisor)
);

DROP TABLE Tarjetas IF EXISTS;
CREATE TABLE Tarjetas (
  idTarjeta INT NOT NULL AUTO_INCREMENT,
  idEmisor INT,
  banco VARCHAR (20),
  numeroTarjeta INT,
  nomTitular VARCHAR (50),
  fechaVencTarj DATE,
  PRIMARY KEY (idTarjeta),
  FOREIGN KEY (idEmisor) REFERENCES Emisores (idEmisor)
);

DROP TABLE Financiamientos IF EXISTS;
CREATE TABLE Financiamientos (
  idFinanciamiento INT NOT NULL AUTO_INCREMENT,
  idTarjeta INT,
  cantCuotas INT,
  montoCuota DOUBLE,
  PRIMARY KEY (idFinanciamiento),
  FOREIGN KEY (idTarjeta) REFERENCES Tarjetas (idTarjeta)
);

DROP TABLE Pagos IF EXISTS;
CREATE TABLE Pagos (
  idPago INT NOT NULL AUTO_INCREMENT,
  idFinanciamiento INT,
  montoPago DOUBLE,
  fechaPago DATE,
  PRIMARY KEY(idPago),
  FOREIGN KEY ( idFinanciamiento) REFERENCES Financiamientos (idFinanciamiento)
);

DROP TABLE Presupuestos IF EXISTS;
CREATE TABLE Presupuestos (
  idPresupuesto INT NOT NULL AUTO_INCREMENT,
  idOT INT NOT NULL,
  idFactura INT,
  idUsuAltaPresu INT NOT NULL,
  idUsuCierrePresu INT,
  idUsuRegPago INT,
  idPago INT,
  fechaAltaPresu DATE,
  comentarioAltaPresu VARCHAR (60),
  fechaCierrePresu DATE,
  comentarioRechazo VARCHAR (60),
  fechaAprobacion DATE,
  estado VARCHAR(20),
  garantia BOOLEAN NOT NULL,
  PRIMARY KEY (idPresupuesto),
  FOREIGN KEY (idOT) REFERENCES OrdenesDeTrabajo (idOT),
  FOREIGN KEY (idUsuAltaPresu) REFERENCES Usuarios (idUsuario),
  FOREIGN KEY (idUsuCierrePresu) REFERENCES Usuarios (idUsuario),
  FOREIGN KEY (idUsuRegPago) REFERENCES Usuarios (idUsuario),
  FOREIGN KEY (idPago) REFERENCES Pagos (idPago)
);

DROP TABLE TrabajosPresupuestados IF EXISTS;
CREATE TABLE TrabajosPresupuestados (
  idTrabajoPresu INT NOT NULL AUTO_INCREMENT,
  idPresupuesto INT,
  descripcionTrabajo VARCHAR (60),
  precioTrabajo DOUBLE,
  tiempoEstTrabajo INT,
  PRIMARY KEY (idTrabajoPresu),
  FOREIGN KEY (idPresupuesto) REFERENCES Presupuestos (idPresupuesto)
);

DROP TABLE Repuestos IF EXISTS;
CREATE TABLE Repuestos (
  idRepuesto INT NOT NULL AUTO_INCREMENT,
  codigoRepuesto INT,
  precioRepuesto DOUBLE,
  marcaRepuesto VARCHAR (20),
  descripcionRepuesto VARCHAR (40),
  stockRepuesto INT,
  fabricante VARCHAR (30),
  stockMinimo INT,
  garantia BOOLEAN NOT NULL,
  precioCompra DOUBLE NOT NULL,
  PRIMARY KEY (idRepuesto)
);

DROP TABLE RepuestosPlanificados IF EXISTS;
CREATE TABLE RepuestosPlanificados (
  idRepuestoPlanificado INT NOT NULL AUTO_INCREMENT,
  idPresu INT NOT NULL,
  idRepuesto INT NOT NULL,
  cantRequerida INT,
  precio DOUBLE,
  PRIMARY KEY (idRepuestoPlanificado),
  FOREIGN KEY (idPresu) REFERENCES Presupuestos (idPresupuesto),
  FOREIGN KEY (idRepuesto) REFERENCES Repuestos (idRepuesto)
);

DROP TABLE RepuestosComprados IF EXISTS;
CREATE TABLE RepuestosComprados (
  idRepuestoComprado INT NOT NULL AUTO_INCREMENT,
  idFactura INT NOT NULL,
  idRepuesto INT NOT NULL,
  cantRequerida INT,
  PRIMARY KEY (idRepuestoComprado),
  FOREIGN KEY (idFactura) REFERENCES Facturas (idFactura),
  FOREIGN KEY (idRepuesto) REFERENCES Repuestos (idRepuesto)
);

DROP TABLE Moneda IF EXISTS;
CREATE TABLE Moneda (
  idMoneda INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(15) NOT NULL,
  simbolo VARCHAR(5) NOT NULL,
  cotizacionDolar DOUBLE NOT NULL,
  PRIMARY KEY (idMoneda)
);

DROP TABLE Sucursal IF EXISTS;
CREATE TABLE Sucursal (
  idSucursal INT NOT NULL AUTO_INCREMENT,
  pais VARCHAR(20) NOT NULL,
  calle VARCHAR(25) NOT NULL,
  altura INT NOT NULL,
  localidad VARCHAR(25) NOT NULL,
  idMoneda INT NOT NULL,
  PRIMARY KEY (idSucursal),
  FOREIGN KEY (idMoneda) REFERENCES Moneda(idMoneda)
);

DROP TABLE CaracteristicasVehiculo IF exists;
CREATE TABLE CaracteristicasVehiculo(
	idCaracteristica INT NOT NULL AUTO_INCREMENT,
	cilindrada VARCHAR(20),
	motor VARCHAR(20),
	direccion VARCHAR(26),
	potencia VARCHAR(20),
	frenosDelanteros VARCHAR(20),
	transmision VARCHAR(30),
	frenosTraseros VARCHAR(20),
	torqueMaximo VARCHAR(20),
	volumenDeBaul VARCHAR(20),
	nroDePuertas VARCHAR(20),
	precio VARCHAR(20),
	PRIMARY KEY (idCaracteristica)
);

DROP TABLE Vehiculos IF EXISTS;
CREATE TABLE Vehiculos (
  idVehiculo INT NOT NULL AUTO_INCREMENT,
  precioVenta DOUBLE NOT NULL,
  idFichaTecnica INT,
  marca VARCHAR(20) NOT NULL,
  familia VARCHAR(20) NOT NULL,
  linea VARCHAR(20) NOT NULL,
  color VARCHAR(20) NOT NULL,
  idCaracteristica INT NOT NULL,
  fechaIngreso DATE,
  disponible BOOLEAN NOT NULL,
  usado BOOLEAN NOT NULL,
  idSucursal INT,
  PRIMARY KEY (idVehiculo),
  FOREIGN KEY (idFichaTecnica) REFERENCES FichaTecnicaVehiculo(idFichaTecnicaVehiculo),
  FOREIGN KEY (idCaracteristica) REFERENCES CaracteristicasVehiculo(idCaracteristica),
  FOREIGN KEY (idSucursal) REFERENCES Sucursal(idSucursal)
);

DROP TABLE CompraVehiculo IF EXISTS;
CREATE TABLE CompraVehiculo (
  idCompraVehiculo INT NOT NULL AUTO_INCREMENT,
  idVehiculo INT NOT NULL,
  PrecioCompra DOUBLE NOT NULL,
  fechaCompra DATE NOT NULL,
  idUsuCompra INT NOT NULL,
  PRIMARY KEY (idCompraVehiculo),
  FOREIGN KEY (idUsuCompra) REFERENCES Usuarios(idUsuario),
  FOREIGN KEY (idVehiculo) REFERENCES Vehiculos(idVehiculo)
);

DROP TABLE VentasVehiculos IF EXISTS;
CREATE TABLE VentasVehiculos (
  idVentaVehiculo INT NOT NULL AUTO_INCREMENT,
  idUsuVentaVN INT NOT NULL,
  idUsuPedido INT,
  idUsuLlegada INT,
  idPagoVentaVN INT,
  fechaVentaVN DATE NOT NULL,
  fechaEntregaReal DATE,
  fabricante VARCHAR(30) NOT NULL,
  comisionCobrada DOUBLE NOT NULL,
  precioVenta DOUBLE NOT NULL,
  financiera VARCHAR(50),
  nroCuotas INT,
  montoCuota DOUBLE,
  idVehiculo INT NOT NULL UNIQUE,
  idCliente INT NOT NULL,
  idUsuEntrega INT,
  idSucursal INT NOT NULL,
  PRIMARY KEY (idVentaVehiculo),
  FOREIGN KEY (idUsuVentaVN) REFERENCES Usuarios(idUsuario),
  FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente),
  FOREIGN KEY (idPagoVentaVN) REFERENCES Pagos(idPago),
  FOREIGN KEY (idVehiculo) REFERENCES Vehiculos(idVehiculo),
  FOREIGN KEY (idUsuPedido) REFERENCES Usuarios(idUsuario),
  FOREIGN KEY (idUsuLlegada) REFERENCES Usuarios(idUsuario),
  FOREIGN KEY (idUsuEntrega) REFERENCES Usuarios(idUsuario),
  FOREIGN KEY (idSucursal) REFERENCES Sucursal(idSucursal)
);

DROP TABLE PedidoVehiculo IF EXISTS;
CREATE TABLE PedidoVehiculo (
  idPedidoVehiculo INTEGER NOT NULL AUTO_INCREMENT,
  fechaPedido DATE NOT NULL,
  fechaIngreso DATE,
  idUsuPedido INTEGER NOT NULL,
  idUsuIngreso INTEGER,
  idVentaVehiculo INTEGER NOT NULL,
  PRIMARY KEY (idPedidoVehiculo),
  FOREIGN KEY (idUsuPedido) REFERENCES Usuarios(idUsuario),
  FOREIGN KEY (idUsuIngreso) REFERENCES Usuarios(idUsuario),
  FOREIGN KEY (idVentaVehiculo) REFERENCES VentasVehiculos(idVentaVehiculo)
);

DROP TABLE Mantenimientos IF EXISTS;
CREATE TABLE Mantenimientos (
  idMantenimiento INTEGER NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(30) NOT NULL,
  comentario VARCHAR(60),
  PRIMARY KEY (idMantenimiento)
);

DROP TABLE RepuestosMantenimiento IF EXISTS;
CREATE TABLE RepuestosMantenimiento(
  idRepuestoMantenimiento INT NOT NULL AUTO_INCREMENT,	
  idRepuesto INT NOT NULL,
  idMantenimiento INT NOT NULL,
  cantidad INT NOT NULL,
  PRIMARY KEY (idRepuestoMantenimiento),
  FOREIGN KEY (idRepuesto) REFERENCES Repuestos(idRepuesto),
  FOREIGN KEY (idMantenimiento) REFERENCES Mantenimientos(idMantenimiento)
);

DROP TABLE TrabajosMantenimiento IF EXISTS;
CREATE TABLE TrabajosMantenimiento(
  idTrabajoMantenimiento INT NOT NULL AUTO_INCREMENT,	
  descripcionTrabajo VARCHAR (60),	
  idMantenimiento INT NOT NULL,
  precio DOUBLE NOT NULL,
  tiempoEstTrabajo INTEGER NOT NULL,
  PRIMARY KEY (idTrabajoMantenimiento),
  FOREIGN KEY (idMantenimiento) REFERENCES Mantenimientos(idMantenimiento)
);

DROP TABLE GarantiasVehiculos IF EXISTS;
CREATE TABLE GarantiasVehiculos(
	idGarantia INT NOT NULL AUTO_INCREMENT,
	idVehiculo INT NOT NULL,
	aniosDeGarantia INT,
	kilometrajeInicialDelVehiculo INT,
	kilometrajeGarantizado INT,
	fechaInicioDeLaGarantia DATE,
	fechaDeCaducidadDeLaGarantia DATE,
	costoFinalConIVA DOUBLE,
	PRIMARY KEY(idGarantia),
	FOREIGN KEY(idVehiculo) REFERENCES Vehiculos(idVehiculo)
);

DROP TABLE CompraRepuesto IF EXISTS;
CREATE TABLE CompraRepuesto(
	idCompra INT NOT NULL AUTO_INCREMENT,
	codigoRepuesto INT NOT NULL,
	precioCompra DOUBLE NOT NULL,
	fechaCompra DATE,
	cantidad INT,
	PRIMARY KEY(idCompra),
);



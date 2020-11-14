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
   PRIMARY KEY (idFichaTecnicaVehiculo)
);

DROP TABLE VehiculoConOrdenesDeTrabajo IF EXISTS;
CREATE TABLE VehiculoConOrdenesDeTrabajo (
   idVehiculoConOT INT NOT NULL AUTO_INCREMENT,
   idFichaTecnicaVehiculo INT NOT NULL,
   idCliente INT NOT NULL,
   kilometrajeGarantia INT NOT NULL,
   aseguradora VARCHAR (20) NOT NULL,
   nroPolizaSeguro INT NOT NULL,
   patenteVehiculo VARCHAR (10),
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
  idOT INT NULL,
  fechaDeAlta DATE,
  fechaDeCierrePorPago DATE,
  PRIMARY KEY(idFactura, idOT),
  FOREIGN KEY (idOT) REFERENCES OrdenesDeTrabajo(idOT),
  total DOUBLE
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
  idUsuAltaPresu INT NOT NULL,
  idUsuCierrePresu INT,
  idUsuRegPago INT,
  idPago INT,
  fechaAltaPresu DATE,
  comentarioAltaPresu VARCHAR (60),
  fechaCierrePresu DATE,
  comentarioCierrePresu VARCHAR (60),
  fechaAprobacion DATE,
  estado VARCHAR(20),
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
  PRIMARY KEY (idRepuesto)
);

DROP TABLE RepuestosPlanificados IF EXISTS;
CREATE TABLE RepuestosPlanificados (
  idRepuestoPlanificado INT NOT NULL AUTO_INCREMENT,
  idPresu INT NOT NULL,
  idRepuesto INT NOT NULL,
  cantRequerida INT,
  PRIMARY KEY (idRepuestoPlanificado),
  FOREIGN KEY (idPresu) REFERENCES Presupuestos (idPresupuesto),
  FOREIGN KEY (idRepuesto) REFERENCES Repuestos (idRepuesto)
);

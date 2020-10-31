DROP TABLE IF EXISTS `Pais`;
CREATE TABLE `Pais`(
	PaisID INT NOT NULL AUTO_INCREMENT,
	PaisNombre VARCHAR(255) UNIQUE,
	PRIMARY KEY(PaisID)
);

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Presupuestos`;
DROP TABLE IF EXISTS `Usuarios`;
DROP TABLE IF EXISTS `Cuentas`;
DROP TABLE IF EXISTS `Especialidades`;
DROP TABLE IF EXISTS `EspecialidadesTecnicos`;
DROP TABLE IF EXISTS `TrabajosPresupuestados`;
DROP TABLE IF EXISTS `RepuestosPlanificados`;
DROP TABLE IF EXISTS `Repuesto`;
DROP TABLE IF EXISTS `RenglonVenta`;
DROP TABLE IF EXISTS `OrdenesDeTrabajo`;
DROP TABLE IF EXISTS `Pagos`;
DROP TABLE IF EXISTS `Financiamientos`;
DROP TABLE IF EXISTS `FichaTecnicaVehiculo`;
DROP TABLE IF EXISTS `Clientes`;
DROP TABLE IF EXISTS `DatosPersonales`;
DROP TABLE IF EXISTS `Turnos`;
DROP TABLE IF EXISTS `TipoTrabajo`;
DROP TABLE IF EXISTS `VentasRepuestos`;
DROP TABLE IF EXISTS `VentasVehiculosUsados`;
DROP TABLE IF EXISTS `VehiculosConOrdenesTrabajo`;
DROP TABLE IF EXISTS `VehiculosUsadosParaVenta`;
DROP TABLE IF EXISTS `VentasVehiculosNuevos`;
DROP TABLE IF EXISTS `Tarjetas`;
DROP TABLE IF EXISTS `Emisor`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `Presupuestos` (
    `idPresu` INTEGER NOT NULL,
    `idOT` INTEGER NOT NULL,
    `idUsuAltaPresu` INTEGER NOT NULL,
    `idUsuCierrePresu` INTEGER NOT NULL,
    `idUsuRegPago` INTEGER NOT NULL,
    `idPago` INTEGER NOT NULL,
    `fechaAltaPresu` DATE NOT NULL,
    `comentarioAltaPresu` VARCHAR NOT NULL,
    `fechaCierrePresu` DATE NOT NULL,
    `comentarioCierrePresu` VARCHAR NOT NULL,
    `fechaAprobadoPresu` DATE NOT NULL,
    `Column1` INTEGER NOT NULL,
    `fechaAprobación` DATE NOT NULL,
    `fechaRechazo` DATE NOT NULL,
    PRIMARY KEY (`idPresu`)
);

CREATE TABLE `Usuarios` (
    `idUsuario` INTEGER NOT NULL,
    `nombreTec` VARCHAR NOT NULL,
    `dniTec` INTEGER NOT NULL,
    `fechaAltaCol` DATE NOT NULL,
    `fechaBajaCol` DATE NOT NULL,
    `activo` BOOLEAN NOT NULL,
    `nroLegajo` INTEGER NOT NULL,
    `idCuenta` INTEGER NOT NULL,
    `datosPersonalesId` INTEGER NOT NULL,
    PRIMARY KEY (`idUsuario`)
);

CREATE TABLE `Cuentas` (
    `idCuenta` INTEGER NOT NULL,
    `idUsu` INTEGER NOT NULL,
    `fechaAltaCuenta` DATE NOT NULL,
    `fechaBajaCuenta` DATE NOT NULL,
    `esActiva` BOOLEAN NOT NULL,
    `nombreUsuCuenta` VARCHAR NOT NULL,
    `passUsuCuenta` VARCHAR NOT NULL,
    `rol` VARCHAR NOT NULL,
    PRIMARY KEY (`idCuenta`)
);

CREATE TABLE `Especialidades` (
    `idespecialidad` INTEGER NOT NULL,
    `nombreEspecialidad` VARCHAR NOT NULL,
    PRIMARY KEY (`idespecialidad`)
);

CREATE TABLE `EspecialidadesTecnicos` (
    `idTecnico` INTEGER NOT NULL,
    `idEspecialidad` INTEGER NOT NULL,
    PRIMARY KEY (`idTecnico`, `idEspecialidad`)
);

CREATE TABLE `TrabajosPresupuestados` (
    `idTrabajoPresu` INTEGER NOT NULL,
    `idPresupuesto` INTEGER NOT NULL,
    `descTrabajo` VARCHAR NOT NULL,
    `montoTrabajo` DOUBLE NOT NULL,
    `tiempoEstTrabajo` INTEGER NOT NULL,
    PRIMARY KEY (`idTrabajoPresu`)
);

CREATE TABLE `RepuestosPlanificados` (
    `idRepPlani` INTEGER NOT NULL,
    `idPresu` INTEGER NOT NULL,
    `idRepu` INTEGER NOT NULL,
    `cantReq` INTEGER NOT NULL,
    PRIMARY KEY (`idRepPlani`, `idPresu`)
);

CREATE TABLE `Repuesto` (
    `idRepuesto` INTEGER NOT NULL,
    `codigoRepuesto` INTEGER NOT NULL,
    `precioRepuesto` DOUBLE NOT NULL,
    `marcaRepuesto` VARCHAR NOT NULL,
    `descripcionRepuesto` VARCHAR NOT NULL,
    `stockRepuesto` INTEGER NOT NULL,
    `fabricante` VARCHAR NOT NULL,
    `stockMinimo` INTEGER NOT NULL,
    PRIMARY KEY (`idRepuesto`)
);

CREATE TABLE `RenglonVenta` (
    `idRenglonVenta` INTEGER NOT NULL,
    `idVenta` INTEGER NOT NULL,
    `idRepuesto` INTEGER NOT NULL,
    `cantRepuesto` INTEGER NOT NULL,
    PRIMARY KEY (`idRenglonVenta`, `idVenta`)
);

CREATE TABLE `OrdenesDeTrabajo` (
    `idOT` INTEGER NOT NULL,
    `idTipoOt` INTEGER NOT NULL,
    `idUsuAlta` INTEGER NOT NULL,
    `fechaAlta` DATE NOT NULL,
    `trabajoSolicitado` VARCHAR NOT NULL,
    `trabajoSugerido` VARCHAR NOT NULL,
    `fechaEntregaVehiculo` DATE NOT NULL,
    `idVehiculoOT` INTEGER NOT NULL,
    PRIMARY KEY (`idOT`)
);

CREATE TABLE `Pagos` (
    `idPago` INTEGER NOT NULL,
    `montoPago` DOUBLE NOT NULL,
    `fechaPago` DATE NOT NULL,
    PRIMARY KEY (`idPago`)
);

CREATE TABLE `Financiamientos` (
    `idFinanciamiento` INTEGER NOT NULL,
    `idTarjeta` INTEGER NOT NULL,
    `cantCuotas` INTEGER NOT NULL,
    `montoCuota` DOUBLE NOT NULL,
    PRIMARY KEY (`idFinanciamiento`)
);

CREATE TABLE `FichaTecnicaVehiculo` (
    `idFichaTecnicaVehiculo` INTEGER NOT NULL,
    `nroChasis` INTEGER NOT NULL,
    `nroMotor` INTEGER NOT NULL,
    `kilometraje` INTEGER NOT NULL,
    `marca` VARCHAR NOT NULL,
    `modelo` INTEGER NOT NULL,
    `color` VARCHAR NOT NULL,
    `combustion` VARCHAR NOT NULL,
    `descripcion` VARCHAR NOT NULL,
    `fechaVencimientoVTV` DATE NOT NULL,
    PRIMARY KEY (`idFichaTecnicaVehiculo`),
    UNIQUE (`nroChasis`, `nroMotor`)
);

CREATE TABLE `Clientes` (
    `idCliente` INTEGER NOT NULL,
    `nombreCliente` VARCHAR NOT NULL,
    `datosPersonalesId` INTEGER NOT NULL,
    `fehaAltaCliente` DATE NOT NULL,
    PRIMARY KEY (`idCliente`)
);

CREATE TABLE `DatosPersonales` (
    `idDatosPerso` INTEGER NOT NULL,
    `nombrePer` VARCHAR NOT NULL,
    `dniPer` INTEGER NOT NULL,
    `telefonoPer` INTEGER NOT NULL,
    `emailPer` VARCHAR NOT NULL,
    `callePer` VARCHAR NOT NULL,
    `alturaPer` INTEGER NOT NULL,
    `dptoPer` VARCHAR NOT NULL,
    `locPer` VARCHAR NOT NULL,
    UNIQUE (`dniPer`, `telefonoPer`, `emailPer`, `locPer`)
);

CREATE TABLE `Turnos` (
    `idTurno` INTEGER NOT NULL,
    `idCliente` INTEGER NOT NULL,
    `fechaCancelado` DATE NOT NULL,
    `fechaAltaTurno` DATE NOT NULL,
    `fechaProgramadaTurno` DATE NOT NULL,
    `nroTurno` INTEGER NOT NULL,
    `nombreCliente` VARCHAR NOT NULL,
    PRIMARY KEY (`idTurno`)
);

CREATE TABLE `TipoTrabajo` (
    `idTipoTrabajo` INTEGER NOT NULL,
    `descripcionTrabajo` VARCHAR NOT NULL,
    PRIMARY KEY (`idTipoTrabajo`)
);

CREATE TABLE `VentasRepuestos` (
    `idVenta` INTEGER NOT NULL,
    `idPago` INTEGER NOT NULL,
    `idUsuarioVenta` INTEGER NOT NULL,
    `fechaVenta` DATE NOT NULL,
    PRIMARY KEY (`idVenta`)
);

CREATE TABLE `VentasVehiculosUsados` (
    `idVentaVehiculo` INTEGER NOT NULL,
    `idVehiculoUsadoVenta` INTEGER NOT NULL,
    `idUsuarioVenta` INTEGER NOT NULL,
    `idUsuEntrega` INTEGER NOT NULL,
    `idPagoVentaVU` INTEGER NOT NULL,
    `idCliente` INTEGER NOT NULL,
    `fechaVenta` DATE NOT NULL,
    `comisionCobrada` DOUBLE NOT NULL,
    `fechaEntregaReal` DATE NOT NULL,
    PRIMARY KEY (`idVentaVehiculo`)
);

CREATE TABLE `VehiculosConOrdenesTrabajo` (
    `idVehiculoConOT` INTEGER NOT NULL,
    `idFichaTecnicaVehiculo` INTEGER NOT NULL,
    `idCliente` INTEGER NOT NULL,
    `kilometrajeGarantia` INTEGER NOT NULL,
    `aseguradora` VARCHAR NOT NULL,
    `nroPolizaSeguro` INTEGER NOT NULL,
    `patenteVehiculo` INTEGER NOT NULL,
    PRIMARY KEY (`idVehiculoConOT`)
);

CREATE TABLE `VehiculosUsadosParaVenta` (
    `idVehiculoUsadoVenta` INTEGER NOT NULL,
    `idFichaTecnicaVehiculo` INTEGER NOT NULL,
    `idUsuCompra` INTEGER NOT NULL,
    `montoVenta` DOUBLE NOT NULL,
    `fechaEstimadaEntregada` DATE NOT NULL,
    `fechaCompra` DATE NOT NULL,
    `montoCompra` DOUBLE NOT NULL,
    PRIMARY KEY (`idVehiculoUsadoVenta`)
);

CREATE TABLE `VentasVehiculosNuevos` (
    `idVentaVehiculoNuevo` INTEGER NOT NULL,
    `idUsuVentaVN` INTEGER NOT NULL,
    `idUsuPedido` INTEGER NOT NULL,
    `idUsuLlegada` INTEGER NOT NULL,
    `idPagoVentaVN` INTEGER NOT NULL,
    `fechaVentaVN` DATE NOT NULL,
    `fechaEntregaReal` DATE NOT NULL,
    `fechaPedidoFabrica` DATE NOT NULL,
    `fechaLlegadaDeFabrica` DATE NOT NULL,
    `fabricante` VARCHAR NOT NULL,
    `comisionCobrada` DOUBLE NOT NULL,
    PRIMARY KEY (`idVentaVehiculoNuevo`)
);

CREATE TABLE `Tarjetas` (
    `idTarjeta` INTEGER NOT NULL,
    `idEmisor` INTEGER NOT NULL,
    `banco` VARCHAR NOT NULL,
    `numeroTarj` INTEGER NOT NULL,
    `nomTitular` VARCHAR NOT NULL,
    `fechaVencTarj` DATE NOT NULL,
    PRIMARY KEY (`idTarjeta`)
);

CREATE TABLE `Emisor` (
    `idEmisor` INTEGER NOT NULL,
    `nombreEmisor` VARCHAR NOT NULL
);

ALTER TABLE `Presupuestos` ADD FOREIGN KEY (`idOT`) REFERENCES `OrdenesDeTrabajo`(`idOT`);
ALTER TABLE `Presupuestos` ADD FOREIGN KEY (`idPago`) REFERENCES `Pagos`(`idPago`);
ALTER TABLE `Presupuestos` ADD FOREIGN KEY (`idUsuAltaPresu`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `Presupuestos` ADD FOREIGN KEY (`idUsuCierrePresu`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `Presupuestos` ADD FOREIGN KEY (`idUsuRegPago`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `Usuarios` ADD FOREIGN KEY (`idCuenta`) REFERENCES `Cuentas`(`idCuenta`);
ALTER TABLE `Usuarios` ADD FOREIGN KEY (`datosPersonalesId`) REFERENCES `DatosPersonales`(`idDatosPerso`);
ALTER TABLE `Cuentas` ADD FOREIGN KEY (`idUsu`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `TrabajosPresupuestados` ADD FOREIGN KEY (`idPresupuesto`) REFERENCES `Presupuestos`(`idPresu`);
ALTER TABLE `RepuestosPlanificados` ADD FOREIGN KEY (`idPresu`) REFERENCES `Presupuestos`(`idPresu`);
ALTER TABLE `RepuestosPlanificados` ADD FOREIGN KEY (`idRepu`) REFERENCES `Repuesto`(`idRepuesto`);
ALTER TABLE `RenglonVenta` ADD FOREIGN KEY (`idRepuesto`) REFERENCES `Repuesto`(`idRepuesto`);
ALTER TABLE `RenglonVenta` ADD FOREIGN KEY (`idVenta`) REFERENCES `VentasRepuestos`(`idVenta`);
ALTER TABLE `OrdenesDeTrabajo` ADD FOREIGN KEY (`idTipoOt`) REFERENCES `TipoTrabajo`(`idTipoTrabajo`);
ALTER TABLE `Financiamientos` ADD FOREIGN KEY (`idTarjeta`) REFERENCES `Tarjetas`(`idTarjeta`);
ALTER TABLE `Turnos` ADD FOREIGN KEY (`idCliente`) REFERENCES `Clientes`(`idCliente`);
ALTER TABLE `VentasRepuestos` ADD FOREIGN KEY (`idUsuarioVenta`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `VentasRepuestos` ADD FOREIGN KEY (`idPago`) REFERENCES `Pagos`(`idPago`);
ALTER TABLE `VentasVehiculosUsados` ADD FOREIGN KEY (`idUsuarioVenta`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `VentasVehiculosUsados` ADD FOREIGN KEY (`idCliente`) REFERENCES `Clientes`(`idCliente`);
ALTER TABLE `VentasVehiculosUsados` ADD FOREIGN KEY (`idVehiculoUsadoVenta`) REFERENCES `VehiculosUsadosParaVenta`(`idVehiculoUsadoVenta`);
ALTER TABLE `VentasVehiculosUsados` ADD FOREIGN KEY (`idPagoVentaVU`) REFERENCES `Pagos`(`idPago`);
ALTER TABLE `VentasVehiculosUsados` ADD FOREIGN KEY (`idUsuEntrega`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `VehiculosConOrdenesTrabajo` ADD FOREIGN KEY (`idFichaTecnicaVehiculo`) REFERENCES `FichaTecnicaVehiculo`(`idFichaTecnicaVehiculo`);
ALTER TABLE `VehiculosConOrdenesTrabajo` ADD FOREIGN KEY (`idCliente`) REFERENCES `Clientes`(`idCliente`);
ALTER TABLE `VehiculosUsadosParaVenta` ADD FOREIGN KEY (`idUsuCompra`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `VehiculosUsadosParaVenta` ADD FOREIGN KEY (`idFichaTecnicaVehiculo`) REFERENCES `FichaTecnicaVehiculo`(`idFichaTecnicaVehiculo`);
ALTER TABLE `VentasVehiculosNuevos` ADD FOREIGN KEY (`idUsuVentaVN`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `VentasVehiculosNuevos` ADD FOREIGN KEY (`idPagoVentaVN`) REFERENCES `Pagos`(`idPago`);
ALTER TABLE `VentasVehiculosNuevos` ADD FOREIGN KEY (`idUsuPedido`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `VentasVehiculosNuevos` ADD FOREIGN KEY (`idUsuLlegada`) REFERENCES `Usuarios`(`idUsuario`);
ALTER TABLE `Tarjetas` ADD FOREIGN KEY (`idEmisor`) REFERENCES `Emisor`(`idEmisor`);
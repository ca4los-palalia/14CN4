
    create table Bascula (
        idBascula  serial not null,
        basculaAsignada bool,
        batchs int4,
        descripcion varchar(255),
        fechaModificacion timestamp,
        modoManual bool,
        nombre varchar(255),
        prepesado bool,
        unidadMedida varchar(255),
        primary key (idBascula)
    );

    create table BatchProduccion (
        idBatchProduccion int4 not null,
        addressMemory varchar(255),
        fechaRegistro timestamp,
        pesoObtenido float8,
        idRegistroProduccion int4,
        primary key (idBatchProduccion)
    );

    create table Formula (
        idFormula int4 not null,
        batchProcesado int4,
        cancelada bool,
        descripcion varchar(255),
        despachada bool,
        nombre varchar(255),
        prepesado bool,
        totalBatchAProcesar int4,
        turno varchar(255),
        primary key (idFormula)
    );

    create table IACNADispatcher (
        idAsignacion int4 not null,
        fechaRegistro timestamp,
        idFormula int4,
        primary key (idAsignacion)
    );

    create table Ingrediente (
        idIngrediente int4 not null,
        bascula varchar(255),
        descripcion varchar(255),
        especificacion float8,
        fechaRegistro timestamp,
        formulaIdForWS varchar(255),
        mp varchar(255),
        nombre varchar(255),
        prepesadoBascula bool,
        producto varchar(255),
        toleranciaMaxima float8,
        toleranciaMinima float8,
        unidadMedida varchar(255),
        idFormula int4,
        primary key (idIngrediente)
    );

    create table Material (
        idMaterial int4 not null,
        descripcion varchar(255),
        fechaModificacion timestamp,
        nombre varchar(255),
        primary key (idMaterial)
    );

    create table OrigenDeDatos (
        idOrigenDeDatos  serial not null,
        descripcion varchar(255),
        fechaModificacion timestamp,
        hostIP varchar(255),
        hostName varchar(255),
        locationProducts varchar(255),
        locationProductosPrepesados varchar(255),
        locationProductosRed varchar(255),
        nombre varchar(255),
        primary key (idOrigenDeDatos)
    );

    create table Privilegio (
        idPrivilegio int4 not null,
        fechaModificacion timestamp,
        productionCalidad bool,
        productionHeads bool,
        productionSupervisor bool,
        superUser bool,
        idUsuario int4,
        primary key (idPrivilegio)
    );

    create table Producto (
        idProducto int4 not null,
        fechaModificacion timestamp,
        nombre varchar(255),
        primary key (idProducto)
    );

    create table RegistroProduccion (
        idRegistroProduccion int4 not null,
        bascula varchar(255),
        descripcion varchar(255),
        especificacion float8,
        fechaRegistro timestamp,
        fechaRegistroDia varchar(255),
        formula varchar(255),
        idFormula varchar(255),
        mp varchar(255),
        sumatoriaBatchPesosObtenidos float8,
        toleranciaMaxima float8,
        toleranciaMinima float8,
        totalBatchProcesados float8,
        turno varchar(255),
        primary key (idRegistroProduccion)
    );

    create table Turno (
        idTurno  serial not null,
        descripcion varchar(255),
        fechaModificacion timestamp,
        horario varchar(255),
        nombre varchar(255),
        turnoHabilitado bool,
        primary key (idTurno)
    );

    create table Usuario (
        idUsuario int4 not null,
        apellidoMaterno varchar(255),
        apellidoPaterno varchar(255),
        fechaModificacion timestamp,
        nombre varchar(255),
        password varchar(255),
        userName varchar(255),
        primary key (idUsuario)
    );

    alter table BatchProduccion 
        add constraint FK28DC6124E96DE6FC 
        foreign key (idRegistroProduccion) 
        references RegistroProduccion;

    alter table IACNADispatcher 
        add constraint FKB9E9EE658C3C7EEA 
        foreign key (idFormula) 
        references Formula;

    alter table Ingrediente 
        add constraint FKE60570948C3C7EEA 
        foreign key (idFormula) 
        references Formula;

    alter table Privilegio 
        add constraint FK1435BC7ACD27C27A 
        foreign key (idUsuario) 
        references Usuario;

    create sequence hibernate_sequence;

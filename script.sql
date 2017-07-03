CREATE TABLE sae_reg (
    cedula INT(10) PRIMARY KEY NOT NULL,
    nac CHAR,
    cod_escolar VARCHAR(10),
    apellidos VARCHAR(20),
    nombres VARCHAR(20),
    grado INT(1),
    seccion CHAR,
    sexo CHAR,
    fecha_nac DATE,
    lugar_nac VARCHAR(10),
    estado_nac VARCHAR(10),
    ef VARCHAR(2),
    cedula_rep INT(10),
    apellido_rep VARCHAR(20),
    nombre_rep VARCHAR(20),
    parentezco VARCHAR(10),
    direccion VARCHAR(40),
    tlfn VARCHAR(12),
    celular VARCHAR(12),
    email VARCHAR(20));
    
select * from sae_reg;
delete from sae_reg;
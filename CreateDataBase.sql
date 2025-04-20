CREATE DATABASE IF NOT EXISTS amm;
use amm; 

DROP TABLE IF EXISTS usuarios_equipo;
DROP TABLE IF EXISTS servicio;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS equipo;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS tipo_limpieza;
DROP TABLE IF EXISTS tipo_documento;
DROP TABLE IF EXISTS tipo_usuario;

CREATE TABLE tipo_usuario (
  id_tipoUsua int NOT NULL AUTO_INCREMENT,
  nombre_tipo varchar(25) NOT NULL,
  user_crea varchar(45) NOT NULL DEFAULT 'Admin',
  creado_el datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_modifica varchar(45) DEFAULT NULL,
  modificado_el datetime DEFAULT NULL,
  PRIMARY KEY (id_tipoUsua),
  UNIQUE KEY id_userType (id_tipoUsua),
  UNIQUE KEY nombre_tipo_UNIQUE (nombre_tipo)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO tipo_usuario (nombre_tipo) VALUES ("Admin"), ("Coordinador"), ("Empleado");

CREATE TABLE tipo_documento (
  id_tipoDocu int NOT NULL AUTO_INCREMENT,
  nombre_tipo varchar(30) NOT NULL,
  user_crea varchar(45) NOT NULL DEFAULT 'Admin',
  creado_el datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_modifica varchar(45) DEFAULT NULL,
  modificado_el datetime DEFAULT NULL,
  PRIMARY KEY (id_tipoDocu),
  UNIQUE KEY id_tipoDocu_UNIQUE (id_tipoDocu),
  UNIQUE KEY nombre_tipo_UNIQUE (nombre_tipo)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO tipo_documento (nombre_tipo) VALUES ("DNI"), ("Pasaporte");

CREATE TABLE tipo_limpieza (
  id_tipoLimp int NOT NULL AUTO_INCREMENT,
  nombre_tipo varchar(30) NOT NULL,
  user_crea varchar(45) NOT NULL DEFAULT 'Admin',
  creado_el datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_modifica varchar(45) DEFAULT NULL,
  modificado_el datetime DEFAULT NULL,
  PRIMARY KEY (id_tipoLimp),
  UNIQUE KEY id_tipoLimp (id_tipoLimp),
  UNIQUE KEY nombre_tipo_UNIQUE (nombre_tipo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO tipo_limpieza (nombre_tipo) VALUES ("Regular"), ("Profunda"), ("Move in/out");

CREATE TABLE cliente (
  id_cliente int NOT NULL AUTO_INCREMENT,
  nombre_cliente varchar(45) NOT NULL,
  apellido_cliente varchar(45) NOT NULL,
  direccion_cliente varchar(65) NOT NULL,
  telefono_cliente varchar(15) NOT NULL,
  correo_cliente varchar(65) NOT NULL,
  observacion_cliente text NOT NULL,
  user_crea varchar(45) NOT NULL DEFAULT 'Admin',
  creado_el datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_modifica varchar(45) DEFAULT NULL,
  modificado_el datetime DEFAULT NULL,
  PRIMARY KEY (id_cliente),
  UNIQUE KEY id_cliente (id_cliente)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE equipo (
  id_equipo int NOT NULL AUTO_INCREMENT,
  nombre_equipo varchar(30) NOT NULL,
  user_crea varchar(45) NOT NULL DEFAULT 'Admin',
  creado_el datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_modifica varchar(45) DEFAULT NULL,
  modificado_el datetime DEFAULT NULL,
  PRIMARY KEY (id_equipo),
  UNIQUE KEY id_equi (id_equipo),
  UNIQUE KEY nombEqui (nombre_equipo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE usuario (
  id_usuario varchar(80) NOT NULL,
  id_tipoUsua int NOT NULL,
  nombre_usuario varchar(15) NOT NULL,
  contrasena_usuario varchar(15) NOT NULL,
  id_tipoDocu int NOT NULL,
  documento_usuario varchar(30) NOT NULL,
  nombres varchar(30) NOT NULL,
  apellidos varchar(30) NOT NULL,
  telefono_usuario varchar(30) NOT NULL,
  correo_usuario varchar(50) NOT NULL,
  user_crea varchar(45) NOT NULL DEFAULT 'Admin',
  creado_el datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_modifica varchar(45) DEFAULT NULL,
  modificado_el datetime DEFAULT NULL,
  PRIMARY KEY (id_usuario),
  UNIQUE KEY nombUsua (nombre_usuario),
  UNIQUE KEY id_usuario_UNIQUE (id_usuario),
  KEY id_tipoDocu (id_tipoDocu),
  KEY usuario_ibfk_2_idx (id_tipoUsua),
  CONSTRAINT usuario_ibfk_1 FOREIGN KEY (id_tipoDocu) REFERENCES tipo_documento (id_tipoDocu),
  CONSTRAINT usuario_ibfk_2 FOREIGN KEY (id_tipoUsua) REFERENCES tipo_usuario (id_tipoUsua)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE usuarios_equipo (
  id_usuarios_equipo int NOT NULL AUTO_INCREMENT,
  id_equipo int NOT NULL,
  id_usuario varchar(80) DEFAULT NULL,
  es_lider tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (id_usuarios_equipo),
  KEY id_equipo (id_equipo),
  KEY usuarios_equipo_ibfk_2 (id_usuario),
  CONSTRAINT usuarios_equipo_ibfk_1 FOREIGN KEY (id_equipo) REFERENCES equipo (id_equipo),
  CONSTRAINT usuarios_equipo_ibfk_2 FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE servicio (
  id_servicio int NOT NULL AUTO_INCREMENT,
  id_cliente int NOT NULL,
  id_equipo int NOT NULL,
  id_tipoLimp int NOT NULL,
  fecha date NOT NULL,
  hora time NOT NULL,
  tiempo_estimado time NOT NULL,
  tiempo_finalizacion time NOT NULL,
  precio int NOT NULL,
  observacion text,
  user_crea varchar(45) NOT NULL DEFAULT 'Admin',
  creado_el datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_modifica varchar(45) DEFAULT NULL,
  modificado_el datetime DEFAULT NULL,
  PRIMARY KEY (id_servicio),
  UNIQUE KEY id_servicio_UNIQUE (id_servicio),
  KEY id_cliente (id_cliente),
  KEY id_equipo (id_equipo),
  KEY id_tipoLimp (id_tipoLimp),
  CONSTRAINT servicio_ibfk_1 FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente),
  CONSTRAINT servicio_ibfk_2 FOREIGN KEY (id_equipo) REFERENCES equipo (id_equipo),
  CONSTRAINT servicio_ibfk_3 FOREIGN KEY (id_tipoLimp) REFERENCES tipo_limpieza (id_tipoLimp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT IGNORE INTO usuario (
id_usuario, id_tipoUsua, nombre_usuario, contrasena_usuario, id_tipoDocu, documento_usuario, nombres, apellidos, telefono_usuario, correo_usuario
) VALUES ('NA' ,3 , 'Ejemplo', '0000', 1, '0', 'Ejemplo', 'Ejemplo', '0', '0@gmail.com'), ('11026571230', 1, 'Admin', '1234', 1, '1026571230', 'Carlos', 'Quevedo', '3212300716', 'nyathepq@gmail.com');
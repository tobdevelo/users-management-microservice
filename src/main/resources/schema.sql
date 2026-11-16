-- =============================================
-- Script de creación de la base de datos
-- Gestión de Usuarios - Arquitectura Hexagonal
-- =============================================

CREATE DATABASE IF NOT EXISTS crud_usuarios
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS noticias (
                                        id              VARCHAR(36)  NOT NULL PRIMARY KEY,
    categoria       VARCHAR(100) NOT NULL,
    fecha           DATE         NOT NULL,
    pais            VARCHAR(100) NOT NULL,
    departamento    VARCHAR(100) NOT NULL,
    ciudad          VARCHAR(100) NOT NULL,
    periodista      VARCHAR(150) NOT NULL,
    programa_emite  VARCHAR(150) NOT NULL,
    fecha_emision   DATE         NOT NULL,
    descripcion     TEXT         NOT NULL,
    nivel_publico   ENUM('GENERAL', 'INFANTIL', 'JUVENIL', 'ADULTO', 'ESPECIALIZADO') NOT NULL,
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
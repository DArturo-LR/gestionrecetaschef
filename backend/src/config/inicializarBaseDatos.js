const mysql = require("mysql2/promise");

const inicializarBaseDatos = async () => {

    const conexion = await mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "1234"
    });

    await conexion.query(`
        CREATE DATABASE IF NOT EXISTS gestion_recetas
    `);

    await conexion.query(`
        USE gestion_recetas
    `);

    await conexion.query(`
        CREATE TABLE IF NOT EXISTS recetas (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(100),
            descripcion TEXT,
            categoria VARCHAR(50),
            tiempo_preparacion INT,
            imagen TEXT
        )
    `);

    await conexion.query(`
        CREATE TABLE IF NOT EXISTS ingredientes (
            id INT AUTO_INCREMENT PRIMARY KEY,
            receta_id INT,
            descripcion VARCHAR(200),
            FOREIGN KEY (receta_id)
                REFERENCES recetas(id)
        )
    `);

    await conexion.query(`
        CREATE TABLE IF NOT EXISTS pasos (
            id INT AUTO_INCREMENT PRIMARY KEY,
            receta_id INT,
            descripcion TEXT,
            orden_paso INT,
            FOREIGN KEY (receta_id)
                REFERENCES recetas(id)
        )
    `);

    await conexion.query(`
        CREATE TABLE IF NOT EXISTS opiniones (
            id INT AUTO_INCREMENT PRIMARY KEY,
            receta_id INT,
            comentario TEXT,
            puntuacion FLOAT,
            veces_preparada INT NULL,
            fecha_opinion TIMESTAMP
                DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (receta_id)
                REFERENCES recetas(id)
        )
    `);

    const [recetas] =
    await conexion.query(
        "SELECT * FROM recetas"
    );

if (recetas.length === 0) {

    await conexion.query(`
        INSERT INTO recetas
        (nombre, descripcion, categoria,
        tiempo_preparacion, imagen)
        VALUES

        (
            'Hamburguesa Artesanal',
            'Hamburguesa premium',
            'Comida Rapida',
            30,
            'https://imagenes.com/hamburguesa.jpg'
        ),

        (
            'Pizza Italiana',
            'Pizza tradicional italiana',
            'Italiana',
            40,
            'https://imagenes.com/pizza.jpg'
        )
    `);

    await conexion.query(`
        INSERT INTO ingredientes
        (receta_id, descripcion)
        VALUES

        (1, 'Pan artesanal'),
        (1, 'Carne premium'),
        (1, 'Queso cheddar'),

        (2, 'Masa italiana'),
        (2, 'Salsa napolitana'),
        (2, 'Queso mozzarella')
    `);

    await conexion.query(`
        INSERT INTO pasos
        (receta_id, descripcion, orden_paso)
        VALUES

        (1, 'Cocinar carne', 1),
        (1, 'Agregar queso', 2),
        (1, 'Servir hamburguesa', 3),

        (2, 'Preparar masa', 1),
        (2, 'Agregar salsa', 2),
        (2, 'Hornear pizza', 3)
    `);

    await conexion.query(`
        INSERT INTO opiniones
        (receta_id, comentario,
        puntuacion, veces_preparada)
        VALUES

        (
            1,
            'Muy deliciosa',
            5,
            2
        ),

        (
            2,
            'Excelente sabor',
            4,
            1
        )
    `);

    console.log("Datos iniciales insertados");
}

    console.log("Base de datos inicializada");
};

module.exports = inicializarBaseDatos;
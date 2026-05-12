const mysql = require("mysql2/promise");

const conexion = mysql.createPool({
    host: "localhost",
    user: "root",
    password: "123456789",
    database: "gestion_recetas"
});

module.exports = conexion;
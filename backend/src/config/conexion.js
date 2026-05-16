const mysql = require("mysql2/promise");

const conexion = mysql.createPool({
    host: "localhost",
    user: "chef",
    password: "1234",
    database: "gestion_recetas"
});

module.exports = conexion;
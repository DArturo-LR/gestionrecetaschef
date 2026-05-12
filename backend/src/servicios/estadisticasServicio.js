const conexion = require("../config/conexion");

const obtenerEstadisticas = async (id) => {

    const [resultado] =
        await conexion.query(
            `
            SELECT
                ROUND(AVG(puntuacion), 1)
                    AS promedio_puntuacion,

                COUNT(id)
                    AS total_opiniones,

                SUM(veces_preparada)
                    AS total_preparaciones

            FROM opiniones
            WHERE receta_id = ?
            `,
            [id]
        );

    return resultado[0];
};

module.exports = {
    obtenerEstadisticas
};
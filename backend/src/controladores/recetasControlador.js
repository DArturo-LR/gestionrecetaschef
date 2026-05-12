const conexion = require("../config/conexion");
const {
    obtenerEstadisticas
} = require("../servicios/estadisticasServicio");
const obtenerRecetas = async (req, res) => {

    try {

        const [recetas] =
            await conexion.query(
                "SELECT * FROM recetas"
            );

        res.json(recetas);

    } catch (error) {

        res.status(500).json({
            mensaje: "Error obteniendo recetas"
        });

    }
};
const obtenerDetalleReceta = async (req, res) => {

    const { id } = req.params;

    try {

        const [receta] =
            await conexion.query(
                "SELECT * FROM recetas WHERE id = ?",
                [id]
            );

        const [ingredientes] =
            await conexion.query(
                "SELECT * FROM ingredientes WHERE receta_id = ?",
                [id]
            );

        const [pasos] =
            await conexion.query(
                "SELECT * FROM pasos WHERE receta_id = ? ORDER BY orden_paso",
                [id]
            );

        const [opiniones] =
            await conexion.query(
                "SELECT * FROM opiniones WHERE receta_id = ?",
                [id]
            );

        res.json({
            receta: receta[0],
            ingredientes,
            pasos,
            opiniones
        });

    } catch (error) {

        res.status(500).json({
            mensaje: "Error obteniendo detalle"
        });

    }
};
const obtenerEstadisticasReceta =
    async (req, res) => {

    const { id } = req.params;

    try {

        const estadisticas =
            await obtenerEstadisticas(id);

        res.json(estadisticas);

    } catch (error) {

        res.status(500).json({
            mensaje: "Error obteniendo estadísticas"
        });

    }
};
module.exports = {
    obtenerRecetas,
    obtenerDetalleReceta,
    obtenerEstadisticasReceta
};
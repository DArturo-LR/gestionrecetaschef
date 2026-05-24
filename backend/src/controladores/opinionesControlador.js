const conexion = require("../config/conexion");

const guardarOpinion = async (req, res) => {

        const {
            receta_id,
            comentario,
            puntuacion,
            veces_preparada
        } = req.body;

    try {

        await conexion.query(
            `
            INSERT INTO opiniones
            (receta_id, comentario, puntuacion, veces_preparada)
            VALUES (?, ?, ?, ?)
            `,
            [
                receta_id,
                comentario,
                puntuacion,
                veces_preparada || 0
            ]
        );

        res.json({
            mensaje: "Opinión guardada correctamente"
        });

    } catch (error) {

        res.status(500).json({
            mensaje: "Error guardando opinión"
        });

    }
};

module.exports = {
    guardarOpinion
};
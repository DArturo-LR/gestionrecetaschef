const conexion = require("../config/conexion");

const crearReceta = async (req, res) => {

    const {
    nombre,
    descripcion,
    categoria,
    tiempo_preparacion,
    ingredientes,
    pasos,
    comentario,
    puntuacion,
    veces_preparada
} = req.body;

    try {

        const [resultado] =
            await conexion.query(
                `
                INSERT INTO recetas
                (nombre, descripcion, categoria, tiempo_preparacion)
                VALUES (?, ?, ?, ?)
                `,
                [
                    nombre,
                    descripcion,
                    categoria,
                    tiempo_preparacion
                ]
            );

        const recetaId = resultado.insertId;

        for (const ingrediente of ingredientes) {

            await conexion.query(
                `
                INSERT INTO ingredientes
                (receta_id, descripcion)
                VALUES (?, ?)
                `,
                [
                    recetaId,
                    ingrediente
                ]
            );
        }

        for (let i = 0; i < pasos.length; i++) {

            await conexion.query(
                `
                INSERT INTO pasos
                (receta_id, descripcion, orden_paso)
                VALUES (?, ?, ?)
                `,
                [
                    recetaId,
                    pasos[i],
                    i + 1
                ]
            );
        }
        if (puntuacion && veces_preparada) {

            await conexion.query(
                `
                INSERT INTO opiniones
                (receta_id, comentario, puntuacion, veces_preparada)
                VALUES (?, ?, ?, ?)
                `,
                [
                    recetaId,
                    comentario || "",
                    puntuacion,
                    veces_preparada
                ]
            );
        }

        res.json({
            mensaje: "Receta creada"
        });

    } catch (error) {

        res.status(500).json({
            mensaje: "Error creando receta"
        });
    }
};

module.exports = {
    crearReceta
};
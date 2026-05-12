const conexion = require("../config/conexion");

const crearReceta = async (req, res) => {

    const {
        nombre,
        descripcion,
        categoria,
        tiempo_preparacion,
        ingredientes,
        pasos
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
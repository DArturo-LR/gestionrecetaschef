const express = require("express");

const router = express.Router();

const {
    obtenerRecetas,
    obtenerDetalleReceta,
    obtenerEstadisticasReceta
} = require("../controladores/recetasControlador");
const {
    crearReceta
} = require("../controladores/crearRecetaControlador");

router.get("/", obtenerRecetas);
router.post("/", crearReceta);
router.get("/:id/estadisticas", obtenerEstadisticasReceta);
router.get("/:id", obtenerDetalleReceta);


module.exports = router;
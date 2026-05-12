const express = require("express");

const router = express.Router();

const {
    obtenerRecetas,
    obtenerDetalleReceta,
    obtenerEstadisticasReceta
} = require("../controladores/recetasControlador");

router.get("/", obtenerRecetas);
router.get("/:id/estadisticas", obtenerEstadisticasReceta);
router.get("/:id", obtenerDetalleReceta);


module.exports = router;
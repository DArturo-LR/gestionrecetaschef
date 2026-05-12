const express = require("express");

const router = express.Router();

const {
    guardarOpinion
} = require("../controladores/opinionesControlador");

router.post("/", guardarOpinion);

module.exports = router;
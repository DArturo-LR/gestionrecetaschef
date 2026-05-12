const express = require("express");
const cors = require("cors");
const inicializarBaseDatos =
    require("./config/inicializarBaseDatos");

const recetasRutas =
    require("./rutas/recetasRutas");
const opinionesRutas =
    require("./rutas/opinionesRutas");

const app = express();

app.use(cors());
app.use(express.json());

app.use("/recetas", recetasRutas);
app.use("/opiniones", opinionesRutas);

app.get("/", (req, res) => {
    res.send("Servidor funcionando");
});

inicializarBaseDatos();

app.listen(3000, "0.0.0.0", () => {
    console.log("Servidor ejecutándose");
});
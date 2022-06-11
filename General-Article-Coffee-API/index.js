const express = require("express");
const mysql = require("mysql");
const app = express();

app.use(express.json());
const port = process.env.PORT || 8080;
app.listen(port, () => {
  console.log(`Discoffery Rest API listening on port ${port}`);
});

app.get("/", async (req, res) => {
  res.json({ status: "server running" });
});

app.get("/:article_coffe", async (req, res) => {
  const query = "SELECT * FROM article_coffe WHERE name = ?";
  pool.query(query, [ req.params.breed ], (error, results) => {
    if (!results[0]) {
      res.json({ status: "Not found!" });
    } else {
      res.json(results);
    }
  });
});

const pool = mysql.createPool({
  user: process.env.DB_USER,
  password: process.env.DB_PASS,
  database: process.env.DB_NAME,
  socketPath: `/cloudsql/${process.env.INSTANCE_CONNECTION_NAME}`,
});






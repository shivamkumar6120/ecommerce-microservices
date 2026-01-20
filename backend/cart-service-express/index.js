const express = require("express");
const app = express();

app.use(express.json());

app.get("/cart-service", (req, res) => {
  res.send("Product Service Running");
});

app.listen(8083, () => {
  console.log("Product Service running on 8083");
});

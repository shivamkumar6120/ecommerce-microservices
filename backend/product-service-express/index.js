const express = require("express");
const app = express();

app.use(express.json());

app.get("/health", (req, res) => {
  res.send("Product Service Running");
});

app.listen(8082, () => {
  console.log("Product Service running on 8082");
});

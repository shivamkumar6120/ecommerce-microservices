require("dotenv").config();
const express = require("express");
const cors = require("cors");

const cartRoutes = require("./routes/cartRoutes");

const app = express();
app.use(cors());
app.use(express.json());

app.get("/health", (req, res) => {
  res.send("Cart Service Running");
});

app.use("/cart", cartRoutes);

const PORT = process.env.PORT || 8083;
app.listen(PORT, () => {
  console.log(`Cart Service running on port ${PORT}`);
});

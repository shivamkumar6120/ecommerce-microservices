require("dotenv").config();
const express = require("express");
const cors = require("cors");
const productRoutes = require("./routes/productRoutes");

const app = express();



app.use("/products", productRoutes);


app.use(cors());
app.use(express.json());

app.get("/health", (req, res) => {
  res.send("Product Service Running");
});

const PORT = process.env.PORT || 8082;
app.listen(PORT, () => {
  console.log(`Product Service running on port ${PORT}`);
});

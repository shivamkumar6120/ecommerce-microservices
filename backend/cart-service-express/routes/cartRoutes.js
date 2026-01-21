const express = require("express");
const authMiddleware = require("../middleware/authMiddleware");

const router = express.Router();

// In-memory cart storage
const carts = {};

// Add item to cart
router.post("/add", authMiddleware, (req, res) => {
  const { product } = req.body;
  const userEmail = req.user.email;

  if (!carts[userEmail]) {
    carts[userEmail] = [];
  }

  carts[userEmail].push(product);

  res.json({
    message: "Item added to cart",
    cart: carts[userEmail]
  });
});

// View cart
router.get("/", authMiddleware, (req, res) => {
  const userEmail = req.user.email;

  res.json({
    user: userEmail,
    cart: carts[userEmail] || []
  });
});

// Clear cart
router.delete("/clear", authMiddleware, (req, res) => {
  const userEmail = req.user.email;
  carts[userEmail] = [];

  res.json({ message: "Cart cleared" });
});

module.exports = router;

const express = require("express");
const authMiddleware = require("../middleware/authMiddleware");

const router = express.Router();

// PUBLIC endpoint
router.get("/public", (req, res) => {
  res.json({
    message: "Public product list",
    products: ["Pen", "Book", "Laptop"]
  });
});

// PROTECTED endpoint
router.get("/secure", authMiddleware, (req, res) => {
  res.json({
    message: "Secure product data",
    user: req.user,
    products: ["iPhone", "MacBook"]
  });
});

module.exports = router;

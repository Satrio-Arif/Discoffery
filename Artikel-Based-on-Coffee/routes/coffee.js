const express = require('express');
const router = express.Router();
const db = require('../config/database/db');
const controller = require('../controller/index');

router.get('/', controller.coffee.getAll);
router.get('/:id_coffee', controller.coffee.getOne);
router.post('/:id_coffee', controller.coffee.post);
router.put('/:id_coffee', controller.coffee.put);




module.exports = router;

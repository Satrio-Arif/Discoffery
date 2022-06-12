const Sequelize = require('sequelize');
const connection = require('../database/db');

var coffee = connection.define('coffee',
  {
    id_coffee:  Sequelize.INTEGER,
    title: Sequelize.STRING,
    origin: Sequelize.STRING,
    type: Sequelize.STRING,
    rating: Sequelize.FLOAT,
    content: Sequelize.TEXT,
    benefit: Sequelize.STRING,
    price: Sequelize.STRING
  }, {
      freezeTableName: true,
      timestamps: false
  });


 coffee.removeAttribute('id');
 module.exports = coffee;
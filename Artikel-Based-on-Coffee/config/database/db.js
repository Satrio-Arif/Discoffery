var Sequelize = require('sequelize');
var connection = new Sequelize('article_coffee', 'root', '', {
    dialect: 'mysql',
    host: 'localhost',
});

module.exports = connection;
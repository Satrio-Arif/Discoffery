var Sequelize = require('sequelize');
var connection = new Sequelize('article_coffee', 'rich', '12345', {
    dialect: 'mysql',
    host: '34.101.56.51',
});

module.exports = connection;
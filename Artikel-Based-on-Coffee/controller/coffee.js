const model = require('../config/model/index');
const controller = {};

controller.getAll = async function(req, res) {
    try {
        if (req.query.id_coffee) {
            let coffee = await model.coffee.findAll({
                where:{
                    id_coffee: req.query.id_coffee
                }
            })
            if (coffee.length > 0) {
                res.status(200).json({
                    message: "Artikel Coffee Ditemukan",
                    data: coffee
                })
            } else{
                res.status(200).json({
                    message: 'Tidak ada Data',
                    data: []
                })
            }
        } else {
            await model.coffee.findAll({
                attributes: ['id_coffee', 'title', 'content']
            })
            .then ((result) => {
                if (result.length > 0) {
                    res.status(200).json({
                        message: 'Get Article Coffee Discoffery',
                        data: result
                  })
                } else{
                    res.status(200).json({
                        message: 'Tidak ada Article Coffee Discoffery',
                        Data:[]
                    })
                }
            })
        }
    } catch (error) {
        res.status(404).json({
            message: error
        })
    }
}

controller.getOne = async function (req, res) {
    try {
        let coffee = await model.coffee.findAll({
            where:{
                id_coffee: req.query.id_coffee
            }
        })
        if (coffee.length > 0) {
            res.status(200).json({
                message: "Artikel Coffee Ditemukan",
                data: coffee
            })
        } else{
            res.status(200).json({
                message: 'Tidak ada Data',
                data: []
            })
        }
    } catch (error) {
        res.status(404).json({
            message: error.message
        })
    }
}

controller.post = async function (req, res){
    try {
        let coffee = await model.coffee.create({
            id_coffee: req.body.id_coffee,
            title: req.body.title,
            content: req.body.content
    })
    res.status(201).json ({
        message: "Berhasil Menambahkan Data Article",
        data: coffee
    })
    }  catch (error) {
        res.status(404).json({
            message: error.message
        })
    }
}

controller.put = async function (req, res) {
    try {
        let coffee = await model.coffee.update({
            id_coffee: req.body.id_coffee,
            title: req.body.title,
            content: req.body.content
        })
    res.status(200).json({
        message: "Berhasil Mengubah Data Article"
    })
} catch (error){
    res.status(404).json({
        message: error.message
    })
    }
}


module.exports = controller;
'use strict';


var path = require('path');

const Controller = require('egg').Controller;

class HomeController extends Controller {
  async index() {


    //和数据库打交道建议放在服务里面


    var result = await this.app.mongo.find('users', { query: { "_id": this.app.getObjectID('5b72abc7e4f0720a68f05b41') } });

    console.log(result)



    //删除
    var result = await this.app.mongo.findOneAndDelete('users', { filter: { "_id": this.app.getObjectID('5b72ad791c2ba42e6c5a4a78') } })

    console.log(result)

    await this.ctx.render('index');

  }

  async order() {

    /*
    //mongo 命令模式
    db.order.aggregate([
        {
          $lookup:
            {
              from: "order_item",
              localField: "order_id",
              foreignField: "order_id",
              as: "items"
            }
      },
      {
        $match:{"all_price":{$gte:90}}
      }

    ],{
      explain:true
    })

    */

    var result = await this.app.mongo.aggregate('order', {

      pipeline: [
        {
          $lookup:
          {
            from: "order_item",
            localField: "order_id",
            foreignField: "order_id",
            as: "items"
          }
        },
        {
          $match: { "all_price": { $gte: 90 } }
        },
        {
          $limit: 2
        }

      ],
      options: {}

    })
    this.ctx.body = result;

  }
}
module.exports = HomeController;

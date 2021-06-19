'use strict';

const Controller = require('egg').Controller;

class OrderController extends Controller {
  async index() {
    
    //实现关联查询


    // var orderResult=await this.ctx.model.Order.find({});


    var orderResult=await this.ctx.model.Order.aggregate([

        {

            $lookup:{
                from:'order_item',
                localField:'order_id',
                foreignField:'order_id',
                as:'items'
            }
        },
        {
            $match:{"all_price":{$gte:90}}
        }

    ]);


    this.ctx.body=orderResult;


  }
}

module.exports = OrderController;

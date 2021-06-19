'use strict';

const Controller = require('egg').Controller;

class HomeController extends Controller {
  async index() {
    // this.ctx.body = '首页';
   

    // //调用extend里面扩展的application

    // console.log(this.app.foo());


    // //调用extend里面扩展的ctx


    // console.log(this.ctx.getHost());



    // //调用extend里面扩展的helper的方法


    // console.log(this.ctx.helper.getHelperData());


   

    // //调用extend 扩展request的方法

    // console.log(this.ctx.request.foo());



    //注意 异步
    await this.ctx.render('index');


  }
}

module.exports = HomeController;

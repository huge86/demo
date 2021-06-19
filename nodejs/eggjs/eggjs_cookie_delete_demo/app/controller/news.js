'use strict';

const Controller = require('egg').Controller;

class NewsController extends Controller {
  async index() {

    //获取cookie
    // var username=this.ctx.cookies.get('username');



    //获取加密的cookie

    var username=this.ctx.cookies.get('userinfo',{
      encrypt:true  
    });




    await this.ctx.render('news',{

      username:username
    });
  }
}

module.exports = NewsController;

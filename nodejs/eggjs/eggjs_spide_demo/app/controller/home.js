'use strict';

const Controller = require('egg').Controller;

class HomeController extends Controller {
  async index() {

    var result=await this.ctx.service.news.getNewsList();
    

    console.log(result);

    this.ctx.body = '你好egg';
  }
}
module.exports = HomeController;

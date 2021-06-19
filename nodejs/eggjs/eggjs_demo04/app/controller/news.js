'use strict';

const Controller = require('egg').Controller;

//egg 是一个mvc的框架

/*

MVC框架：

  view                          视图 模板 页面的展示

  controller控制器               负责处理一些业务逻辑的处理  (简单业务逻辑处理)

  model 模型（service）          和数据打交道（查询数据库、操作数据库数据   请求数据）（复杂的业务逻辑  以及数据操作）

  更适合团队开发、业务逻辑清晰 、 有利于开发和维护

*/
class NewsController extends Controller {
  async index() {

    var msg = 'ejs';
    var list = await this.service.news.getNewsList();
    await this.ctx.render('news', {
      mag: msg,
      list: list
    });
  }

}

module.exports = NewsController;

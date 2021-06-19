'use strict';

const Controller = require('egg').Controller;

class ArticleController extends Controller {
  async index() {
    
    this.ctx.body='后台文章管理列表'
  }
  async add(){

    this.ctx.body='后台增加文章'

  }
  async edit(){
    this.ctx.body='后台文章编辑'

  }
}

module.exports = ArticleController;

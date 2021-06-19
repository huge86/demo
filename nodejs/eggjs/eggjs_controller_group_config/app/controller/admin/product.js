'use strict';

const Controller = require('egg').Controller;

class ProductController extends Controller {
    async index() {
    
        this.ctx.body='后台商品管理列表'
      }
      async add(){
    
        this.ctx.body='后台增加商品'
    
      }
      async edit(){
        this.ctx.body='后台商品编辑'
    
      }
}

module.exports = ProductController;

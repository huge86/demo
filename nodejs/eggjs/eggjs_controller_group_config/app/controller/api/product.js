'use strict';

const Controller = require('egg').Controller;

class ProductController extends Controller {
  async index() {
    this.ctx.body='商品接口api'
  }
}

module.exports = ProductController;

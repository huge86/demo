'use strict';

const Controller = require('egg').Controller;

class ShopController extends Controller {
  async index() {
    
    this.ctx.body="shop页面";
  } 
}

module.exports = ShopController;

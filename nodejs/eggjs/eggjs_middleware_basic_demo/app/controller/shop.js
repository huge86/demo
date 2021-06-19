'use strict';

const Controller = require('egg').Controller;

class ShopController extends Controller {
  async index() {
    
    this.ctx.body={

      name:'这是shop页面'
    };
  } 
}

module.exports = ShopController;

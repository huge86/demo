'use strict';

const Controller = require('egg').Controller;

class ShopController extends Controller {
  async index() {
    
    console.log(this.ctx.cookies.get('username'));

    this.ctx.body="cookie="+this.ctx.cookies.get('username');
  } 
}

module.exports = ShopController;

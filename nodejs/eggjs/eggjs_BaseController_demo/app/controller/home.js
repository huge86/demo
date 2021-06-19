'use strict';

const Controller = require('egg').Controller;

class HomeController extends Controller {
  async index(ctx) {       /*不推荐*/

      await ctx.render('home');
  }


  
}

module.exports = HomeController;

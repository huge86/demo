'use strict';

const Controller = require('egg').Controller;

class SpiderController extends Controller {
  async requestUrl(url) {    

       var result=await this.ctx.curl(url);

       return result;

  }
}

module.exports = SpiderController;

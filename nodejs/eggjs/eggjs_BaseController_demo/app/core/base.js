'use strict';


const Controller = require('egg').Controller;

class BaseController extends Controller {
  async getUserInfo() {

    return{

        name:'张三',
        age:20
    }
  }

  async success(redirectUrl){
    await this.ctx.render('public/success',{

        redirectUrl:redirectUrl||'/'
    });

  }
  async error(redirectUrl){
        await this.ctx.render('public/error',{

            redirectUrl:redirectUrl||'/'
        });

  }


}

module.exports = BaseController;

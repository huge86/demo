'use strict';


var BaseController =require('./base.js');

class LoginController extends BaseController {
  async index() {

    await this.ctx.render('admin/login');
  }

  //执行登录的方法  post
  async doLogin() {

    console.log(this.ctx.request.body);
    

  }

}

module.exports = LoginController;

'use strict';


const BaseController = require('../core/base.js');

class UserController extends BaseController {
  async login() {
        await this.ctx.render('login');
  }
  async register() {
         await this.ctx.render('register');
  }

  async doLogin() {
      
       console.log(this.ctx.request.body);
    
      //注意 await

      await this.success('/login');

  }
  async doRegister() {
      console.log(this.ctx.request.body);

      await this.error('/register');
     
  }
}

module.exports = UserController;

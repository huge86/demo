'use strict';

const Controller = require('egg').Controller;

class HomeController extends Controller {
  async index() {    



    //设置session

      this.ctx.session.username='张三';
    


      this.ctx.session.userinfo={

        name:'李四',
        age:20
      }

    //设置session的过期时间   修改session的默认参数  不建议用这样的方式
    
    // this.ctx.session.maxAge=5000; 



    
       await this.ctx.render('home');
  } 

  
}

module.exports = HomeController;

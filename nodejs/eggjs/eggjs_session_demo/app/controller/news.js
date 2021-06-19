'use strict';

const Controller = require('egg').Controller;

class NewsController extends Controller {
  async index() {


  
    var username=this.ctx.session.username;


    var userinfo=this.ctx.session.userinfo;

    //设置session的过期时间   修改session的默认参数  不建议用这样的方式
    
    // this.ctx.session.maxAge=5000; 


    console.log(userinfo);



    await this.ctx.render('news',{

      username:username
    });
  }
}

module.exports = NewsController;

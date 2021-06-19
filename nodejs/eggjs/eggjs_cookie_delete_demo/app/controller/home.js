'use strict';

const Controller = require('egg').Controller;

class HomeController extends Controller {
  async index() {    

      /*
      cookie:

        1.可以实现 同一个浏览器访问同一个域的时候 不同页面之间的数据共享


        2、实现数据的持久化  （关闭浏览器重新打开以后数据还存在）


      */

      /*
      
      第一个参数：cookies的名称

      第二个参数：cookies的值

      第三个参数：配置

      默认情况：cookies当浏览器关闭以后就销毁了
      */

      //  this.ctx.cookies.set('username','zhangsan');





 
      //注意：默认情况下面 egg.js里面的cookie没法设置中文    argument value is invalid (code: ERR_ASSERTION)

      // this.ctx.cookies.set('username','张三');





      // this.ctx.cookies.set('username','zhangsan',{
      //     maxAge:1000*3600*24,  //cookie存储一天     设置过期时间后关闭浏览器重新打开cookie还存在
      //     httpOnly:true,
      //     signed:true,     //对cookie进行签名  防止用户修改cookie
      //     encrypt:true   //是否对cookie进行加密     如果cookie加密那么获取的时候要对cookie进行解密

      // });







      //如果cookie加密以后就可以设置中文cookie   (encrypt:true)


      this.ctx.cookies.set("userinfo",'张三',{
          maxAge:1000*3600*24,  //cookie存储一天     设置过期时间后关闭浏览器重新打开cookie还存在
          httpOnly:true,
          signed:true,     //对cookie进行签名  防止用户修改cookie
          encrypt:true   //是否对cookie进行加密     如果cookie加密那么获取的时候要对cookie进行解密
      })

      this.ctx.cookies.set("name",'张三',{
          maxAge:1000*3600*24,  //cookie存储一天     设置过期时间后关闭浏览器重新打开cookie还存在
          httpOnly:true,
          signed:true,     //对cookie进行签名  防止用户修改cookie
          encrypt:true   //是否对cookie进行加密     如果cookie加密那么获取的时候要对cookie进行解密
      })




      //cookies里面设置对象         JSON.stringify()          JSON.parse()
      
     
       await this.ctx.render('home');
  } 

  async loginOut(){

    //清理cookies
    // this.ctx.cookies.set('userinfo',null);


    // this.ctx.cookies.set('userinfo',null,{
    //   maxAge:0
    // });
    

    this.ctx.redirect('/news');   /*路由跳转*/


  }
}

module.exports = HomeController;

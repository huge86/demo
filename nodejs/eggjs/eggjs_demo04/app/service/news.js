'use strict';


/*

服务继承Service为了方便egg在this上面绑定以下的方法：

  this.ctx: 当前请求的上下文 Context 对象的实例，通过它我们可以拿到框架封装好的处理当前请求的各种便捷属性和方法。
  this.app: 当前应用 Application 对象的实例，通过它我们可以拿到框架提供的全局对象和方法。
  this.service：应用定义的 Service，通过它我们可以访问到其他业务层，等价于 this.ctx.service 。
  this.config：应用运行时的配置项。
  this.logger：logger 对象，上面有四个方法（debug，info，warn，error），分别代表打印四个不同级别的日志，使用方法和效果与 context logger 中介绍的一样，但是通过这个 logger 对象记录的日志，在日志前面会加上打印该日志的文件路径，以便快速定位日志打印位置


//服务的命名规则

  Service 文件必须放在 app/service 目录，可以支持多级目录，访问的时候可以通过目录名级联访问。
  app/service/biz/user.js => ctx.service.biz.user    (建议)****  
  app/service/sync_user.js => ctx.service.syncUser
  app/service/HackerNews.js => ctx.service.hackerNews

*/

const Service = require('egg').Service;
class NewsService extends Service {
  async getNewsList() {

    //获取config的数据
    console.log(this.config.api);
    //注意获取新闻数据
    var list = ['11111', '22222', '33333'];
    //调用user服务的数据
    var user = await this.service.user.getUserInfo();
    console.log(user);
    return list;

  }

}

module.exports = NewsService;

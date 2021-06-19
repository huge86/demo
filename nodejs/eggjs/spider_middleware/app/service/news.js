'use strict';

const Service = require('egg').Service;

class NewsService extends Service {
  async getNewsList() {
    
    //通过抓取接口返回数据

    // curl的方法可以获取远程的数据


    var api=this.config.api+'appapi.php?a=getPortalList&catid=20&page=1'


    var response=await this.ctx.curl(api);

    // console.log(response.data); // 返回的是Buffer

    var data=JSON.parse(response.data);    //把Buffer类型转转换成对象

    // console.log(data);
    return data.result;

  }

  //获取新闻详情

  async getNewsContent(aid){


    var api=this.config.api+'appapi.php?a=getPortalArticle&aid='+aid;


    var response=await this.ctx.curl(api);

    var data=JSON.parse(response.data);    //把Buffer类型转转换成对象

    return data.result;

  }
}

module.exports = NewsService;

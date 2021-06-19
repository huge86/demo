'use strict';

module.exports = appInfo => {
  const config = exports = {};

  // use for cookie sign key, should change to your own and keep security
  config.keys = appInfo.name + '_1532511512428_3477';


 //配置session      session的配置和cookie基本是一样的，可以使用cookie里面的配置
  config.session={
      key:'SESSION_ID',   //设置session cookie里面的key
      maxAge:30*1000*60,
      httpOnly:true,     
      encrypt:true,
      renew:true   //renew等于true  那么每次刷新页面的时候 session都会被延期
  }
  
  
  // config.middleware = ['auth','jsonp','compress','adminAuth'];
  // 配置中间件
  config.middleware = ['auth','jsonp','compress'];

  config.compress = {
    // enable:false,    
    threshold: 1024 //它支持指定只有当 body 大于配置的 threshold 时才进行 gzip 压缩
  };


  //https://eggjs.org/zh-cn/basics/middleware.html
  config.auth = {
    // enable:false, 
    // match: '/news',
    // ignore:'/news',

    match(ctx){
      // ctx 上下文  可以获取请求的地址
      console.log(ctx.request.url);
      if(ctx.request.url=='/shop' || ctx.request.url=='/news'){
        return true;
      }
      return false;      
    },
    title:'this is  middleware'
  };


  

  //框架中间件的默认配置
  config.bodyParser={
    jsonLimit: '10mb' //Default is 1mb.   
  }

  
  //配置模板引擎
  config.view = {
    mapping: {
      '.html': 'ejs',
    },
  };
  

  return config;
};


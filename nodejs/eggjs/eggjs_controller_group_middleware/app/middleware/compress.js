
//egg.js中使用koa-compress开启压缩



/*

egg.js中配置koa-compress模块：


1、安装 cnpm  install koa-compress --save
2、middleware文件夹下面新建一个compress.js

3、在jsonp.js中引入koa-compress 并且通过module.exports暴露
module.exports=require('koa-compress');

4、config.default.js中配置jsonp中间件   config.middleware = ['compress'];

*/


module.exports=require('koa-compress');




//非标准的中间件

/*
koa中的非标准中间件的配置

    const Middleware = require('some-koa-middleware');


    app.use(Middleware(options.compiler,options.xxxx))


egg.js中非标准的中间件配置：


const Middleware = require('some-koa-middleware');
module.exports=(option,app)=>{
    return Middleware(options.compiler,options.xxxx);

}

*/


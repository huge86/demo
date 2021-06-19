
//egg.js的中间件生态基于koa
/*

egg.js中配置koa-jsonp模块：


1、安装 cnpm  install koa-jsonp --save
2、middleware文件夹下面新建一个jsonp.js

3、在jsonp.js中引入koa-jsonp 并且通过module.exports暴露
module.exports=require('koa-jsonp');

4、config.default.js中配置jsonp中间件   config.middleware = ['jsonp'];

*/

var jsonp=require('koa-jsonp');

module.exports=jsonp;
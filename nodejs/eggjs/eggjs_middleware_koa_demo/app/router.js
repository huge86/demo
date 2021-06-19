'use strict';

/**
 * @param {Egg.Application} app - egg application
 */
module.exports = app => {
  const { router, controller } = app;

  //路由中获取中间件

  var auth=app.middleware.auth({title:'this is router.js  middleware'})


  router.get('/',auth, controller.home.index);  
  router.get('/news',auth, controller.news.index);
  router.get('/shop', controller.shop.index);
};



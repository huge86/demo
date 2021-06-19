'use strict';

/**
 * @param {Egg.Application} app - egg application
 */
module.exports = app => {
  const { router, controller } = app;

  //路由中获取中间件
  router.get('/', controller.home.index);  
  router.get('/news', controller.news.index);
  router.get('/shop', controller.shop.index);
};



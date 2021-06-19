'use strict';

/**
 * @param {Egg.Application} app - egg application
 */
module.exports = app => {
  const { router, controller } = app;
  router.get('/', controller.home.index);

  router.get('/order', controller.home.order);
  
  router.get('/newslist', controller.news.index);


};

'use strict';

/**
 * @param {Egg.Application} app - egg application
 */
module.exports = app => {
  const { router, controller } = app;

  //路由中获取中间件

  // var auth=app.middleware.auth({title:'this is router.js  middleware'})



  //后台

 router.get('/admin/user', controller.admin.user.index);  


 router.get('/admin/article', controller.admin.article.index);  
 router.get('/admin/article/add', controller.admin.article.add);  
 router.get('/admin/article/edit', controller.admin.article.edit);  


 router.get('/admin/product', controller.admin.product.index);  
 router.get('/admin/product/add', controller.admin.product.add);  
 router.get('/admin/product/edit', controller.admin.product.edit);  




//   //api接口
  router.get('/api/user', controller.api.user.index);  
  router.get('/api/product', controller.api.product.index);  



  //前台首页
  router.get('/', controller.home.index);  
  router.get('/news', controller.news.index);
  router.get('/shop', controller.shop.index);
};



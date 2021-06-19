'use strict';

/**
 * @param {Egg.Application} app - egg application
 */
module.exports = app => {
  const { router, controller } = app;

  //路由中获取中间件

  // var auth=app.middleware.auth({title:'this is router.js  middleware'})



  var adminAuth=app.middleware.adminAuth();

  //后台

 router.get('/admin/user',adminAuth, controller.admin.user.index);  


 router.get('/admin/article',adminAuth, controller.admin.article.index);  
 router.get('/admin/article/add',adminAuth, controller.admin.article.add);  
 router.get('/admin/article/edit', adminAuth,controller.admin.article.edit);  


 router.get('/admin/product', adminAuth,controller.admin.product.index);  
 router.get('/admin/product/add', adminAuth,controller.admin.product.add);  
 router.get('/admin/product/edit',adminAuth, controller.admin.product.edit);  




//   //api接口
  router.get('/api/user', controller.api.user.index);  
  router.get('/api/product', controller.api.product.index);  



  //前台首页
  router.get('/', controller.home.index);  
  router.get('/news', controller.news.index);
  router.get('/shop', controller.shop.index);
};



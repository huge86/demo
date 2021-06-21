'use strict';

/**
 * @param {Egg.Application} app - egg application
 */
module.exports = app => {
  const { router, controller } = app;
  router.get('/', controller.home.index);
  router.get('/create', controller.home.create);
  router.get('/find', controller.home.find);
  router.get('/update', controller.home.update);
  router.get('/destroy', controller.home.destroy);

  router.get('/article', controller.article.index);

  router.get('/showAll', controller.article.showAll);

  
  
  
};

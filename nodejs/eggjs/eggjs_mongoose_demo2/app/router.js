'use strict';

/**
 * @param {Egg.Application} app - egg application
 */
module.exports = app => {
  const { router, controller } = app;
  
  router.get('/', controller.home.index);
  
  router.get('/user', controller.user.index);


  router.get('/user/add', controller.user.addUser);
  router.get('/user/edit', controller.user.editUser);
  router.get('/user/remove', controller.user.removeUser);
  

  
};

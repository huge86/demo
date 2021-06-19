'use strict';

module.exports = appInfo => {
  const config = exports = {};

  // use for cookie sign key, should change to your own and keep security
  config.keys = appInfo.name + '_1534304805936_5738';

  // add your config here
  config.middleware = [];

  exports.view = {
    mapping: {
      '.html': 'ejs',
    },
  };

  //配置数据库

  exports.mongo = {
    client: {
      host: '127.0.0.1',
      port: '27017',
      name: 'eggcms',
      user: 'eggadmin',
      password: '123456',
      options: {},
    },
  };


  return config;
};

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

  //配置mongoose数据库

  //mongodb://127.0.0.1/eggcms
  //mongodb://eggadmin:123456@localhost:27017/eggcms

  exports.mongoose = {
    client: {
      url: 'mongodb://127.0.0.1/eggcms',
      options: {},
    },
  };



  return config;
};

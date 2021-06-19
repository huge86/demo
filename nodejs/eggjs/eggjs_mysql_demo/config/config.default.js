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

  //配置mysql数据库的连接地址

  exports.mysql = {
    // database configuration
    client: {
      // host
      host: 'localhost',
      // port
      port: '3306',
      // username
      user: 'root',
      // password
      password: '123456',
      // database
      database: 'eggcms'
    },
    // load into app, default is open
    app: true,
    // load into agent, default is close
    agent: false,
  };


  return config;
};

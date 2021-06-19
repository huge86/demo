'use strict';

module.exports = appInfo => {
  const config = exports = {};

  // use for cookie sign key, should change to your own and keep security
  config.keys = appInfo.name + '_1532656413112_8161';

  // 中间件配置
  config.middleware = [];
 
  


  //配置ejs模板引擎
  config.view = {
    mapping: {
      '.html': 'ejs',
    }
  };


  //配置公共的api

  config.api='http://www.phonegap100.com/';

  return config;
};

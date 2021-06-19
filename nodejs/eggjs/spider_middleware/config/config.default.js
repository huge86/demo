'use strict';

module.exports = appInfo => {
  const config = exports = {};

  // use for cookie sign key, should change to your own and keep security
  config.keys = appInfo.name + '_1532656413112_8161';

  // 增加配置中间件
  config.middleware = ['printdate','forbidip'];

  //给printdate中间件传入的参数
  config.printdate={

    aaa: 'aaaaaa',
    bbb: "bbbb"
  }


  config.forbidip={

    forbidips:[
      '127.0.0.1',
      '192.168.0.10'
    ]
  }



  


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

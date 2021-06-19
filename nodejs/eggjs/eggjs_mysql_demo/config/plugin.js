'use strict';

// had enabled by egg
// exports.static = true;
exports.ejs = {
    enable: true,
    package: 'egg-view-ejs',
};


//配置插件
exports.mysql = {
    enable: true,
    package: 'egg-mysql',
};
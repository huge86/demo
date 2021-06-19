'use strict';

// had enabled by egg
// exports.static = true;
exports.ejs = {
    enable: true,
    package: 'egg-view-ejs',
};


//配置egg-mongoose插件
exports.mongoose = {
    enable: true,
    
    package: 'egg-mongoose'


};

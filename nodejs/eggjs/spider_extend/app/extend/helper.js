//扩展里面引入第三方模块

/*
https://www.npmjs.com/package/silly-datetime

1.npm i silly-datetime --save


2、var sd = require('silly-datetime');
*/

var sd = require('silly-datetime');

module.exports = {
    formatTime(param) {
      // this 是 helper 对象，在其中可以调用其他 helper 方法

      // this.ctx => context 对象
      // this.app => application 对象
     
      
    //   console.log(new Date(param));


      //格式化日期  param（时间戳）

      return sd.format(new Date(param*1000), 'YYYY-MM-DD HH:mm');


    },
    getHelperData(){

        return '我是helper里面的数据'
    }
  };
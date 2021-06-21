/*
需要的模块：https://www.npmjs.com/package/jsonwebtoken

https://www.npmjs.com/package/basic-auth

*/

var jwt = require('jsonwebtoken');
var auth = require('basic-auth')
var express = require('express');
var router = express.Router();
//权限判断的中间件
var authMiddle=function(req, res, next){
  
  var result = auth(req);
  console.log(result);
  
  /*
  1、可以获取客户端传过来的pass(sign)
  
  2、token

  3、获取url传过来的参数   uid=1&address_id=345

  4、我们根据uid查找用户的密码     私钥=md5(密码)

  5、用户同样的算法生成 服务器的Sign    var 服务器的Sign=md5('uid=1&address_id=345+私钥');

  6、对比客户端的签名和服务器端是否一样   如果一样允许  不一样拒绝
  
  */

  if (!result) {
    res.send({
      success: false,
      msg: "token错误"
    });
    return;
  }
  try {
    var decoded = jwt.verify(result.name, 'this is sign');
    console.log(decoded);    
    next();
  } catch (error) {
    res.send({
      success: false,
      msg: error
    })
  }

 
}

router.get('/', function (req, res, next) {
  res.send('api接口首页');
});

router.get('/login', function (req, res, next) {

  var token = jwt.sign({ uid: '1', username: "zhangsan" }, 'this is sign', {
    expiresIn: 60*60*24
  });

  res.send({
    "token":token
  });

});

//用户登录以后才能访问的
router.get('/address',authMiddle, function (req, res, next) {
  res.send({
    success: true,
    result:[
      {"name":"张三","address":"北京市"},
      {"name":"李四","address":"北京市"},
      {"name":"王五","address":"北京市"}
    ]      
  });

});

router.get('/order',authMiddle, function (req, res, next) {
  res.send({
    success: true,
    result:[
      {"orderId":"111111"},
      {"orderId":"111111"},
      {"orderId":"111111"}
    ]      
  });

});

module.exports = router;


// 模块化，每个模块中都连接数据库，是否会影响性能
// 答：不会，底层做了优化
console.time('user');

var UserModel = require('./model/user.js');

console.timeEnd('user');



console.time('news');
var NewsModel = require('./model/news.js');

console.timeEnd('news');






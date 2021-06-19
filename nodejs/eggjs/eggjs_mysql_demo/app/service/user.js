

const Service = require('egg').Service;

class UserService extends Service {
  async getUserList() {
    
        //查询user表的数据

       return [
          {username:'张三'},
          {age:20}
       ]
  }
}

module.exports = UserService;

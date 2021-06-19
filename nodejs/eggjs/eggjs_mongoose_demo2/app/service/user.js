

const Service = require('egg').Service;

class UserService extends Service {
  async getUserList() {
    
        //查询user表的数据

       return await this.ctx.model.User.find({});

  }
}

module.exports = UserService;

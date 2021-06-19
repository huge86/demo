'use strict';

const Controller = require('egg').Controller;

class UserController extends Controller {
  async index() {



    var userList=await this.service.user.getUserList();
    console.log(userList);       
  

    this.ctx.body='我是用户页面';
    
  }

  async addUser() {
    //增加数据

    var user=new this.ctx.model.User({
        username:'李四',
        password:'123456'

    });

    var result=await user.save();
    console.log(result)



    this.ctx.body='增加用户成功';
    
  }

  async editUser() {
    //增加数据


    await this.ctx.model.User.updateOne({
        "_id":"5b84d4405f66f20370dd53de"
    },{
      username:"哈哈哈",
      password:'1234'
    },function(err,result){

      if(err){
        console.log(err);
        return;
      }
      console.log(result)
    })

    this.ctx.body='修改用户成功';
    
  }

  async removeUser() {
    //增加数据

    var rel =await this.ctx.model.User.deleteOne({"_id":"5b84d4b3c782f441c45d8bab"});

    cnsole.log(rel);

    this.ctx.body='删除用户成功';
    
  }
}

module.exports = UserController;

//mongoose aggregate实现关联表的查询
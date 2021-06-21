'use strict';

const Controller = require('egg').Controller;

class HomeController extends Controller {

  //查询数据
  async index() {
    const { ctx } = this;

    // const userList = await ctx.model.User.findAll();

    //查询指定字段

    
    // const userList = await ctx.model.User.findAll({attributes: ['id', 'username','age']});


    //查询条件

    // const userList = await ctx.model.User.findAll({attributes: ['id', 'username','age'],where:{id:2}});


    // const userList = await ctx.model.User.findAll({attributes: ['id', 'username','age'],where:{username:"李四"}});



    //limit   offset

    const userList = await ctx.model.User.findAll({limit: 10, offset: 0,order:[["id","desc"]]})

    ctx.body = userList;


  }
  //增加数据
  async create() {
    const { ctx } = this;
    const user = await ctx.model.User.create({ username:"王五11", age:25,sex:"女"});
    ctx.body=user;

  }
  //根据主键查找数据
  async find() {
    const { ctx } = this;
    const user = await ctx.model.User.findByPk(9);
    ctx.body=user;
  }

   //修改数据
   async update() {
    const { ctx } = this;
    const user = await ctx.model.User.findByPk(9);

    user.update({"username":"王麻子",'sex':"女"})


    ctx.body='修改成功';
  }

  //删除数据
  async destroy(){
    const { ctx } = this;
    const user = await ctx.model.User.findByPk(10);
    if(!user){
      ctx.state=404;
      return;
    }
    user.destroy();
    ctx.body='删除成功';

  }
}

module.exports = HomeController;

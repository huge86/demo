'use strict';

const Controller = require('egg').Controller;

class UserController extends Controller {
  async index() {

    // 1、获取用户表的数据 、  get一次只能查询一条数据


    // var result = await this.app.mysql.get('user', { "id": 1 });

    // console.log(result)




    //2、查询全部数据  指定条件

    // var result = await this.app.mysql.select('user', {
    //   //where: { "id": 3 },
    //   limit: 2,
    //   orders: [['id', 'desc']]
    // })
    // console.log(result)



    //3、增加数据


    // const result = await this.app.mysql.insert('user', { name: '哈哈哈', 'password': '123456' });




    //4、修改数据  根据主键修改



    // var userInfo = {
    //   id: 2,
    //   name: '小六子'
    // }

    // var result = await this.app.mysql.update('user', userInfo);





    //5、修改数据  执行sql语句来修改数据


    // var result = await this.app.mysql.query('update user set name=? where password=?', ['李四', '666666']);



    //6、删除数据

    // const result = await this.app.mysql.delete('user', {
    //   id: '3'
    // }
    // );


    //7、通过sql语句查询数据


    //var result= await this.app.mysql.query('select * from user');

    // var myid=3;
    // var result= await this.app.mysql.query('select * from user where id=?',[myid]);




    //8、mysql事务   数据库事务(Database Transaction) ，是指作为单个逻辑工作单元执行的一系列操作，要么完全地执行，要么完全地不执行

    //mongodb新版本里面也支持事务 在mongodb中使用事务必须创建MongoDB副本集（主从数据库）


    // mysql事务：要么完全地执行，要么完全地不执行

    /*
         两人转账的操作,A给B转账10元

             1、需要更新user表，让A用户的money-10                A总共有5元  （执行失败） 异常

             2、需要更新user表，让B用户的money+10                            （执行成功）


         事务：

           如果失败执行回滚操作（如果有一个失败），如果成功执行提交操作（真正的增加到数据库里面）


   */

    const conn = await this.app.mysql.beginTransaction();

    try {

      //增加数据操作   
      await conn.insert('user', { name: '事务操作', 'password': '654321' });   //增加成功


      // window.xxxx();   //不存在  报错

      //修改数据操作
      await conn.query('update user set name=? where id=?', ['李四666', '1']);

      await conn.commit();

    } catch (err) {
      // error, rollback
      await conn.rollback(); // rollback call won't throw err

    }




    this.ctx.body = "result";

  }
}

module.exports = UserController;

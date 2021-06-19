'use strict';


var ObjectID = require('mongodb').ObjectID;

const Controller = require('egg').Controller;

class NewsController extends Controller {
  async index() {
    

//1、查找


    // var result=await this.app.mongo.find('users');

    // console.log(result);




    // var result=await this.app.mongo.find('users',{query:{"name":"lisi111"}});
    // console.log(result);




    // var result=await this.app.mongo.find('users',{query:{"name":"lisi111"},limit:1});
    // console.log(result);





//2、增加


        // var result=await this.app.mongo.insertOne('users',{doc:{

        //     name:'王五',
        //     age:20,
        //     sex:1
        // }})

        // console.log(result);

//3、修改数据



    // var result=await this.app.mongo.findOneAndUpdate('users',{

        
    //     filter:{"name":"哈哈哈"},
    //     update:{

    //         $set:{

    //             name:'哈哈哈哈哈哈',
    //             age:30
    //         }
    //     }
    // })

    // console.log(result);



//4、删除数据

    // var result=await this.app.mongo.findOneAndDelete('users',{filter:{"name":"王五"}})
    // console.log(result);



//5、查找指定id的数据   删除指定_id的数据




    var result=await this.app.mongo.find('users',{query:{"_id":ObjectID('5b72abc7e4f0720a68f05b41')}});

    console.log(result)






    this.ctx.body='新闻页面'
  }
}

module.exports = NewsController;

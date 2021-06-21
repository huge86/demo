/**
 * Created by Administrator on 2017/10/23 0023.
 */


//封装操作数据库的方法


var config=require('./config.js');

var  MongoClient=require('mongodb').MongoClient;
var ObjectID = require('mongodb').ObjectID;   /*查找_id*/
var dbUrl =config.dbUrl;


//封装连接数据库的方法

function _connect(callback){

    MongoClient.connect(dbUrl,function(err,db){
        if(err){
            console.log('数据库连接失败');
            console.log(err);
            return;
        }
        callback(db);

    })
}

exports.ObjectID=ObjectID;

/*
 db.find('user',{},function(err,data){
        console.log(data)
 })

* */


/*
* json1 条件
* json2  列数
* json3 配置信息
* */
exports.find=function(collectionName,json1,json2,json3,callback){

    if(arguments.length==3){
        var cb=json2;
        var col={};  /*查询的列*/
        var skip=0;
        var limit=0;
    }else if(arguments.length==4){

        var cb=json3;
        var col=json2;  /*查询的列*/
        var skip=0;
        var limit=0;
    }else if(arguments.length==5){
        var cb=callback;
        var col=json2;  /*查询的列*/
        //json3={
        //    page，
        //    pageSize
        //}
        var limit=json3.pageSize || 10;   /*如果pageSize 每页10条*/

        var skip=json3.page? (json3.page-1)*limit : 0;  /*page每页传显示第一页*/

    }else{
        console.log('传入参数错误');
        return;
    }

    _connect(function(db){

       var result=db.collection(collectionName).find(json1,col).skip(skip).limit(limit);

        result.toArray(function(err,data){
            db.close();
            cb(err,data);

        })
    })

}

/*
 db.insert("user",{"name":"zhangsan"},function(){

 })

增加数据
* */

exports.insert=function(collectionName,json,callback){

    _connect(function(db){
        db.collection(collectionName).insertOne(json,function(err,result){
            db.close();
            callback(err,result);
        })
    })

}



/*

db.update('user',{"id":123},{'name':"zhangsan"},function(){

})
更新数据
*
* */
exports.update=function(collectionName,json1,json2,callback){

    _connect(function(db){
        db.collection(collectionName).updateOne(json1,{
            $set:json2
        },function(err,result){
            db.close();
            callback(err,result);
        })
    })


}



//封装删除的方法

exports.remove=function(collectionName,json,callback){

    _connect(function(db){
        db.collection(collectionName).removeOne(json,function(err,result){
            db.close();
            callback(err,result);
        })
    })


}

exports.count=function(collectionName,json,callback){

    _connect(function(db){
        db.collection(collectionName).count(json,function(err,data){

            callback(err,data)
        })
    })


}
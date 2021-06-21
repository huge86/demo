/**
 * Created by Administrator on 2017/10/27 0027.
 */


var express=require('express');

var app=express();

var DB=require('./module/db.js');

/*第一步*/
var server = require('http').Server(app);
var io = require('socket.io')(server);


app.set('view engine','ejs');
app.use(express.static('public'));

app.get('/',function(req,res){
    //res.send('首页');
    res.render('index');
})


app.get('/news',function(req,res){
    res.send('news');

})

//2.监听端口
server.listen(8000,'127.0.0.1');   /*改ip*/


//3、写socket的代码

io.on('connection', function (socket) {
    console.log('建立链接')

    socket.on('message',function(data){

        console.log(data)
        //socket.emit('servermessage',msg);

        var msg=data.msg||'';  /*获取客户端的数据*/

        //去服务器查询数据

        DB.find('article',{'title':{$regex:new RegExp(msg)}},{'title':1},function(err,data){

            console.log(data);


            socket.emit('servermessage',{
                result:data
            });
        })


    })
});

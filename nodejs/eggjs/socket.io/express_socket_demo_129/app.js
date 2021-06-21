/**
 * Created by Administrator on 2017/10/27 0027.
 */


var express=require('express');

var app=express();

var DB=require('./module/db.js');


app.set('view engine','ejs');
app.use(express.static('public'));


//express里面使用socket.io
var server = require('http').Server(app);
var io = require('socket.io')(server);
server.listen(8000);


app.get('/',function(req,res){

    //res.send('首页');

    res.render('index');

})

app.get('/chat',function(req,res){

    //res.send('首页');

    var name=req.query.name;
    res.render('chat',{
        name:name
    });

})

//写socket.io 服务端
io.on('connection',function(socket){
    console.log('有个客户端连接了');
    socket.on('message',function(data){
        io.emit('message',data);  /*全部广播*/
    })
})


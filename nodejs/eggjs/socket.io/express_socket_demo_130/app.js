

var express=require('express');

var url=require('url');

var app=express();

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

server.listen(8000,'127.0.0.1');   /*改ip*/



//写socket.io的服务

io.on('connection', function (socket) {

    console.log('socket配置成功')

    //io.emit  广播
    //socket.emit  谁给我发的信息我回返回给谁

    //获取客户端建立连接的时候传入的值
    //console.log(socket.request.url);
   var roomid=url.parse(socket.request.url,true).query.roomid;   /*获取房间号/ 获取桌号*/

   //console.log(roomid);

    socket.join(roomid);  /*加入房间/加入分组*/

    socket.on('addCart',function(data){

        console.log(data)

        //广播给指定桌号的用户        对房间（分组）内的用户发送消息
        io.to(roomid).emit('addCart','Server AddCart Ok'); //通知分组内的所有用户

        // socket.broadcast.to(roomid).emit('addCart','Server AddCart Ok'); //通知分组内的用户不包括自己

    })


});




/*1.安装
 npm install socket.io


 2、复制下面代码

 var server = require('http').Server(app);
 var io = require('socket.io')(server);

 3、app.listen改成

    server.listen(8000,'192.168.0.3');


4、配置socket

 io.on('connection', function (socket) {

     console.log('socket配置成功')
 });


 5、在客户端引入socket.io  建立连接

 var socket = io.connect('http://192.168.0.3:8000');



6、

io.emit  广播
socket.emit  谁给我发的信息我回返回给谁

socket.join(roomid);   加入分组

io.to(roomid).emit('addCart','Server AddCart Ok')  通知分组内的所有用户

socket.broadcast.to(roomid).emit('addCart','Server AddCart Ok');  通知分组内的用户不包括自己


 */
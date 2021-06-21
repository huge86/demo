/**
 * Created by Administrator on 2017/10/27 0027.
 */


var express=require('express');

var app=express();

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

        console.log(data);

        //io.emit  广播
        //socket.emit  谁给我发的信息我回返回给谁


        //io.emit('servermessage',data);   /*服务器给客户端发送数据*/


        if(data==1){

            var msg='您当前的话费有2元'
        }else if(data==2){
            var msg='您当前的流量有200M'

        }else{
            var msg='我不知道你在说什么，返回给你输入的内容：' + data
        }

        socket.emit('servermessage',msg);

    })
});

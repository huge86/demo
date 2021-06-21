
var http=require('http');

var fs=require('fs');  /*fs内置的模块*/

var app=http.createServer(function(req,res){
    //加载静态页面
    fs.readFile('app.html',function(err,data){

        res.writeHead(200,{"Content-Type":"text/html;charset='utf-8'"});
        res.end(data);
    })
})

//引入socket.io
var io = require('socket.io')(app);

io.on('connection', function (socket) {

    console.log('服务器建立连接了');

    //服务器获取客户端广播的数据

    socket.on('addcart',function(data){

        console.log(data);

        //服务器给客户端发送数据

        socket.emit();   /*谁给我发信息我把信息广播给谁*/

        //io.emit() ;   /*群发  给所有连接服务器的客户都广播数据*/


        //socket.emit('to-client','我是服务器的数据'+data.client);

        io.emit('to-client','我是服务器的数据'+data.client)

    })

});




app.listen(3000);


/*使用socket.io
1.安装

 npm install socket.io

 2、引入建立连接

 var io = require('socket.io')(app);


 io.on('connection', function (socket) {

    console.log('服务器建立连接了');
 });

3、在客户端 html里面引入js

 http://localhost:3000/socket.io/socket.io.js





* */
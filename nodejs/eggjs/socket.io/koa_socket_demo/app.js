
var Koa=require('koa'),
    router = require('koa-router')(),
    views = require('koa-views');

var url=require('url');

const IO = require( 'koa-socket' )
const io = new IO();
var app=new Koa();

io.attach( app );


app.use(views('views',{
    extension:'ejs'  /*应用ejs模板引擎*/
}))

router.get('/',async (ctx)=>{
   let title="你好ejs";
   await ctx.render('index',{
        title:title
    });
})

app.use(router.routes());   /*启动路由*/
app.use(router.allowedMethods());


app._io.on( 'connection', socket => {

    console.log('建立连接了');

    var roomid=url.parse(socket.request.url,true).query.roomid;   /*获取房间号/ 获取桌号*/

    //console.log(roomid);

    socket.join(roomid);  /*加入房间/加入分组*/


    socket.on('addCart',function(data){

        console.log(data);
        //socket.emit('serverEmit','我接收到增加购物车的事件了');  /*发给指定用户*/

        //app._io.emit('serverEmit','我接收到增加购物车的事件了');  /*广播*/

        //app._io.to(roomid).emit('serverEmit','我接收到增加购物车的事件了');

        socket.broadcast.to(roomid).emit('serverEmit','我接收到增加购物车的事件了');



    })
})


app.listen(3000);




/*使用步骤
    1、安装

    cnpm i -S koa-socket

    2、引入

    const IO = require( 'koa-socket' )

    3、实例化const io = new IO()


    4、
    io.attach( app )


    5、配置服务端

    app._io.on( 'connection', socket => {

     console.log('建立连接了');
     })


    6、

         <script src="http://localhost:3000/socket.io/socket.io.js"></script>

         <script>

         var socket=io.connect('http://localhost:3000/')

         </script>
 * */



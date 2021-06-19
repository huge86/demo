
/*
options: 中间件的配置项，框架会将 app.config[${middlewareName}] 传递进来。
app: 当前应用 Application 的实例。


配置中间件

*/

module.exports = (options, app) => {
   
    console.log(options); 
    //返回一个异步的方法
    return async function printDate(ctx,next){


        console.log(new Date());
        console.log(options); 

            await next();
    }

};
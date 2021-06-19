module.exports=(option,app)=>{


    return async function auth(ctx,next){


        console.log(option);

        //实现中间件的功能

        console.log(new Date());


        await next();

    }

}
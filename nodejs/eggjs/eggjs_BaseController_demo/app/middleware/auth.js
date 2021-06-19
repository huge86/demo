module.exports=(option,app)=>{

    return async function auth(ctx,next){

        //设置模板全局变量
        ctx.state.csrf=ctx.csrf;

        await next();
    }
}
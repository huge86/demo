module.exports=(option,app)=>{


    return async function auth(ctx,next){

        //如果session存在表示已经登录 继续访问，如果session不存在表示没有登录 跳转到首页
      
        if(ctx.session && ctx.session.userinfo){
             await next();               
        }else{
            ctx.redirect('/') ;           
        }


    }

}
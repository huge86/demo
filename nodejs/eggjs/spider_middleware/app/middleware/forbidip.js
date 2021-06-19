
module.exports = (options, app) => {
   
    //返回一个异步的方法
    return async function forbidIp(ctx,next){

            //要屏蔽的id ： 1.从数据库获取     2、从参数传入
          
            var forbidips=options.forbidips;  

        //    console.log(forbidips);


            //获取客户端的ip

            var clientIp=ctx.request.ip;            
            //some和forEach相似
            var hasIp=forbidips.some(function(val){
                
                if(val==clientIp){

                    return true;
                }

            })

            if(hasIp){
                //屏蔽
                   ctx.status = 403;                
                   ctx.body='您的ip已经被屏蔽';

            }else{
                await next();

            }
  

           
    }

};

































// module.exports = (options, app) => {
   
//     //返回一个异步的方法
//     return async function forbidIp(ctx,next){

//             //要屏蔽的id ： 1.从数据库获取     2、从参数传入



//             /*要屏蔽的ip*/
//             var forbidip='127.0.0.1';

//             //获取客户端的ip

//             // console.log(ctx.request.ip)

//             if(ctx.request.ip==forbidip){

//                 ctx.status = 403;                
//                 ctx.body='您的ip已经被屏蔽';
               
//             }else{
//                 await next();
//             }
           
//     }

// };
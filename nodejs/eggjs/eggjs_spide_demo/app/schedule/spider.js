
// var k=110;
// module.exports={

//     schedule: {
//         interval: '5s', // 1 分钟间隔
//         type: 'all', // 指定所有的 worker 都需要执行
//     },

//     async task(ctx) {
//         ++k;
//         console.log(k)
//     }
// }






var k=110;
module.exports=(app)=>{
    return{

        schedule: {
            interval: '5s', // 1 分钟间隔
            type: 'all', // 指定所有的 worker 都需要执行,
            // disable:true
        },
    
        async task(ctx) {
            ++k;

            // var result=await ctx.service.news.getNewsList()
            // console.log(result)

            console.log(k)
        }
    }
}
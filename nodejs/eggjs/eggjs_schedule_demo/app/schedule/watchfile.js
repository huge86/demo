const Subscription = require('egg').Subscription;

var i=0;

class WatchFile extends Subscription{

  // 通过 schedule 属性来设置定时任务的执行间隔等配置

    static get schedule(){

        return{

            interval:'2s',
            type:'all'   //指定所有的 worker（进程）  都需要执行
        }
    }

    async subscribe() {
      //定时任务执行的操作
      ++i;
      console.log(i);

    //   var result=await this.ctx.service.news.getNewsList()
    //   console.log(result)
      
    }


}

//注意
module.exports = WatchFile;
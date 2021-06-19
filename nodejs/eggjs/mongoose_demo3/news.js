
var NewsModel=require('./model/news.js');



var news =new NewsModel({    
    title:"    我是一个国际新闻3333333   ",
    author:'张三333',
    pic:'http://www.xxx.com/x.png'             
  
})
news.content='content'

news.save(function(err){

    if(err){

        console.log(err);
        return;
    }
    NewsModel.find({},function(err,docs){

        if(err){

            console.log(err);
            return;
        }

        console.log(docs);
    })

})




















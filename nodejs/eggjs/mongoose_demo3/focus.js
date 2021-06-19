
var FocusModel=require('./model/focus.js');



var focus =new FocusModel({    
    title:"    我是一个国际新闻666666   ",    
    pic:'http://www.xxx.com/x.png',    
    redirect:'www.bbbbb.com'
  
})

focus.save(function(err){

    if(err){

        console.log(err);
        return;
    }
    FocusModel.find({},function(err,docs){

        if(err){

            console.log(err);
            return;
        }

        console.log(docs);
    })

});


















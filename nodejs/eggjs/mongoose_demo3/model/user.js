var mongoose=require('./db.js');


var UserSchema=mongoose.Schema({
    
    name:{

        type:String,
        get(params){   //不建议使用

            return "a001"+params
        }   
    },
    age:Number,       
    status:{
        type:Number,
        default:1

    }
})


module.exports=mongoose.model('User',UserSchema,'user');

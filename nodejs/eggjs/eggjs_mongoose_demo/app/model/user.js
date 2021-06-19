module.exports = app => {

    const mongoose = app.mongoose;   /*引入建立连接的mongoose */
    const Schema = mongoose.Schema;
   

    //数据库表的映射
    const UserSchema = new Schema({
      username: { type: String  },
      password: { type: String  },
      status:{
        type:Number,
        default:1
      }

    });
   
    return mongoose.model('User', UserSchema,'user');
}
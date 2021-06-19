module.exports = app => {

    const mongoose = app.mongoose;   /*引入建立连接的mongoose */
    const Schema = mongoose.Schema;

    var OrderSchema=Schema({

        order_id:String,
        uid:Number,
        trade_no:String,
        all_price:Number,
        all_num:Number    
    })    
    
   return mongoose.model('Order',OrderSchema,'order');  
    
   
}
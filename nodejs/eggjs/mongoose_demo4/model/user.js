var mongoose = require('./db.js');


var UserSchema = mongoose.Schema({

    name: {
        type: String
    },
    sn: {
        type: String,
        index: true
    },
    age: Number,
    status: {
        type: Number,
        default: 1

    }
})
// Mongoose内置方法、扩展Mongoose Model的静态方法和实例方法
// 例如：mongoose有findById,但么有findBySn，那么我们可以自己扩展一些实用的方法
// 扩展mongoose method的内置方法，两种方式
//静态方法 

UserSchema.statics.findBySn = function (sn, cb) {


    //通过 find方法获取 sn的数据    this 关键字获取当前的model


    this.find({ "sn": sn }, function (err, docs) {
        cb(err, docs)
    })


}



// 实例方法   (基本不用)

UserSchema.methods.print = function () {


    console.log('我是一个实例方法')

    console.log(this.name)
}






module.exports = mongoose.model('User', UserSchema, 'user');



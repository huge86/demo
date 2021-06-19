var mongoose = require('./db.js');


var FocusSchema = mongoose.Schema({
    title: {
        type: String,
        trim: true    //定义 mongoose模式修饰符 去掉空格
    },
    pic: String,
    redirect: {
        type: String,
        // set(parmas) {   //增加数据的时候对redirect字段进行处理
        //     // parmas可以获取redirect的值 、    返回的数据就是redirect在数据库实际保存的值
        //     /*
        //      www.baidu.com              http://www.baidu.com
        //      http://www.baidu.com       http://www.baidu.com
        //     */
        //     if (!parmas) {
        //         return ''
        //     } else {
        //         if (parmas.indexOf('http://') != 0 && parmas.indexOf('https://') != 0) {

        //             return 'http://' + parmas;
        //         }
        //         return parmas

        //     }

        // }
    },
    status: {
        type: Number,
        default: 1

    }
})

module.exports = mongoose.model('Focus', FocusSchema, 'focus');

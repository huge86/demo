'use strict';

const Controller = require('egg').Controller;

class ArticleController extends Controller {

  //查询数据 1对1 1对多
  async index() {
    const { ctx } = this;

    // let result = await ctx.model.Article.findAll({
    //     include: {
    //       model: ctx.model.ArticleCate
    //     }
    // });


    let result = await ctx.model.ArticleCate.findAll({
        include: {
          model: ctx.model.Article
        }
    });

    ctx.body = result;
  }
  

   //查询数据  多对多
   async showAll() {
    const { ctx } = this;

    //课程有哪些学生选修
    // let result = await ctx.model.Lesson.findAll({
    //   include: {
    //     model:  ctx.model.Student
    //   }
    // });   
    

    //每个学生选修了哪些课程
    let result = await ctx.model.Student.findAll({
      include: {
        model:  ctx.model.Lesson
      }
    });   
    ctx.body = result;
  }
  
}

module.exports = ArticleController;

'use strict';

module.exports = app => {
  const { STRING, INTEGER, DATE } = app.Sequelize;

  const ArticleCate = app.model.define('article_cate', {
    id: { type: INTEGER, primaryKey: true, autoIncrement: true },
    title: STRING(255),
    state: INTEGER  
  },{
    timestamps: false,  //关闭时间戳
    tableName: 'article_cate'    //配置表名称 
  });

  ArticleCate.associate = function (){
    // 1对1
    // app.model.ArticleCate.hasOne(app.model.Article, {foreignKey: 'cateId'});

    //1对多
    app.model.ArticleCate.hasMany(app.model.Article, {foreignKey: 'cateId'});
       
  }
 

  return ArticleCate;
};
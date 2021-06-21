'use strict';

module.exports = app => {
  const { STRING, INTEGER, DATE } = app.Sequelize;

  const Article = app.model.define('article', {
    id: { type: INTEGER, primaryKey: true, autoIncrement: true },
    title: STRING(255),
    description: INTEGER,
    cateId:STRING(30),
    state: DATE   
  },{
    timestamps: false,
    tableName: 'article'    
  });
 
  
  Article.associate = function (){
    // 1对1
    app.model.Article.belongsTo(app.model.ArticleCate, {foreignKey: 'cateId'});
       
  }
 

  return Article;
};
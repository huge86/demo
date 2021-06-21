'use strict';

module.exports = app => {

  const { STRING, INTEGER, DATE } = app.Sequelize;
  const User = app.model.define('user',{
    id: { type: INTEGER, primaryKey: true, autoIncrement: true },
    username: STRING(255),
    age: INTEGER,
    sex:STRING(30),
    created_at: DATE,
    updated_at: DATE,
  },{    
    timestamps: false,  //表示：不自动增加创建时间
    tableName: 'user'   //表示：指定表名称
  });

  return User;
};
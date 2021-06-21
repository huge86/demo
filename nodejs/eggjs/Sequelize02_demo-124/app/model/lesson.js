
module.exports = app => {
    const { INTEGER, STRING } = app.Sequelize;
 
    const Lesson = app.model.define('lesson', {
        id: {
            type: INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        name: {
            type: STRING,
            allowNull: false
        }
    },{
        timestamps: false,
        tableName: 'lesson'    
      });


      Lesson.associate = function () {

        //一个学生可以选修多门课程 
       //一门课程可以被多个学生选修
            app.model.Lesson.belongsToMany(app.model.Student, {
                through: app.model.LessonStudent, 
                foreignKey: 'lessonId',//注意写法 
                otherKey: 'studentId' 
            });
     }

 
  
 
    return Lesson;
}

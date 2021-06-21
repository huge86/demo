
module.exports = app => {
    const { INTEGER } = app.Sequelize;
 
    const LessonStudent = app.model.define('lesson_student', {
        lessonId: {
            type: INTEGER,
            primaryKey: true
        },
        studentId: {
            type: INTEGER,
            primaryKey: true
        }
    },{
        timestamps: false,
        tableName: 'lesson_student'    
      });
 
    //   LessonStudent.associate = function(){
 
    // }
 
    return LessonStudent;
}

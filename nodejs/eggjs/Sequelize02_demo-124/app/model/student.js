
module.exports = app => {
    const { STRING, INTEGER } = app.Sequelize;

    const Student = app.model.define('student', {
        id: {
            type: INTEGER,
            autoIncrement: true,
            primaryKey: true
        },
        name: {
            type: STRING,
        },
        number: {
            type: STRING,
            allowNull: false,
        },
        password: {
            type: STRING(32),
            allowNull: false
        }
    }, {
        timestamps: false,
        tableName: 'student'
    });

    Student.associate = function () {

        //一个学生可以选修多门课程 
        app.model.Student.belongsToMany(app.model.Lesson, {
            through: app.model.LessonStudent, 
            foreignKey: 'studentId',//注意写法 
            otherKey: 'lessonId' 
        });
    }

    return Student;
}

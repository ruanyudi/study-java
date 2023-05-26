package cn.ruanyudi.homework.chapter;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class Chapter5 {
    public static void showMenu(){
        System.out.println("nWelcome to the assignment in Chapter 5, please select the job number to view:");
        while (true) {
            System.out.println("1 - JDBC-test");
            System.out.println("Enter your choice (0-exit)");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    try {
                        new Welcome().show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
//            System.out.println("输入0以继续");
//            sc.nextInt();
        }
    }
}


class Welcome {

    public void show() throws Exception {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("1.学生信息管理");
            System.out.println("2.课程信息管理");
            System.out.println("3.学生成绩管理");
            System.out.println("0.退出");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    new StudentOperation().main();
                    break;
                case 2:
                    new CourseOperation().main();
                    break;
                case 3:
                    new GradeOperation().gradeMenu();
                    break;
                case 0:
                    return;
            }
        }
    }
}


class DataBase {
    public static Connection getConn() {
        String url = "jdbc:mysql://192.168.40.128:3306/student?useUnicode=True&characterEncoding=utf8";
        String userName = "root";
        String password = "a86159263";
        try {
            try {
                Class.forName("org.gjt.mm.mysql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Connection conn = (Connection) DriverManager.getConnection(url, userName, password);
//            if(conn !=null){
//                System.out.println("Got Connection!");
//            }
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeSql(Connection c,String sql) {

        try {

            PreparedStatement p=c.prepareStatement(sql);

            p.executeUpdate();

            p.close();

            c.close();

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

            System.out.println("数据库操作异常！");

        }

    }
}

class Student {

    static int count = 0;

    private String name;

    private int no;

    private String major;

    Scanner reader = new Scanner(System.in);

    public Student(int no, String name, String major) {

        this.no = no;

        this.name = name;

        this.major = major;

    }

    public Student() throws Exception {

        // TODO Auto-generated constructor stub

        System.out.println("输入学生姓名：");

        String name = reader.next();

        System.out.println("输入学生专业：");

        String major = reader.next();

        count = count + 1;

        this.no = count;

        this.major = major;

        this.name = name;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public int getNo() {

        return no;

    }


    public void setNo(int no) {

        this.no = no;

    }


    public String getMajor() {

        return major;

    }


    public void setMajor(String major) {

        this.major = major;

    }


    public String toString() {

        return this.no + "\t\t" + this.name + "\t\t" + this.major;


    }

}
class Course {

    static int count = 0;

    private int no;

    private String name;

    Scanner reader = new Scanner(System.in);


    // 课程学分

    private double score;


    public Course() throws Exception {

        System.out.println("输入课程名称：");

        String name = reader.next();

        System.out.println("输入课程学分：");

        double score = reader.nextDouble();

        count = count + 1;

        this.name = name;

        this.no = count;

        this.score = score;


    }

    public Course(int no, String name, double score) {

        this.no = no;

        this.name = name;

        this.score = score;

    }


    public int getNo() {

        return no;

    }


    public void setNo(int no) {

        this.no = no;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public double getScore() {

        return score;

    }


    public void setScore(double score) {

        this.score = score;

    }


    public String toString() {

        return this.no + "\t\t" + this.name + "\t\t" + this.score;

    }

}

class Grade {

    private Student student;

    private Course course;
    private int no;
    private double grade;

    public Student getStudent() {

        return student;

    }

    public void setStudent(Student student) {

        this.student = student;

    }

    public Course getCourse() {

        return course;

    }

    public void setCourse(Course course) {

        this.course = course;

    }

    public double getGrade() {

        return grade;

    }

    public void setGrade(double grade) {

        this.grade = grade;

    }

    public void setId(int no) {
        this.no = no;
    }
    public int getId(){
        return this.no;
    }
}


class StudentBase {

    //数据库学生表插入学生对象

    public static void insert(Student s) {

        Connection conn=DataBase.getConn();

        String sql="insert into student (name,major) "

                + "values ('"+s.getName()+"','"+s.getMajor()+"')";

        DataBase.executeSql(conn, sql);

    }

    //数据库学生表删除学生对象

    public static void delete(int no) {

        Connection conn=DataBase.getConn();

        String sql="delete from student where no=?";

        PreparedStatement p;

        try {

            p = conn.prepareStatement(sql);

            p.setInt(1, no);

            p.executeUpdate();

            p.close();

            conn.close();

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }

    //数据库学生表中获得一个学生对象

    public static Student getOneByNo(int no) {

        Connection conn=DataBase.getConn();

        String sql="select * from student where no=?";

        PreparedStatement p;

        try {

            p = conn.prepareStatement(sql);

            p.setInt(1, no);

            ResultSet result=p.executeQuery();

            while(result.next()) {

                int stuNo=result.getInt("no");

                String name=result.getString("name");

                String major=result.getString("major");

                Student s=new Student(stuNo,name,major);

                p.close();

                conn.close();

                return s;

            }



        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return null;

    }

    //数据库学生表更新一个学生对象

    public static void update(Student s) {

        Connection conn=DataBase.getConn();

        String sql="update student set name=?,major=? where no=?";

        PreparedStatement p;

        try {

            p = conn.prepareStatement(sql);

            p.setString(1, s.getName());

            p.setString(2, s.getMajor());

            p.setInt(3, s.getNo());

            p.executeUpdate();

            p.close();

            conn.close();

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }

    //数据库学生表中查询学生对象，采用模糊查询

    public static List<Student> findStudent(String key){

        List<Student> students=new ArrayList<Student>();

        Connection conn=DataBase.getConn();

        String sql="select * from student where name like ? or major like ? or no like ?";

        PreparedStatement p;

        try {

            p = conn.prepareStatement(sql);

            p.setString(1, "%"+key+"%");

            p.setString(2, "%"+key+"%");

            p.setString(3, "%"+key+"%");

            ResultSet row=p.executeQuery();

            while(row.next()) {

                int stuNo=row.getInt("no");

                String name=row.getString("name");

                String major=row.getString("major");

                Student s=new Student(stuNo,name,major);

                students.add(s);

            }



        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return students;

    }

}
class CourseBase {

//课程表插入一个课程对象

    public static void insert(Course c) {

        Connection conn = DataBase.getConn();

        String sql = "insert into course (name,score) values (?,?)";

        try {

            PreparedStatement p = conn.prepareStatement(sql);

            p.setString(1, c.getName());

            p.setDouble(2, c.getScore());

            p.executeUpdate();

            p.close();

            conn.close();

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

            System.out.println("数据插入失败！！");

        }

    }

//课程表删除一个课程对象


    public static void delete(int no) {

        Connection conn=DataBase.getConn();

        String sql="delete from course where no=?";

        PreparedStatement p;

        try {

            p = conn.prepareStatement(sql);

            p.setInt(1, no);

            p.executeUpdate();

            p.close();

            conn.close();

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }

    //课程表获得一个课程对象

    public static Course getOneByNo(int no) {

        Connection conn=DataBase.getConn();

        String sql="select * from course where no=?";

        PreparedStatement p;

        try {

            p = conn.prepareStatement(sql);

            p.setInt(1, no);

            ResultSet result=p.executeQuery();

            while(result.next()) {

                int courseNo=result.getInt("no");

                String name=result.getString("name");

                double score=result.getDouble("score");

                Course c=new Course(courseNo,name,score);

                p.close();

                conn.close();

                return c;

            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return null;

    }

//课程表更新一个课程对象

    public static void update(Course s) {

        Connection conn=DataBase.getConn();

        String sql="update course set name=?,score=? where no=?";

        PreparedStatement p;

        try {

            p = conn.prepareStatement(sql);

            p.setString(1, s.getName());

            p.setDouble(2, s.getScore());

            p.setInt(3, s.getNo());

            p.executeUpdate();

            p.close();

            conn.close();

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }

    //课程表查询课程

    public static List<Course> findCourse(String key){

        List<Course> courses=new ArrayList<Course>();

        Connection conn=DataBase.getConn();

        String sql="select * from course where name like ? or no like ?";

        PreparedStatement p;

        try {

            p = conn.prepareStatement(sql);

            p.setString(1, "%"+key+"%");

            p.setString(2, "%"+key+"%");

            ResultSet result=p.executeQuery();

            courses=coursesResults(result);

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return courses;

    }



    public static List<Course> coursesResults(ResultSet result){

        List<Course> courses=new ArrayList<Course>();

        try {

            while(result.next()) {

                int no=result.getInt("no");

                String name=result.getString("name");

                double score=result.getDouble("score");

                Course c=new Course(no,name,score);

                courses.add(c);

            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return courses;

    }



}
class GradeBase{

    /**

     * f：true 已选课程 ;f=false：未选课程

     * @param s

     * @param f

     * @return

     */

    public static List<Course> findCoursesSelectBystudent(Student s, boolean f) {

        List<Course> selectCourses = new ArrayList<>();

        Connection conn = DataBase.getConn();

        int stuNo = s.getNo();

        String sql = "";

        if (f) {

            sql = "select * from course where no in (select g.courseNo from grade g where stuNo=?)";

        } else {

            sql = "select * from course where no not in (select g.courseNo from grade g where stuNo=?)";

        }

        try {

            PreparedStatement p = conn.prepareStatement(sql);

            p.setInt(1, stuNo);

            ResultSet result = p.executeQuery();

            selectCourses = CourseBase.coursesResults(result);

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return selectCourses;

    }



    public static Grade getOneByStuAndCourse(Student s, Course c) {

        String sql = " and (g.stuNo="+s.getNo()+" and g.courseNo="+c.getNo() +")";

        List<Grade> grades=new ArrayList<Grade>();

        grades=search(sql);

        if(grades.isEmpty()) {

            return null;

        }

        return grades.get(0);

    }



    public static void insert(Grade g) {

        Connection conn = DataBase.getConn();

        String sql = "insert into grade (stuNo,courseNo) values (?,?)";

        try {

            PreparedStatement p = conn.prepareStatement(sql);

            p.setInt(1, g.getStudent().getNo());

            p.setInt(2, g.getCourse().getNo());

            p.executeUpdate();

            p.close();

            conn.close();

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

            System.out.println("数据插入失败！！");

        }

    }



    /*

     * 成绩信息修改

     */

    public static int updateGrade(double grade, int no) {

        int i = 0;

        Connection conn = DataBase.getConn();

        String sql = "update grade set grade=? where no=?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setDouble(1, grade);

            pst.setInt(2, no);

            i = pst.executeUpdate();



            pst.close();

            conn.close();

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return i;

    }





    ///// 返回grade对象的集合的基本查询,在输入不同的sql条件实现组合查询

    public static List<Grade> search(String sql1) {

        List<Grade> results = new ArrayList<Grade>();

        Connection conn = DataBase.getConn();

        String sql = "select g.no, s.no as stuNo, s.name as stuName, s.major, "

                + "c.no as courseNo,c.name as courseName,c.score, "

                + "g.grade "

                + "from student s, course c, grade g "

                + "where s.no=g.stuNo and c.no=g.courseNo ";

        sql = sql + sql1;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {

                int no = resultSet.getInt("no");

                int stuNo = resultSet.getInt("stuNo");

                String stuName = resultSet.getString("stuName");

                String major = resultSet.getString("major");

                int courseNo = resultSet.getInt("courseNo");

                String courseName = resultSet.getString("courseName");

                int mark = resultSet.getInt(7);

                double score = resultSet.getDouble(8);

                Student student = new Student(stuNo, stuName, major);

                Course course = new Course(courseNo, courseName, mark);

                Grade grade = new Grade();

                grade.setCourse(course);

                grade.setId(no);

                grade.setStudent(student);

                grade.setGrade(score);

                results.add(grade);

            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return results;

    }



    public static List<Grade> findGradesByCourse(Course c){

        List<Grade> grades=new ArrayList<Grade>();

        String sql= "and c.no="+c.getNo();

        grades=search(sql);

        return grades;

    }



    public static List<Grade> findGradesByStudent(Student s){

        List<Grade> grades=new ArrayList<Grade>();

        String sql= "and s.no="+s.getNo();

        grades=search(sql);

        return grades;

    }

}



class GradeOperation {

    static Scanner reader = new Scanner(System.in);

    /**

     * 学生选课方法

     */

    public static void studentSelectCourse() throws Exception{

        System.out.println("输入学号：");

        int stuNo = reader.nextInt();

        Student s = StudentBase.getOneByNo(stuNo);

        if (s != null) {

            while (true) {

                System.out.println(s);

                System.out.println("--------已选课程------------");

                List<Course> selectCourses = GradeBase.findCoursesSelectBystudent(s, true);

                CourseOperation.printCourses(selectCourses);

                System.out.println("-----------------------------");

                System.out.println("--------未选课程------------");

                List<Course> noSelectCourses = GradeBase.findCoursesSelectBystudent(s, false);

                CourseOperation.printCourses(noSelectCourses);

                System.out.println("-----------------------------");

                System.out.println("输入课程编号：-1——退出");

                int courseNo = reader.nextInt();

                if (courseNo == -1) {

                    break;

                }

                Course c = CourseBase.getOneByNo(courseNo);

                if (c != null) {

                    Grade g = GradeBase.getOneByStuAndCourse(s, c);

                    if (g != null) {

                        System.out.println("该课程已经被选择");

                    } else {

                        Grade grade = new Grade();

                        grade.setStudent(s);

                        grade.setCourse(c);

                        GradeBase.insert(grade);



                    }

                } else {

                    System.out.println("没有这门课！");

                }

            }

        } else {

            System.out.println("没有这个学生！");

        }

    }



    /**

     * 成绩录入

     */

    public static void inputGrade() throws Exception {

        //打印所有课程

        CourseOperation.printCourses(CourseBase.findCourse(""));

        System.out.println("输入课程编号");

        int courseNo = reader.nextInt();

        Course c = CourseBase.getOneByNo(courseNo);

        if (c != null) {

            // 显示课程的所有成绩记录

            while (true) {

                List<Grade> grades = GradeBase.findGradesByCourse(c);

                printCourseGrade(c, grades);

                System.out.println("请输入学生编号:，-1退出");

                int stuNo = reader.nextInt();

                if (stuNo==-1) {

                    break;

                } else {

                    Student stu = StudentBase.getOneByNo(stuNo);

                    if (stu != null) {

                        Grade g = GradeBase.getOneByStuAndCourse(stu, c);

                        if (g != null) {

                            System.out.println("输入" + stu.getName() + "的成绩：");

                            double m = reader.nextDouble();

                            GradeBase.updateGrade(m, g.getId());

                        } else {

                            System.out.println(stu.getName() + "没有选这门课！");

                        }

                    } else {

                        System.out.println("没有这个学生！");

                    }

                }

            }



        } else {

            System.out.println("没有这门课程");

        }



    }



    /**

     * 打印课程成绩表

     * @param c

     * @param results

     */

    public static void printCourseGrade(Course c, List<Grade> results) {

        System.out.println("课程：" + c.getName());

        System.out.println("学号\t\t学生名字\t\t成绩");

        System.out.println("----------------------------------------");

        for (Grade g : results) {

            System.out.println(g.getStudent().getNo() + "\t\t" + g.getStudent().getName() + "\t\t" + g.getGrade());

        }

        System.out.println("----------------------------------------");

    }



    /**

     * 打印学生成绩表

     * @param s

     * @param results

     */

    public static void printStudentGrade(Student s, List<Grade> results) {

        System.out.println("学生：" + s.getName());

        System.out.println("课程编号\t\t课程名字\t\t成绩");

        System.out.println("----------------------------------------");

        double sum=0;

        for (Grade g : results) {

            System.out.println(g.getCourse().getNo() + "\t\t" + g.getCourse().getName() + "\t\t" + g.getGrade());

            if(g.getGrade()>=60) {

                sum=sum+g.getCourse().getScore();

            }

        }

        System.out.println("已修学分："+sum);

        System.out.println("----------------------------------------");



    }





    public static void queryGradeByStudent() throws Exception{

        System.out.println("请输入学号：");

        int stuNo=reader.nextInt();

        Student s=StudentBase.getOneByNo(stuNo);

        if(s!=null) {

            List<Grade> grades=GradeBase.findGradesByStudent(s);

            printStudentGrade(s,grades);

        }else {

            System.out.println("没有这个学生！！");

        }

    }





    public static void gradeMenu() {

        while (true) {

            try {

                System.out.println("成绩管理");

                System.out.println("-------------------------");

                System.out.println("1————学生选课");

                System.out.println("2————老师录入成绩");

                System.out.println("3————成绩查询");

                System.out.println("0————退出");

                int s = reader.nextInt();

                if (s == 1) {

                    studentSelectCourse();

                } else if (s == 0) {

                    break;

                }else if(s==2) {

                    inputGrade();

                }else if(s==3) {

                    queryGradeByStudent();

                }

            }catch (Exception e) {

                // TODO: handle exception

                System.out.println("输入有误，请重新输入！！");

            }



        }



    }

}

class CourseOperation {

    static Scanner reader = new Scanner(System.in);

//课程操作交互方法

    public static void main() throws Exception {

        while (true) {

            System.out.println("课程信息管理");

            System.out.println("--------------------------------");

            System.out.println("课程信息添加————1");

            System.out.println("课程信息修改————2");

            System.out.println("课程信息删除————3");

            System.out.println("课程信息查询————4");

            System.out.println("退出————0");

            int s = reader.nextInt();

            if (s == 1) {

                addCourse();

            } else if (s == 3) {

                delete();

            } else if (s == 2) {

                edit();

            } else if (s == 4) {

                search();

            } else if (s == 0) {

                break;

            }

        }

    }


    public static void addCourse() throws Exception {

        System.out.println("请输入课程名称, 学分（,号隔开）");

        String[] s = (reader.next()).split(",");

        Course course = null;

        String name = s[0];

        double score = Double.parseDouble(s[1]);

        course = new Course(0, name, score);
        CourseBase.insert(course);

        printCourses(CourseBase.findCourse(""));

    }


    public static void delete() {

        System.out.println("请输入课程编号：");

        int no = reader.nextInt();

        Course s = CourseBase.getOneByNo(no);

        if (s == null) {

            System.out.println("没有这个课程！");

        } else {

            CourseBase.delete(no);

        }

        printCourses(CourseBase.findCourse(""));

    }


    public static void edit() {

        System.out.println("请输入课程编号：");

        int no = reader.nextInt();

        Course c = CourseBase.getOneByNo(no);

        if (c == null) {

            System.out.println("没有这个课程！");

        } else {

            System.out.println("请输入课程名称，学分（，号隔开）");

            String[] s1 = (reader.next()).split(",");

            String name = s1[0];

            double score = Double.parseDouble(s1[1]);

            Course course = new Course(c.getNo(), name, score);

            CourseBase.update(course);

            printCourses(CourseBase.findCourse(""));

        }

    }


    public static void search() {

        System.out.println("请输入查询条件");

        reader.nextLine();

        String key = reader.nextLine();

        printCourses(CourseBase.findCourse(key));

    }


    public static void printCourses(List<Course> results) {

        System.out.println("编号\t\t课程\t\t学分");

        System.out.println("--------------------------------------------------");

        if (results.size() > 0) {

            Iterator<Course> iterator = results.iterator();

            while (iterator.hasNext()) {

                Course s = iterator.next();

                System.out.println(s);

            }

        } else {

            System.out.println("没有课程信息！！");

        }

        System.out.println("--------------------------------------------------");

    }
}

class StudentOperation{
    static Scanner reader = new Scanner(System.in);
    public static void main() throws Exception {

        while (true) {

            System.out.println("学生信息管理");

            System.out.println("--------------------------------");

            System.out.println("学生信息添加————1");

            System.out.println("学生信息修改————2");

            System.out.println("学生信息删除————3");

            System.out.println("学生信息查询————4");

            System.out.println("退出————0");

            int s = reader.nextInt();

            if (s == 1) {

                addStudent();

            } else if (s == 3) {

                delete();

            } else if (s == 2) {

                edit();

            } else if (s == 4) {

                search();

            }else if(s==0) {

                break;

            }

        }

    }

//学生信息的新增

    public static void addStudent() throws Exception {

        System.out.println("请输入学生姓名, 专业（,号隔开）");

        String[] s = (reader.next()).split(",");

        Student student = null;

        String name = s[0];

        String major = s[1];

        student = new Student(0, name, major);

        StudentBase.insert(student);

        printStudents(StudentBase.findStudent(""));

    }

//学生信息的删除

    public static void delete() {

        System.out.println("请输入学生编号：");

        int no = reader.nextInt();

        Student s=StudentBase.getOneByNo(no);

        if (s == null) {

            System.out.println("没有这个学生！");

        } else {

            StudentBase.delete(no);

        }

        printStudents(StudentBase.findStudent(""));

    }

//一个学生信息的编辑

    public static void edit() {

        System.out.println("请输入学生编号：");

        int no = reader.nextInt();

        Student s=StudentBase.getOneByNo(no);

        if (s == null) {

            System.out.println("没有这个学生！");

        } else {

            System.out.println("请输入学生姓名，专业（，号隔开）");

            String[] s1 = (reader.next()).split(",");

            s.setName(s1[0]);

            s.setMajor(s1[1]);

            StudentBase.update(s);

            printStudents(StudentBase.findStudent(""));

        }

    }

//学生信息的查询显示

    public static void search() {

        System.out.println("请输入查询条件:");

        //输入空格查询所有

        reader.nextLine(); //用一个nextLine消除前一个输入后面的回车

        String key = reader.nextLine();

        printStudents(StudentBase.findStudent(key));

    }

//学生信息的打印显示

    public static void printStudents(List<Student> results) {

        System.out.println("编号\t\t姓名\t\t专业");

        System.out.println("--------------------------------");

        if (results.size() > 0) {

            Iterator<Student> iterator=results.iterator();

            while(iterator.hasNext()) {

                Student s=iterator.next();

                System.out.println(s);

            }

        } else {

            System.out.println("没有学生信息！！");

        }

        System.out.println("--------------------------------");

    }


}
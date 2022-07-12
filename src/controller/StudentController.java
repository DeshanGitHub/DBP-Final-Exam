package controller;

import db.DbConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {

    public static boolean saveStudent(Student student) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Student VALUES (?,?,?,?,?,?)");
        pstm.setObject(1, student.getId());
        pstm.setObject(2, student.getName());
        pstm.setObject(3, student.getEmail());
        pstm.setObject(4, student.getContact());
        pstm.setObject(5, student.getAddress());
        pstm.setObject(6, student.getNic());

        return pstm.executeUpdate() > 0;
    }

    public static ArrayList<Student> getAllStudents() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM student");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Student> itemArrayList=new ArrayList<>();

        while (resultSet.next()){
            itemArrayList.add(new Student(
                    resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)
            ));
        }
        return itemArrayList;
    }

}

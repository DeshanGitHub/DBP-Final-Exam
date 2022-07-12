package controller;

import db.DbConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Student;

import java.sql.SQLException;

public class StudentFormController {
    public JFXTextField txtStudentEmail;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentContact;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtStudentNIC;


    public JFXButton btnSaveId;

    public void saveButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Student s=new Student(txtStudentId.getText(),txtStudentName.getText(),txtStudentEmail.getText(),txtStudentContact.getText(),txtStudentAddress.getText(),txtStudentNIC.getText());

        if(StudentController.saveStudent(s)){
            new Alert(Alert.AlertType.INFORMATION, "Saved..").show();
        }
        else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }
}

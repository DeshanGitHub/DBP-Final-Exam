package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentFormController {
    public JFXTextField txtStudentEmail;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentContact;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtStudentNIC;


    public JFXButton btnSaveId;
    public TableView<StudentTM> tblStudentTable;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colStudentEmail;
    public TableColumn colStudentContact;
    public TableColumn colStudentAddress;
    public TableColumn colStudentNIC;

    public void initialize(){
        try {
            loadTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colStudentContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStudentNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));


    }

    public void saveButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Student s=new Student(txtStudentId.getText(),txtStudentName.getText(),txtStudentEmail.getText(),txtStudentContact.getText(),txtStudentAddress.getText(),txtStudentNIC.getText());

        if(StudentController.saveStudent(s)){
            new Alert(Alert.AlertType.INFORMATION, "Saved..").show();
        }
        else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

        loadTable();
    }

    private void loadTable() throws SQLException, ClassNotFoundException {
        ObservableList<StudentTM> studentTMObservableList = FXCollections.observableArrayList();
        studentTMObservableList.clear();
        ArrayList<Student> allStudent = StudentController.getAllStudents();

        //int num=0;

        for (Student student:allStudent
        ) {
            //num++;
            studentTMObservableList.add(new StudentTM(
                    student.getId(),student.getName(),student.getEmail(),student.getContact(),student.getAddress(),student.getNic()
            ));
        }
        tblStudentTable.setItems(studentTMObservableList);
    }

}

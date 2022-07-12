package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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
    public TableColumn colDeleteStudent;

    int tableSelectedRow = -1;

    public void initialize() {
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
        colDeleteStudent.setCellValueFactory(new PropertyValueFactory<>("btn"));


    }

    public void saveButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Student s = new Student(txtStudentId.getText(), txtStudentName.getText(), txtStudentEmail.getText(), txtStudentContact.getText(), txtStudentAddress.getText(), txtStudentNIC.getText());

        if (StudentController.saveStudent(s)) {
            new Alert(Alert.AlertType.INFORMATION, "Saved..").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

        loadTable();
    }

    //ObservableList<StudentTM> studentTMObservableList = FXCollections.observableArrayList();
    private void loadTable() throws SQLException, ClassNotFoundException {
        ArrayList<Student> allStudents = StudentController.getAllStudents();
        ObservableList<StudentTM> tmStudentList = FXCollections.observableArrayList();

        for (Student s : allStudents
        ) {
            Button btn = new Button("Delete");
            tmStudentList.add(new StudentTM(
                    s.getId(), s.getName(), s.getEmail(), s.getContact(), s.getAddress(),
                    s.getNic(), btn
            ));

            btn.setOnAction((event) -> {

                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure,You Want To Delete This Student?", yes, no);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.orElse(no) == yes) {
                    try {
                        StudentController.deleteStudent(s.getId());
                        loadTable();
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }

            });
        }

        tblStudentTable.setItems(tmStudentList);

    }

}

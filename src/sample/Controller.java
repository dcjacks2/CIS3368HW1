package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends Employee implements Initializable
{
    @FXML
    private ListView<Employee> employeeListView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private Button clearButton;
    @FXML
    private Button addNewButton;
    @FXML
    private Button deleteSelectedButton;




    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        employeeListView.getSelectionModel().selectedItemProperty().addListener((
                        ObservableValue< ? extends Worker> ov, Worker old_val, Worker new_val)->
                {
                    Worker selectedItem = employeeListView.getSelectionModel().getSelectedItem();
                    firstNameTextField.setText(((Employee)selectedItem).firstName);
                    lastNameTextField.setText(((Employee)selectedItem).lastName);
                    isActiveCheckBox.setSelected(((Employee)selectedItem).isActive);
                }
        );


        ObservableList<Employee> items = employeeListView.getItems();
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        employee1.firstName = "Robert";
        employee1.lastName = "Smith";
        employee2.firstName = "Lisa";
        employee2.lastName = "Scott";
        items.add(employee1);
        items.add(employee2);

        for(int i = 0; i < 10; i++)
        {
            Employee employee = new Employee();
            employee.firstName = "Generic";
            employee.lastName = "Employee" + " " + i;
            employee.hire();
            items.add(employee);

        }

        Staff staff1 = new Staff();
        staff1.firstName = "StaffPerson";
        staff1.lastName = "GoodWorker";

        Faculty faculty1 = new Faculty();
        faculty1.firstName = "FacultyPerson";
        faculty1.lastName = "TerribleWorker";

        items.add(staff1);
        items.add(faculty1);
    }
    @FXML
    private void clearData(){

        firstNameTextField.clear();
        lastNameTextField.clear();
        isActiveCheckBox.setSelected(false);

    }
    @FXML
    private void deleteEmployee(){

        int Employee = employeeListView.getSelectionModel().getSelectedIndex();

            if(Employee >= 0){
                employeeListView.getItems().remove(Employee);
            }
            else{

                Alert dAlert = new Alert(Alert.AlertType.ERROR);
                dAlert.setTitle("Warning");
                dAlert.setHeaderText("No employee selected");
                dAlert.setContentText("Please select an employee in the list");
                dAlert.show();

            }
    }
    @FXML
    private void addEmployee(){

        Employee addEmployee = new Employee();
        addEmployee.firstName = firstNameTextField.getText();
        addEmployee.lastName = lastNameTextField.getText();

        if(isActiveCheckBox.isSelected()){
            addEmployee.hire();
        }
        else{
            addEmployee.fire();
        }

        employeeListView.getItems().add(addEmployee);

    }

}

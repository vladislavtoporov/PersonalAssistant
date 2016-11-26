package ch.makery.weather.view;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/*
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;*/

public class PersonOverviewController {
    /*
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;*/

    @FXML
    private Label date;

    @FXML
    private Label advise1;
    @FXML
    private Label advise2;
    @FXML
    private Label advise3;
    @FXML
    private Label advise4;
    @FXML
    private Label advise5;
    @FXML
    private Label advise6;
    @FXML
    private Label advise7;
    @FXML
    private Label advise8;
    @FXML
    private Label advise9;
    @FXML
    private Label advise10;
    @FXML
    private Label advise11;
    @FXML
    private Label advise12;
   /*
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    */

    // Reference to the main application.

    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */

    private void showWeatherDetails(Weather weather) {
        if (weather != null) {
            // Fill the labels with info from the person object.
            date.setText(DateUtil.format(weather.getDate()));
            advice1.setText(weather.getAdvice1());
            advice2.setText(weather.getAdvice2());
            advice3.setText(weather.getAdvice3());
            advice4.setText(weather.getAdvice4());
            advice5.setText(weather.getAdvice5());
            advice6.setText(weather.getAdvice6());
            advice7.setText(weather.getAdvice7());
            advice8.setText(weather.getAdvice8());
            advice9.setText(weather.getAdvice9());
            advice10.setText(weather.getAdvice10());
            advice11.setText(weather.getAdvice11());
            advice12.setText(weather.getAdvice12());


            /*lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday())); */
        } else {
            // Person is null, remove all the text.
            date.setText("");
            advice1.setText("");
            advice2.setText("");
            advice3.setText("");
            advice4.setText("");
            advice5.setText("");
            advice6.setText("");
            advice7.setText("");
            advice8.setText("");
            advice9.setText("");
            advice10.setText("");
            advice11.setText("");
            advice12.setText("");
            /*
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");*/
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleUpdate() {
        // Вызов Кода Саши
    }

    /*
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        }
		/*else {
			// Nothing selected.
			Dialogs.create()
		        .title("No Selection")
		        .masthead("No Person Selected")
		        .message("Please select a person in the table.")
		        .showWarning();

    }


     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.

    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }


     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.

    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        }
		/*else {
			// Nothing selected.
			Dialogs.create()
				.title("No Selection")
				.masthead("No Person Selected")
				.message("Please select a person in the table.")
				.showWarning();
		}
    }
    */
}
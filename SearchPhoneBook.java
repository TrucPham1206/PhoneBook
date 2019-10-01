package phonebook;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import phonebook.PhoneBook2.Person;

public class SearchPhoneBook extends Application
{      
    private final ObservableList<Person> data
            = FXCollections.observableArrayList(
                    new Person("Kaila", "Dafoe", "2267863334", "12/5/1990"),
                    new Person("Hai", "Nguyen", "6476665877","24/7/1998"),
                    new Person("Kelly", "Smith", "2264563546","03/8/1992"),
                    new Person("Bound", "Jump", "6475566663","26/5/1998"),
                    new Person("Lucy", "Pham", "9053344567","21/2/1996")
            );
    private String contactList = "c:/temp/ContactList.txt";
    TextField addFirstName;
    TextField addLastName;
    TextField addPhoneNo;
    TextField addBirthDay;
    int index;

    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage)
    {
    	try {
    	TableView<Person> table = new TableView<Person>();
        Scene scene = new Scene(new Group());
        
        final Label label = new Label("Phone Book");
        label.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.YELLOW);
        table.setEditable(true);

        TableColumn fNameCol = new TableColumn("First Name");
        fNameCol.setMinWidth(110);
        fNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn lNameCol = new TableColumn("Last Name");
        lNameCol.setMinWidth(110);
        lNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn phoneCol = new TableColumn("Phone Number");
        phoneCol.setMinWidth(120);
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("phoneNo"));
        
        TableColumn dOBCol = new TableColumn("Date of Birth");
        dOBCol.setMinWidth(110);
        dOBCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("birthDay"));

        FilteredList<Person> search = new FilteredList(data, p -> true);
        table.setItems(search);
        table.getColumns().addAll(fNameCol, lNameCol, phoneCol, dOBCol);
        
        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("First Name", "Last Name", "Phone Number","Date of Birth");
        choiceBox.setValue("First Name");

        TextField textField = new TextField();
        textField.setPromptText("Search contact");
        textField.setOnKeyReleased(keyEvent ->
        {
            switch (choiceBox.getValue())            {
                case "First Name":
                    search.setPredicate(p -> p.getFirstName().toLowerCase().contains(textField.getText().toLowerCase().trim()));
                    break;
                case "Last Name":
                    search.setPredicate(p -> p.getLastName().toLowerCase().contains(textField.getText().toLowerCase().trim()));
                    break;
                case "Phone Number":
                    search.setPredicate(p -> p.getPhoneNo().toLowerCase().contains(textField.getText().toLowerCase().trim()));
                    break;
                case "Date of Birth":
                    search.setPredicate(p -> p.getBirthDay().toLowerCase().contains(textField.getText().toLowerCase().trim()));
                    break;
            }
        });

        Button loadFile = new Button("Load File");
        Button btnCreate = new Button("Create");
        Button btnDelete = new Button("Delete");
    
        HBox hBox = new HBox(choiceBox, textField, btnCreate, loadFile);
        hBox.setAlignment(Pos.CENTER);
        RadioButton redBt = new RadioButton("Red");
	    RadioButton blueBt = new RadioButton("Blue");
	    RadioButton purpleBt = new RadioButton("Purple");
	    ToggleGroup group = new ToggleGroup();
	    redBt.setToggleGroup(group);
	    blueBt.setToggleGroup(group);
	    purpleBt.setToggleGroup(group);
	     HBox hb = new HBox();
	     hb.getChildren().addAll(redBt,blueBt, purpleBt);
	     hb.setPadding(new Insets(5, 15, 5, 15));
	     redBt.setFont(Font.font("Arial",FontWeight.BOLD, 23));
	        redBt.setAlignment(Pos.CENTER);
	        redBt.setTextFill(Color.RED);
	        blueBt.setFont(Font.font("Arial",FontWeight.BOLD, 23));
	        blueBt.setAlignment(Pos.CENTER);
	        blueBt.setTextFill(Color.CYAN);
	       purpleBt.setFont(Font.font("Arial",FontWeight.BOLD, 23));
	       purpleBt.setAlignment(Pos.CENTER);
	       purpleBt.setTextFill(Color.PURPLE);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().addAll(label, table, hBox,hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        vbox.setStyle("-fx-background-color: navy;");
        final Label label2 = new Label("New Contact");
        label2.setFont(Font.font("Arial",FontWeight.BOLD, 23));
        label2.setAlignment(Pos.CENTER);
        label2.setTextFill(Color.YELLOW);
        TableView<Person> table2 = new TableView<Person>();
        TableColumn fNameCol2 = new TableColumn("First Name");
        fNameCol2.setMinWidth(110);
        fNameCol2.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn lNameCol2 = new TableColumn("Last Name");
        lNameCol2.setMinWidth(110);
        lNameCol2.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn phoneCol2 = new TableColumn("Phone Number");
        phoneCol2.setMinWidth(120);
        phoneCol2.setCellValueFactory(
                new PropertyValueFactory<Person, String>("phoneNo"));
        
        TableColumn dOBCol2 = new TableColumn("Date of Birth");
        dOBCol2.setMinWidth(110);
        dOBCol2.setCellValueFactory(
                new PropertyValueFactory<Person, String>("birthDay"));
        
        table2.setItems(data);
        table2.getColumns().addAll(fNameCol2, lNameCol2, phoneCol2, dOBCol2);
                      
        Button create = new Button("Create new contact");
        Button home = new Button("Home");
        Button btClear = new Button("Clear");
        Button saveToFile = new Button("Save File");
        addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(fNameCol.getPrefWidth());
        addLastName = new TextField();
        addLastName.setMaxWidth(lNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        addPhoneNo = new TextField();
        addPhoneNo.setMaxWidth(phoneCol.getPrefWidth());
        addPhoneNo.setPromptText("Phone");
        addBirthDay = new TextField();
        addBirthDay.setMaxWidth(dOBCol.getPrefWidth());
        addBirthDay.setPromptText("Date of Birth");
        HBox hBox2 = new HBox(create, addFirstName, addLastName,addPhoneNo,addBirthDay);
        hBox2.setPadding(new Insets(5, 5, 5, 5));
        HBox hBox3 = new HBox(home, saveToFile,btnDelete,btClear);
        hBox3.setAlignment(Pos.CENTER);
        
        VBox vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(5);
        vbox2.setStyle("-fx-background-color: navy;");
        vbox2.setPadding(new Insets(5, 5, 5, 5));       
        vbox2.getChildren().addAll(label2,table2,hBox2,hBox3);
        Scene scene2 = new Scene(new Group(vbox2)); 
        redBt.setOnAction(e -> {
		      if (redBt.isSelected()) {
		    	  btnCreate.setTextFill(Color.RED); fNameCol.setStyle("-fx-text-color: red;"); loadFile.setTextFill(Color.RED); 
		    	  btnCreate.setTextFill(Color.RED); 
		        create.setTextFill(Color.RED); btClear.setTextFill(Color.RED); 
		      }		    });	
        
        create.setOnAction(e -> {        			
	        	try {
//					Integer.parseInt(addLastName.getText());
				String fName = addFirstName.getText();
				String lName = addLastName.getText();
				String phoneNo = addPhoneNo.getText();
				String birthDay = addBirthDay.getText();
				if (fName.length() == 0 && lName.length() == 0) {
					Alert alert = new Alert(AlertType.INFORMATION, "Please enter your first name");
					alert.showAndWait(); 
				}
				else if (phoneNo.length() <= 7) {
					Alert alert = new Alert(AlertType.INFORMATION, "Please enter 7 digits or more");
					alert.showAndWait();
				}
				else {
				data.add(new Person(fName, lName, phoneNo, birthDay));
				}
					
			}catch(Exception ex) {
				 ex.printStackTrace();
			}
		});
        btClear.setOnAction(ac ->{
        	addFirstName.clear(); addLastName.clear(); 
        	addPhoneNo.clear();  addBirthDay.clear();
        });
        btnCreate.setOnAction( e-> {
			stage.setScene(scene2);
		});
        home.setOnAction( e-> {
			stage.setScene(scene);
		});
        
        btnDelete.setOnAction(e -> {
            Person selectedItem = table2.getSelectionModel().getSelectedItem();
            table2.getItems().remove(selectedItem);
        });
        
        loadFile.setOnAction(e -> {
			data.clear();
			
			File f = new File(contactList);
			try {
				if(!f.exists()) f.createNewFile();
				Scanner lines = new Scanner(f);
				if(!lines.hasNextLine()) {
					 Alert alert = new Alert(AlertType.INFORMATION, "data file is empty\nenter new contact and click the save button"); 
					 alert.showAndWait();
				}
				else {
					while(lines.hasNextLine()) {
						Scanner line = new Scanner(lines.nextLine());
						line.useDelimiter(":\\s*");
						data.add(new Person(line.next(), line.next(), line.next(), line.next()));
						line.close();
					}
				}
				lines.close();

			} catch (Exception e1) {
				e1.printStackTrace();
			}						
		});
        
        saveToFile.setOnAction(e-> {
			File f = new File(contactList);
			try {
				if(!f.exists()) f.createNewFile();
				PrintWriter pw = new PrintWriter(f);
				for(Person c : data) 
					pw.printf("%s:%s:%s:%s\n", 
							c.getFirstName(), c.getLastName(), c.getPhoneNo(), c.getBirthDay());
				pw.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			 Alert alert = new Alert(AlertType.INFORMATION, "data is saved in a file\nyou can close the \napplication now"); 
			 alert.showAndWait();

		});
        stage.setScene(scene);
        stage.setTitle("Group Project");
        stage.setWidth(480);
        stage.setHeight(550);
        stage.show();
    	} catch(Exception e) {
			e.printStackTrace();
		}
	}   

    public static class Person
    {
        private  String firstName;
        private  String lastName;
        private  String phoneNo;
        private  String birthday;

        private Person(String fName, String lName, String phoneNum, String birthdate )
        {
            this.firstName = new String(fName);
            this.lastName = new String(lName);
            this.phoneNo = new String(phoneNum);
            this.birthday = new String(birthdate);
        }

        public String getFirstName()
        {
            return firstName;
        }

        public void setFirstName(String fName)
        {
            this.firstName= fName;
        }

        public String getLastName()
        {
            return lastName;
        }

        public void setLastName(String lName)
        {
        	this.lastName= lName;
        }

        public String getPhoneNo()
        {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNum)
        {
        	this.phoneNo= phoneNum;
        }
        public String getBirthDay()
        {
            return birthday;
        }

        public void setBirthDay(String birthdate)
        {
        	this.birthday= birthdate;
        }
        public String toString() {
        	return String.format(" %s\n  %s\n  %s\n  %s\n %s ",
				  firstName, lastName, phoneNo, birthday );
	  
        }
    }
}
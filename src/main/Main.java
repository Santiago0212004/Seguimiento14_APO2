package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import control.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {

		launch(args);	
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MainWindow.fxml"));
		loader.setController(new MainWindow(loadCSV()));
		Parent parent = (Parent) loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setTitle("Seguimiento 14");
		//stage.getIcons().add(new Image(""));
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static ArrayList<String> loadCSV() throws IOException {
		String path = "src/data.csv";
		
		File file = new File(path);
		
		System.out.println(file.getAbsolutePath());
			
		ArrayList<String> data = new ArrayList<>();

		if(file.exists()) {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			
			int i = 0;
			while((line = br.readLine())!=null) {
				if(i>0) {
					data.add(line);
				}
				i++;
			}
			
			if (br != null)br.close();
            if (fr != null)fr.close();
			
		}
		return data;
	}
	
	

}

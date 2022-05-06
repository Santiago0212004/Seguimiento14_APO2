package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MainWindow implements Initializable{

    @FXML
    private Canvas canvas;
    
    private GraphicsContext gc;
    
    
    private ArrayList<String> data;
    
    public MainWindow(ArrayList<String> data) {
    	this.data = data;
    }

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		gc = canvas.getGraphicsContext2D();
		
		//Motor de renderizado

			try {
				paint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

	}
	
	public void paint() throws InterruptedException {
		
		gc.setFill(Color.rgb(240,240,240));
		
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());	
		
		gc.setFill(Color.rgb(20,20,20));
		
	
		for(int i = 0; i<data.size()-1; i++) {
			
			String point = data.get(i);
			String nextPoint = data.get(i+1);
			
			
			
			int x = Integer.parseInt(point.split(",")[0]);
			int y = Integer.parseInt(point.split(",")[1].split(";")[0]);
			
			x = transformX(x);
			y = transformY(y);
			
			int nextX = Integer.parseInt(nextPoint.split(",")[0]);
			int nextY = Integer.parseInt(nextPoint.split(",")[1].split(";")[0]);
			
			nextX = transformX(nextX);
			nextY = transformY(nextY);
			
			
			System.out.println("\nx: "+x+"\ny: "+y);
			
			gc.setFill(Color.BLACK);
			gc.beginPath();
			gc.moveTo(x,y);
		    gc.lineTo(nextX,nextY);
		    gc.fill();
			gc.closePath();
			gc.stroke();
			
			gc.fillOval(nextX-3, nextY-3, 6, 6);
			
		}	
	}
	
	public int getMinX() {
		int min = 1000000;
		
		for(String point : data) {
			int x = Integer.parseInt(point.split(",")[0]);
			
			
			if(x<min) {
				min = x;
			}
		}
		
		return min;
		
	}
	
	public int getMinY() {
		int min = 1000000;
		
		for(String point : data) {
			int y = Integer.parseInt(point.split(",")[1].split(";")[0]);
			
			if(y<min) {
				min = y;
			}
		}
		
		return min;
		
	}
	
	public int getMaxX() {
		int max = 0;
		
		for(String point : data) {
			int x = Integer.parseInt(point.split(",")[0]);
			
			if(x>max) {
				max = x;
			}
		}
		
		return max;
		
	}
	
	public int getMaxY() {
		int max = 0;
		
		for(String point : data) {
			int y = Integer.parseInt(point.split(",")[1].split(";")[0]);
			
			if(y>max) {
				max = y;
			}
		}
		
		return max;
		
	}
	
	public int transformX(int x) {

		int multiplicator = (int) (canvas.getWidth() / getMaxX());
		
		x = getMinX() + (x*multiplicator);
		
		return x;
	}
	
	public int transformY(int y) {
		int multiplicator = (int) (canvas.getHeight() / getMaxY()) ;
		
		y = getMinY() + (y*multiplicator);
		
		y = (int) (canvas.getHeight() - y);
		
		return y;
	}
	
		

}

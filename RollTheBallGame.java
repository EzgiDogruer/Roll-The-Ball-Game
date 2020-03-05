
//Ezgi Doğruer		150117042
//İsra Nur Alperen  150117061


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RollTheBallGame extends Application {
	private Scanner input;
	String[][] ImageName = new String[4][4];
	ImageView[] images = new ImageView[16]; //create an Array to hide the pictures
	Pane mypane = new Pane();// creating a pane
	Scene scene;
	final int width = 70;
	final int height = 70;
	final int gap = 10;
	
	public void start(Stage stage) throws FileNotFoundException {
		int ari = 0;
		int arj = 0;
		
		
		
		System.out.print("Please enter the level number (1 to 5): ");
		Scanner input2 = new Scanner(System.in);
		String number = input2.next();
		
										
			
			try {

				input = new Scanner(new File("level"+number+".txt"));
			}

			catch (Exception e) {

				System.out.println("Couldn't find");
			}
			while (input.hasNext()) {// reading file and making a String for each row

				String as = input.next();
				String d = as.substring(as.indexOf(',')+1);
				
				//To put the names into the Array
				if (ari < 4) {
					ImageName[arj][ari] = d;
					}
				else if (ari == 4) {
					ari = 0;
			arj++;
			ImageName[arj][ari] = d;
			
			}		
				
				ari++;
			}
			ari = 0;
			arj = 0;
			
			
			
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			for (int j = 0; j < 16; j++) {// finding strings from and adding in the pane
				if (ari == 4) {
					ari = 0;
					arj++;
				}
				

				Image image = new Image(new FileInputStream(ImageName[arj][ari] + ".jpeg"));
				ari++;
				
				// Setting the image view 1
				ImageView imageView1 = new ImageView(image);
				images[j] = imageView1;
				// Setting the position of the image
				images[j].setX(10 + (ari - 1) * 70);
				images[j].setY(10 + arj * 70);

				// setting the fit height and width of the image view
				images[j].setFitHeight(70);
				images[j].setFitWidth(70);

				// Setting the preserve ratio of the image view

				mypane.getChildren().add(images[j]);
				
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				final int j2=j;
				
				images[j].setOnMouseDragged(b -> { // when we dragged the image
					int secilenX = ((int) b.getX() - gap) / width;
					int secilenY = ((int) b.getY() - gap) / height;
					images[j2].setOnMouseDragged(e -> {
						
						// Taken place
						final int x2=(((int) e.getX() - gap) / width);
						final int y2=(((int) e.getY() - gap) / height);
						final int konum = 4 *y2 +x2;
						
						//To designate the pictures which can moves and they can move only location of empty free
						if ((ImageName[secilenY][secilenX].equals("Pipe,Horizontal")
								|| ImageName[secilenY][secilenX].equals("Pipe,Vertical")
								|| ImageName[secilenY][secilenX].equals("Pipe,01")
								|| ImageName[secilenY][secilenX].equals("Pipe,00")
								|| ImageName[secilenY][secilenX].equals("Pipe,11")
								||ImageName[secilenY][secilenX].equals("Pipe,10")
								|| ImageName[secilenY][secilenX].equals("Empty,none"))
								&& (ImageName[((((int) e.getY()) - gap) / height)][((((int) e.getX()) - gap) / width)]
										.equals("Empty,Free"))) {
							
							
							//For corner of [3][3]
							if(secilenX == 3 && secilenY == 3) {
								if(ImageName[secilenY-1][secilenX] == ImageName[y2][x2] ||
										ImageName[secilenY][secilenX-1] == ImageName[y2][x2]
										) {
									images[j2].setX(e.getX());
									images[j2].setY(e.getY());
									 Swap(images[j2],images[konum],x2,y2,secilenX,secilenY,width,gap ,height);
									 SwapImage(images,j2,konum);
									 }}
							
							//For corner of [0][3]
							else if(secilenX == 3 && secilenY ==0){
								if(ImageName[secilenY][secilenX-1] == ImageName[y2][x2] ||
										ImageName[secilenY+1][secilenX]== ImageName[y2][x2]
										) {//aşağı sürükleme
									images[j2].setX(e.getX());
									images[j2].setY(e.getY());
									 Swap(images[j2],images[konum],x2,y2,secilenX,secilenY,width,gap ,height);
									 SwapImage(images,j2,konum);
									 
									 }}
							
							//For corner of [3][0]
							else if(secilenX == 0 && secilenY ==3) {
								if(ImageName[secilenY][secilenX+1] == ImageName[y2][x2] ||
										ImageName[secilenY-1][secilenX]==ImageName[y2][x2]
										) {//aşağı sürükleme
									images[j2].setX(e.getX());
									images[j2].setY(e.getY());
									 Swap(images[j2],images[konum],x2,y2,secilenX,secilenY,width,gap ,height);
									 SwapImage(images,j2,konum);
									 }}
							
							//For corner of [0][0]
						  else if(secilenX == 0 && secilenY ==0){
							if(ImageName[secilenY+1][secilenX] == ImageName[y2][x2] ||
									ImageName[secilenY][secilenX+1]==ImageName[y2][x2]
									) {//aşağı sürükleme
								images[j2].setX(e.getX());
								images[j2].setY(e.getY());
								 Swap(images[j2],images[konum],x2,y2,secilenX,secilenY,width,gap ,height);
								 SwapImage(images,j2,konum);
								}}
							
							//For right column
						else if(secilenX == 3) {
								if(ImageName[secilenY+1][secilenX] == ImageName[y2][x2] ||
										ImageName[secilenY-1][secilenX] == ImageName[y2][x2]||
										ImageName[secilenY][secilenX-1] == ImageName[y2][x2]
										) {//aşağı sürükleme
									images[j2].setX(e.getX());
									images[j2].setY(e.getY());
									 Swap(images[j2],images[konum],x2,y2,secilenX,secilenY,width,gap ,height);
									 SwapImage(images,j2,konum);
									 
							
								}}
								
							//For bottom row
						else if(secilenY == 3) {
								if(ImageName[secilenY-1][secilenX] == ImageName[y2][x2] ||
										ImageName[secilenY][secilenX-1] == ImageName[y2][x2]||
												ImageName[secilenY][secilenX+1] == ImageName[y2][x2]
										) {//yukarı sürükleme
									images[j2].setX(e.getX());
									images[j2].setY(e.getY());
									 Swap(images[j2],images[konum],x2,y2,secilenX,secilenY,width,gap ,height);
									 SwapImage(images,j2,konum);
									 
								}
								}
							//For left column
							else if(secilenX == 0) {
								if(ImageName[secilenY][secilenX+1] == ImageName[y2][x2] ||
										ImageName[secilenY-1][secilenX] == ImageName[y2][x2] ||
												ImageName[secilenY+1][secilenX] == ImageName[y2][x2]
										) {//yukarı sürükleme
									images[j2].setX(e.getX());
									images[j2].setY(e.getY());
									 Swap(images[j2],images[konum],x2,y2,secilenX,secilenY,width,gap ,height);
									 SwapImage(images,j2,konum);
									 
								}
								}
							
							//For top row
							else if(secilenY == 0) {
								if(ImageName[secilenY+1][secilenX] == ImageName[y2][x2] ||
										ImageName[secilenY][secilenX+1] == ImageName[y2][x2] ||
												ImageName[secilenY][secilenX-1] == ImageName[y2][x2]
										) {//yukarı sürükleme
									images[j2].setX(e.getX());
									images[j2].setY(e.getY());
									 Swap(images[j2],images[konum],x2,y2,secilenX,secilenY,width,gap ,height);
									 SwapImage(images,j2,konum);
									
								}
								
								}
							//For other locations
							else {
								if (ImageName[secilenY][secilenX+1] == ImageName[y2][x2] ||
										ImageName[secilenY][secilenX-1] == ImageName[y2][x2]||
												ImageName[secilenY+1][secilenX]== ImageName[y2][x2] ||
														ImageName[secilenY-1][secilenX] == ImageName[y2][x2]
												
										) {
									images[j2].setX(e.getX());
									images[j2].setY(e.getY());
									 Swap(images[j2],images[konum],x2,y2,secilenX,secilenY,width,gap ,height);
									 SwapImage(images,j2,konum);
									 
								}}
							//To change locations in ImageName array
							String degisim=ImageName[secilenY][secilenX];
							ImageName[secilenY][secilenX]=ImageName[y2][x2];
							 ImageName[y2][x2]=degisim;
							
							 //When you completed the level, stage will close
							 if(control(ImageName)) {
								
								 stage.close();
								 System.out.println("You Win!!");
									}
								
							}

					}); 
				});
				
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
			
		
		// Creating a scene object
			scene = new Scene(mypane, 300, 300);
			

		// Setting title to the Stage
		stage.setTitle("ROLL THE BALL GAME");

		// Adding scene to the stage
		stage.setScene(scene);

		// Displaying the contents of the stage
		stage.show();
		
		
		
	}
	//This method swap locations on the scene
	public void Swap(ImageView image1,ImageView image2,int x,int y,int chosenX,int chosenY, int width,int gap , int height) {
		image1.setX(x* width + gap);
		image1.setY(y* height+ gap);
		image2.setX(chosenX*width+gap);
		image2.setY(chosenY*height+gap);
		
		
	}
	//This method change images in Image array
	public ImageView[] SwapImage(ImageView[] images, int locationInArray1 ,int locationInArray2) {
		ImageView degisen =images[locationInArray1];
		images[locationInArray1]=images[locationInArray2];
		images[locationInArray2]=degisen;
		return images;
	}
	///////////////////////////////////     FİNİSH MOVE   ///////////////////////////////////////////////////////////
	
	//This method controls whether the level is finished 
	/* Explain the control method :
	 *Firstly,we determined the images(tile),
	 *Secondly,we create a default variable as "yön" for directon
	 *Thirdly, we imagined a ball and if the ball move, we think that ball can move which direction
	 *Then, we update the values of i and j by direction
	 * After that,we think the ball will move which direction
	 * Finally,we update the "yön" for posibilities of each tile */
	public static boolean control(String[][] ImageName) {
		int i=0;
		int j=0;
		String yön ="";
			for(;i<4;) {
				for( ;j<4;) {
					switch(ImageName[i][j]) {
					
					case "Starter,Vertical":{
						
						if( ImageName[i+1][j].equals("Pipe,01") ||
							ImageName[i+1][j].equals("PipeStatic,01")) { 
							yön ="right";
							i++; 
						}
						
						else if( ImageName[i+1][j].equals("Pipe,Vertical")) { i++; yön = "down";
					}
							
																		
						else if(  ImageName[i+1][j].equals("Pipe,00")) {  
							i++;
							yön = "left";
						}
						else  {	
							i=6;
							j=6;
						}
						
						 break;
					}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////				
					case "Starter,Horizontal":{
		
						if( ImageName[i][j-1].equals("Pipe,Horizontal")||
							ImageName[i][j-1].equals("PipeStatic,Horizontal")) {
							
							yön="left";
							j--; 
						}
						
						else if(ImageName[i][j-1].equals("Pipe,01") ||ImageName[i][j-1].equals("PipeStatic,01")) {
							
							yön="up";
							j--;
						}
						else if (ImageName[i][j-1].equals("Pipe,11")){
							yön ="down";
							j--;
						}
						
						else  {	
							i=6;
							j=6;
						}
						
						 break;
					}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
					case "Empty,Free":{
						i=6;
						j=6;
						
						break;
					}
					
					case "Empty,none":{
						i=6;
						j=6;
						
						break;
					}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////				
					case "Pipe,Vertical":{
						
						if(yön.equals("up")) {
							if(ImageName[i-1][j].equals("Pipe,10")) {
								yön ="left";
								i--;
							}
							else if(ImageName[i-1][j].equals("Pipe,11")) {
								yön = "right";
								i--;
							}
							
							else if(ImageName[i-1][j].equals("Pipe,Vertical")||
									ImageName[i-1][j].equals("PipeStatic,Vertical")) {
								yön = "up";
								i--;
							}
							else if(ImageName[i-1][j].equals("End,Vertical")){
								i=5 ;
								j=5;
							}
						}
										
						else if(yön.equals("down")) {
							if(ImageName[i+1][j].equals("Pipe,00")) {
								yön ="left";
								i++;
							}
							else if(ImageName[i+1][j].equals("Pipe,01") ||ImageName[i+1][j].equals("PipeStatic,01")) {
								yön = "right";
								i++;
							}
							else if(ImageName[i+1][j].equals("Pipe,Vertical")||
									ImageName[i+1][j].equals("PipeStatic,Vertical")) {//en aşağıda olmucak
								yön = "down";
								i++;
							}
							
						}
						break;
					}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
					case "Pipe,Horizontal":{
						if(yön.equals("right")) {
					
						if(ImageName[i][j+1].equals("Pipe,10")) {
							yön ="down";
							j++;
						}
						else if(ImageName[i][j+1].equals("Pipe,00")) {
							yön = "up";
							j++;
						}
						
						else if(ImageName[i][j+1].equals("Pipe,Horizontal")||
								ImageName[i][j+1].equals("PipeStatic,Horizontal")) {
							yön = "right";
							j++;
						}
						else if(ImageName[i][j+1].equals("End,Vertical")){
							i=5 ;
							j=5;
						}
					}
					
					else if(yön.equals("left")) {
						if(ImageName[i][j-1].equals("Pipe,11")) {
							yön ="down";
							j--;
						}
						else if(ImageName[i][j-1].equals("Pipe,01") ||ImageName[i][j-1].equals("PipeStatic,01")) {
							yön = "up";
							j--;
						}
						else if(ImageName[i][j-1].equals("Pipe,Horizontal")||
								ImageName[i][j-1].equals("PipeStatic,Horizontal")) {
							yön = "leftt";
							j--;
						}
						
						
					}
						break;
				}
///////////////////////////////////////////////////////////////////////////////////////////////////////////					
					case "Pipe,00":{
						if(yön.equals("down")) {
							
							if(ImageName[i][j-1].equals("Pipe,01") ||ImageName[i][j-1].equals("PipeStatic,01")) {
								yön ="up";
								j--;
							}
							else if(ImageName[i][j-1].equals("Pipe,11")) {
								yön = "down";
								j--;
							}
							
							else if(ImageName[i][j-1].equals("Pipe,Horizontal")||
									ImageName[i][j-1].equals("PipeStatic,Horizontal")) {
								yön = "left";
								j--;
							}
							else  {	
								i=6;
								j=6;
							}
						}
						
						else if(yön.equals("right")) {
							if(ImageName[i-1][j].equals("Pipe,11")) {
								yön ="right";
								i--;
							}
							else if(ImageName[i-1][j].equals("Pipe,10")) {
								yön = "left";
								i--;
							}
							else if(ImageName[i-1][j].equals("Pipe,Vertical")||
									ImageName[i-1][j].equals("PipeStatic,Vertical")) {
								yön = "up";
								i--;
							}
							else if(ImageName[i-1][j].equals("End,Vertical")){
								i=5;
								j=5;
							}
							else  {	
								i=6;
								j=6;
							}
							
						}
						break;
					}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
					case "Pipe,01" :{
					if(yön.equals("right")) {
							if(ImageName[i][j+1].equals("Pipe,Horizontal")||
									ImageName[i][j+1].equals("PipeStatic,Horizontal")) {
								yön = "right";
								j++;
							}
							else if(ImageName[i][j+1].equals("Pipe,10")) {
								yön = "down";
								j++;
							}
							else if(ImageName[i][j+1].equals("Pipe,00")) {
								yön = "up";
								j++;
							}
							else if(ImageName[i][j+1].equals("End,Horizontal")){
								i=5;
								j=5;
							}
							else  {	
								i=6;
								j=6;
							}
						}
						
						
					else if(yön.equals("up")) {
						if(ImageName[i-1][j].equals("Pipe,Vertical")||
								ImageName[i-1][j].equals("PipeStatic,Vertical")) {
							yön = "up";
							i--;
						}
						if(ImageName[i-1][j].equals("Pipe,11")) {
							yön ="left";
							i--;
						}
						else if(ImageName[i-1][j].equals("Pipe,10")) {
							yön = "right";
							i--;
						}
						else if(ImageName[i-1][j].equals("End,Vertical")){
							i=5;
							j=5;
						}
						else  {	
							i=6;
							j=6;
						}
						}
						break;
					}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
					case "Pipe,10":{
						if(yön.equals("up")) {
							
							if(ImageName[i][j-1].equals("Pipe,Horizontal")||
									ImageName[i][j-1].equals("PipeStatic,Horizontal")) {//en yukarda olmucak
								yön = "left";
								j--;
							}
							else if(ImageName[i][j-1].equals("Pipe,01") ||ImageName[i][j-1].equals("PipeStatic,01")) {
								yön ="up";
								j--;
							}
							else if(ImageName[i][j-1].equals("Pipe,11")) {
								yön ="down";
								j--;
							}
							else  {	
								i=6;
								j=6;
							}
							
						}
						
						else if(yön.equals("right")) {
							
							if(ImageName[i+1][j].equals("Pipe,Vertical")||
									ImageName[i+1][j].equals("PipeStatic,Vertical")) {//en yukarda olmucak
								yön = "down";
								i++;
							}
							else if(ImageName[i+1][j].equals("Pipe,01") ||ImageName[i][j-1].equals("PipeStatic,01")) {
								yön ="right";
								i++;
							}
							else if(ImageName[i+1][j].equals("Pipe,00")) {
								yön = "left";
								i++;
							}
							else  {	
								i=6;
								j=6;
							}
							
						}
						break;
					}
	///////////////////////////////////////////////////////////////////////////////////////////////////////				
					case "Pipe,11":{
						if(yön.equals("left")) {
							
							if(ImageName[i+1][j].equals("Pipe,Vertical")||
								ImageName[i+1][j].equals("PipeStatic,Vertical")) {//en yukarda olmucak
								yön = "down";
								i++;
							}
							else if(ImageName[i+1][j].equals("Pipe,01") ||ImageName[i][j-1].equals("PipeStatic,01")) {
								yön ="right";
								i++;
							}
							else if(ImageName[i+1][j].equals("Pipe,00")) {
								yön = "left";
								i++;
							}
							else  {	
								i=6;
								j=6;
							}
							
							
						}
						
						else if(yön.equals("up")) {
							
								
								if(ImageName[i][j+1].equals("Pipe,Horizontal")||
										ImageName[i][j+1].equals("PipeStatic,Horizontal")) {//en yukarda olmucak
									yön = "right";
									j++;
								}
								else if(ImageName[i][j+1].equals("Pipe,10")) {
									yön = "down";
									j++;
								}
								else if(ImageName[i][j+1].equals("Pipe,00")) {
									yön = "up";
									j++;
								}
								else if(ImageName[i][j+1].equals("End,Horizontal")){
									i=5;
									j=5;
								}
								else  {	
									i=6;
									j=6;
								}
							}
						
						break;
					}
		//////////////////////////////////////////////////////////////////////////////////////////////////			
					case "PipeStatic,01":{
						if(yön.equals("down")) {
							if(ImageName[i][j+1].equals("Pipe,Horizontal")||
									ImageName[i][j+1].equals("PipeStatic,Horizontal")) {//en yukarda olmucak
								yön = "right";
								j++;
							}
							else if(ImageName[i][j+1].equals("Pipe,10")) {
								yön = "down";
								j++;
							}
							else if(ImageName[i][j+1].equals("Pipe,00")) {
								yön = "up";
								j++;
							}
							else if(ImageName[i][j+1].equals("End,Horizontal")){
								i=5;
								j=5;
							}
							else  {	
								i=6;
								j=6;
							}
						}
						
						
					else if(yön.equals("left")) {
						if(ImageName[i-1][j].equals("Pipe,Vertical")||
								ImageName[i-1][j].equals("PipeStatic,Vertical")) {//en yukarda olmucak
							yön = "up";
							i--;
						}
						if(ImageName[i-1][j].equals("Pipe,11")) {
							yön ="left";
							i--;
						}
						else if(ImageName[i-1][j].equals("Pipe,10")) {
							yön = "right";
							i--;
						}
						else if(ImageName[i-1][j].equals("End,Vertical")){
							i=5;
							j=5;
						}
						else  {	
							i=6;
							j=6;
						}
						}
						break;
					}
//////////////////////////////////////////////////////////////////////////////////////////////////////					
					case "PipeStatic,Horizontal":{
						if(yön.equals("right")) {
							
							if(ImageName[i][j+1].equals("Pipe,10")) {
								yön ="down";
								j++;
							}
							else if(ImageName[i][j+1].equals("Pipe,00")) {
								yön = "up";
								j++;
							}
							
							else if(ImageName[i][j+1].equals("Pipe,Horizontal")||
									ImageName[i][j+1].equals("PipeStatic,Horizontal")) {
								yön = "right";
								j++;
							}
							else if(ImageName[i][j+1].equals("End,Horizontal")){
								i=5 ;
								j=5;
							}
							
							else  {	
								i=6;
								j=6;
							}
						}
						
						
						else if(yön.equals("left")) {
							if(ImageName[i][j-1].equals("Pipe,11")) {
								yön ="down";
								j--;
							}
							else if(ImageName[i][j-1].equals("Pipe,01") ||ImageName[i][j-1].equals("PipeStatic,01")) {
								yön = "up";
								j--;
							}
							else if(ImageName[i][j-1].equals("Pipe,Horizontal")||
									ImageName[i][j-1].equals("PipeStatic,Horizontal")) {
								yön = "leftt";
								j--;
							}
							
							else  {	
								i=6;
								j=6;
							}
						}
						break;
					}
//////////////////////////////////////////////////////////////////////////////////////////////////////////					
					case "PipeStatic,Vertical":{
						
						if(yön.equals("up")) {
							if(ImageName[i-1][j].equals("Pipe,10")) {
								yön ="left";
								i--;
							}
							else if(ImageName[i-1][j].equals("Pipe,11")) {
								yön = "right";
								i--;
							}
							
							else if(ImageName[i-1][j].equals("Pipe,Vertical")||
									ImageName[i-1][j].equals("PipeStatic,Vertical")) {
								yön = "up";
								i--;
							}
							else if(ImageName[i-1][j].equals("End,Vertical")){
								i=5 ;
								j=5;
							}
						}
										
						else if(yön.equals("down")) {
							if(ImageName[i+1][j].equals("Pipe,00")) {
								yön ="left";
								i++;
							}
							else if(ImageName[i+1][j].equals("Pipe,01") ||ImageName[i+1][j].equals("PipeStatic,01")) {
								yön = "right";
								i++;
							}
							else if(ImageName[i+1][j].equals("Pipe,Vertical")||
									ImageName[i+1][j].equals("PipeStatic,Vertical")) {
								yön = "down";
								i++;
							}
							else  {	
								i=6;
								j=6;
							}
						} 
						break;
					}
	/////////////////////////////////////////////////////////////////////////////////////////////////////			
					case "End,Horizontal":{
						i=5;
						j=5;
						break;
					}
	////////////////////////////////////////////////////////////////////////////////////////////////////				
					case "End,Vertical":{
						i=5;
						j=5;
						break;
					}
					
					}//switch
				}//for of j
				
			}// for of i
			if(i==5 && j==5)
				return true;
				
				else 
					return false;
		
	
	
}
	

	//The main method
	public static void main(String[] args) {
		
		launch(args);
		
		
	}

}
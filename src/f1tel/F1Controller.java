package f1tel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import packet.data.Packet;
import f1tel.F1UDPclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class F1Controller implements Initializable {
	
	private Pane test1;
	private Pane test2;
	private Pane test3;
	private Pane test4;
	private Pane test5;
	private Pane packError = errorPane();
	private Boolean telBool1 = false;
	private Boolean telBool2 = false;
	private Boolean telBool3 = false;
	private Boolean telBool4 = false;
	private Boolean telBool5 = false;
	
	private List<CanvasLineChart> charts = new ArrayList<>();
	private List<CanvasLineChart> speedChart = new ArrayList<>();
	private List<CanvasLineChart> throttleChart = new ArrayList<>();
	private List<CanvasLineChart> engineChart = new ArrayList<>();
	private List<CanvasLineChart> tempChart = new ArrayList<>();
	private List<CanvasLineChart> chart4 = new ArrayList<>();

    private double t = 0.0;
    private double t1 = 0.0;

    @FXML
    private Label label1;
    
	@FXML
	private Label label;
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
	private void button1Action(ActionEvent event) {
		if (F1UDPclient.packet == null) {
			mainPane.setCenter(packError);
		}
		else {
			if (!telBool1) {
				test1 = createSpeedData();
				telBool1 = true;
			}
			System.out.println(F1UDPclient.partPack.getParticipants().get(0).getName());
			test1.setStyle("-fx-border-color: black");
			mainPane.setCenter(test1);
		}
	}

	@FXML
	private void button2Action(ActionEvent event) {
		//if (F1UDPclient.packet == null) {
		//	mainPane.setCenter(packError);
		//}
		//else {
			if (!telBool2) {
				test2 = createThrottleData();
				telBool2 = true;
			}
			mainPane.setCenter(test2);
		//}
	}

	@FXML
	private void button3Action(ActionEvent event) {
		//if (F1UDPclient.packet == null) {
		//	mainPane.setCenter(packError);
		//}
		//else {
			if (!telBool3) {
				test3 = createEngineData();
				telBool3 = true;
			}
			mainPane.setCenter(test3);
		//}
	}

	@FXML
	private void button4Action(ActionEvent event) {
		//if (F1UDPclient.packet == null) {
		//	mainPane.setCenter(packError);
		//}
		//else {
			if (!telBool4) {
				test4 = createTemperatureData();
				telBool4 = true;
			}
			mainPane.setCenter(test4);
		//}
	}

	@FXML
	private void button5Action(ActionEvent event) throws FileNotFoundException {
		//if (F1UDPclient.packet == null) {
		//	mainPane.setCenter(packError);
		//}
		//else {
			if (!telBool5) {
				test5 = createConditionsData();
				telBool5 = true;
			}
			mainPane.setCenter(test5);
		//}
	}
	
	private Pane errorPane() {
		Pane pane = new Pane();
		Label error = new Label();
		error.setText("No packets being recieved...");
		error.setFont(new Font("Arial", 64));
        error.setStyle("-fx-font-weight: bold");
        error.setPadding(new Insets(30, 30, 30, 30));
        error.setAlignment(Pos.CENTER);
		pane.getChildren().add(error);
		return pane;
	}
	
	
	private final TableView<Driver> table = new TableView<>();
    private final ObservableList<Driver> data = FXCollections.observableArrayList();
    
    public static class Driver {
    	 
        private final SimpleStringProperty pos;
        private final SimpleStringProperty name;
        private final SimpleStringProperty time;
 
        private Driver(Integer position, String driverName, String lapTime) {
        	
            this.pos = new SimpleStringProperty(Integer.toString(position));
            this.name = new SimpleStringProperty(driverName);
            this.time = new SimpleStringProperty(lapTime);
        }
 
        public String getPos() {
            return pos.get();
        }
 
        public void setPos(String position) {
            pos.set(position);
        }
        
        public String getName() {
            return name.get();
        }
 
        public void setName(String fName) {
            name.set(fName);
        }
 
        public String getTime() {
            return time.get();
        }
 
        public void setTime(String fTime) {
            time.set(fTime);
        }
    }
	
	private GridPane createSpeedData() {		
		GridPane rootGrid = new GridPane();
		GridPane graphGrid = new GridPane();
		Pane graphPane = new Pane();
<<<<<<< HEAD
		VBox vertAxis = axis("360", "300", "240", "180", "120", "60", "0");
		VBox lapInfo = new VBox();
		VBox gears = new VBox();
		Canvas canvas = new Canvas(1200, 600);
        GraphicsContext g = canvas.getGraphicsContext2D();
		
		HBox horBox = new HBox();
		HBox horBox2 = new HBox();
		Label speedAxis = new Label();
        Label speedLabel = new Label();
        Label currentLap = new Label();
        Label position = new Label();
        Label s1 = new Label();
        Label s2 = new Label();
        Label s3 = new Label();
        Label gearTop = new Label();
        Label gearBottom = new Label();
        Label lastLap = new Label();
        Label title = new Label();
        Group labelHolder = new Group(speedAxis);
        
        title.setText("Speed and Braking Graph");
        title.setFont(new Font("Arial", 32));
        title.setStyle("-fx-font-weight: bold");
        title.setPadding(new Insets(10, 10, 10, 0));
		
        position.setPadding(new Insets(5, 5, 5, 20));
        graphGrid.setPadding(new Insets(5, 25, 5, 5));
        speedLabel.setPadding(new Insets(5, 5, 5, 20));
        currentLap.setPadding(new Insets(5, 5, 5, 20));
        gearTop.setPadding(new Insets(5, 5, 5, 20));
        gearBottom.setPadding(new Insets(5, 5, 5, 20));
        s1.setPadding(new Insets(5, 5, 5, 20));
        s2.setPadding(new Insets(5, 5, 5, 20));
        s3.setPadding(new Insets(5, 5, 5, 20));
        lastLap.setPadding(new Insets(5, 5, 5, 20));
        
        gearTop.setFont(new Font("Arial", 16));
        gearBottom.setFont(new Font("Arial", 48));
        position.setFont(new Font("Arial", 36));
        speedLabel.setFont(new Font("Arial", 36));
    	currentLap.setFont(new Font("Arial", 36));
    	lastLap.setFont(new Font("Arial", 36));
    	s1.setFont(new Font("Arial", 36));
    	s2.setFont(new Font("Arial", 36));
    	position.setMinWidth(400);
    	speedLabel.setMinWidth(400);
    	currentLap.setMinWidth(400);
    	s1.setMinWidth(400);
    	s2.setMinWidth(400);
    	s3.setMinWidth(200);
    	gearTop.setText("Selected Gear:");
		
        rootGrid.setMinSize(600, 500);
=======
		rootGrid.setMinSize(600, 500);
>>>>>>> parent of ae469a6 (New Tabs)
		rootGrid.setPadding(new Insets(10, 10, 10, 10)); 
		rootGrid.gridLinesVisibleProperty().set(true);
		HBox horBox = new HBox();
        Label speedLabel = new Label();
        Label currentLap = new Label();
        Label lastLap = new Label();
        Label title = new Label();
        title.setText("Speed and Braking Graph");
        title.setFont(new Font("Arial", 24));
		
        speedLabel.setPadding(new Insets(5, 5, 5, 20));
        currentLap.setPadding(new Insets(5, 5, 5, 20));
        lastLap.setPadding(new Insets(5, 5, 5, 20));
		
		VBox vertBox = new VBox();
		Label speedAxis = new Label();
        Label label = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        Label label5 = new Label();
        Label label6 = new Label();
        
        label.setPadding(new Insets(0, 5, 75, 5));
        label1.setPadding(new Insets(0, 5, 83, 5));
        label2.setPadding(new Insets(0, 5, 83, 5));
        label3.setPadding(new Insets(0, 5, 83, 5));
        label4.setPadding(new Insets(0, 5, 83, 5));
        label5.setPadding(new Insets(0, 5, 83, 5));
        label6.setPadding(new Insets(0, 5, 5, 5));
    
        label.setText("300");
        label1.setText("Test");
        label2.setText("Test");
        label3.setText("Test");
        label4.setText("Test");
        label5.setText("Test");
        label6.setText("Test");
        speedAxis.setText("Speed mph");
        speedAxis.setRotate(-90);
        
        vertBox.getChildren().addAll(label, label1, label2, label3, label4, label5, label6);
        
        vertBox.setPadding(new Insets(0, 5, 5, 5));

        
        horBox.getChildren().addAll(speedLabel, currentLap, lastLap);
        horBox.setPadding(new Insets(0, 5, 5, 5));
		table.setEditable(false);
		table.setItems(data);
		
		TableColumn colPos = new TableColumn("Position");
		colPos.setCellValueFactory(new PropertyValueFactory<>("pos"));
		
		TableColumn name = new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		
	    TableColumn time = new TableColumn("Time");
	    time.setCellValueFactory(new PropertyValueFactory<>("time"));
	    table.getColumns().addAll(colPos, name, time);
	    
	    for (int i=0;i < 20; i++) {
	    	data.add(new Driver(1, "Placeholder", "Placeholder"));
	    }
<<<<<<< HEAD
        
        if (F1UDPclient.partPack.getParticipants().size() == 20) {
        	speedChart.add(new CanvasLineChart(g, Color.GREEN, () -> 1-Double.valueOf(F1UDPclient.telPack.getCarTelemetryData().get(19).getSpeed())/360));
        } else {
        	speedChart.add(new CanvasLineChart(g, Color.GREEN, () -> 1-Double.valueOf(F1UDPclient.telPack.getCarTelemetryData().get(0).getSpeed())/360));
        }
=======
>>>>>>> parent of ae469a6 (New Tabs)

        Canvas canvas = new Canvas(1200, 600);
        GraphicsContext g = canvas.getGraphicsContext2D();
        
        speedChart.add(new CanvasLineChart(g, Color.GREEN, () -> 1-Double.valueOf(F1UDPclient.telPack.getCarTelemetryData().get(0).getSpeed())/300));
        speedChart.add(new CanvasLineChart(g, Color.RED, () -> 1-Double.valueOf(F1UDPclient.telPack.getCarTelemetryData().get(0).getBrake())/100));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	speedLabel.setText("Speed: " + Integer.toString(F1UDPclient.telPack.getCarTelemetryData().get(0).getSpeed())+" mph");
            	currentLap.setText("Current Lap: " + Float.toString(F1UDPclient.lapPack.getDataList().get(0).getCurrentLapTime()));
            	lastLap.setText("Last Lap: " + Float.toString(F1UDPclient.lapPack.getDataList().get(0).getLastLapTime()));
            	speedLabel.setFont(new Font("Arial", 36));
            	currentLap.setFont(new Font("Arial", 36));
            	lastLap.setFont(new Font("Arial", 36));
            	
            	if (F1UDPclient.partPack.getParticipants().size() == 20) {
	            	for (int i=0;i < 20; i++) {
	        	    	data.set(i, new Driver(F1UDPclient.lapPack.getDataList().get(i).getPosition(), F1UDPclient.partPack.getParticipants().get(i).getName(), Float.toString(F1UDPclient.lapPack.getDataList().get(i).getCurrentLapTime())));
	        	    }
            	} else {
            		data.set(0, new Driver(F1UDPclient.lapPack.getDataList().get(0).getPosition(), F1UDPclient.partPack.getParticipants().get(0).getName(), Float.toString(F1UDPclient.lapPack.getDataList().get(0).getCurrentLapTime())));
            	}
            	
            	t1 += 0.016;
                if (t1 > 0.2) {
                    g.clearRect(0, 0, 1200, 600);

                    g.setStroke(Color.BLACK);

                    for (int y = 0; y <= 600; y += 100) {
                        g.strokeLine(0, y, 1200, y);
                    }

                    speedChart.forEach(CanvasLineChart::update);

                    t1 = 0.0;
                }
            }
        };
        timer.start();
        
        graphPane.getChildren().add(canvas);
        graphPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        
        table.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        rootGrid.setStyle("-fx-border-color: black");
        graphGrid.add(title, 2, 0);
        graphGrid.add(speedAxis, 0, 1);
        graphGrid.add(vertBox, 1, 1);
        graphGrid.add(graphPane, 2, 1);
        rootGrid.add(graphGrid, 0, 0);
        rootGrid.add(table, 1, 0);
        rootGrid.add(horBox, 0, 1);
        
        return rootGrid;
	}
	
	private Pane createThrottleData() {
		GridPane rootGrid = new GridPane();
		GridPane graphGrid1 = new GridPane();
		
		Pane graphPane1 = new Pane();
		
		rootGrid.setMinSize(600, 500);
		rootGrid.setPadding(new Insets(50, 50, 50, 50)); 
		
        Label title1 = new Label();
        
        title1.setText("Speed and Braking Graph");
        title1.setFont(new Font("Arial", 24));
        title1.setPadding(new Insets(0, 25, 25, 25));

        VBox axisBox1 = axis("300", "250", "200", "150", "100", "50", "0");
        Label speedAxis1 = new Label();
        
        speedAxis1.setText("Speed mph");
        speedAxis1.setRotate(-90);

        Canvas canvas1 = new Canvas(1200, 600);
        
        GraphicsContext g1 = canvas1.getGraphicsContext2D();
        
        throttleChart.add(new CanvasLineChart(g1, Color.GREEN, new RandomDataSource()));
        throttleChart.add(new CanvasLineChart(g1, Color.RED, new RandomDataSource()));
        

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	
            	t1 += 0.016;
                if (t1 > 0.2) {
                    g1.clearRect(0, 0, 1200, 600);

                    g1.setStroke(Color.BLACK);

                    for (int y = 0; y <= 600; y += 100) {
                        g1.strokeLine(0, y, 1200, y);
                    }

                    throttleChart.forEach(CanvasLineChart::update);

                    t1 = 0.0;
                }
            }
        };
        timer.start();
        
        graphPane1.getChildren().add(canvas1);
        graphPane1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        
        graphGrid1.add(title1, 2, 0);
        graphGrid1.add(speedAxis1, 0, 1);
        graphGrid1.add(axisBox1, 1, 1);
        graphGrid1.add(graphPane1, 2, 1);
        
        rootGrid.add(graphGrid1, 0, 0);
        
        return rootGrid;
	}
	
	private Pane createEngineData() {
		GridPane rootGrid = new GridPane();
		GridPane graphGrid1 = new GridPane();
		
		Pane graphPane1 = new Pane();
		
		rootGrid.setMinSize(600, 500);
		rootGrid.setPadding(new Insets(50, 50, 50, 50)); 
		
        Label title1 = new Label();
        
        title1.setText("Speed and Braking Graph");
        title1.setFont(new Font("Arial", 24));
        title1.setPadding(new Insets(0, 25, 25, 25));

        VBox axisBox1 = axis("300", "250", "200", "150", "100", "50", "0");
        Label speedAxis1 = new Label();
        
        speedAxis1.setText("Speed mph");
        speedAxis1.setRotate(-90);

        Canvas canvas1 = new Canvas(1200, 600);
        
        GraphicsContext g1 = canvas1.getGraphicsContext2D();
        
        engineChart.add(new CanvasLineChart(g1, Color.GREEN, new RandomDataSource()));
        engineChart.add(new CanvasLineChart(g1, Color.RED, new RandomDataSource()));
        

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	
            	t1 += 0.016;
                if (t1 > 0.2) {
                    g1.clearRect(0, 0, 1200, 600);

                    g1.setStroke(Color.BLACK);

                    for (int y = 0; y <= 600; y += 100) {
                        g1.strokeLine(0, y, 1200, y);
                    }

                    engineChart.forEach(CanvasLineChart::update);

                    t1 = 0.0;
                }
            }
        };
        timer.start();
        
        graphPane1.getChildren().add(canvas1);
        graphPane1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        
        graphGrid1.add(title1, 2, 0);
        graphGrid1.add(speedAxis1, 0, 1);
        graphGrid1.add(axisBox1, 1, 1);
        graphGrid1.add(graphPane1, 2, 1);
        
        rootGrid.add(graphGrid1, 0, 0);
        
        return rootGrid;
	}

	
	private Pane createTemperatureData() {
		GridPane rootGrid = new GridPane();
		GridPane graphGrid1 = new GridPane();
		
		Pane graphPane1 = new Pane();
		
		rootGrid.setMinSize(600, 500);
		rootGrid.setPadding(new Insets(50, 50, 50, 50)); 
		
        Label title1 = new Label();
        
        title1.setText("Speed and Braking Graph");
        title1.setFont(new Font("Arial", 24));
        title1.setPadding(new Insets(0, 25, 25, 25));

        VBox axisBox1 = axis("300", "250", "200", "150", "100", "50", "0");
        Label speedAxis1 = new Label();
        
<<<<<<< HEAD
        key.setFont(new Font("Arial", 18));
        key.setText(" Engine: Cyan\n FL Brake: Blue\n FR Brake: Red\n RL Brake: Orange\n RR Brake: Green");
=======
        speedAxis1.setText("Speed mph");
        speedAxis1.setRotate(-90);

        Canvas canvas1 = new Canvas(1200, 600);
>>>>>>> parent of ae469a6 (New Tabs)
        
        GraphicsContext g1 = canvas1.getGraphicsContext2D();
        
        tempChart.add(new CanvasLineChart(g1, Color.GREEN, new RandomDataSource()));
        tempChart.add(new CanvasLineChart(g1, Color.RED, new RandomDataSource()));
        

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	
            	t1 += 0.016;
                if (t1 > 0.2) {
                    g1.clearRect(0, 0, 1200, 600);

                    g1.setStroke(Color.BLACK);

                    for (int y = 0; y <= 600; y += 100) {
                        g1.strokeLine(0, y, 1200, y);
                    }

                    tempChart.forEach(CanvasLineChart::update);

                    t1 = 0.0;
                }
            }
        };
        timer.start();
        
        graphPane1.getChildren().add(canvas1);
        graphPane1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        
        graphGrid1.add(title1, 2, 0);
        graphGrid1.add(speedAxis1, 0, 1);
        graphGrid1.add(axisBox1, 1, 1);
        graphGrid1.add(graphPane1, 2, 1);
        
        rootGrid.add(graphGrid1, 0, 0);
        
        return rootGrid;
	}
	
	private Pane createConditionsData() throws FileNotFoundException {
		
		GridPane tyresPane = new GridPane();
		GridPane mainPane = new GridPane();
		Label compoundTitle = new Label();
		Label wearFL = new Label();
		Label wearFR = new Label();
		Label wearBL = new Label();
		Label wearBR = new Label();
		Label weather = new Label();
		Label trackTemp = new Label();
		Label airTemp = new Label();
		
		VBox tyreFL = new VBox();
		VBox tyreFR = new VBox();
		VBox tyreBL = new VBox();
		VBox tyreBR = new VBox();
		VBox conditions = new VBox();
		
		ImageView compound = new ImageView();
		ImageView compound1 = new ImageView();
		ImageView compound2 = new ImageView();
		ImageView compound3 = new ImageView();
		Image soft = new Image(new FileInputStream("C:\\Users\\Zake\\eclipse-workspace\\Formula1Telemetry\\src\\f1tel\\Softs.png"));
		Image medium = new Image(new FileInputStream("C:\\Users\\Zake\\eclipse-workspace\\Formula1Telemetry\\src\\f1tel\\Mediums.png"));
		Image hard = new Image(new FileInputStream("C:\\Users\\Zake\\eclipse-workspace\\Formula1Telemetry\\src\\f1tel\\Hards.png"));
		Image inter = new Image(new FileInputStream("C:\\Users\\Zake\\eclipse-workspace\\Formula1Telemetry\\src\\f1tel\\Intermediates.png"));
		Image wet = new Image(new FileInputStream("C:\\Users\\Zake\\eclipse-workspace\\Formula1Telemetry\\src\\f1tel\\Wets.png"));
		
		weather.setFont(new Font("Arial", 36));
		trackTemp.setFont(new Font("Arial", 36));
		airTemp.setFont(new Font("Arial", 36));
		compoundTitle.setFont(new Font("Arial", 36));
		wearFL.setPadding(new Insets(5, 5, 0, 5));
		wearFR.setPadding(new Insets(5, 5, 0, 5));
		wearBL.setPadding(new Insets(5, 5, 0, 5));
		wearBR.setPadding(new Insets(5, 5, 0, 5));
		wearFL.setFont(new Font("Arial", 24));
		wearFR.setFont(new Font("Arial", 24));
		wearBL.setFont(new Font("Arial", 24));
		wearBR.setFont(new Font("Arial", 24));
		
		conditions.setPadding(new Insets(25, 25, 25, 25));
		mainPane.setPadding(new Insets(5, 25, 25, 25));
		tyresPane.gridLinesVisibleProperty().set(true);
		tyreFL.setPadding(new Insets(10, 10, 10, 10));
		tyreFR.setPadding(new Insets(10, 10, 10, 10));
		tyreBL.setPadding(new Insets(10, 10, 10, 10));
		tyreBR.setPadding(new Insets(10, 10, 10, 10));
		
		compound.setFitHeight(350);
		compound.setPreserveRatio(true);
		compound1.setFitHeight(350);
		compound1.setPreserveRatio(true);
		compound2.setFitHeight(350);
		compound2.setPreserveRatio(true);
		compound3.setFitHeight(350);
		compound3.setPreserveRatio(true);
		// 16 C5 17 C4 18 C3 19 C2 20 C1 Hardest 7 Inters 8 Wets
		
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (F1UDPclient.partPack.getParticipants().size() == 20) {
            		if (F1UDPclient.statusPack.getCarStatuses().get(19).getTyreCompound() == 16) {
            			compound.setImage(soft);
            			compound1.setImage(soft);
            			compound2.setImage(soft);
            			compound3.setImage(soft);
            			compoundTitle.setText("Tyre Compound: C5 - Softs");
            		} else if (F1UDPclient.statusPack.getCarStatuses().get(19).getTyreCompound() == 17) {
            			compound.setImage(soft);
            			compound1.setImage(soft);
            			compound2.setImage(soft);
            			compound3.setImage(soft);
            			compoundTitle.setText("Tyre Compound: C4 - Softs");
            		} else if (F1UDPclient.statusPack.getCarStatuses().get(19).getTyreCompound() == 18) {
            			compound.setImage(medium);
            			compound1.setImage(medium);
            			compound2.setImage(medium);
            			compound3.setImage(medium);
            			compoundTitle.setText("Tyre Compound: C3 - Mediums");
            		} else if (F1UDPclient.statusPack.getCarStatuses().get(19).getTyreCompound() == 19) {
            			compound.setImage(hard);
            			compound1.setImage(hard);
            			compound2.setImage(hard);
            			compound3.setImage(hard);
            			compoundTitle.setText("Tyre Compound: C2 - Hards");
            		} else if (F1UDPclient.statusPack.getCarStatuses().get(19).getTyreCompound() == 20) {
            			compound.setImage(hard);
            			compound1.setImage(hard);
            			compound2.setImage(hard);
            			compound3.setImage(hard);
            			compoundTitle.setText("Tyre Compound: C1 - Hards");
            		} else if (F1UDPclient.statusPack.getCarStatuses().get(19).getTyreCompound() == 7) {
            			compound.setImage(inter);
            			compound1.setImage(inter);
            			compound2.setImage(inter);
            			compound3.setImage(inter);
            			compoundTitle.setText("Tyre Compound: Intermediates - Wets");
            		} else {
            			compound.setImage(wet);
            			compound1.setImage(wet);
            			compound2.setImage(wet);
            			compound3.setImage(wet);
            			compoundTitle.setText("Tyre Compound: Wets");
            		}
            		
            		weather.setText("Weather Condition: " + F1UDPclient.sessPack.getWeather());
            		trackTemp.setText("\nTrack Temperature: " + F1UDPclient.sessPack.getTrackTemp() + " \u2103");
            		airTemp.setText("\nAir Temperature: " + F1UDPclient.sessPack.getAirTemp() + " \u2103");
            		wearFL.setText("Tyre Wear: " + F1UDPclient.statusPack.getCarStatuses().get(19).getTyresWear().getFrontLeft() + "%");
            		wearFR.setText("Tyre Wear: " + F1UDPclient.statusPack.getCarStatuses().get(19).getTyresWear().getFrontRight() + "%");
            		wearBL.setText("Tyre Wear: " + F1UDPclient.statusPack.getCarStatuses().get(19).getTyresWear().getRearLeft() + "%");
            		wearBR.setText("Tyre Wear: " + F1UDPclient.statusPack.getCarStatuses().get(19).getTyresWear().getRearRight() + "%");
            	} else {
            		if (F1UDPclient.statusPack.getCarStatuses().get(0).getTyreCompound() == 16) {
            			compound.setImage(soft);
            			compound1.setImage(soft);
            			compound2.setImage(soft);
            			compound3.setImage(soft);
            			compoundTitle.setText("Tyre Compound: C5 - Softs");
            		} else if (F1UDPclient.statusPack.getCarStatuses().get(0).getTyreCompound() == 17) {
            			compound.setImage(soft);
            			compound1.setImage(soft);
            			compound2.setImage(soft);
            			compound3.setImage(soft);
            			compoundTitle.setText("Tyre Compound: C4 - Softs");
            		} else if (F1UDPclient.statusPack.getCarStatuses().get(0).getTyreCompound() == 18) {
            			compound.setImage(medium);
            			compound1.setImage(medium);
            			compound2.setImage(medium);
            			compound3.setImage(medium);
            			compoundTitle.setText("Tyre Compound: C3 - Mediums");
            		} else if (F1UDPclient.statusPack.getCarStatuses().get(0).getTyreCompound() == 19) {
            			compound.setImage(hard);
            			compound1.setImage(hard);
            			compound2.setImage(hard);
            			compound3.setImage(hard);
            			compoundTitle.setText("Tyre Compound: C2 - Hards");
            		} else if (F1UDPclient.statusPack.getCarStatuses().get(0).getTyreCompound() == 20) {
            			compound.setImage(hard);
            			compound1.setImage(hard);
            			compound2.setImage(hard);
            			compound3.setImage(hard);
            			compoundTitle.setText("Tyre Compound: C1 - Hards");
            		} else if (F1UDPclient.statusPack.getCarStatuses().get(0).getTyreCompound() == 7) {
            			compound.setImage(inter);
            			compound1.setImage(inter);
            			compound2.setImage(inter);
            			compound3.setImage(inter);
            			compoundTitle.setText("Tyre Compound: Intermediates - Wets");
            		} else {
            			compound.setImage(wet);
            			compound1.setImage(wet);
            			compound2.setImage(wet);
            			compound3.setImage(wet);
            			compoundTitle.setText("Tyre Compound: Wets");
            		}
            		
            		trackTemp.setText("Track Temperature: " + F1UDPclient.sessPack.getTrackTemp() + " \u2103");
            		airTemp.setText("\nAir Temperature: " + F1UDPclient.sessPack.getAirTemp() + " \u2103");
            		weather.setText("\nWeather Condition: " + F1UDPclient.sessPack.getWeather());
            		wearFL.setText("Tyre Wear: " + F1UDPclient.statusPack.getCarStatuses().get(0).getTyresWear().getFrontLeft() + "%");
            		wearFR.setText("Tyre Wear: " + F1UDPclient.statusPack.getCarStatuses().get(0).getTyresWear().getFrontRight() + "%");
            		wearBL.setText("Tyre Wear: " + F1UDPclient.statusPack.getCarStatuses().get(0).getTyresWear().getRearLeft() + "%");
            		wearBR.setText("Tyre Wear: " + F1UDPclient.statusPack.getCarStatuses().get(0).getTyresWear().getRearRight() + "%");
            	} 
            }  
        };
        
        conditions.getChildren().addAll(compoundTitle, weather, trackTemp, airTemp);
        
        tyreFL.getChildren().addAll(compound, wearFL);
        tyreFR.getChildren().addAll(compound1, wearFR);
        tyreBL.getChildren().addAll(compound2, wearBL);
        tyreBR.getChildren().addAll(compound3, wearBR);
        
        tyresPane.add(tyreFL, 0, 0);
        tyresPane.add(tyreFR, 1, 0);
        tyresPane.add(tyreBL, 0, 1);
        tyresPane.add(tyreBR, 1, 1);
        mainPane.add(conditions, 1, 0);
        mainPane.add(tyresPane, 0, 0);
        
        timer.start();
		
		return mainPane;
	}
	
<<<<<<< HEAD
	private Pane createDamageData() {
		Pane root = new Pane();
		Label title = new Label();
		Label damages = new Label();
		VBox vertDamage = new VBox();
		
		vertDamage.setPadding(new Insets(50, 50, 50 ,50));
		title.setStyle("-fx-font-weight: bold");
		title.setFont(new Font("Arial", 48));
		title.setText("Percentage Damage to Vehicle Components");
		damages.setFont(new Font("Arial", 42));
		damages.setAlignment(Pos.CENTER);
		
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (F1UDPclient.partPack.getParticipants().size() == 20) {
            		damages.setText("\nFront Left Tyre: " + F1UDPclient.statusPack.getCarStatuses().get(19).getTyresDamage().getFrontLeft() + "%\n"
            				+ "Front Right Tyre: " + F1UDPclient.statusPack.getCarStatuses().get(19).getTyresDamage().getFrontRight() + "%\n"
            				+ "Rear Left Tyre: " + F1UDPclient.statusPack.getCarStatuses().get(19).getTyresDamage().getRearLeft() + "%\n"
            				+ "Rear Right Tyre: " + F1UDPclient.statusPack.getCarStatuses().get(19).getTyresDamage().getRearRight() + "%\n"
            				+ "Front Left Wing: " + F1UDPclient.statusPack.getCarStatuses().get(19).getFrontLeftWingDamage() + "%\n"
            				+ "Front Right Wing: " + F1UDPclient.statusPack.getCarStatuses().get(19).getFrontRightWingDamage() + "%\n"
            				+ "Rear Wing: " + F1UDPclient.statusPack.getCarStatuses().get(19).getRearWingDamage() + "%\n"
            				+ "Engine: " + F1UDPclient.statusPack.getCarStatuses().get(19).getEngineDamage() + "%\n"
            				+ "Gear Box: " + F1UDPclient.statusPack.getCarStatuses().get(19).getGearBoxDamage() + "%\n"
            				+ "Exhaust: " + F1UDPclient.statusPack.getCarStatuses().get(19).getExhaustDamage() + "%\n");
            		
            	} else {
            		damages.setText("\nFront Left Tyre: " + F1UDPclient.statusPack.getCarStatuses().get(0).getTyresDamage().getFrontLeft() + "%\n"
            				+ "Front Right Tyre: " + F1UDPclient.statusPack.getCarStatuses().get(0).getTyresDamage().getFrontRight() + "%\n"
            				+ "Rear Left Tyre: " + F1UDPclient.statusPack.getCarStatuses().get(0).getTyresDamage().getRearLeft() + "%\n"
            				+ "Rear Right Tyre: " + F1UDPclient.statusPack.getCarStatuses().get(0).getTyresDamage().getRearRight() + "%\n"
            				+ "Front Left Wing: " + F1UDPclient.statusPack.getCarStatuses().get(0).getFrontLeftWingDamage() + "%\n"
            				+ "Front Right Wing: " + F1UDPclient.statusPack.getCarStatuses().get(0).getFrontRightWingDamage() + "%\n"
            				+ "Rear Wing: " + F1UDPclient.statusPack.getCarStatuses().get(0).getRearWingDamage() + "%\n"
            				+ "Engine: " + F1UDPclient.statusPack.getCarStatuses().get(0).getEngineDamage() + "%\n"
            				+ "Gear Box: " + F1UDPclient.statusPack.getCarStatuses().get(0).getGearBoxDamage() + "%\n"
            				+ "Exhaust: " + F1UDPclient.statusPack.getCarStatuses().get(0).getExhaustDamage() + "%\n");
            	} 
=======
	private Pane createGraphContent() {
		GridPane root = new GridPane();
		Pane graphPane = new Pane();
		root.setMinSize(600, 500);
		root.setPadding(new Insets(1, 1, 1, 1)); 
		//root.gridLinesVisibleProperty().set(true);
        //root.setMaxSize(1200, 900);
        
        VBox box = new VBox();
        Label label = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        Label label5 = new Label();
        Label label6 = new Label();
        
        label.setPadding(new Insets(0, 5, 75, 5));
        label1.setPadding(new Insets(0, 5, 83, 5));
        label2.setPadding(new Insets(0, 5, 83, 5));
        label3.setPadding(new Insets(0, 5, 83, 5));
        label4.setPadding(new Insets(0, 5, 83, 5));
        label5.setPadding(new Insets(0, 5, 83, 5));
        label6.setPadding(new Insets(0, 5, 83, 5));
    
        label.setText("300");
        label1.setText("Test");
        label2.setText("Test");
        label3.setText("Test");
        label4.setText("Test");
        label5.setText("Test");
        label6.setText("Test");
        
        box.getChildren().addAll(label, label1, label2, label3, label4, label5, label6);
        
        box.setPadding(new Insets(0, 5, 5, 5));

        Canvas canvas = new Canvas(1200, 600);
        GraphicsContext g = canvas.getGraphicsContext2D();
        charts = new ArrayList<>();

        charts.add(new CanvasLineChart(g, Color.RED, new RandomDataSource()));
        //charts.add(new CanvasLineChart(g, Color.GREEN, new RandomDataSource()));
        //charts.add(new CanvasLineChart(g, Color.BLUE, () -> Math.random() * 0.3));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                t += 0.016;

                if (t > 0.2) {
                    g.clearRect(0, 0, 1200, 600);

                    g.setStroke(Color.BLACK);

                    for (int y = 0; y <= 600; y += 100) {
                        g.strokeLine(0, y, 1200, y);
                    }

                    charts.forEach(CanvasLineChart::update);

                    t = 0.0;
                }
>>>>>>> parent of ae469a6 (New Tabs)
            }
        };
        timer.start();
<<<<<<< HEAD
        vertDamage.getChildren().addAll(title, damages);
		root.getChildren().add(vertDamage);
		return root;
	}
	
	private Pane createPacketInfo() {
		Pane root = new Pane();
		Label title = new Label();
		Label packetInfo = new Label();
		VBox vertPack = new VBox();
		
		vertPack.setPadding(new Insets(10, 10, 10 ,10));
		title.setStyle("-fx-font-weight: bold");
		title.setFont(new Font("Arial", 36));
		title.setText("Packet Information Recieved:\n");
		packetInfo.setFont(new Font("Arial", 12));
		packetInfo.setAlignment(Pos.CENTER);
		packetInfo.setMaxWidth(1550);
		packetInfo.wrapTextProperty().set(true);
		
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	packetInfo.setText(F1UDPclient.packet.toJSON());
            }
            
        };
		
        timer.start();
        vertPack.getChildren().addAll(title, packetInfo);
		root.getChildren().add(vertPack);
		return root;
	}
=======
        
        graphPane.getChildren().add(canvas);
        graphPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        
        root.setAlignment(Pos.TOP_CENTER);
        root.add(box, 0, 0);
        root.add(graphPane,1,0);

        return root;
    }
>>>>>>> parent of ae469a6 (New Tabs)
	
	private VBox axis(String string1, String string2, String string3, String string4, String string5, String string6, String string7) {
		VBox vertBox = new VBox();
		
        Label label = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        Label label5 = new Label();
        Label label6 = new Label();
        
        label.setPadding(new Insets(0, 5, 75, 5));
        label1.setPadding(new Insets(0, 5, 83, 5));
        label2.setPadding(new Insets(0, 5, 83, 5));
        label3.setPadding(new Insets(0, 5, 83, 5));
        label4.setPadding(new Insets(0, 5, 83, 5));
        label5.setPadding(new Insets(0, 5, 83, 5));
        label6.setPadding(new Insets(0, 5, 5, 5));
    
        label.setText(string1);
        label1.setText(string2);
        label2.setText(string3);
        label3.setText(string4);
        label4.setText(string5);
        label5.setText(string6);
        label6.setText(string7);
        
        vertBox.getChildren().addAll(label, label1, label2, label3, label4, label5, label6);
        vertBox.setPadding(new Insets(0, 5, 5, 5));
        
		return vertBox;
	}
	
	private static class CanvasLineChart {
        private GraphicsContext g;
        private Color color;
        private DataSource<Double> dataSource;

        private Deque<Double> buffer = new ArrayDeque<>();

        private double oldX = -1;
        private double oldY = -1;

        private static final int PIXELS_PER_UNIT = 10;
        //Size of the Graph
        private static final int MAX_ITEMS = 1200 / PIXELS_PER_UNIT;

        public CanvasLineChart(GraphicsContext g, Color color, DataSource<Double> dataSource) {
            this.g = g;
            this.color = color;
            this.dataSource = dataSource;
        }

        public void update() {
            double value = dataSource.getValue();

            buffer.addLast(value);

            if (buffer.size() > MAX_ITEMS) {
                buffer.removeFirst();
            }

            // Render
            g.setStroke(color);
            g.setLineWidth(2.5);

            buffer.forEach(y -> {
                if (oldY > -1) {
                    // [0..1] * 600 = [0..600]
                    g.strokeLine(
                            oldX * PIXELS_PER_UNIT,
                            oldY * 600,
                            (oldX + 1) * PIXELS_PER_UNIT,
                            y * 600
                    );
                }

                oldX = oldX + 1;
                oldY = y;
            });

            oldX = -1;
            oldY = -1;
        }
    }

    private static class RandomDataSource implements DataSource<Double> {

        private Random random = new Random();

        @Override
        public Double getValue() {
            return random.nextDouble();
        }
    }

    private interface DataSource<T> {
        T getValue();
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
    
}

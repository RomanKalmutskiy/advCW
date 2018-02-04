package application;

import java.awt.FlowLayout;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.TextAlignment;

public class Main extends Application {

	PatchP p = new PatchP();

	MediaPlayer musP;
	DBWorker payBase;
	StartValue startValue = new StartValue();
	Payment payment;

	DBWorker getStart;

	Counter countC;
	ElectricityCounter elCountC;
	GasCounter gasCountC;
	HotWaterCounter hotWaterCountC;
	ColdWaterCounter coldWaterCountC;
	Generator generator;

	MyService myServiseEl;
	MyService2 myServiseGs;
	MyService3 myServiseHW;
	MyService4 myServiseCW;
	MyService5 myServiseMP;
	MyService6 myServiseSetToBase;

	DBWorker dbWorker;
	NewTariffes newTariffes = new NewTariffes();

	Button counterView;
	Button tariffsView;
	Button statisticsView;
	Button startAllCount;

	Label labElectrTarif;
	Label labGasTarif;
	Label labHotWaterTarif;
	Label labColdWaterTarif;
	Label labDataUpdateTarif;

	Label labElValuekWt;
	Label labGasValueM3;
	Label labHotWaterValueM3;
	Label labColdWaterValueM3;
	Label labTotalAmount;

	Label elToPay;
	Label gsToPay;
	Label hWToPay;
	Label cWToPay;

	Button backToMain;
	Button updateTarif;
	Button updateCV;
	Button saveCV;
	Button updateFQty;

	Label labUnitTarif;

	TextField filElectrTarif;
	TextField filGasTarif;
	TextField filHotWaterTarif;
	TextField filColdWaterTarif;
	TextField filUpdateTarif;

	Stage primaryStage;
	GridPane gridC;

	String eFT;
	String gFT;
	String hWFT;
	String cWFT;

	Label labAmEl;
	Label labAmGS;
	Label labAmHW;
	Label labAmCW;

	Double amountEl;
	Double amountGS;
	Double amountHW;
	Double amountCW;

	TextField eLText;
	TextField gSText;
	TextField hWText;
	TextField cWText;

	Date date1;
	Label elStatistik;

	DBWorker dbGetListPay;
	// ListView<Date> list;

	// Label elPay;
	// Label gsPay;

	@Override
	public void start(Stage primaryStage) {

		System.out.print("Loading driver...");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Success");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println("Cant Load Driver");
		}

		this.primaryStage = primaryStage;
		primaryStage.setTitle("Roman Kalmutskiy");
		try {
			primaryStage.setScene(setStart());
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		myServiseEl = new MyService();
		myServiseGs = new MyService2();
		myServiseHW = new MyService3();
		myServiseCW = new MyService4();
		myServiseMP = new MyService5();
		myServiseSetToBase = new MyService6();
	}

	public class MyService extends Service {

		@Override
		protected Task createTask() {

			return new Task() {

				@Override
				protected Object call() throws Exception {

					System.out.println("Cuncurent");
					int genValue = 0;
					boolean start = true;
					elCountC = new ElectricityCounter(startValue);

					while (start) {
						TimeUnit.SECONDS.sleep(1);
						genValue++;
						elCountC.generatorEl();

						elCountC.getValue();

						labElValuekWt = new Label();
						labElValuekWt.setText(Integer.toString(elCountC.getValue()));

					}

					return null;
				}
			};

		}

	}

	public class MyService2 extends Service {

		@Override
		protected Task createTask() {

			return new Task() {

				@Override
				protected Object call() throws Exception {

					System.out.println("Cuncurent");
					int genValue = 0;
					boolean start = true;
					gasCountC = new GasCounter(startValue);

					while (start) {
						TimeUnit.SECONDS.sleep(1);
						genValue++;
						gasCountC.generatorGs();

						labGasValueM3 = new Label();
						labGasValueM3.setText(Integer.toString(gasCountC.getValue()));
					}

					return null;
				}
			};

		}

	}

	public class MyService5 extends Service {

		@Override
		protected Task createTask() {

			return new Task() {

				@Override
				protected Object call() throws Exception {
					
					Media musF = new Media(p.patch());
					
					musP = new MediaPlayer(musF);
					musP.setAutoPlay(true);
					musP.setVolume(1);
					System.out.println("Start of play");

					return null;
				}
			};

		}

	}

	public class MyService3 extends Service {

		@Override
		protected Task createTask() {

			return new Task() {

				@Override
				protected Object call() throws Exception {

					System.out.println("Cuncurent");
					int genValue = 0;
					boolean start = true;

					hotWaterCountC = new HotWaterCounter(startValue);

					while (start) {
						TimeUnit.SECONDS.sleep(1);
						genValue++;
						hotWaterCountC.generatorHW();
						hotWaterCountC.setValue(genValue);

						labHotWaterValueM3 = new Label();
						labHotWaterValueM3.setText(Integer.toString(hotWaterCountC.getValue()));

					}

					return null;
				}
			};

		}

	}

	public class MyService4 extends Service {

		@Override
		protected Task createTask() {

			return new Task() {

				@Override
				protected Object call() throws Exception {

					System.out.println("Cuncurent");
					int genValue = 0;
					boolean start = true;
					coldWaterCountC = new ColdWaterCounter(startValue);

					while (start) {
						TimeUnit.SECONDS.sleep(1);
						genValue++;
						coldWaterCountC.generatorCW();
						coldWaterCountC.setValue(genValue);

						labColdWaterValueM3 = new Label();
						labColdWaterValueM3.setText(Integer.toString(coldWaterCountC.getValue()));

					}

					return null;
				}
			};

		}

	}

	public class MyService6 extends Service {

		@Override
		protected Task createTask() {

			return new Task() {

				@Override
				protected Object call() throws Exception {

					boolean start = true;

					while (start) {

						Date date = new Date();
						SimpleDateFormat formatterBT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						SimpleStringProperty dateStringProp = new SimpleStringProperty();
						dateStringProp.set(formatterBT.format(date).toString());

						dateStringProp.getValue();

						try {
							formatterBT.parse(dateStringProp.getValue());
						} catch (ParseException e2) {
							e2.printStackTrace();
						}

						TimeUnit.SECONDS.sleep(15);
						System.out.println("To data base");

						Double elqVel = new Double(elCountC.getValue());
						Double gqVel = new Double(gasCountC.getValue());
						Double hvqVel = new Double(hotWaterCountC.getValue());
						Double cwqVel = new Double(coldWaterCountC.getValue());
						try {
							DBWorker intoBase = new DBWorker(formatterBT.parse(dateStringProp.getValue()), elqVel,
									gqVel, hvqVel, cwqVel);
						} catch (InstantiationException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}

					return null;
				}
			};

		}

	}

	private Scene setStart() {

		startAllCount = new Button("Start count");
		startAllCount.setStyle("-fx-text-fill:red;" + "-fx-font-size: 18px;" + "-fx-font-family: " + "Arial Black" + ";"
				+ "-fx-size: 400, 200;");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		URL url = this.getClass().getClassLoader().getResource("mainB.jpg");
		String imageM = url.toString();

		grid.setStyle("-fx-background-image: url('" + imageM + "'); " + "-fx-background-position: center center; "
				+ "-fx-background-repeat: stretch;" + "-fx-background-size:100% 100%;");

		grid.setHgap(50);
		grid.setVgap(50);
		grid.setPadding(new Insets(15, 15, 15, 15));
		Scene scene = new Scene(grid, 700, 450);

		grid.add(startAllCount, 0, 0);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
		initListeners1();

		return scene;
	}

	private void initListeners1() {

		startAllCount.setOnMouseClicked((e) -> {
			setStartView();

			setStartVelue();
			myServiseMP.start();
			myServiseEl.start();
			myServiseGs.start();
			myServiseHW.start();
			myServiseCW.start();
			myServiseSetToBase.start();

		});

	}

	private void setStartVelue() {
		try {
			getStart = new DBWorker(startValue);
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		getStart.sqlGetLastValue();

	}

	private Scene setStartView() {

		counterView = new Button("Counters");
		tariffsView = new Button("Tariffs");
		statisticsView = new Button("Statistics");
		startAllCount = new Button("Start count");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		URL url = this.getClass().getClassLoader().getResource("mainB.jpg");
		String imageM = url.toString();

		grid.setStyle("-fx-background-image: url('" + imageM + "'); " + "-fx-background-position: center center; "
				+ "-fx-background-repeat: stretch;" + "-fx-background-size:100% 100%;");

		grid.setHgap(50);
		grid.setVgap(50);
		grid.setPadding(new Insets(15, 15, 15, 15));
		Scene scene = new Scene(grid, 700, 450);

		grid.add(counterView, 0, 0);
		grid.add(tariffsView, 0, 1);
		grid.add(statisticsView, 0, 2);
		grid.add(startAllCount, 0, 3);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
		initListeners2();

		return scene;
	}

	private void initListeners2() {
		tariffsView.setOnMouseClicked((e) -> {

			NewTariffes newTariffes = new NewTariffes();

			try {
				dbWorker = new DBWorker(newTariffes);
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			dbWorker.sqlGetTarifs();

			eFT = newTariffes.getElecTarif().toString();
			gFT = newTariffes.getGasTarif().toString();
			hWFT = newTariffes.gethWaterTarif().toString();
			cWFT = newTariffes.getcWaterTarif().toString();

			setTarifeView(eFT, gFT, hWFT, cWFT);
		});

		counterView.setOnMouseClicked((e) -> {

			NewTariffes newTariffes = new NewTariffes();

			try {
				dbWorker = new DBWorker(newTariffes);
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			dbWorker.sqlGetTarifs();

			eFT = newTariffes.getElecTarif().toString();
			gFT = newTariffes.getGasTarif().toString();
			hWFT = newTariffes.gethWaterTarif().toString();
			cWFT = newTariffes.getcWaterTarif().toString();

			setCounterView(eFT, gFT, hWFT, cWFT);

		});

		statisticsView.setOnMouseClicked((e) -> {

			setStatisticView();

		});

	}

	private Scene setStatisticView() {

		try {
			dbGetListPay = new DBWorker();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		dbGetListPay.sqlGetListPay();

		ObservableList<Integer> items = FXCollections.observableArrayList(dbGetListPay.sqlGetListPay());

		ListView<Integer> list = new ListView<>();
		list.setItems(items);
		list.setPrefSize(100, 300);

		Payment payment = new Payment();
		Label datePay = new Label("____");
		Label elPay = new Label("____");
		Label gsPay = new Label("____");
		Label hwPay = new Label("____");
		Label cwPay = new Label("____");

		list.getSelectionModel().selectedItemProperty().addListener((ov, old_val, new_val) -> {

			dbGetListPay.getMainPayItem(new_val, payment);

			datePay.setText(payment.getDate1().toString());
			System.out.println("from list" + payment.getDate1().toString());

			elPay.setText(payment.getEla().toString());
			System.out.println("from list" + payment.getEla().toString());

			gsPay.setText(payment.getGa().toString());
			System.out.println("from list" + payment.getGa().toString());

			hwPay.setText(payment.getHwa().toString());
			System.out.println("from list" + payment.getHwa().toString());

			cwPay.setText(payment.getCwa().toString());
			System.out.println("from list" + payment.getCwa().toString());
		});

		try {
			payBase = new DBWorker();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		backToMain = new Button("Back to main menu");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		URL url = this.getClass().getClassLoader().getResource("mainB.jpg");
		String imageM = url.toString();

		grid.setStyle("-fx-background-image: url('" + imageM + "'); " + "-fx-background-position: center center; "
				+ "-fx-background-repeat: stretch;" + "-fx-background-size:100% 100%;");

		grid.setHgap(50);
		grid.setVgap(50);
		grid.setPadding(new Insets(15, 15, 15, 15));
		Scene scene = new Scene(grid, 700, 450);

		grid.add(list, 0, 2);

		grid.add(new Label("Payment of..."), 3, 0);
		grid.add(new Label("Date of payment"), 1, 1);
		grid.add(datePay, 1, 2);
		grid.add(new Label("Electricity, UAH"), 2, 1);
		grid.add(elPay, 2, 2);
		grid.add(new Label("Gas, UAH"), 3, 1);
		grid.add(gsPay, 3, 2);
		grid.add(new Label("Hot water, UAH"), 4, 1);
		grid.add(hwPay, 4, 2);
		grid.add(new Label("Cold water, UAH"), 5, 1);
		grid.add(cwPay, 5, 2);
		grid.add(backToMain, 1, 3);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
		initListeners5();

		return scene;

	}

	private void initListeners5() {
		backToMain.setOnMouseClicked((e) -> {
			setStartView();

		});

	}

	private Scene setCounterView(String eFT, String gFT, String hWFT, String cWFT) {
		try {
			payBase = new DBWorker();
		} catch (InstantiationException e1) {

			e1.printStackTrace();
		} catch (IllegalAccessException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		payment = new Payment();
		payBase.sqlGetLastPay(payment);

		Label elLastPay = new Label(payment.getElq().toString());
		Label gsLastPay = new Label(payment.getGq().toString());
		Label hWLastPay = new Label(payment.getHvq().toString());
		Label cWLastPay = new Label(payment.getCwq().toString());

		URL url3 = this.getClass().getClassLoader().getResource("el.jpg");
		String imageE = url3.toString();
		Label labElectrValue = new Label("Electricity value kWt");
		labElectrValue.setMinSize(250, 100);
		labElectrValue.setTextAlignment(TextAlignment.CENTER);
		labElectrValue.setStyle("-fx-graphic:url('" + imageE + "');" + "-fx-content-display:top ;");
		labElectrValue.setAlignment(Pos.CENTER);

		URL url4 = this.getClass().getClassLoader().getResource("gas.jpg");
		String imageG = url4.toString();
		Label labGasValue = new Label("Gas value m3");
		labGasValue.setMinSize(250, 100);
		labGasValue.setStyle("-fx-graphic:url('" + imageG + "');" + "-fx-content-display:top ;");
		labGasValue.setAlignment(Pos.CENTER);

		URL url5 = this.getClass().getClassLoader().getResource("hWater.jpg");
		String imageH = url5.toString();
		Label labHotWaterValue = new Label("Hot Water per m3");
		labHotWaterValue.setMinSize(250, 100);
		labHotWaterValue.setAlignment(Pos.CENTER);
		labHotWaterValue.setStyle("-fx-graphic:url('" + imageH + "');" + "-fx-content-display:top ;");

		URL url6 = this.getClass().getClassLoader().getResource("cW.jpg");
		String imageC = url6.toString();

		Label labColdWaterValue = new Label("Cold Water tarif per m3");
		labColdWaterValue.setMinSize(250, 100);
		labColdWaterValue.setMinSize(250, 100);
		labHotWaterValue.setAlignment(Pos.CENTER);
		labColdWaterValue.setStyle("-fx-graphic:url('" + imageC + "');" + "-fx-content-display:top ;");

		Label labTarEl = new Label(eFT);
		Label labTarGS = new Label(gFT);
		Label labTarHW = new Label(hWFT);
		Label labTarCW = new Label(cWFT);

		eLText = new TextField();
		eLText.textProperty().setValue(labElValuekWt.getText());
		labElValuekWt.textProperty().bind(eLText.textProperty());
		
		gSText = new TextField();
		gSText.textProperty().setValue(labGasValueM3.getText());
		labGasValueM3.textProperty().bind(gSText.textProperty());
		

		hWText = new TextField();
		hWText.textProperty().setValue(labHotWaterValueM3.getText());
		labHotWaterValueM3.textProperty().bind(hWText.textProperty());
		
		cWText = new TextField();
		cWText.textProperty().setValue(labColdWaterValueM3.getText());
		labColdWaterValueM3.textProperty().bind(cWText.textProperty());

		URL url2 = this.getClass().getClassLoader().getResource("back.jpg");
		String imageB = url2.toString();

		backToMain = new Button("Back to main menu");
		backToMain.setMinSize(120, 120);
		backToMain.setAlignment(Pos.CENTER);
		backToMain.setStyle("-fx-graphic:url('" + imageB + "');" + "-fx-content-display:top ;");
		URL url7 = this.getClass().getClassLoader().getResource("update.jpg");
		String imageUp = url7.toString();

		updateCV = new Button("Update Q-ty");
		updateCV.setStyle("-fx-graphic:url('" + imageUp + "');" + "-fx-content-display:top ;");
		updateFQty = new Button("Update Final Q-ty");

		URL url8 = this.getClass().getClassLoader().getResource("save.jpg");
		String imageSv = url8.toString();
		saveCV = new Button("Save");
		saveCV.setMinSize(120, 120);
		saveCV.setAlignment(Pos.CENTER);
		saveCV.setStyle("-fx-graphic:url('" + imageSv + "');" + "-fx-content-display:top ;");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = new Date();
		LocalDate today = LocalDate.now();
		SimpleStringProperty dateStringProp = new SimpleStringProperty();
		dateStringProp.set(dateFormat.format(date).toString());
		labDataUpdateTarif = new Label(dateStringProp.getValue());

		Double elToPayQ = new Double(eLText.getText()) - payment.getElq();
		Double gsToPayQ = new Double(gSText.getText()) - payment.getGq();
		Double hWToPayQ = new Double(hWText.getText()) - payment.getHvq();
		Double cWToPayQ = new Double(cWText.getText()) - payment.getHvq();

		elToPay = new Label(Double.toString(new Double(eLText.getText()) - payment.getElq()));
		gsToPay = new Label(Double.toString(new Double(gSText.getText()) - payment.getGq()));
		hWToPay = new Label(Double.toString(new Double(hWText.getText()) - payment.getHvq()));
		cWToPay = new Label(Double.toString(new Double(cWText.getText()) - payment.getCwq()));

		amountEl = Double.parseDouble(eFT) * Double.parseDouble(elToPay.getText());
		labAmEl = new Label(String.format("%.2f", amountEl));

		amountGS = Double.parseDouble(gFT) * Double.parseDouble(gsToPay.getText());
		labAmGS = new Label(String.format("%.2f", amountGS));

		amountHW = Double.parseDouble(hWFT) * Double.parseDouble(hWToPay.getText());
		labAmHW = new Label(String.format("%.2f", amountHW));

		amountCW = Double.parseDouble(cWFT) * Double.parseDouble(cWToPay.getText());
		labAmCW = new Label(String.format("%.2f", amountCW));
		labTotalAmount = new Label(String.format("%.2f", (amountEl + amountGS + amountHW + amountCW)));

		updateFQty.setOnMouseClicked((e) -> {

			elToPay.setText((Double.toString(new Double(eLText.getText()) - payment.getElq())));
			gsToPay.setText((Double.toString(new Double(gSText.getText()) - payment.getGq())));
			hWToPay.setText((Double.toString(new Double(hWText.getText()) - payment.getHvq())));
			cWToPay.setText((Double.toString(new Double(cWText.getText()) - payment.getCwq())));

			amountEl = Double.parseDouble(eFT) * Double.parseDouble(elToPay.getText());
			labAmEl.setText(String.format("%.2f", amountEl));

			amountGS = Double.parseDouble(gFT) * Double.parseDouble(gsToPay.getText());
			labAmGS.setText(String.format("%.2f", amountGS));

			amountHW = Double.parseDouble(hWFT) * Double.parseDouble(hWToPay.getText());
			labAmHW.setText(String.format("%.2f", amountHW));

			amountCW = Double.parseDouble(cWFT) * Double.parseDouble(cWToPay.getText());
			labAmCW.setText(String.format("%.2f", amountCW));

			labTotalAmount.setText(String.format("%.2f", (amountEl + amountGS + amountHW + amountCW)));

		});

		gridC = new GridPane();
		gridC.setAlignment(Pos.CENTER);

		URL url = this.getClass().getClassLoader().getResource("mainB.jpg");
		String imageM = url.toString();

		gridC.setStyle("-fx-background-image: url('" + imageM + "'); " + "-fx-background-position: center center; "
				+ "-fx-background-repeat: stretch;" + "-fx-background-size:100% 100%;");

		gridC.setHgap(40);
		gridC.setVgap(20);
		gridC.setPadding(new Insets(15, 15, 15, 15));
		Scene scene = new Scene(gridC, 700, 450);

		gridC.add(new Label("Last payment"), 1, 0);
		gridC.add(new Label("Q-ty"), 2, 0);
		gridC.add(new Label("Final-ty"), 3, 0);
		gridC.add(new Label("To pay"), 4, 0);
		gridC.add(new Label("Price, UAH"), 5, 0);
		gridC.add(new Label("Total, UAH"), 6, 0);

		gridC.add(labElectrValue, 0, 1);
		gridC.add(labGasValue, 0, 2);
		gridC.add(labHotWaterValue, 0, 3);
		gridC.add(labColdWaterValue, 0, 4);

		gridC.add(elLastPay, 1, 1);
		gridC.add(gsLastPay, 1, 2);
		gridC.add(hWLastPay, 1, 3);
		gridC.add(cWLastPay, 1, 4);

		gridC.add(labElValuekWt, 2, 1);
		gridC.add(labGasValueM3, 2, 2);
		gridC.add(labHotWaterValueM3, 2, 3);
		gridC.add(labColdWaterValueM3, 2, 4);

		gridC.add(elToPay, 4, 1);
		gridC.add(gsToPay, 4, 2);
		gridC.add(hWToPay, 4, 3);
		gridC.add(cWToPay, 4, 4);

		gridC.add(labTarEl, 5, 1);
		gridC.add(labTarGS, 5, 2);
		gridC.add(labTarHW, 5, 3);
		gridC.add(labTarCW, 5, 4);

		gridC.add(labAmEl, 6, 1);
		gridC.add(labAmGS, 6, 2);
		gridC.add(labAmHW, 6, 3);
		gridC.add(labAmCW, 6, 4);

		gridC.add(eLText, 3, 1);
		gridC.add(gSText, 3, 2);
		gridC.add(hWText, 3, 3);
		gridC.add(cWText, 3, 4);

		gridC.add(backToMain, 0, 5);
		gridC.add(updateCV, 1, 5);
		gridC.add(updateFQty, 3, 5);
		gridC.add(saveCV, 5, 5);

		gridC.add(labTotalAmount, 6, 5);
		gridC.add(labDataUpdateTarif, 0, 0);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();

		initListeners4();

		return scene;

	}

	private void initListeners4() {

		backToMain.setOnMouseClicked((e) -> {
			setStartView();

		});

		updateCV.setOnMouseClicked((e) -> {
			setCounterView(eFT, gFT, hWFT, cWFT);

		});

		saveCV.setOnMouseClicked((e) -> {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String elq = eLText.getText().trim();
			System.out.println("elq " + elq);
			double elect = Double.parseDouble(elq.replace(',', '.'));
			System.out.println("after parse" + elect);

			String gsq = gSText.getText().trim();
			System.out.println("gsq " + gsq);
			Double gas = Double.parseDouble(gsq.replace(',', '.'));
			System.out.println("after parse" + gas);

			String hwq = hWText.getText().trim();
			System.out.println("hwq " + hwq);
			Double hWat = Double.parseDouble(hwq.replace(',', '.'));
			System.out.println("after parse" + hWat);

			String cwq = cWText.getText().trim();
			System.out.println("cwq " + cwq);
			Double cWat = Double.parseDouble(cwq.replace(',', '.'));
			System.out.println("after parse" + cWat);

			String ela = labAmEl.getText();
			System.out.println("ela " + ela);
			double electa = Double.parseDouble(ela.replace(',', '.'));
			System.out.println("electa " + electa);

			String gsa = labAmGS.getText().trim();
			System.out.println("gsq " + gsa);
			Double gasa = Double.parseDouble(gsa.replace(',', '.'));

			String hwa = labAmHW.getText().trim();
			Double hWata = Double.parseDouble(hwa.replace(',', '.'));

			String cwa = labAmCW.getText().trim();
			Double cWata = Double.parseDouble(cwa.replace(',', '.'));

			try {
				DBWorker dbWorker1 = new DBWorker(formatter.parse(labDataUpdateTarif.getText().trim()), elect, gas,
						hWat, cWat, electa, gasa, hWata, cWata);
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

		});

	}

	private Scene setTarifeView(String eFT, String gFT, String hWFT, String cWFT) {

		labElectrTarif = new Label("Electricity tarif per kWt/H");
		labGasTarif = new Label("Gas tarif per m3");
		labHotWaterTarif = new Label("Hot Water tarif per m3");
		labColdWaterTarif = new Label("Cold Water tarif per m3");

		filElectrTarif = new TextField(eFT);
		filGasTarif = new TextField(gFT);
		filHotWaterTarif = new TextField(hWFT);
		filColdWaterTarif = new TextField(cWFT);

		URL url2 = this.getClass().getClassLoader().getResource("back.jpg");
		String imageB = url2.toString();

		backToMain = new Button("Back to main");
		backToMain.setMinSize(130, 130);
		backToMain.setAlignment(Pos.CENTER);
		backToMain.setStyle("-fx-graphic:url('" + imageB + "');" + "-fx-content-display:top ;");

		URL url8 = this.getClass().getClassLoader().getResource("update.jpg");
		String imageU = url8.toString();
		updateTarif = new Button("Update");
		updateTarif.setMinSize(130, 130);
		updateTarif.setAlignment(Pos.CENTER);
		updateTarif.setStyle("-fx-graphic:url('" + imageU + "');" + "-fx-content-display:top ;");

		LocalDate today = LocalDate.now();
		labDataUpdateTarif = new Label(today.toString());

		System.out.println(today);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		grid.setHgap(50);
		grid.setVgap(50);
		grid.setPadding(new Insets(15, 15, 15, 15));
		Scene scene = new Scene(grid, 700, 450);

		URL url = this.getClass().getClassLoader().getResource("mainB.jpg");
		String imageM = url.toString();

		grid.setStyle("-fx-background-image: url('" + imageM + "'); " + "-fx-background-position: center center; "
				+ "-fx-background-repeat: stretch;" + "-fx-background-size:100% 100%;");

		grid.add(labElectrTarif, 0, 0);
		grid.add(labGasTarif, 0, 1);
		grid.add(labHotWaterTarif, 0, 2);
		grid.add(labColdWaterTarif, 0, 3);
		grid.add(backToMain, 0, 5);
		grid.add(updateTarif, 0, 4);
		grid.add(labDataUpdateTarif, 1, 4);

		grid.add(filElectrTarif, 1, 0);
		grid.add(filGasTarif, 1, 1);
		grid.add(filHotWaterTarif, 1, 2);
		grid.add(filColdWaterTarif, 1, 3);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
		initListeners3();

		return scene;
	}

	private void initListeners3() {

		backToMain.setOnMouseClicked((e) -> {
			setStartView();
		});

		updateTarif.setOnMouseClicked((e) -> {

			String el = filElectrTarif.getText().trim();
			System.out.println("el " + el);
			Double elect = Double.parseDouble(el);

			String gs = filGasTarif.getText().trim();
			System.out.println("gs " + gs);
			Double gas = Double.parseDouble(gs);

			String hw = filHotWaterTarif.getText().trim();

			Double hWat = Double.parseDouble(hw);

			String cw = filColdWaterTarif.getText().trim();
			Double cWat = Double.parseDouble(cw);

			NewTariffes newTariffes = new NewTariffes(elect, gas, hWat, cWat);

			System.out.println(newTariffes.getElecTarif());
			System.out.println(newTariffes.getGasTarif());
			System.out.println(newTariffes.gethWaterTarif());
			System.out.println(newTariffes.getcWaterTarif());

			try {
				dbWorker = new DBWorker(newTariffes);
			} catch (InstantiationException e2) {
				e2.printStackTrace();
			} catch (IllegalAccessException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				dbWorker.sqlAddTarifs();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

	}

	public static void main(String[] args) {

		launch(args);
	}
}

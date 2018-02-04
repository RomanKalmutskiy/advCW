package application;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class DBWorker {

	private Connection conn = null;
	private Statement st = null;

	NewTariffes newTariffes;
	StartValue startValue;
	Payment payment;

	java.util.Date date1;
	Double elq;
	Double gq;
	Double hvq;
	Double cwq;
	Double ela;
	Double ga;
	Double hwa;
	Double cwa;

	Double elqVel;
	Double gqVel;
	Double hvqVel;
	Double cwqVel;
	
//	Date listDatePay;
	List<Integer> listOfPay;

	LocalDate today = LocalDate.now();
	Date date = Date.valueOf(today);

	DBWorker(NewTariffes newTariffes)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost/adv?" + "user=root&password=");
		st = conn.createStatement();
		System.out.println("Sucses conn");

		this.newTariffes = newTariffes;

	}
	
	public ArrayList<Integer> sqlGetListPay(){
		 ArrayList<Integer> list = new ArrayList<Integer>();
		
		ResultSet listsPay = null;
		String query = "SELECT pay_id FROM pay";
		System.out.print("Executing query...sqlGetLastPay");
		try {
				listsPay = st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (listsPay.next()) {
				try {

				int	listDatePay =  listsPay.getInt(1);
					System.out.println("To list>>>"+listDatePay);
					list.add(listDatePay);
					

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	
	}
	
	public void getMainPayItem(Integer pay_id, Payment payment2) {
		
		ResultSet pay = null;
		String query = "SELECT * FROM pay WHERE pay_id ="+pay_id;
		System.out.print("Executing query...sqlGetLastPay");
		try {
			pay = st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (pay.next()) {
				try {

					payment2.setDate1(pay.getDate(2));

					//System.out.println(pay.getDate(2));
					payment2.setElq(pay.getDouble(3));
					//System.out.println(pay.getDouble(3));
					payment2.setGq(pay.getDouble(4));
					//System.out.println(pay.getDouble(4));
					payment2.setHvq(pay.getDouble(5));
					//System.out.println(pay.getDouble(5));
					payment2.setCwq(pay.getDouble(6));
					//System.out.println(pay.getDouble(6));
					payment2.setEla(pay.getDouble(7));
					//System.out.println(pay.getDouble(7));
					payment2.setGa(pay.getDouble(8));
					//System.out.println(pay.getDouble(8));
					payment2.setHwa(pay.getDouble(9));
					
					payment2.setCwa(pay.getDouble(10));
					System.out.println(pay.getDouble(10));

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void sqlGetLastPay(Payment payment) {
		this.payment=payment;
		ResultSet lastPay = null;
		String query = "SELECT * FROM pay WHERE pay_id = (SELECT MAX(pay_id) FROM pay)";
		System.out.print("Executing query...sqlGetLastPay");
		try {
			lastPay = st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (lastPay.next()) {
				try {

					payment.setDate1(lastPay.getDate(2));

					System.out.println(lastPay.getDate(2));
					payment.setElq(lastPay.getDouble(3));
					System.out.println(lastPay.getDouble(3));
					payment.setGq(lastPay.getDouble(4));
					System.out.println(lastPay.getDouble(4));
					payment.setHvq(lastPay.getDouble(5));
					System.out.println(lastPay.getDouble(5));
					payment.setCwq(lastPay.getDouble(6));
					System.out.println(lastPay.getDouble(6));
					payment.setEla(lastPay.getDouble(7));
					System.out.println(lastPay.getDouble(7));
					payment.setHwa(lastPay.getDouble(8));
					System.out.println(lastPay.getDouble(8));
					payment.setCwa(lastPay.getDouble(9));
					System.out.println(lastPay.getDouble(9));

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void sqlGetLastValue() {
		ResultSet lastValue = null;
		String query = "SELECT * FROM base WHERE base_id = (SELECT MAX(base_id) FROM base)";
		System.out.println("Executing query...sqlGetLastValue");
		try {
			lastValue = st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (lastValue.next()) {
				try {

					startValue.setElLast(lastValue.getDouble(3));
					System.out.println("lastValue" + startValue.getElLast());
					startValue.setGasLast(lastValue.getDouble(4));
					System.out.println("lastValue" + startValue.getGasLast());
					startValue.sethWaterLast(lastValue.getDouble(5));
					System.out.println("lastValue" + startValue.gethWaterLast());
					startValue.setcWaterLast(lastValue.getDouble(6));
					System.out.println("lastValue" + startValue.getcWaterLast());

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	DBWorker(java.util.Date date2, Double elq, Double gq, Double hvq, Double cwq, Double ela, Double ga, Double hwa,
			Double cwa) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.date1 = date2;
		this.elq = elq;
		this.gq = gq;
		this.hvq = hvq;
		this.cwq = cwq;
		this.ela = ela;
		this.ga = ga;
		this.hwa = hwa;
		this.cwa = cwa;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost/adv?" + "user=root&password=");
		st = conn.createStatement();
		System.out.println("Sucses conn1");
		sqlAddpay();

	}

	DBWorker(StartValue startValue)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost/adv?" + "user=root&password=");
		st = conn.createStatement();
		System.out.println("Sucses conn");
		this.startValue = startValue;

	}

	public DBWorker() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost/adv?" + "user=root&password=");
		st = conn.createStatement();
		System.out.println("Sucses conn");

	}

	public DBWorker(java.util.Date date2, Double elqVel, Double gqVel, Double hvqVel, Double cwqVel)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println("csjcjsncjsdnj");
		this.date1 = date2;
		this.elqVel = elqVel;
		this.gqVel = gqVel;
		this.hvqVel = hvqVel;
		this.cwqVel = cwqVel;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost/adv?" + "user=root&password=");
		st = conn.createStatement();
		System.out.println("sqlAddBase()");
		sqlAddBase();
	}

	private void sqlAddBase() throws SQLException {
		String query = "INSERT INTO base (base_date, base_elecq, base_gasq, base_hwaterq, base_cwaterq) values (?,?,?,?,?)";
		PreparedStatement intoB = conn.prepareStatement(query);

		LocalDate today = LocalDate.now();
		Date date = Date.valueOf(today);

		System.out.println("metod ");
		intoB.setDate(1, date);
		System.out.println(date1);
		intoB.setDouble(2, elqVel);
		intoB.setDouble(3, gqVel);
		intoB.setDouble(4, hvqVel);
		intoB.setDouble(5, cwqVel);

		System.out.println("metodToBase ");
		intoB.execute();
		System.out.println("metodInBase ");

	}

	public void sqlAddTarifs() throws SQLException {

		String query = "INSERT INTO tarifs (tar_date, tar_elec, tar_gas, tar_hwater, tar_cwater) values (?,?,?,?,?)";
		PreparedStatement tar = conn.prepareStatement(query);

		System.out.println("metod ");
		tar.setDate(1, date);
		tar.setDouble(2, newTariffes.getElecTarif());
		tar.setDouble(3, newTariffes.getGasTarif());
		tar.setDouble(4, newTariffes.gethWaterTarif());
		tar.setDouble(5, newTariffes.getcWaterTarif());
		System.out.println("metod2 ");
		tar.execute();
		System.out.println("metod3 ");

	}

	public void sqlAddpay() throws SQLException {

		String query = "INSERT INTO pay (pay_data, pay_elecq, pay_gasq, pay_hwaterq, pay_cwaterq, pay_eleca, pay_gasa, pay_hwatera, pay_cwatera) values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pay = conn.prepareStatement(query);

		System.out.println("metod ");
		pay.setDate(1, date);
		pay.setDouble(2, elq);
		pay.setDouble(3, gq);
		pay.setDouble(4, hvq);
		pay.setDouble(5, cwq);
		pay.setDouble(6, ela);
		pay.setDouble(7, ga);
		pay.setDouble(8, hwa);
		pay.setDouble(9, cwa);

		System.out.println("metod2 ");
		pay.execute();
		System.out.println("metod3 ");

	}

	public void sqlGetTarifs() {
		ResultSet setTar = null;
		String query = "SELECT * FROM tarifs WHERE tar_id = (SELECT MAX(tar_id) FROM tarifs)";
		System.out.print("Executing query...sqlGetTarifs");
		try {
			setTar = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (setTar.next()) {
				try {
					System.out.println(setTar.getInt(1));

					System.out.println(setTar.getDate(2));
					System.out.println(setTar.getDouble(3));
					newTariffes.setElecTarif(setTar.getDouble(3));
					System.out.println(setTar.getDouble(4));
					newTariffes.setGasTarif(setTar.getDouble(4));
					System.out.println(setTar.getDouble(5));
					newTariffes.sethWaterTarif(setTar.getDouble(5));
					System.out.println(setTar.getDouble(6));
					newTariffes.setcWaterTarif(setTar.getDouble(6));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	

}

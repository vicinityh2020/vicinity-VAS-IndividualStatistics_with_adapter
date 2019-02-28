/**
Copyright 2018-2019. Information Technologies Institute (CERTH-ITI)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package vicinity.vas.MySQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class MySQLMethods {

	private static String username = "vicinity_mph";
	private static String dbName = "VCNT_INDIVIDUAL_STATISTICS";
	private static String serverIP = "194.219.31.173";

	public static int getWeek(Date measurementDate) {
		int week = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = measurementDate.toLocalDate();
		System.out.println(dtf.format(localDate));
		String input = dtf.format(localDate);
		String format = "yyyy-MM-dd";

		SimpleDateFormat df = new SimpleDateFormat(format);
		java.util.Date date = null;
		try {
			date = df.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		week = cal.get(Calendar.WEEK_OF_YEAR);

		return week;
	}

	public static java.sql.Date getEndDate(Date measurementDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = measurementDate.toLocalDate();
		System.out.println(dtf.format(localDate));

		java.sql.Date sqlDate = java.sql.Date.valueOf(dtf.format(localDate));
		return sqlDate;
	}

	public static java.sql.Date getStartDate(int week) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of
											// day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		// get start of this week in milliseconds
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		System.out.println("Start of this week:       " + cal.getTime());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String inActiveDate = null;
		inActiveDate = format1.format(cal.getTime());
		System.out.println(inActiveDate);
		java.sql.Date sqlDate = java.sql.Date.valueOf(inActiveDate);
		System.out.println("Start of this week (sqlDate):       " + sqlDate);
		return sqlDate;
	}

	public static void createTable(String table_name, String sql) {
		Connection c = null;
		Statement stmt = null;

		try {
			c = connectToDB("//" + serverIP + ":3306/" + dbName, username, username);

			stmt = c.createStatement();

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
			return;
		}
		System.out.println("Table with name " + table_name + " was created successfully");
	}

	public static void insertInTable(String table_name, String insertString) throws SQLException {
		Connection c = null;
		Statement stmt = null;

//		try {
			c = connectToDB("//" + serverIP + ":3306/" + dbName, username, username);

			stmt = c.createStatement();

			String sql = "INSERT INTO " + table_name + insertString;
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			// System.exit(0);
//			return;
//		}
		System.out.println("New entry was inserted to table " + table_name + " successfully");

	}

	public static void updateUsers(User user) {
		Connection c = null;
		Statement stmt = null;
		if (user.getUserId() != null) {
			try {
				c = connectToDB("//" + serverIP + ":3306/" + dbName, username, username);

				stmt = c.createStatement();

				String sql = "UPDATE users\nSET ";

				// check which properties need update

				if (user.getUserType() != null) {
					sql += "userType = \'" + user.getUserType() + "\'";
				}
				if (user.getUsualMeasureTime() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "usualMeasureTime = " + user.getUsualMeasureTime();
				}
				if (user.getHasBL() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "hasBL = " + user.getHasBL();
				}
				if (user.getHasButton() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "hasButton = " + user.getHasButton();
				}
				if (user.getHasWS() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "hasWS = " + user.getHasWS();
				}
				sql += "\n";
				sql += "WHERE\n" + "             userId=\'" + user.getUserId() + "\';";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				// System.exit(0);
				return;
			}
			System.out.println("Update users table successfully");
		}

	}

	public static void updateFrequencies(Frequencies freq) {
		Connection c = null;
		Statement stmt = null;
		if (freq.getUserId() != null && freq.getWeek() != null && freq.getYear() != null) {
			try {
				c = connectToDB("//" + serverIP + ":3306/" + dbName, username, username);

				stmt = c.createStatement();

				String sql = "UPDATE frequencies\nSET ";

				// check which properties need update

				if (freq.getBl_freq() != null) {
					sql += "bl_freq = bl_freq + " + freq.getBl_freq();
				}
				if (freq.getEnd_date() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "end_date = \'" + freq.getEnd_date() + "\'";
				}
				if (freq.getButton_freq() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "button_freq = button_freq + " + freq.getButton_freq();
				}
				if (freq.getWeight_freq() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "weight_freq = weight_freq + " + freq.getWeight_freq();
				}
				if (freq.getStart_date() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "start_date = \'" + freq.getStart_date() + "\'";
				}
				sql += "\n";
				sql += "WHERE\n" + "             (userId=\'" + freq.getUserId() + "\' AND week=" + freq.getWeek()
						+ " AND year=" + freq.getYear() + ");";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				// System.exit(0);
				return;
			}
			System.out.println("Update frequencies table successfully");
		}

	}

	public static void updateBloodPressureStats(BloodPressureStats bl_stats) throws SQLException {
		Connection c = null;
		Statement stmt = null;
		if (bl_stats.getUserId() != null) {
//			try {
				c = connectToDB("//" + serverIP + ":3306/" + dbName, username, username);

				stmt = c.createStatement();

				String sql = "UPDATE bloodpressure_stats\nSET ";

				// check which properties need update

				// if (bl_stats.getMean_bl_freq() != null) {
				sql += "mean_bl_freq = " + bl_stats.getMean_bl_freq();
				// }
				if (bl_stats.getMean_systolic() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					////////////////////////////////////////// should be integer
					////////////////////////////////////////// ///////////////////////////////////////////////////
					sql += "mean_systolic = (mean_systolic + " + bl_stats.getMean_systolic() + ") / 2";
				}
				if (bl_stats.getMean_diastolic() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "mean_diastolic = (mean_diastolic + " + bl_stats.getMean_diastolic() + ") / 2";
				}
				if (bl_stats.getMean_heart_rate() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "mean_heart_rate = (mean_heart_rate + " + bl_stats.getMean_heart_rate() + ") / 2";
				}
				if (bl_stats.getMax_systolic() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "max_systolic_date = CASE WHEN max_systolic < " + bl_stats.getMax_systolic() + " THEN \'"
							+ bl_stats.getMax_systolic_date() + "\' ELSE max_systolic_date END";
					sql += ",\n";
					sql += "max_systolic = CASE WHEN max_systolic < " + bl_stats.getMax_systolic() + " THEN "
							+ bl_stats.getMax_systolic() + " ELSE max_systolic END";
					
				}
				if (bl_stats.getMin_systolic() != null) {
					
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "min_systolic_date = CASE WHEN min_systolic > " + bl_stats.getMin_systolic() + " THEN \'"
							+ bl_stats.getMin_systolic_date() + "\' ELSE min_systolic_date END";
					sql += ",\n";
					sql += "min_systolic = CASE WHEN min_systolic > " + bl_stats.getMin_systolic() + " THEN "
							+ bl_stats.getMin_systolic() + " ELSE min_systolic END";
				}
				if (bl_stats.getMax_diastolic() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "max_diastolic_date = CASE WHEN max_diastolic < " + bl_stats.getMax_diastolic() + " THEN \'"
							+ bl_stats.getMax_diastolic_date() + "\' ELSE max_diastolic_date END";
					sql += ",\n";
					sql += "max_diastolic = CASE WHEN max_diastolic < " + bl_stats.getMax_diastolic() + " THEN "
							+ bl_stats.getMax_diastolic() + " ELSE max_diastolic END";
					
					
				}
				if (bl_stats.getMin_diastolic() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "min_diastolic_date = CASE WHEN min_diastolic > " + bl_stats.getMin_diastolic() + " THEN \'"
							+ bl_stats.getMin_diastolic_date() + "\' ELSE min_diastolic_date END";
					sql += ",\n";
					sql += "min_diastolic = CASE WHEN min_diastolic > " + bl_stats.getMin_diastolic() + " THEN "
							+ bl_stats.getMin_diastolic() + " ELSE min_diastolic END";
					
				}

				if (sql.contains("=")) {
					sql += ",\n";
				}
				sql += "total_measurements = total_measurements + " + bl_stats.getTotal_measurements();
				sql += ",\n";
				sql += "hypo_measurements = hypo_measurements + " + bl_stats.getHypo_measurements();
				sql += ",\n";
				sql += "normal_measurements = normal_measurements + " + bl_stats.getNormal_measurements();
				sql += ",\n";
				sql += "hyper1_measurements = hyper1_measurements + " + bl_stats.getHyper1_measurements();
				sql += ",\n";
				sql += "hyper2_measurements = hyper2_measurements + " + bl_stats.getHyper2_measurements();
				sql += ",\n";
				sql += "hypercrisis_measurements = hypercrisis_measurements + " + bl_stats.getHypercrisis_measurements();

				if (bl_stats.getMean_measure_time() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "mean_measure_time = " + bl_stats.getMean_measure_time() ;
				}
				sql += "\n";
				sql += "WHERE\n" + "             (userId=\'" + bl_stats.getUserId() + "\');";
				System.out.println(sql);
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
//			} catch (Exception e) {
//				System.err.println(e.getClass().getName() + ": " + e.getMessage());
//				// System.exit(0);
//				return;
//			}
			System.out.println("Update bloodpressure_stats table successfully");
		}
	}

	public static void updateButtonStats(ButtonStats bt_stats) throws SQLException {
		Connection c = null;
		Statement stmt = null;
		if (bt_stats.getUserId() != null) {
			//try {
				c = connectToDB("//" + serverIP + ":3306/" + dbName, username, username);

				stmt = c.createStatement();

				String sql = "UPDATE button_stats\nSET ";

				// check which properties need update

				// if (bt_stats.getMean_button_freq() != null) {
				sql += "mean_button_freq = " + bt_stats.getMean_button_freq();
				// }

				if (bt_stats.getMean_measure_time() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "mean_measure_time = " + bt_stats.getMean_measure_time() ;
				}
				if (sql.contains("=")) {
					sql += ",\n";
				}
				sql += "total_measurements = total_measurements + " + bt_stats.getTotal_measurements();
				sql += ",\n";
				sql += "BaseBtn_measurements = BaseBtn_measurements + " + bt_stats.getBaseBtn_measurements();
				sql += ",\n";
				sql += "PanicBtn_measurements = PanicBtn_measurements + " + bt_stats.getPanicBtn_measurements();
				sql += ",\n";
				sql += "Fall_measurements = Fall_measurements + " + bt_stats.getFall_measurements();
				sql += "\n";
				sql += "WHERE\n" + "             (userId=\'" + bt_stats.getUserId() + "\');";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
//			} catch (Exception e) {
//				System.err.println(e.getClass().getName() + ": " + e.getMessage());
//				// System.exit(0);
//				return;
//			}
			System.out.println("Update button_stats table successfully");
		}

	}
	
	public static void updateWeightStats(WeightStats ws_stats) throws SQLException {
		Connection c = null;
		Statement stmt = null;
		if (ws_stats.getUserId() != null) {
			//try {
				c = connectToDB("//" + serverIP + ":3306/" + dbName, username, username);

				stmt = c.createStatement();

				String sql = "UPDATE weight_stats\nSET ";

				// check which properties need update

				// if (bt_stats.getMean_button_freq() != null) {
				sql += "mean_ws_freq = " + ws_stats.getMean_ws_freq();
				// }

				if (ws_stats.getMean_measure_time() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "mean_measure_time = " + ws_stats.getMean_measure_time();
				}
				if (ws_stats.getMax_weight() != null) {
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "max_weight_date = CASE WHEN max_weight < " + ws_stats.getMax_weight() + " THEN \'"
							+ ws_stats.getMax_weight_date() + "\' ELSE max_weight_date END";
					sql += ",\n";
					sql += "max_weight = CASE WHEN max_weight < " + ws_stats.getMax_weight() + " THEN "
							+ ws_stats.getMax_weight() + " ELSE max_weight END";
					
				}
				if (ws_stats.getMin_weight() != null) {
					
					if (sql.contains("=")) {
						sql += ",\n";
					}
					sql += "min_weight_date = CASE WHEN min_weight > " + ws_stats.getMin_weight() + " THEN \'"
							+ ws_stats.getMin_weight_date() + "\' ELSE min_weight_date END";
					sql += ",\n";
					sql += "min_weight = CASE WHEN min_weight > " + ws_stats.getMin_weight() + " THEN "
							+ ws_stats.getMin_weight() + " ELSE min_weight END";
				}
				if (sql.contains("=")) {
					sql += ",\n";
				}
				sql += "total_measurements = total_measurements + " + ws_stats.getTotal_measurements();
				
				sql += "\n";
				sql += "WHERE\n" + "             (userId=\'" + ws_stats.getUserId() + "\');";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
//			} catch (Exception e) {
//				System.err.println(e.getClass().getName() + ": " + e.getMessage());
//				// System.exit(0);
//				return;
//			}
			System.out.println("Update weight_stats table successfully");
		}

	}

	public static void createUserInMySqlDB(String username, String dbName) {

		Connection c = null;
		Statement stmt = null;
		// int row = 0;

		try {
			c = connectToMySqlDB("//" + serverIP + ":3306/" + dbName);
			c.setAutoCommit(false);

			// DatabaseMetaData md = c.getMetaData();
			// ResultSet rs = md.getTables(null, null, "%", null);
			// while (rs.next()) {
			// System.out.println(rs.getString(3));
			// }

			stmt = c.createStatement();

			String sql = "CREATE USER '" + username + "'@'" + serverIP + "';";
			stmt.executeUpdate(sql);
			// get key
			// row = stmt.getGeneratedKeys().getInt(1);
			// System.out.println(row);

			stmt = c.createStatement();

			String new_sql = "GRANT ALL PRIVILEGES ON " + dbName + ".* To '" + username
					+ "'@'" + serverIP + "' IDENTIFIED BY '" + username + "';";
			stmt.executeUpdate(new_sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		System.out.println("Records created successfully");
		// return row;

	}

	public static boolean checkMySqlUserExists(String username) {
		boolean exists = false;
		Connection c = null;
		Statement statement;
		ResultSet resultSet;
		String query = "SELECT host, user, password FROM user WHERE user = '" + username + "';";

		try {

			c = connectToMySqlDB("//" + serverIP + ":3306/mysql");
			if (c != null) {
				statement = c.createStatement();
				resultSet = statement.executeQuery(query);
				if (resultSet.next()) {
					exists = true;
				} else {
					exists = false;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return exists;
	}

	public static boolean checkIfRowExists(String queryString) {
		boolean exists = false;
		Connection c = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			c = connectToDB("//" + serverIP + ":3306/" + dbName, username, username);
			if (c != null) {
				statement = c.createStatement();
				resultSet = statement.executeQuery(queryString);
				if (resultSet.next()) {
					exists = true;
				} else {
					exists = false;
				}
			}
			resultSet.close();
			statement.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return exists;
	}

	public static ArrayList<Frequencies> selectFreqQuery(String query) {

		Connection c = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Frequencies u = new Frequencies();
		ArrayList<Frequencies> freq_array = new ArrayList<Frequencies>();

		try {

			c = connectToDB("//" + serverIP + ":3306/" + dbName, username, username);
			if (c != null) {
				statement = c.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int freq_id = resultSet.getInt("freq_id");
					String userId = resultSet.getString("userId");
					int bl_freq = resultSet.getInt("bl_freq");
					int button_freq = resultSet.getInt("button_freq");
					int weight_freq = resultSet.getInt("weight_freq");
					int week = resultSet.getInt("week");
					int year = resultSet.getInt("year");
					Date start_date = resultSet.getDate("start_date");
					Date end_date = resultSet.getDate("end_date");
					u = new Frequencies(freq_id, userId, bl_freq, button_freq, weight_freq, week, year, start_date,
							end_date);
					freq_array.add(u);
				}
			}
			resultSet.close();
			statement.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return freq_array;
	}

	public static BloodPressureStats selectBLStatsQuery(String query) {

		Connection c = null;
		Statement statement = null;
		ResultSet resultSet = null;
		BloodPressureStats u = new BloodPressureStats();

		try {

			c = connectToDB("//" + serverIP + ":3306/" + dbName, username, username);
			if (c != null) {
				statement = c.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					String userId = resultSet.getString("userId");
					Integer mean_bl_freq = resultSet.getInt("mean_bl_freq");
					Integer mean_systolic = resultSet.getInt("mean_systolic");
					Integer mean_diastolic = resultSet.getInt("mean_diastolic");
					Integer mean_heart_rate = resultSet.getInt("mean_heart_rate");
					Integer max_systolic = resultSet.getInt("max_systolic");
					Integer min_systolic = resultSet.getInt("min_systolic");
					Date max_systolic_date = resultSet.getDate("max_systolic_date");
					Date min_systolic_date = resultSet.getDate("min_systolic_date");
					Integer max_diastolic = resultSet.getInt("max_diastolic");
					Integer min_diastolic = resultSet.getInt("min_diastolic");
					Date max_diastolic_date = resultSet.getDate("max_diastolic_date");
					Date min_diastolic_date = resultSet.getDate("min_diastolic_date");
					Integer total_measurements = resultSet.getInt("total_measurements");
					Integer hypo_measurements = resultSet.getInt("hypo_measurements");
					Integer normal_measurements = resultSet.getInt("normal_measurements");
					Integer hyper1_measurements = resultSet.getInt("hyper1_measurements");
					Integer hyper2_measurements = resultSet.getInt("hyper2_measurements");
					Integer hypercrisis_measurements = resultSet.getInt("hypercrisis_measurements");
					int mean_measure_time = resultSet.getInt("mean_measure_time");

					u = new BloodPressureStats(userId, mean_bl_freq, mean_systolic, mean_diastolic, mean_heart_rate,
							max_systolic, min_systolic, max_systolic_date, min_systolic_date, max_diastolic,
							min_diastolic, max_diastolic_date, min_diastolic_date, total_measurements,
							hypo_measurements, normal_measurements, hyper1_measurements, hyper2_measurements,
							hypercrisis_measurements, mean_measure_time);
				}
			}
			resultSet.close();
			statement.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return u;
	}

	public static void createDB(String dbName) {
		// Create DB if it does not exist
		boolean DBExists = false;
		Connection c = null;
		Statement statement;

		String query = "CREATE DATABASE IF NOT EXISTS " + dbName + ";";
		try {
			// Connect to MySql Server
			c = connectToDB("//" + serverIP + ":3306", "root", "");
			if (c != null) {

				DatabaseMetaData meta = c.getMetaData();
				ResultSet rs = meta.getCatalogs();
				while (rs.next()) {
					String db = rs.getString(1);
					if (db.equalsIgnoreCase(dbName)) {
						DBExists = true;
						System.out.println(db + " exists. No need to create it!");
					}
				}
				rs.close();

				if (!DBExists) {
					System.out.println("Creating database...");
					statement = c.createStatement();
					statement.executeUpdate(query);

					ResultSet resultSet = c.getMetaData().getCatalogs();

					// iterate each catalog in the ResultSet
					while (resultSet.next()) {
						// Get the database name, which is at position 1
						String databaseName = resultSet.getString(1);
						if (databaseName.equalsIgnoreCase(dbName)) {
							System.out.println("Database " + dbName + " created successfully...");
						}
					}
					resultSet.close();
				}

			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
	}

	public static Connection connectToMySqlDB(String location) {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// create db if it does not exist
			c = DriverManager.getConnection("jdbc:mysql:" + location, "root", "");
			System.out.println("Opened databaseat at '" + location + "' successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return c;
	}

	public static Connection connectToDB(String location, String user, String pass) {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// create db if it does not exist
			c = DriverManager.getConnection("jdbc:mysql:" + location, user, pass);
			System.out.println("Opened database at '" + location + "' successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return c;
	}

}

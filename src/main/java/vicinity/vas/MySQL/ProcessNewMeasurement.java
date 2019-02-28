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

import java.util.Calendar;
import static java.lang.Math.toIntExact;

import java.sql.Date;
import java.sql.SQLException;

import vicinity.vas.VCNT_VAS_IndividualStatistics.Requests.Measurement;
import vicinity.vas.VCNT_VAS_IndividualStatistics.Requests.Properties;
import vicinity.vas.utilities.Utils;


public class ProcessNewMeasurement {

	public static void process(Measurement request) throws SQLException {
		/**
		 * Inputs
		 */
		String userId = request.getUserId();
		String adapterId = request.getAdapterId();
		String deviceType = request.getDeviceType();
		String deviceId = request.getDeviceId();
		String client = request.getClient();
		Date date = request.getDateRecorded();

		// panic button measurement
		String event_type = null;
		// blood pressure measurement
		Double systolic = null;
		Double diastolic = null;
		Integer pulse = null;
		// weight measurement
		Double weight = null;
		Double bmi = null;
		for (Properties prop : request.getProperties()) {
			String c = prop.getCode();
			if (c.equals(Utils.SYSTOLIC)) {
				systolic = prop.getSystolic();
			} else if (c.equals(Utils.DIASTOLIC)) {
				diastolic = prop.getDiastolic();
			} else if (c.equals(Utils.PULSE)) {
				pulse = prop.getHeart_rate();
			} else if (c.equals(Utils.WEIGHT)) {
				weight = prop.getWeight();
			} else if (c.equals(Utils.BMI)) {
				bmi = prop.getBmi();
			} else if (c.equals(Utils.EVENT_TYPE)) {
				event_type = prop.getEvent_type();
			} else {
				System.out.println("Unknown measurement code");
			}
		}
		printInput(userId, adapterId, deviceType, deviceId, client, event_type, systolic, diastolic, pulse, weight, bmi,
				date);
		/**
		 * Users table
		 */
		// Insert new vicinity user if it does not exist
		// userType and usualMeasureTime will be later calculated. userType
		// could be frequent, normal, sparse. usualMeasureTime could be morning,
		// afternoon, evening, night
		insertUserTable(userId, deviceType);
		// TODO: if user exists, update (he might have more than one devices)

		/**
		 * Update Frequencies
		 */

		int week = MySQLMethods.getWeek(date);
		System.out.println(week);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		insertFrequenciesTable(userId, deviceType, year, week, date);

		if (deviceType.equals(Utils.BLOODPRESSURE_MONITOR)) {
			// Insert or Update bloodpressure stats
			insertBlStatsTable(userId, deviceType, systolic, diastolic, pulse, date);
			// TODO : check measurement and create notification
		}
		if (deviceType.equals(Utils.PANIC_BUTTON)) {
			// Insert or Update panic button stats
			insertButtonStatsTable(userId, deviceType, event_type, date);
		}
		if (deviceType.equals(Utils.WEIGHT_SCALE)) {
			// Insert or Update weight scale stats
			insertWeightStatsTable(userId, deviceType, weight, date);
			// TODO : check measurement and create notification
		}

	}

	private static void insertUserTable(String userId, String deviceType) throws SQLException {
		String userType = "normal";
		String usualMeasureTime = "not calculated";
		boolean hasBL = false;
		boolean hasWS = false;
		boolean hasButton = false;

		if (deviceType.equals(Utils.BLOODPRESSURE_MONITOR)) {
			hasBL = true;
		} else if (deviceType.equals(Utils.PANIC_BUTTON)) {
			hasButton = true;
		} else if (deviceType.equals(Utils.WEIGHT_SCALE)) {
			hasWS = true;
		}
		String queryString = "SELECT uid FROM users WHERE (userId = \'" + userId + "\');";
		boolean u_row_exists = MySQLMethods.checkIfRowExists(queryString);
		if (!u_row_exists) {
			String insertString = "(userId, userType, usualMeasureTime, hasBL, hasWS, hasButton) " + "VALUES ( \'"
					+ userId + "\', \'" + userType + "\', \'" + usualMeasureTime + "\', " + hasBL + ", " + hasWS + ", "
					+ hasButton + ");";
			MySQLMethods.insertInTable("users", insertString);
		}
	}

	private static void insertFrequenciesTable(String userId, String deviceType, int year, int week, Date date)
			throws SQLException {
		// Insert new row in frequencies
		// first check if there is a row for this week, year, and userId
		String queryString = "SELECT freq_id FROM frequencies WHERE (userId = \'" + userId + "\' AND week=" + week
				+ " AND year=" + year + ");";
		boolean f_row_exists = MySQLMethods.checkIfRowExists(queryString);
		if (!f_row_exists) {

			int bl_freq = 0;
			int button_freq = 0;
			int weight_freq = 0;

			if (deviceType.equals(Utils.BLOODPRESSURE_MONITOR)) {
				bl_freq = 1;
			} else if (deviceType.equals(Utils.PANIC_BUTTON)) {
				button_freq = 1;
			} else if (deviceType.equals(Utils.WEIGHT_SCALE)) {
				weight_freq = 1;
			}

			java.sql.Date start_date = MySQLMethods.getStartDate(week);
			java.sql.Date end_date = MySQLMethods.getEndDate(date);
			String insertString = "(userId, bl_freq, button_freq, weight_freq, week, year, start_date, end_date ) "
					+ "VALUES ( \'" + userId + "\', " + bl_freq + ", " + button_freq + ", " + weight_freq + ", " + week
					+ ", " + year + ", \'" + start_date + "\', \'" + end_date + "\' );";
			;
			MySQLMethods.insertInTable("frequencies", insertString);
		} else {
			System.out.println("A row for this user, week, year (" + userId + ", " + week + ", " + year
					+ ") already exists and cannot be inserted. Please update!");
			// Get frequency value and add one
			// String query = "SELECT * FROM frequencies WHERE (userId = \'" +
			// userId + "\' AND week=" + week
			// + " AND year=" + year + ");";
			// Frequencies f1 = MySQLMethods.selectFreqQuery(query);

			if (deviceType.equals(Utils.BLOODPRESSURE_MONITOR)) {
				// Update frequencies
				// int blood_pres_freq = f1.getBl_freq();
				int newValue = 1;
				System.out.println("Update blood pressure measurement frequency for user " + userId + " and week "
						+ week + ": " + newValue);
				Frequencies freq = new Frequencies();
				freq.setUserId(userId);
				freq.setWeek(week);
				freq.setYear(year);
				freq.setBl_freq(newValue);
				MySQLMethods.updateFrequencies(freq);

			}
			if (deviceType.equals(Utils.PANIC_BUTTON)) {
				// int button_freq = f1.getButton_freq();
				int newValue = 1;
				System.out.println("Update panic button measurement frequency for user " + userId + " and week " + week
						+ ": " + newValue);
				Frequencies freq = new Frequencies();
				freq.setUserId(userId);
				freq.setWeek(week);
				freq.setYear(year);
				freq.setButton_freq(newValue);
				MySQLMethods.updateFrequencies(freq);

			}
			if (deviceType.equals(Utils.WEIGHT_SCALE)) {
				// int weight_freq = f1.getWeight_freq();
				int newValue = 1;
				System.out.println("Update weight measurement frequency for user " + userId + " and week " + week + ": "
						+ newValue);
				Frequencies freq = new Frequencies();
				freq.setUserId(userId);
				freq.setWeek(week);
				freq.setYear(year);
				freq.setWeight_freq(newValue);
				MySQLMethods.updateFrequencies(freq);

			}
		}
	}

	private static void insertBlStatsTable(String userId, String deviceType, Double systolic, Double diastolic,
			Integer pulse, Date date) throws SQLException {
		String blqueryString = "SELECT bl_id FROM bloodpressure_stats WHERE (userId = \'" + userId + "\');";
		boolean bl_row_exists = MySQLMethods.checkIfRowExists(blqueryString);

		Integer mean_bl_freq = null;
		Integer mean_systolic = null;
		Integer mean_diastolic = null;
		Integer mean_pulse = null;

		Integer max_systolic = systolic.intValue();
		Integer min_systolic = systolic.intValue();
		Date max_systolic_date = date;
		Date min_systolic_date = date;
		Integer max_diastolic = diastolic.intValue();
		Integer min_diastolic = diastolic.intValue();
		Date max_diastolic_date = date;
		Date min_diastolic_date = date;
		Integer total_measurements = 1;
		Integer hypo_measurements = 0;
		Integer normal_measurements = 0;
		Integer hyper1_measurements = 0;
		Integer hyper2_measurements = 0;
		Integer hypercrisis_measurements = 0;

		// check bl
		// I check diastolic only in extreme cases
		if (systolic < 90 || diastolic < 70) {
			hypo_measurements += 1;
		} else if ((systolic >= 90 && systolic < 120)
		// || (diastolic >= 70 && diastolic < 80)
		) {
			normal_measurements += 1;
		} else if ((systolic >= 120 && systolic < 140)
		// || (diastolic >= 80 && diastolic < 90)
		) {
			hyper1_measurements += 1;
		} else if ((systolic >= 140 && systolic < 180)
		// || (diastolic >= 90 && diastolic < 120)
		) {
			hyper2_measurements += 1;
		} else if ((systolic >= 180) || (diastolic >= 120)) {
			hypercrisis_measurements += 1;
		}

//		if (systolic != null) {
//			mean_systolic = systolic.intValue();
//		}
//		if (diastolic != null) {
//			mean_diastolic = diastolic.intValue();
//		}
//		if (pulse != null) {
//			mean_pulse = pulse.intValue();
//		}
//		Date sql_date = date;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(sql_date);
//		// Hours
//		int mean_measure_time = cal.get(Calendar.HOUR_OF_DAY);
//		// returns 2 instead of 14 for 14.00
//		System.out.println("Hours: " + mean_measure_time);

		if (!bl_row_exists) {

			String insertString = "(userId, mean_bl_freq, mean_systolic, mean_diastolic, "
					+ "mean_heart_rate, max_systolic, min_systolic, max_systolic_date, min_systolic_date, "
					+ "max_diastolic, min_diastolic, max_diastolic_date, min_diastolic_date, total_measurements, "
					+ "hypo_measurements,normal_measurements, hyper1_measurements, hyper2_measurements, "
					+ "hypercrisis_measurements, mean_measure_time) " + "VALUES ( \'" + userId + "\', " + mean_bl_freq
					+ ", " + mean_systolic + ", " + mean_diastolic + ", " + mean_pulse + ", " + max_systolic + ", "
					+ min_systolic + ", \'" + max_systolic_date + "\', \'" + min_systolic_date + "\', " + max_diastolic
					+ ", " + min_diastolic + ", \'" + max_diastolic_date + "\', \'" + min_diastolic_date + "\', "
					+ total_measurements + ", " + hypo_measurements + ", " + normal_measurements + ", "
					+ hyper1_measurements + ", " + hyper2_measurements + ", " + hypercrisis_measurements + ", null );";
			;
			MySQLMethods.insertInTable("bloodpressure_stats", insertString);
		} else {
			System.out.println("A row for this user (" + userId
					+ ") already exists and cannot be inserted. Please update!");
			// query values for this userId
			BloodPressureStats bl_stats = new BloodPressureStats(userId, mean_bl_freq, mean_systolic, mean_diastolic,
					mean_pulse, max_systolic, min_systolic, max_systolic_date, min_systolic_date, max_diastolic,
					min_diastolic, max_diastolic_date, min_diastolic_date, total_measurements, hypo_measurements,
					normal_measurements, hyper1_measurements, hyper2_measurements, hypercrisis_measurements,
					null);
			// update row
			MySQLMethods.updateBloodPressureStats(bl_stats);
		}
	}

	private static void insertButtonStatsTable(String userId, String deviceType, String event_type, Date date)
			throws SQLException {
		String queryString = "SELECT button_id FROM button_stats WHERE (userId = \'" + userId + "\');";
		boolean bt_row_exists = MySQLMethods.checkIfRowExists(queryString);

		Integer mean_bt_freq = null;///////////////////////////////////////////////////////// todo
									///////////////////////////////////////////////////////// ////////////////////
		Integer total_measurements = 1;
		Integer BaseBtn_measurements = 0;
		Integer PanicBtn_measurements = 0;
		Integer Fall_measurements = 0;
		if (event_type.equals("BaseBtn")) {
			BaseBtn_measurements += 1;
		} else if (event_type.equals("PanicBtn")) {
			PanicBtn_measurements += 1;
		} else if (event_type.equals("Fall")) {
			Fall_measurements += 1;
		}
//		Date sql_date = date;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(sql_date);
//		// Hours
//		int mean_measure_time = cal.get(Calendar.HOUR_OF_DAY);
//		// returns 2 instead of 14 for 14.00
//		System.out.println("Hours: " + mean_measure_time);

		if (!bt_row_exists) {
			String insertString = "(userId, mean_button_freq, total_measurements, BaseBtn_measurements, "
					+ "PanicBtn_measurements, Fall_measurements, mean_measure_time) " + "VALUES ( \'" + userId + "\', "
					+ mean_bt_freq + ", " + total_measurements + ", " + BaseBtn_measurements + ", "
					+ PanicBtn_measurements + ", " + Fall_measurements + ", null );";
			;
			MySQLMethods.insertInTable("button_stats", insertString);
		} else {
			System.out.println("A row for this user (" + userId
					+ ") already exists and cannot be inserted. Please update!");
			// update values for this userId
			ButtonStats bl_stats = new ButtonStats(userId, mean_bt_freq, total_measurements, BaseBtn_measurements,
					PanicBtn_measurements, Fall_measurements, null);
			// update row
			MySQLMethods.updateButtonStats(bl_stats);
		}
	}
	private static void insertWeightStatsTable(String userId, String deviceType, Double weight, Date date)
			throws SQLException {
		Double max_weight = weight;
		Double min_weight = weight;
		Date max_weight_date = date;
		Date min_weight_date = date;
		int total_measurements = 1;
		String queryString = "SELECT ws_id FROM weight_stats WHERE (userId = \'" + userId + "\');";
		boolean ws_row_exists = MySQLMethods.checkIfRowExists(queryString);
		if (!ws_row_exists) {
			String insertString = "(userId, mean_ws_freq, mean_weight, max_weight, min_weight, "
					+ "max_weight_date, min_weight_date, total_measurements, mean_measure_time) " 
					+ "VALUES ( \'" + userId + "\', null, null , " + max_weight + ", "
					+ min_weight + ", \'" + max_weight_date + "\', \'" + min_weight_date + "\'," 
					+ total_measurements + ", null );";
			;
			MySQLMethods.insertInTable("weight_stats", insertString);
		} else {
			System.out.println("A row for this user (" + userId
					+ ") already exists and cannot be inserted. Please update!");
			// update values for this userId
			WeightStats ws_stats = new WeightStats(userId, null, null, max_weight, min_weight, 
					max_weight_date, min_weight_date, total_measurements, null);
			// update row
			MySQLMethods.updateWeightStats(ws_stats);
		}
	}

	private static void printInput(String userId, String adapterId, String deviceType, String deviceId, String client,
			String event_type, Double systolic, Double diastolic, Integer pulse, Double weight, Double bmi, Date date) {
		System.out.println("UserId: " + userId);
		System.out.println("AdapterId: " + adapterId);
		System.out.println("DeviceType: " + deviceType);
		System.out.println("DeviceId: " + deviceId);
		System.out.println("Client: " + client);
		if (event_type != null) {
			System.out.println("Event type: " + event_type);
		}
		if (systolic != null) {
			System.out.println("Systolic: " + systolic);
		}
		if (diastolic != null) {
			System.out.println("Diastolic: " + diastolic);
		}
		if (pulse != null) {
			System.out.println("Pulse: " + pulse);
		}
		if (weight != null) {
			System.out.println("Weight: " + weight);
		}
		if (bmi != null) {
			System.out.println("BMI: " + bmi);
		}
		System.out.println("DateRecorded: " + date);
	}

}

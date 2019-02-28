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

package vicinity.vas.VCNT_VAS_IndividualStatistics;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import vicinity.vas.MySQL.MySQLMethods;
import vicinity.vas.VCNT_VAS_IndividualStatistics.CallRestfulService.Variable;

@SpringBootApplication
public class VcntVasIndividualStatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VcntVasIndividualStatisticsApplication.class, args);
		createTables();
		ArrayList<Variable> requestHeaderList = new ArrayList<Variable>();
		String entity = ThingDescription.readTDFile();
		System.out.println(entity);
		String wsUrl = "http://localhost:9997/agent/objects";
		if (args.length != 0) {
			wsUrl = "http://" + args[0] + ":9997/agent/objects";
		}
		// System.out.println(wsUrl);
		ArrayList<Variable> inputs = new ArrayList<Variable>();
		String result = CallRestfulService.callService(wsUrl, "POST", inputs, entity, requestHeaderList);
		System.out.println(result);
	}

	public static void createTables() {
		// Create tables
		// Users
		////////////////////////////////////////////////////////// add table for
		// mean frequency, has
		String table_name = "users";
		String sql = "CREATE TABLE IF NOT EXISTS " + table_name + " "
				+ "(uid int NOT NULL AUTO_INCREMENT PRIMARY KEY, userId VARCHAR(255) NOT NULL UNIQUE,"
				+ " userType           TEXT, usualMeasureTime           TEXT, hasBL           BOOLEAN, hasWS           BOOLEAN, hasButton           BOOLEAN)";
		MySQLMethods.createTable(table_name, sql);

		// Frequencies
		table_name = "frequencies";
		sql = "CREATE TABLE IF NOT EXISTS " + table_name + " " + "(freq_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ " userId           VARCHAR(255)    NOT NULL," + " bl_freq           int,"
				+ " button_freq           int," + " weight_freq           int," + " week           int,"
				+ " year           int," + " start_date           DATE," + " end_date           DATE,"
				+ " FOREIGN KEY (userId) REFERENCES users(userId)" + ")";
		MySQLMethods.createTable(table_name, sql);

		// BloodPressure_Stats
		table_name = "bloodpressure_stats";
		sql = "CREATE TABLE IF NOT EXISTS " + table_name + " " + "(bl_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ " userId           VARCHAR(255)    NOT NULL," + " mean_bl_freq           int,"
				+ " mean_systolic           int," + " mean_diastolic           int," + " mean_heart_rate           int,"
				+ " max_systolic           double," + " min_systolic           double,"
				+ " max_systolic_date           DATE," + " min_systolic_date           DATE,"
				+ " max_diastolic           double," + " min_diastolic           double,"
				+ " max_diastolic_date           DATE," + " min_diastolic_date           DATE,"
				+ " total_measurements           int," + " hypo_measurements           int,"
				+ " normal_measurements           int," + " hyper1_measurements           int,"
				+ " hyper2_measurements           int," + " hypercrisis_measurements           int,"
				+ " mean_measure_time           int," + " FOREIGN KEY (userId) REFERENCES users(userId)" + ")";
		MySQLMethods.createTable(table_name, sql);
		// Button_Stats
		table_name = "button_stats";
		sql = "CREATE TABLE IF NOT EXISTS " + table_name + " " + "(button_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ " userId           VARCHAR(255)    NOT NULL," + " mean_button_freq           int,"
				+ " total_measurements           int," + " BaseBtn_measurements           int,"
				+ " PanicBtn_measurements           int," + " Fall_measurements           int,"
				+ " mean_measure_time           int," + " FOREIGN KEY (userId) REFERENCES users(userId)" + ")";
		MySQLMethods.createTable(table_name, sql);
		// Weight_Stats
		table_name = "weight_stats";
		sql = "CREATE TABLE IF NOT EXISTS " + table_name + " " + "(ws_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ " userId           VARCHAR(255)    NOT NULL," + " mean_ws_freq           int,"
				+ " mean_weight           double," + " max_weight           double," + " min_weight           double,"
				+ " max_weight_date           DATE," + " min_weight_date           DATE,"
				+ " total_measurements		int," + " mean_measure_time           int,"
				+ " FOREIGN KEY (userId) REFERENCES users(userId)" + ")";
		MySQLMethods.createTable(table_name, sql);
	}
}

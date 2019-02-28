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

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import vicinity.vas.VCNT_VAS_IndividualStatistics.Requests.UserProfileRequest;


public class LatestFrequencies {

	public static void getFreqs(UserProfileRequest request) throws SQLException {
		// week
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate));

		java.sql.Date sqlDate = java.sql.Date.valueOf(dtf.format(localDate));
		int week = MySQLMethods.getWeek(sqlDate);
		
		String userId = request.getUserId();
		String query = "";
		ArrayList<Frequencies> freq_array = MySQLMethods.selectFreqQuery(query);
		
	}
}

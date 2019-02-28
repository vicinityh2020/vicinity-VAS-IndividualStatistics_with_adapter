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

public class User {
	String userId;
	String userType;
	String usualMeasureTime;
	Boolean hasBL;
	Boolean hasWS;
	Boolean hasButton;
//	Integer bl_freq;
//	Integer button_freq;
//	Integer week;
//	Integer year;

	public User() {

	}

	public User(String userId, String userType, String usualMeasureTime, Boolean hasBL, Boolean hasWS, Boolean hasButton) {
		this.userId = userId;
		this.userType = userType;
		this.usualMeasureTime = usualMeasureTime;
		this.hasBL = hasBL;
		this.hasWS = hasWS;
		this.hasButton = hasButton;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUsualMeasureTime() {
		return usualMeasureTime;
	}

	public void setUsualMeasureTime(String usualMeasureTime) {
		this.usualMeasureTime = usualMeasureTime;
	}

	public Boolean getHasBL() {
		return hasBL;
	}

	public void setHasBL(Boolean hasBL) {
		this.hasBL = hasBL;
	}

	public Boolean getHasWS() {
		return hasWS;
	}

	public void setHasWS(Boolean hasWS) {
		this.hasWS = hasWS;
	}

	public Boolean getHasButton() {
		return hasButton;
	}

	public void setHasButton(Boolean hasButton) {
		this.hasButton = hasButton;
	}

//	public Integer getBl_freq() {
//		return bl_freq;
//	}
//
//	public void setBl_freq(int bl_freq) {
//		this.bl_freq = bl_freq;
//	}
//
//	public Integer getButton_freq() {
//		return button_freq;
//	}
//
//	public void setButton_freq(int button_freq) {
//		this.button_freq = button_freq;
//	}
//
//	public Integer getWeek() {
//		return week;
//	}
//
//	public void setWeek(int week) {
//		this.week = week;
//	}
//	public Integer getYear() {
//		return year;
//	}
//
//	public void setYear(int year) {
//		this.year = year;
//	}
}

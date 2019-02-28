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

import java.sql.Date;

public class Frequencies {

	Integer freq_id;
	String userId;          
	Integer bl_freq;        
	Integer button_freq;
	Integer weight_freq;
	Integer week;        
	Integer year;           
	java.sql.Date start_date;          
	java.sql.Date end_date ;
	
	public Frequencies(){
		
	}
	
	public Frequencies(Integer freq_id, String userId, Integer bl_freq, Integer button_freq, Integer weight_freq, Integer week,
			Integer year, Date start_date, Date end_date) {
		super();
		this.freq_id = freq_id;
		this.userId = userId;
		this.bl_freq = bl_freq;
		this.button_freq = button_freq;
		this.weight_freq = weight_freq;
		this.week = week;
		this.year = year;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	public Integer getFreq_id() {
		return freq_id;
	}
	public void setFreq_id(Integer freq_id) {
		this.freq_id = freq_id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getBl_freq() {
		return bl_freq;
	}
	public void setBl_freq(Integer bl_freq) {
		this.bl_freq = bl_freq;
	}
	public Integer getButton_freq() {
		return button_freq;
	}
	public void setButton_freq(Integer button_freq) {
		this.button_freq = button_freq;
	}
	public Integer getWeight_freq() {
		return weight_freq;
	}

	public void setWeight_freq(Integer weight_freq) {
		this.weight_freq = weight_freq;
	}

	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public java.sql.Date getStart_date() {
		return start_date;
	}
	public void setStart_date(java.sql.Date start_date) {
		this.start_date = start_date;
	}
	public java.sql.Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(java.sql.Date end_date) {
		this.end_date = end_date;
	}         
}

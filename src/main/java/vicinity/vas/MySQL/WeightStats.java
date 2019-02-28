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

public class WeightStats {

	String userId;
	Integer mean_ws_freq;
	Double mean_weight;
	Double max_weight ;
	Double min_weight;
	Date max_weight_date ;
	Date min_weight_date;
	int total_measurements ;
	Integer mean_measure_time;
	
	WeightStats(){
		
	}
	public WeightStats(String userId, Integer mean_ws_freq, Double mean_weight, Double max_weight, Double min_weight,
			Date max_weight_date, Date min_weight_date, int total_measurements, Integer mean_measure_time) {
		
		this.userId = userId;
		this.mean_ws_freq = mean_ws_freq;
		this.mean_weight = mean_weight;
		this.max_weight = max_weight;
		this.min_weight = min_weight;
		this.max_weight_date = max_weight_date;
		this.min_weight_date = min_weight_date;
		this.total_measurements = total_measurements;
		this.mean_measure_time = mean_measure_time;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getMean_ws_freq() {
		return mean_ws_freq;
	}
	public void setMean_ws_freq(Integer mean_ws_freq) {
		this.mean_ws_freq = mean_ws_freq;
	}
	public Double getMean_weight() {
		return mean_weight;
	}
	public void setMean_weight(Double mean_weight) {
		this.mean_weight = mean_weight;
	}
	public Double getMax_weight() {
		return max_weight;
	}
	public void setMax_weight(Double max_weight) {
		this.max_weight = max_weight;
	}
	public Double getMin_weight() {
		return min_weight;
	}
	public void setMin_weight(Double min_weight) {
		this.min_weight = min_weight;
	}
	public Date getMax_weight_date() {
		return max_weight_date;
	}
	public void setMax_weight_date(Date max_weight_date) {
		this.max_weight_date = max_weight_date;
	}
	public Date getMin_weight_date() {
		return min_weight_date;
	}
	public void setMin_weight_date(Date min_weight_date) {
		this.min_weight_date = min_weight_date;
	}
	public int getTotal_measurements() {
		return total_measurements;
	}
	public void setTotal_measurements(int total_measurements) {
		this.total_measurements = total_measurements;
	}
	public Integer getMean_measure_time() {
		return mean_measure_time;
	}
	public void setMean_measure_time(Integer mean_measure_time) {
		this.mean_measure_time = mean_measure_time;
	}
}

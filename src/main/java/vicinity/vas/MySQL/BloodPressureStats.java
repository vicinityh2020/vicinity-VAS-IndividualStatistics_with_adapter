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

public class BloodPressureStats {

	String userId;
	Integer mean_bl_freq;
	Integer mean_systolic;
	Integer mean_diastolic;
	Integer mean_pulse;
	Integer max_systolic;
	Integer min_systolic ;
	Date max_systolic_date ;
	Date min_systolic_date;
	Integer max_diastolic;
	Integer min_diastolic ;
	Date max_diastolic_date;
	Date min_diastolic_date;
	int total_measurements ;
	int hypo_measurements;
	int normal_measurements ;
	int hyper1_measurements ;
	int hyper2_measurements ;
	int hypercrisis_measurements ;
	Integer mean_measure_time;
	
	public BloodPressureStats(){
		
	}
	
	
	public BloodPressureStats(String userId, Integer mean_bl_freq, Integer mean_systolic, Integer mean_diastolic,
			Integer mean_pulse, Integer max_systolic, Integer min_systolic, Date max_systolic_date,
			Date min_systolic_date, Integer max_diastolic, Integer min_diastolic, Date max_diastolic_date,
			Date min_diastolic_date, int total_measurements, int hypo_measurements,
			int normal_measurements, int hyper1_measurements, int hyper2_measurements, int hypercrisis_measurements, Integer mean_measure_time) {
		
		this.userId = userId;
		this.mean_bl_freq = mean_bl_freq;
		this.mean_systolic = mean_systolic;
		this.mean_diastolic = mean_diastolic;
		this.mean_pulse = mean_pulse;
		this.max_systolic = max_systolic;
		this.min_systolic = min_systolic;
		this.max_systolic_date = max_systolic_date;
		this.min_systolic_date = min_systolic_date;
		this.max_diastolic = max_diastolic;
		this.min_diastolic = min_diastolic;
		this.max_diastolic_date = max_diastolic_date;
		this.min_diastolic_date = min_diastolic_date;
		this.mean_measure_time = mean_measure_time;
		this.total_measurements = total_measurements;
		this.hypo_measurements = hypo_measurements;
		this.normal_measurements = normal_measurements;
		this.hyper1_measurements = hyper1_measurements;
		this.hyper2_measurements = hyper2_measurements;
		this.hypercrisis_measurements = hypercrisis_measurements;
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getMean_bl_freq() {
		return mean_bl_freq;
	}
	public void setMean_bl_freq(Integer mean_bl_freq) {
		this.mean_bl_freq = mean_bl_freq;
	}
	public Integer getMean_systolic() {
		return mean_systolic;
	}
	public void setMean_systolic(Integer mean_systolic) {
		this.mean_systolic = mean_systolic;
	}
	public Integer getMean_pulse() {
		return mean_pulse;
	}

	public void setMean_pulse(Integer mean_pulse) {
		this.mean_pulse = mean_pulse;
	}

	public Integer getMax_systolic() {
		return max_systolic;
	}

	public void setMax_systolic(Integer max_systolic) {
		this.max_systolic = max_systolic;
	}

	public Integer getMin_systolic() {
		return min_systolic;
	}

	public void setMin_systolic(Integer min_systolic) {
		this.min_systolic = min_systolic;
	}

	public Date getMax_systolic_date() {
		return max_systolic_date;
	}

	public void setMax_systolic_date(Date max_systolic_date) {
		this.max_systolic_date = max_systolic_date;
	}

	public Date getMin_systolic_date() {
		return min_systolic_date;
	}

	public void setMin_systolic_date(Date min_systolic_date) {
		this.min_systolic_date = min_systolic_date;
	}

	public Integer getMax_diastolic() {
		return max_diastolic;
	}

	public void setMax_diastolic(Integer max_diastolic) {
		this.max_diastolic = max_diastolic;
	}

	public Integer getMin_diastolic() {
		return min_diastolic;
	}

	public void setMin_diastolic(Integer min_diastolic) {
		this.min_diastolic = min_diastolic;
	}

	public Date getMax_diastolic_date() {
		return max_diastolic_date;
	}

	public void setMax_diastolic_date(Date max_diastolic_date) {
		this.max_diastolic_date = max_diastolic_date;
	}

	public Date getMin_diastolic_date() {
		return min_diastolic_date;
	}

	public void setMin_diastolic_date(Date min_diastolic_date) {
		this.min_diastolic_date = min_diastolic_date;
	}

	public int getTotal_measurements() {
		return total_measurements;
	}

	public void setTotal_measurements(int total_measurements) {
		this.total_measurements = total_measurements;
	}

	public int getHypo_measurements() {
		return hypo_measurements;
	}

	public void setHypo_measurements(int hypo_measurements) {
		this.hypo_measurements = hypo_measurements;
	}

	public int getNormal_measurements() {
		return normal_measurements;
	}

	public void setNormal_measurements(int normal_measurements) {
		this.normal_measurements = normal_measurements;
	}

	public int getHyper1_measurements() {
		return hyper1_measurements;
	}

	public void setHyper1_measurements(int hyper1_measurements) {
		this.hyper1_measurements = hyper1_measurements;
	}

	public int getHyper2_measurements() {
		return hyper2_measurements;
	}

	public void setHyper2_measurements(int hyper2_measurements) {
		this.hyper2_measurements = hyper2_measurements;
	}

	public int getHypercrisis_measurements() {
		return hypercrisis_measurements;
	}

	public void setHypercrisis_measurements(int hypercrisis_measurements) {
		this.hypercrisis_measurements = hypercrisis_measurements;
	}

	public Integer getMean_diastolic() {
		return mean_diastolic;
	}
	public void setMean_diastolic(Integer mean_diastolic) {
		this.mean_diastolic = mean_diastolic;
	}
	public Integer getMean_heart_rate() {
		return mean_pulse;
	}
	public void setMean_heart_rate(Integer mean_heart_rate) {
		this.mean_pulse = mean_heart_rate;
	}
	public Integer getMean_measure_time() {
		return mean_measure_time;
	}
	public void setMean_measure_time(Integer mean_measure_time) {
		this.mean_measure_time = mean_measure_time;
	}
}

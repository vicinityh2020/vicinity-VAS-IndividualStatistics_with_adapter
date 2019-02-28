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

public class ButtonStats {

	String userId;
	Integer mean_button_freq;
	Integer total_measurements;
	Integer BaseBtn_measurements;
	Integer PanicBtn_measurements;
	Integer Fall_measurements;
	Integer mean_measure_time;
	
	
	public ButtonStats(){
		
	}
	public ButtonStats(String userId, Integer mean_button_freq, Integer total_measurements,
	Integer BaseBtn_measurements, Integer PanicBtn_measurements, Integer Fall_measurements, Integer mean_measure_time) {
		this.userId = userId;
		this.mean_button_freq = mean_button_freq;
		this.mean_measure_time = mean_measure_time;
		this.total_measurements = total_measurements;
		this.BaseBtn_measurements = BaseBtn_measurements;
		this.PanicBtn_measurements = PanicBtn_measurements;
		this.Fall_measurements = Fall_measurements;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getMean_button_freq() {
		return mean_button_freq;
	}
	public void setMean_button_freq(Integer mean_button_freq) {
		this.mean_button_freq = mean_button_freq;
	}
	public Integer getTotal_measurements() {
		return total_measurements;
	}
	public void setTotal_measurements(Integer total_measurements) {
		this.total_measurements = total_measurements;
	}
	public Integer getBaseBtn_measurements() {
		return BaseBtn_measurements;
	}
	public void setBaseBtn_measurements(Integer baseBtn_measurements) {
		BaseBtn_measurements = baseBtn_measurements;
	}
	public Integer getPanicBtn_measurements() {
		return PanicBtn_measurements;
	}
	public void setPanicBtn_measurements(Integer panicBtn_measurements) {
		PanicBtn_measurements = panicBtn_measurements;
	}
	public Integer getFall_measurements() {
		return Fall_measurements;
	}
	public void setFall_measurements(Integer fall_measurements) {
		Fall_measurements = fall_measurements;
	}
	public Integer getMean_measure_time() {
		return mean_measure_time;
	}
	public void setMean_measure_time(Integer mean_measure_time) {
		this.mean_measure_time = mean_measure_time;
	}
}

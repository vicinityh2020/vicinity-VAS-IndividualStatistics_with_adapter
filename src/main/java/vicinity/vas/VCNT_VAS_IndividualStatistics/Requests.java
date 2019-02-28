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

import java.sql.Date;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

import vicinity.vas.utilities.Utils;

public class Requests {

	// TODO: Change value to systolic, diastolic, event type etc
	public static class EventRequest {

		private String valueStr;
		private Double valueDbl;
		private Integer valueInt;
		private Boolean valueBoo;

		@JsonProperty("timestamp")
		private String timestamp = "";

		@JsonProperty("userId")
		private String userId = "";

		@JsonSetter("value")
		public void setValueVariable(JsonNode valueVariable) {
			if (valueVariable != null) {
				if (valueVariable.isTextual()) {
					valueStr = valueVariable.asText();
				} else if (valueVariable.isDouble()) {
					valueDbl = valueVariable.asDouble();
				} else if (valueVariable.isInt()) {
					valueInt = valueVariable.asInt();
				} else if (valueVariable.isBoolean()) {
					valueBoo = valueVariable.asBoolean();
				}
			} else {
				System.out.println("Value is null!");
			}
		}

		public Double getvalueDbl() {
			return valueDbl;
		}

		public String getvalueStr() {
			return valueStr;
		}

		public Integer getvalueInt() {
			return valueInt;
		}

		public Boolean getvalueBoo() {
			return valueBoo;
		}

		public String gettimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

	}

	public static class Properties {
		@JsonProperty
		private String code;
		@JsonProperty
		private String unit;

		@JsonSetter("value")
		public void setValue(Object valueVariable) {
			if (valueVariable != null) {
				if (valueVariable instanceof String && code.equals(Utils.EVENT_TYPE)) {
					event_type = (String) valueVariable;
				} else if (valueVariable instanceof Double && code.equals("luminance")) {
					luminance = (Double) valueVariable;
				} else if (valueVariable instanceof Double && code.equals("temperature")) {
					temperature = (Double) valueVariable;
				} else if ((valueVariable instanceof Integer || valueVariable instanceof Long)
						&& code.equals("tamperAlarm")) {
					tamperAlarm = ((Number) valueVariable).longValue();
				} else if ((valueVariable instanceof Integer || valueVariable instanceof Long)
						&& code.equals("motionAlarm")) {
					motionAlarm = ((Number) valueVariable).longValue();
				} else if ((valueVariable instanceof Integer || valueVariable instanceof Long)
						&& code.equals("doorAlarm")) {
					doorAlarm = ((Number) valueVariable).longValue();
				} else if ((valueVariable instanceof Integer || valueVariable instanceof Long)
						&& code.equals("pressed")) {
					pressed = ((Number) valueVariable).longValue();
				} else if (valueVariable instanceof String && code.equals(Utils.SYSTOLIC)) {
					String systolic_str = (String) valueVariable;
					systolic = Double.parseDouble(systolic_str);
				} else if (valueVariable instanceof String && code.equals(Utils.DIASTOLIC)) {
					String diastolic_str = (String) valueVariable;
					diastolic = Double.parseDouble(diastolic_str);
					// diastolic = ((Number) valueVariable).longValue();
				} else if (valueVariable instanceof String && code.equals(Utils.PULSE)) {
					String pulse_str = (String) valueVariable;
					pulse = Integer.parseInt(pulse_str);
				} else if (valueVariable instanceof String && code.equals(Utils.WEIGHT)) {
					String weight_str = (String) valueVariable;
					weight = Double.parseDouble(weight_str);
				} else if (valueVariable instanceof String && code.equals(Utils.BMI)) {
					String bmi_str = (String) valueVariable;
					bmi = Double.parseDouble(bmi_str);
				}
			} else {
				System.out.println("Value is null!");
			}
		}

		private String event_type;

		private Long pressed;

		private Long motionAlarm;

		private Long tamperAlarm;

		private Long doorAlarm;

		private Double luminance;

		private Double temperature;

		private Double systolic;

		private Double diastolic;

		private Integer pulse;

		private Double weight;

		private Double bmi;

		Properties() {

		}

		public Long getMotionAlarm() {
			return motionAlarm;
		}

		public void setMotionAlarm(Long motionAlarm) {
			this.motionAlarm = motionAlarm;
		}

		public Long getTamperAlarm() {
			return tamperAlarm;
		}

		public void setTamperAlarm(Long tamperAlarm) {
			this.tamperAlarm = tamperAlarm;
		}

		public Long getDoorAlarm() {
			return doorAlarm;
		}

		public void setDoorAlarm(Long doorAlarm) {
			this.doorAlarm = doorAlarm;
		}

		public Double getLuminance() {
			return luminance;
		}

		public void setLuminance(Double luminance) {
			this.luminance = luminance;
		}

		public Double getTemperature() {
			return temperature;
		}

		public void setTemperature(Double temperature) {
			this.temperature = temperature;
		}

		public String getEvent_type() {
			return event_type;
		}

		public void setEvent_type(String event_type) {
			this.event_type = event_type;
		}

		public Long getPressed() {
			return pressed;
		}

		public void setPressed(Long pressed) {
			this.pressed = pressed;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public Double getSystolic() {
			return systolic;
		}

		public void setSystolic(Double systolic) {
			this.systolic = systolic;
		}

		public Double getDiastolic() {
			return diastolic;
		}

		public void setDiastolic(Double diastolic) {
			this.diastolic = diastolic;
		}

		public Integer getHeart_rate() {
			return pulse;
		}

		public void setHeart_rate(Integer pulse) {
			this.pulse = pulse;
		}

		public Double getWeight() {
			return weight;
		}

		public void setWeight(Double weight) {
			this.weight = weight;
		}

		public Double getBmi() {
			return bmi;
		}

		public void setBmi(Double bmi) {
			this.bmi = bmi;
		}

	}

	public static class Measurement {
		@JsonProperty
		protected String userId;
		@JsonProperty
		protected String adapterId;
		@JsonProperty
		protected String deviceId;
		@JsonProperty
		protected String deviceType;
		@JsonProperty
		protected String client;
		@JsonProperty("dateRecorded")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
		private Date dateRecorded;
		@JsonProperty("properties")
		private ArrayList<Properties> properties = new ArrayList<Properties>();

		Measurement() {

		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getAdapterId() {
			return adapterId;
		}

		public void setAdapterId(String adapterId) {
			this.adapterId = adapterId;
		}

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public String getDeviceType() {
			return deviceType;
		}

		public void setDeviceType(String deviceType) {
			this.deviceType = deviceType;
		}

		public String getClient() {
			return client;
		}

		public void setClient(String gatewayType) {
			this.client = gatewayType;
		}

		public Date getDateRecorded() {
			return dateRecorded;
		}

		public void setDateRecorded(Date dateRecorded) {
			this.dateRecorded = dateRecorded;
		}

		public ArrayList<Properties> getProperties() {
			return properties;
		}

		public void setProperties(ArrayList<Properties> properties) {
			this.properties = properties;
		}
	}

	public static class NotificationsRequest {

	}

	public static class LatestFrequenciesRequest {

	}

	public static class UserProfileRequest {
		String userId;

		UserProfileRequest(){
			
		}
		public UserProfileRequest(String userId) {
			super();
			this.userId = userId;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}
	}
}

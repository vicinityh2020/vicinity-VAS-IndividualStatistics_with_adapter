package vicinity.vas.VCNT_VAS_IndividualStatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

public class Requests {

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
				}else if (valueVariable.isBoolean()) {
					valueBoo = valueVariable.asBoolean();
				}
			}else{
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
}

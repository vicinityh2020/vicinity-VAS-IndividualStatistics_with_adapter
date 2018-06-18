package vicinity.vas.VCNT_VAS_IndividualStatistics;

import java.util.HashMap;
import java.util.Map;

import vicinity.vas.VCNT_VAS_IndividualStatistics.Requests.EventRequest;

public class ProcessNewMeasurement {

	Map<String, String> myMap = new HashMap<String, String>();

	ProcessNewMeasurement() {
		myMap.put("a", "bloodpressure monitor");
		myMap.put("b", "panic button");
		myMap.put("c", "weight scale");
	}

	protected void check(EventRequest request, String oid) {
		String type = checkDevices(oid);
		if (type != null) {
			if (type.equals("bloodpressure monitor")) {
				checkBloodPressure(request);
				// TODO: Get frequency of this week's bl measurements and
				// increase
				// it by one
			} else if (type.equals("panic button")){
				// TODO: Get frequency of this week's pb measurements and
				// increase
				// it by one
			} else {
				// TODO: Get frequency of this week's ws measurements and
				// increase
				// it by one
			}
		} else {
			System.out.println("The device type does not exist");
		}
	}

	private String checkDevices(String oid) {
		if (myMap.containsKey(oid)) {
			return myMap.get(oid);
		}
		return null;
	}

	private void checkBloodPressure(EventRequest request) {
		if (request.getvalueInt() >= 150) {
			// push notification
			String message = "High bloodpressure";
			System.out.println(message);
		}
		// TODO: Check if time is between 11pm-7am
	}
}

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vicinity.vas.VCNT_VAS_IndividualStatistics.Requests.EventRequest;
import vicinity.vas.VCNT_VAS_IndividualStatistics.Requests.LatestFrequenciesRequest;
import vicinity.vas.VCNT_VAS_IndividualStatistics.Requests.Measurement;
import vicinity.vas.VCNT_VAS_IndividualStatistics.Requests.NotificationsRequest;
import vicinity.vas.VCNT_VAS_IndividualStatistics.Requests.UserProfileRequest;
import vicinity.vas.VCNT_VAS_IndividualStatistics.Responses.Response;
import vicinity.vas.MySQL.*;

@Controller
public class VcntVasIndividualStatisticsController {

	/**
	 * @param request:
	 *            it is forwarded from GDPR VAS
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/vas3.1.2/properties/push_measurement", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> generateResponseEvent(@RequestBody Measurement request) throws Exception {
		System.out.println("Entered PUT property");
		String message = "A request to VAS with id: vas3.1.2 to inform about the change of a property with name: push_measurement was made.";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		String timestamp = dateFormat.format(date);

		if (request.getProperties() != null && !request.getProperties().isEmpty()) {
			try{
				ProcessNewMeasurement.process(request);
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				message = e.toString();
				Response response = new Response(message, timestamp, "500", "true", "/objects/vas3.1.2/properties/push_measurement");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		// Return the response
		Response response = new Response(message, timestamp, "200", "false", "/objects/vas3.1.2/properties/push_measurement");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * @param request:
	 *            it is forwarded from GDPR VAS
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/vas3.1.2/properties/user_profile", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Response generateResponseEvent(@RequestBody UserProfileRequest request) throws Exception {
		System.out.println("Entered PUT property");
		String message = "A request to VAS with id: vas3.1.2 to inform about the change of a property with name: push_measurement was made.";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		String timestamp = dateFormat.format(date);

		// Return the response
		Response response = new Response(message, timestamp, "200", "false", "/objects/vas3.1.2/properties/user_profile");
		return response;
	}

	/**
	 * @param request:
	 *            it is forwarded from GDPR VAS
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/vas3.1.2/properties/latest_frequencies", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Response generateResponseEvent(@RequestBody LatestFrequenciesRequest request) throws Exception {
		System.out.println("Entered PUT property");
		String message = "A request to VAS with id: vas3.1.2 to inform about the change of a property with name: push_measurement was made.";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		String timestamp = dateFormat.format(date);

		// Return the response
		Response response = new Response(message, timestamp, "200", "false", "/objects/vas3.1.2/properties/latest_frequencies");
		return response;
	}

	/**
	 * @param request:
	 *            it is forwarded from GDPR VAS
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/vas3.1.2/properties/notifications", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Response generateResponseEvent(@RequestBody NotificationsRequest request) throws Exception {
		System.out.println("Entered PUT property");
		String message = "A request to VAS with id: vas3.1.2 to inform about the change of a property with name: notifications was made.";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		String timestamp = dateFormat.format(date);

		// Return the response
		Response response = new Response(message, timestamp, "200", "false", "/objects/vas3.1.2/properties/push_measurement");
		return response;
	}

	////////////////////////////////////////// Thing Descriptions
	////////////////////////////////////////// ///////////////////////////////////////////////
	@RequestMapping(value = "/objects", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String generateResponse() throws Exception {
		String response = "";
		response = ThingDescription.readTDFile();
		return response;
	}

}

package vicinity.vas.VCNT_VAS_IndividualStatistics;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vicinity.vas.VCNT_VAS_IndividualStatistics.Requests.EventRequest;
import vicinity.vas.VCNT_VAS_IndividualStatistics.Responses.Response;


@Controller
public class VcntVasIndividualStatisticsController {

	///////////////////////////////////// event
	///////////////////////////////////// ////////////////////////////////////////////////////////////////
	/**
	 * @param eid:
	 *            the service event name (similar to topic) (not the Vicinity
	 *            eid)
	 * @param oid:
	 *            the device/service id in the infrastructure (not the Vicinity
	 *            oid)
	 * @param request:
	 *            it is user specific-here just an example
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/{oid}/events/{eid}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Response generateResponseEvent(@PathVariable("eid") String eid, @PathVariable("oid") String oid,
			@RequestBody EventRequest request) throws Exception {
		System.out.println("Entered PUT event");
		// Do something with the inputs..
		System.out.println("timestamp " + request.gettimestamp());
		System.out.println("uid " + request.getUserId());
		if (request.getvalueStr()!=null){
			System.out.println("String value " + request.getvalueStr());
		} else if (request.getvalueDbl()!=null){
			System.out.println("Double value " + request.getvalueDbl());
		}else if (request.getvalueInt()!=null){
			System.out.println("Integer value " + request.getvalueInt());
		}else if (request.getvalueBoo()!=null){
			System.out.println("Boolean value " + request.getvalueBoo());
		}
		
		ProcessNewMeasurement nm = new ProcessNewMeasurement();
		nm.check(request, oid);

		// Return the response
		Response response = new Response("A request to VAS with id: " + oid
				+ " to inform about the change of an event(topic) with name: " + eid + " was made.");
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

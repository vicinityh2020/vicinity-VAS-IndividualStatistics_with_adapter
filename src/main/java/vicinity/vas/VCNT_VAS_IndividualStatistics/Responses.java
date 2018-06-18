package vicinity.vas.VCNT_VAS_IndividualStatistics;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Responses {

	@XmlRootElement(name = "Response")
	public static class Response {
		private String message;

		public Response() {
		}

		public Response(String message) {
			this.message = message;
		}

		public void setmessage(String message) {
			this.message = message;
		}

		@XmlElement
		public String getmessage() {
			return message;
		}
	}

	
	
}

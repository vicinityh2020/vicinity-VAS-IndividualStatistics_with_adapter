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


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Responses {

	@XmlRootElement(name = "Response")
	public static class Response {
		private String message;
		private String timestamp;
		private String status;
		private String error;
		private String path;

		public Response() {
		}

		public Response(String message, String timestamp, String status, String error, String path) {
			this.message = message;
			this.timestamp = timestamp;
			this.status = status;
			this.error = error;
			this .path = path;
		}

		public void setmessage(String message) {
			this.message = message;
		}

		@XmlElement
		public String getmessage() {
			return message;
		}
		@XmlElement
		public String gettimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}
	}

	
	
}

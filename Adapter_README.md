# 1. Infrastructure overview
Vicinity Adapter for GDPR Value Added Service of MPH Pilot Case.

# 2. Configuration and deployment
## Build using Maven

In the root folder of the project:

`mvn clean install`

## Run
The adapter is the interface of this VAS to Vicinity. In order to run this VAS, in the target folder of the project:

`java -jar adapter-0.0.1-SNAPSHOT.jar`

# 3. Functionality and API

## Endpoints
* GET /objects (optional): Retrieve all devices Thing Descriptions(TDs) for registration to VICINITY. This functionality is optional since auto-registration will be performed, so the adapter will send the TDs to the agent at start-up.

## Functions
*	Send device TDs to the vicinity agent at start up

import http from "../http-common"; 

class MonitoringSessionService {
  getAllMonitoringSessions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/monitoringSession/monitoringSessions`, searchDTO);
  }

  get(monitoringSessionId) {
    return this.getRequest(`/monitoringSession/${monitoringSessionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/monitoringSession?field=${matchData}`, null);
  }

  addMonitoringSession(data) {
    return http.post("/monitoringSession/addMonitoringSession", data);
  }

  update(data) {
  	return http.post("/monitoringSession/updateMonitoringSession", data);
  }
  
  uploadImage(data,monitoringSessionId) {
  	return http.postForm("/monitoringSession/uploadImage/"+monitoringSessionId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new MonitoringSessionService();

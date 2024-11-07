import http from "../http-common"; 

class PowerMeterService {
  getAllPowerMeters(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/powerMeter/powerMeters`, searchDTO);
  }

  get(powerMeterId) {
    return this.getRequest(`/powerMeter/${powerMeterId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/powerMeter?field=${matchData}`, null);
  }

  addPowerMeter(data) {
    return http.post("/powerMeter/addPowerMeter", data);
  }

  update(data) {
  	return http.post("/powerMeter/updatePowerMeter", data);
  }
  
  uploadImage(data,powerMeterId) {
  	return http.postForm("/powerMeter/uploadImage/"+powerMeterId, data);
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

export default new PowerMeterService();

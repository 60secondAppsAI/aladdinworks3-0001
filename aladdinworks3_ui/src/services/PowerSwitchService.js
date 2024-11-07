import http from "../http-common"; 

class PowerSwitchService {
  getAllPowerSwitchs(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/powerSwitch/powerSwitchs`, searchDTO);
  }

  get(powerSwitchId) {
    return this.getRequest(`/powerSwitch/${powerSwitchId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/powerSwitch?field=${matchData}`, null);
  }

  addPowerSwitch(data) {
    return http.post("/powerSwitch/addPowerSwitch", data);
  }

  update(data) {
  	return http.post("/powerSwitch/updatePowerSwitch", data);
  }
  
  uploadImage(data,powerSwitchId) {
  	return http.postForm("/powerSwitch/uploadImage/"+powerSwitchId, data);
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

export default new PowerSwitchService();

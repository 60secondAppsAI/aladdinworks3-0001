import http from "../http-common"; 

class TechnicianService {
  getAllTechnicians(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/technician/technicians`, searchDTO);
  }

  get(technicianId) {
    return this.getRequest(`/technician/${technicianId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/technician?field=${matchData}`, null);
  }

  addTechnician(data) {
    return http.post("/technician/addTechnician", data);
  }

  update(data) {
  	return http.post("/technician/updateTechnician", data);
  }
  
  uploadImage(data,technicianId) {
  	return http.postForm("/technician/uploadImage/"+technicianId, data);
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

export default new TechnicianService();

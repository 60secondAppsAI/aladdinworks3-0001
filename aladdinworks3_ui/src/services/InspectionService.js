import http from "../http-common"; 

class InspectionService {
  getAllInspections(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/inspection/inspections`, searchDTO);
  }

  get(inspectionId) {
    return this.getRequest(`/inspection/${inspectionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/inspection?field=${matchData}`, null);
  }

  addInspection(data) {
    return http.post("/inspection/addInspection", data);
  }

  update(data) {
  	return http.post("/inspection/updateInspection", data);
  }
  
  uploadImage(data,inspectionId) {
  	return http.postForm("/inspection/uploadImage/"+inspectionId, data);
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

export default new InspectionService();

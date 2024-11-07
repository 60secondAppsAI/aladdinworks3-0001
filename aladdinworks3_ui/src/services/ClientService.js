import http from "../http-common"; 

class ClientService {
  getAllClients(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/client/clients`, searchDTO);
  }

  get(clientId) {
    return this.getRequest(`/client/${clientId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/client?field=${matchData}`, null);
  }

  addClient(data) {
    return http.post("/client/addClient", data);
  }

  update(data) {
  	return http.post("/client/updateClient", data);
  }
  
  uploadImage(data,clientId) {
  	return http.postForm("/client/uploadImage/"+clientId, data);
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

export default new ClientService();

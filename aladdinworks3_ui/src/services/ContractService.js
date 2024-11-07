import http from "../http-common"; 

class ContractService {
  getAllContracts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/contract/contracts`, searchDTO);
  }

  get(contractId) {
    return this.getRequest(`/contract/${contractId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/contract?field=${matchData}`, null);
  }

  addContract(data) {
    return http.post("/contract/addContract", data);
  }

  update(data) {
  	return http.post("/contract/updateContract", data);
  }
  
  uploadImage(data,contractId) {
  	return http.postForm("/contract/uploadImage/"+contractId, data);
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

export default new ContractService();

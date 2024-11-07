import http from "../http-common"; 

class VendorService {
  getAllVendors(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/vendor/vendors`, searchDTO);
  }

  get(vendorId) {
    return this.getRequest(`/vendor/${vendorId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/vendor?field=${matchData}`, null);
  }

  addVendor(data) {
    return http.post("/vendor/addVendor", data);
  }

  update(data) {
  	return http.post("/vendor/updateVendor", data);
  }
  
  uploadImage(data,vendorId) {
  	return http.postForm("/vendor/uploadImage/"+vendorId, data);
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

export default new VendorService();

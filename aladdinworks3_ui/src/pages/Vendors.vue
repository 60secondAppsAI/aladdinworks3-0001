<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <vendor-table
            v-if="vendors && vendors.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:vendors="vendors"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-vendors="getAllVendors"
             >

            </vendor-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import VendorTable from "@/components/VendorTable";
import VendorService from "../services/VendorService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    VendorTable,
  },
  data() {
    return {
      vendors: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllVendors(sortBy='vendorId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await VendorService.getAllVendors(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.vendors.length) {
					this.vendors = response.data.vendors;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching vendors:", error);
        }
        
      } catch (error) {
        console.error("Error fetching vendor details:", error);
      }
    },
  },
  mounted() {
    this.getAllVendors();
  },
  created() {
    this.$root.$on('searchQueryForVendorsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllVendors();
    })
  }
};
</script>
<style></style>

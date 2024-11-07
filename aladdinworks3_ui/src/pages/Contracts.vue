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
            <contract-table
            v-if="contracts && contracts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:contracts="contracts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-contracts="getAllContracts"
             >

            </contract-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ContractTable from "@/components/ContractTable";
import ContractService from "../services/ContractService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ContractTable,
  },
  data() {
    return {
      contracts: [],
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
    async getAllContracts(sortBy='contractId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ContractService.getAllContracts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.contracts.length) {
					this.contracts = response.data.contracts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching contracts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching contract details:", error);
      }
    },
  },
  mounted() {
    this.getAllContracts();
  },
  created() {
    this.$root.$on('searchQueryForContractsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllContracts();
    })
  }
};
</script>
<style></style>

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
            <client-table
            v-if="clients && clients.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:clients="clients"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-clients="getAllClients"
             >

            </client-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ClientTable from "@/components/ClientTable";
import ClientService from "../services/ClientService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ClientTable,
  },
  data() {
    return {
      clients: [],
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
    async getAllClients(sortBy='clientId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ClientService.getAllClients(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.clients.length) {
					this.clients = response.data.clients;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching clients:", error);
        }
        
      } catch (error) {
        console.error("Error fetching client details:", error);
      }
    },
  },
  mounted() {
    this.getAllClients();
  },
  created() {
    this.$root.$on('searchQueryForClientsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllClients();
    })
  }
};
</script>
<style></style>

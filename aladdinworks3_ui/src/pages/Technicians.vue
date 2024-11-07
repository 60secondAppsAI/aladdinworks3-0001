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
            <technician-table
            v-if="technicians && technicians.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:technicians="technicians"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-technicians="getAllTechnicians"
             >

            </technician-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import TechnicianTable from "@/components/TechnicianTable";
import TechnicianService from "../services/TechnicianService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    TechnicianTable,
  },
  data() {
    return {
      technicians: [],
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
    async getAllTechnicians(sortBy='technicianId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await TechnicianService.getAllTechnicians(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.technicians.length) {
					this.technicians = response.data.technicians;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching technicians:", error);
        }
        
      } catch (error) {
        console.error("Error fetching technician details:", error);
      }
    },
  },
  mounted() {
    this.getAllTechnicians();
  },
  created() {
    this.$root.$on('searchQueryForTechniciansChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllTechnicians();
    })
  }
};
</script>
<style></style>

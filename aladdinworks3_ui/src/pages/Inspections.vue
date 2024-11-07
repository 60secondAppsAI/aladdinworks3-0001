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
            <inspection-table
            v-if="inspections && inspections.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:inspections="inspections"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-inspections="getAllInspections"
             >

            </inspection-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import InspectionTable from "@/components/InspectionTable";
import InspectionService from "../services/InspectionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    InspectionTable,
  },
  data() {
    return {
      inspections: [],
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
    async getAllInspections(sortBy='inspectionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await InspectionService.getAllInspections(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.inspections.length) {
					this.inspections = response.data.inspections;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching inspections:", error);
        }
        
      } catch (error) {
        console.error("Error fetching inspection details:", error);
      }
    },
  },
  mounted() {
    this.getAllInspections();
  },
  created() {
    this.$root.$on('searchQueryForInspectionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllInspections();
    })
  }
};
</script>
<style></style>

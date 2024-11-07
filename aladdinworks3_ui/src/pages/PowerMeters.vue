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
            <powerMeter-table
            v-if="powerMeters && powerMeters.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:powerMeters="powerMeters"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-power-meters="getAllPowerMeters"
             >

            </powerMeter-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PowerMeterTable from "@/components/PowerMeterTable";
import PowerMeterService from "../services/PowerMeterService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PowerMeterTable,
  },
  data() {
    return {
      powerMeters: [],
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
    async getAllPowerMeters(sortBy='powerMeterId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PowerMeterService.getAllPowerMeters(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.powerMeters.length) {
					this.powerMeters = response.data.powerMeters;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching powerMeters:", error);
        }
        
      } catch (error) {
        console.error("Error fetching powerMeter details:", error);
      }
    },
  },
  mounted() {
    this.getAllPowerMeters();
  },
  created() {
    this.$root.$on('searchQueryForPowerMetersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPowerMeters();
    })
  }
};
</script>
<style></style>

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
            <monitoringSession-table
            v-if="monitoringSessions && monitoringSessions.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:monitoringSessions="monitoringSessions"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-monitoring-sessions="getAllMonitoringSessions"
             >

            </monitoringSession-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MonitoringSessionTable from "@/components/MonitoringSessionTable";
import MonitoringSessionService from "../services/MonitoringSessionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MonitoringSessionTable,
  },
  data() {
    return {
      monitoringSessions: [],
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
    async getAllMonitoringSessions(sortBy='monitoringSessionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MonitoringSessionService.getAllMonitoringSessions(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.monitoringSessions.length) {
					this.monitoringSessions = response.data.monitoringSessions;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching monitoringSessions:", error);
        }
        
      } catch (error) {
        console.error("Error fetching monitoringSession details:", error);
      }
    },
  },
  mounted() {
    this.getAllMonitoringSessions();
  },
  created() {
    this.$root.$on('searchQueryForMonitoringSessionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMonitoringSessions();
    })
  }
};
</script>
<style></style>

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
            <powerSwitch-table
            v-if="powerSwitchs && powerSwitchs.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:powerSwitchs="powerSwitchs"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-power-switchs="getAllPowerSwitchs"
             >

            </powerSwitch-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PowerSwitchTable from "@/components/PowerSwitchTable";
import PowerSwitchService from "../services/PowerSwitchService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PowerSwitchTable,
  },
  data() {
    return {
      powerSwitchs: [],
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
    async getAllPowerSwitchs(sortBy='powerSwitchId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PowerSwitchService.getAllPowerSwitchs(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.powerSwitchs.length) {
					this.powerSwitchs = response.data.powerSwitchs;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching powerSwitchs:", error);
        }
        
      } catch (error) {
        console.error("Error fetching powerSwitch details:", error);
      }
    },
  },
  mounted() {
    this.getAllPowerSwitchs();
  },
  created() {
    this.$root.$on('searchQueryForPowerSwitchsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPowerSwitchs();
    })
  }
};
</script>
<style></style>

import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Racks from  '@/pages/Racks.vue';
import RackDetail from  '@/pages/RackDetail.vue';
import PowerStrips from  '@/pages/PowerStrips.vue';
import PowerStripDetail from  '@/pages/PowerStripDetail.vue';
import PowerSupplys from  '@/pages/PowerSupplys.vue';
import PowerSupplyDetail from  '@/pages/PowerSupplyDetail.vue';
import CoolingUnits from  '@/pages/CoolingUnits.vue';
import CoolingUnitDetail from  '@/pages/CoolingUnitDetail.vue';
import Generators from  '@/pages/Generators.vue';
import GeneratorDetail from  '@/pages/GeneratorDetail.vue';
import DataCenters from  '@/pages/DataCenters.vue';
import DataCenterDetail from  '@/pages/DataCenterDetail.vue';
import TemperatureSensors from  '@/pages/TemperatureSensors.vue';
import TemperatureSensorDetail from  '@/pages/TemperatureSensorDetail.vue';
import CurrentSensors from  '@/pages/CurrentSensors.vue';
import CurrentSensorDetail from  '@/pages/CurrentSensorDetail.vue';
import Clients from  '@/pages/Clients.vue';
import ClientDetail from  '@/pages/ClientDetail.vue';
import PowerSwitchs from  '@/pages/PowerSwitchs.vue';
import PowerSwitchDetail from  '@/pages/PowerSwitchDetail.vue';
import StaticTransferSwitchs from  '@/pages/StaticTransferSwitchs.vue';
import StaticTransferSwitchDetail from  '@/pages/StaticTransferSwitchDetail.vue';
import PowerMeters from  '@/pages/PowerMeters.vue';
import PowerMeterDetail from  '@/pages/PowerMeterDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import Alerts from  '@/pages/Alerts.vue';
import AlertDetail from  '@/pages/AlertDetail.vue';
import MaintenanceRecords from  '@/pages/MaintenanceRecords.vue';
import MaintenanceRecordDetail from  '@/pages/MaintenanceRecordDetail.vue';
import Inspections from  '@/pages/Inspections.vue';
import InspectionDetail from  '@/pages/InspectionDetail.vue';
import Technicians from  '@/pages/Technicians.vue';
import TechnicianDetail from  '@/pages/TechnicianDetail.vue';
import Vendors from  '@/pages/Vendors.vue';
import VendorDetail from  '@/pages/VendorDetail.vue';
import Contracts from  '@/pages/Contracts.vue';
import ContractDetail from  '@/pages/ContractDetail.vue';
import MonitoringSessions from  '@/pages/MonitoringSessions.vue';
import MonitoringSessionDetail from  '@/pages/MonitoringSessionDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/racks',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/racks',
		name: 'Racks',
		layout: DefaultLayout,
		component: Racks,
	},
	{
	    path: '/rack/:rackId', 
	    name: 'RackDetail',
		layout: DefaultLayout,
	    component: RackDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerStrips',
		name: 'PowerStrips',
		layout: DefaultLayout,
		component: PowerStrips,
	},
	{
	    path: '/powerStrip/:powerStripId', 
	    name: 'PowerStripDetail',
		layout: DefaultLayout,
	    component: PowerStripDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerSupplys',
		name: 'PowerSupplys',
		layout: DefaultLayout,
		component: PowerSupplys,
	},
	{
	    path: '/powerSupply/:powerSupplyId', 
	    name: 'PowerSupplyDetail',
		layout: DefaultLayout,
	    component: PowerSupplyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/coolingUnits',
		name: 'CoolingUnits',
		layout: DefaultLayout,
		component: CoolingUnits,
	},
	{
	    path: '/coolingUnit/:coolingUnitId', 
	    name: 'CoolingUnitDetail',
		layout: DefaultLayout,
	    component: CoolingUnitDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/generators',
		name: 'Generators',
		layout: DefaultLayout,
		component: Generators,
	},
	{
	    path: '/generator/:generatorId', 
	    name: 'GeneratorDetail',
		layout: DefaultLayout,
	    component: GeneratorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/dataCenters',
		name: 'DataCenters',
		layout: DefaultLayout,
		component: DataCenters,
	},
	{
	    path: '/dataCenter/:dataCenterId', 
	    name: 'DataCenterDetail',
		layout: DefaultLayout,
	    component: DataCenterDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/temperatureSensors',
		name: 'TemperatureSensors',
		layout: DefaultLayout,
		component: TemperatureSensors,
	},
	{
	    path: '/temperatureSensor/:temperatureSensorId', 
	    name: 'TemperatureSensorDetail',
		layout: DefaultLayout,
	    component: TemperatureSensorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/currentSensors',
		name: 'CurrentSensors',
		layout: DefaultLayout,
		component: CurrentSensors,
	},
	{
	    path: '/currentSensor/:currentSensorId', 
	    name: 'CurrentSensorDetail',
		layout: DefaultLayout,
	    component: CurrentSensorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/clients',
		name: 'Clients',
		layout: DefaultLayout,
		component: Clients,
	},
	{
	    path: '/client/:clientId', 
	    name: 'ClientDetail',
		layout: DefaultLayout,
	    component: ClientDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerSwitchs',
		name: 'PowerSwitchs',
		layout: DefaultLayout,
		component: PowerSwitchs,
	},
	{
	    path: '/powerSwitch/:powerSwitchId', 
	    name: 'PowerSwitchDetail',
		layout: DefaultLayout,
	    component: PowerSwitchDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/staticTransferSwitchs',
		name: 'StaticTransferSwitchs',
		layout: DefaultLayout,
		component: StaticTransferSwitchs,
	},
	{
	    path: '/staticTransferSwitch/:staticTransferSwitchId', 
	    name: 'StaticTransferSwitchDetail',
		layout: DefaultLayout,
	    component: StaticTransferSwitchDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerMeters',
		name: 'PowerMeters',
		layout: DefaultLayout,
		component: PowerMeters,
	},
	{
	    path: '/powerMeter/:powerMeterId', 
	    name: 'PowerMeterDetail',
		layout: DefaultLayout,
	    component: PowerMeterDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/alerts',
		name: 'Alerts',
		layout: DefaultLayout,
		component: Alerts,
	},
	{
	    path: '/alert/:alertId', 
	    name: 'AlertDetail',
		layout: DefaultLayout,
	    component: AlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/maintenanceRecords',
		name: 'MaintenanceRecords',
		layout: DefaultLayout,
		component: MaintenanceRecords,
	},
	{
	    path: '/maintenanceRecord/:maintenanceRecordId', 
	    name: 'MaintenanceRecordDetail',
		layout: DefaultLayout,
	    component: MaintenanceRecordDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/inspections',
		name: 'Inspections',
		layout: DefaultLayout,
		component: Inspections,
	},
	{
	    path: '/inspection/:inspectionId', 
	    name: 'InspectionDetail',
		layout: DefaultLayout,
	    component: InspectionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/technicians',
		name: 'Technicians',
		layout: DefaultLayout,
		component: Technicians,
	},
	{
	    path: '/technician/:technicianId', 
	    name: 'TechnicianDetail',
		layout: DefaultLayout,
	    component: TechnicianDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/vendors',
		name: 'Vendors',
		layout: DefaultLayout,
		component: Vendors,
	},
	{
	    path: '/vendor/:vendorId', 
	    name: 'VendorDetail',
		layout: DefaultLayout,
	    component: VendorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/contracts',
		name: 'Contracts',
		layout: DefaultLayout,
		component: Contracts,
	},
	{
	    path: '/contract/:contractId', 
	    name: 'ContractDetail',
		layout: DefaultLayout,
	    component: ContractDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/monitoringSessions',
		name: 'MonitoringSessions',
		layout: DefaultLayout,
		component: MonitoringSessions,
	},
	{
	    path: '/monitoringSession/:monitoringSessionId', 
	    name: 'MonitoringSessionDetail',
		layout: DefaultLayout,
	    component: MonitoringSessionDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;

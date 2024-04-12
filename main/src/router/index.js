import { createRouter, createWebHistory } from 'vue-router'
import LoginIn from '../views/LoginIn.vue'
import MainPage from '../views/MainPage.vue'
import DigitalFactory from  '../components/DigitalFactory.vue'
import PatrolCar from "../components/PatrolCar.vue";
import DataAnalysis from "../components/DataAnalysis.vue";
import Monitoring from "../components/Monitoring.vue";
import Attendance from "../components/Attendance.vue";
import Staff from "../components/Staff.vue";
import Incidents from "../components/Incidents.vue";
import TreatedIncidents from "../components/Incidents/TreatedIncidents.vue";
import UntreatedIncidents from "../components/Incidents/UntreatedIncidents.vue";



const routes = [
    {
        path: '/',
        name: 'MainPage',
        component: MainPage,
        children:[
            {
                path: '/DigitalFactory',
                name: 'DigitalFactory',
                component: DigitalFactory,
            },
            {
                path: '/PatrolCar',
                name:'PatrolCar',
                component: PatrolCar,
            },
            {
                path: '/Incidents',
                name:'Incidents',
                component: Incidents,
                children: [
                    {
                        path: '/TreatedIncidents',
                        name:'TreatedIncidents',
                        component: TreatedIncidents,
                    },
                    {
                        path: '/UntreatedIncidents',
                        name:'UntreatedIncidents',
                        component: UntreatedIncidents,
                    }
                ]
            },
            {
                path: '/DataAnalysis',
                name: 'DataAnalysis',
                component: DataAnalysis,
            },
            {
                path: '/Monitoring',
                name:'Monitoring',
                component: Monitoring,
            },
            {
                path: '/Attendance',
                name: 'Attendance',
                component: Attendance,
            },
            {
                path: '/Staff',
                name:'Staff',
                component: Staff,
            },
        ]
    },

]

const index = createRouter({
    history: createWebHistory(),
    routes
})

export default index

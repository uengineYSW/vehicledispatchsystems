
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import UserManagementUserManager from "./components/listers/UserManagementUserCards"
import UserManagementUserDetail from "./components/listers/UserManagementUserDetail"

import DriverManagementDriverManager from "./components/listers/DriverManagementDriverCards"
import DriverManagementDriverDetail from "./components/listers/DriverManagementDriverDetail"

import VehicleManagementVehicleManager from "./components/listers/VehicleManagementVehicleCards"
import VehicleManagementVehicleDetail from "./components/listers/VehicleManagementVehicleDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/userManagements/users',
                name: 'UserManagementUserManager',
                component: UserManagementUserManager
            },
            {
                path: '/userManagements/users/:id',
                name: 'UserManagementUserDetail',
                component: UserManagementUserDetail
            },

            {
                path: '/driverManagements/drivers',
                name: 'DriverManagementDriverManager',
                component: DriverManagementDriverManager
            },
            {
                path: '/driverManagements/drivers/:id',
                name: 'DriverManagementDriverDetail',
                component: DriverManagementDriverDetail
            },

            {
                path: '/vehicleManagements/vehicles',
                name: 'VehicleManagementVehicleManager',
                component: VehicleManagementVehicleManager
            },
            {
                path: '/vehicleManagements/vehicles/:id',
                name: 'VehicleManagementVehicleDetail',
                component: VehicleManagementVehicleDetail
            },



    ]
})

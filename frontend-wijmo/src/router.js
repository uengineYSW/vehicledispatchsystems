
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import UserManager from "./components/ui/UserGrid"

import DriverManager from "./components/ui/DriverGrid"

import VehicleManager from "./components/ui/VehicleGrid"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/users',
                name: 'UserManager',
                component: UserManager
            },

            {
                path: '/drivers',
                name: 'DriverManager',
                component: DriverManager
            },

            {
                path: '/vehicles',
                name: 'VehicleManager',
                component: VehicleManager
            },



    ]
})

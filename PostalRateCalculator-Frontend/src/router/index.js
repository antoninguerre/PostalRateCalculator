import Vue from 'vue'
import Router from 'vue-router'
import PostalRateCalculator from "../components/PostalRateCalculator";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'PostalRateCalculator',
      component: PostalRateCalculator
    }
  ]
})

import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

export var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


import Vue from 'vue'
import Router from 'vue-router'
import PostalRateCalculator from "../components/PostalRateCalculator";
import Test from "../components/Test";

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

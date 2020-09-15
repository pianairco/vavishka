// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
// npm install vuex --save
import Vuex from 'vuex'
import { mapState } from 'vuex'
import App from './App'
import router from './router'
import axios from 'axios'
import Buefy from 'buefy'
import 'buefy/dist/buefy.css'
import GAuth from 'vue-google-oauth2'

const gauthOption = {
  clientId: '290205995528-o268sq4cttuds0f44jnre5sb6rudfsb5.apps.googleusercontent.com',
  scope: 'profile email',
  prompt: 'select_account'
}
Vue.use(GAuth, gauthOption)

Vue.use(Buefy)

Vue.prototype.$axios = axios

Vue.config.productionTip = false

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    appInfo: null,
    forms: {waiter: {wait: 0}}
  },
  mutations: {
    setAppInfo (state, appInfo) {
      state.appInfo = appInfo
    },
    setToForm: function(formName, propertyName, property) {
      this.state.forms[formName][propertyName] = property;
      this.state.forms[formName] = JSON.parse(JSON.stringify(this.state.forms[formName]));
    },
    getFromForm: function(formName, propertyName) {
      return this.state.forms[formName][propertyName];
    },
    setToFormProperty: function(formName, propertyName, property, value) {
      this.state.forms[formName][propertyName][property] = value;
      this.state.forms[formName] =  JSON.parse(JSON.stringify(this.state.forms[formName]));
    },
    removeFromFormProperty: function(formName, propertyName, property) {
      delete this.state.forms[formName][propertyName][property];
      this.state.forms[formName] =  JSON.parse(JSON.stringify(this.state.forms[formName]));
    },
    replaceToFormProperty: function(formName, propertyName, property, field, value) {
      this.state.forms[formName][propertyName][property][field] = value;
      this.state.forms[formName] =  JSON.parse(JSON.stringify(this.state.forms[formName]));
    }
  }
})

Vue.mixin({
  data: function() {
    return {
      get remoteServer() {
        return "http://localhost";
      }
    }
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

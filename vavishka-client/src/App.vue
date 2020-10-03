<template>
  <div id="app" >
    <spinner></spinner>
    <topbar></topbar>
    <app-header v-if="isHome"></app-header>
    <router-view/>
    <app-footer></app-footer>
  </div>
</template>

<script>
  import Topbar from './components/Topbar';
  import AppHeader from './components/Header';
  import AppFooter from './components/Footer';

  export default {
    name: 'App',
    async mounted () {
    // async beforeCreate () {
      console.log('Nothing gets called before me!')
      console.log(this.remoteServer);
      try {
        let appInfo = await this.$axios.get(this.remoteServer + '/api/app-info').then((res) => {
          let appInfo = res['data'];
          this.$store.commit('setAppInfo', appInfo)
          return appInfo
        })
      } catch (e) {
        console.log("set appinfo : ", appInfo)
      }
    },
    computed: {
      isHome() {
        return this.$route.name === 'Home'
      }
    },
    components: {
      'topbar': Topbar,
      'app-header': AppHeader,
      'app-footer': AppFooter
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    margin-top: 0px;
  }
</style>

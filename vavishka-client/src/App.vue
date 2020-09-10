<template>
  <div id="app">
    <topbar></topbar>
    <app-header></app-header>
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

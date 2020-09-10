<template lang="html">

  <b-navbar>
    <template slot="brand">
      <b-navbar-item tag="router-link" :to="{ path: '/' }">
        <img
          src="../assets/img/vavishka.png"
          alt="Lightweight UI components for Vue.js based on Bulma"
        >
      </b-navbar-item>
    </template>
    <template slot="start">
      <b-navbar-item href="#">
        Home
      </b-navbar-item>
      <b-navbar-item href="#">
        Documentation
      </b-navbar-item>
      <b-navbar-dropdown label="Info">
        <b-navbar-item href="#">
          About
        </b-navbar-item>
        <b-navbar-item href="#">
          Contact
        </b-navbar-item>
      </b-navbar-dropdown>
    </template>

    <template slot="end">
      <b-navbar-item tag="div">
        <div class="buttons">
          <a v-on:click="handleClickSignIn" class="button is-primary">
            <strong>Sign up</strong>
          </a>
          <a class="button is-light" v-on:click="handleClickLogin">
            Log in
          </a>

        </div>
      </b-navbar-item>
      <b-loading :is-full-page="isFullPage" v-model="isLoading" :can-cancel="false">
        <b-icon
          pack="fas"
          icon="sync-alt"
          size="is-large"
          custom-class="fa-spin">
        </b-icon>
      </b-loading>
    </template>
  </b-navbar>

</template>

<script lang="js">

  export default  {
    name: 'Topbar',
    props: [],
    mounted () {

    },
    data () {
      return {
        isLoading: false,
        isFullPage: true
      }
    },
    methods: {
      handleClickLogin() {
        this.isLoading = true;
        this.$gAuth.getAuthCode().then((authCode) => {
          //on success
          this.isLoading = false;
          console.log("authCode", authCode);
        })
      .catch((error) => {
          //on fail do something
          this.isLoading = false;
          console.log("error", error);
        });
      },
      async handleClickSignIn() {
        try {
          this.isLoading = true;
          const googleUser = await this.$gAuth.signIn();
          this.isLoading = false;
          if (!googleUser) {
            return null;
          }
          console.log("googleUser", googleUser);
          console.log("getId", googleUser.getId());
          console.log("getBasicProfile", googleUser.getBasicProfile());
          console.log("getAuthResponse", googleUser.getAuthResponse());
          console.log("getAuthResponse", this.$gAuth.GoogleAuth.currentUser.get().getAuthResponse());
          this.isSignIn = this.$gAuth.isAuthorized;
        } catch (error) {
          this.isLoading = false;
          //on fail do something
          console.error(error);
          return null;
        }
      },
      async handleClickSignOut() {
        try {
          await this.$gAuth.signOut();
          this.isSignIn = this.$gAuth.isAuthorized;
          console.log("isSignIn", this.$gAuth.isAuthorized);
        } catch (error) {
          console.error(error);
        }
      },
      handleClickDisconnect() {
        window.location.href = `https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=${window.location.href}`;
      },
    },
    created() {
      let that = this;
      let checkGauthLoad = setInterval(function () {
        that.isInit = that.$gAuth.isInit;
        that.isSignIn = that.$gAuth.isAuthorized;
        if (that.isInit) clearInterval(checkGauthLoad);
      }, 1000);
    },
    computed: {

    }
}


</script>

<style lang="css">

</style>

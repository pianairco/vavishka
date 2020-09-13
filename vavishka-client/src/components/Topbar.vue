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
          <b-button size="" class="is-success" icon-pack="fab"
                    v-on:click="handleClickSignIn"
                    v-if="!appInfo || !appInfo.isLoggedIn"
                    icon-left="google">
            Sign in with Google
          </b-button>
<!--          <div class="field" v-if="appInfo && appInfo.isLoggedIn">-->
<!--            <b-tag-->
<!--              style="direction: rtl"-->
<!--              close-icon-type='is-dark'-->
<!--              attached-->
<!--              closable-->
<!--              aria-close-label="خروج"-->
<!--              close-icon-pack='fas'-->
<!--              close-icon='sign-out-alt'-->
<!--              @click="handleClickSignOut"-->
<!--              rounded>{{appInfo.username}}</b-tag>-->
<!--          </div>-->
          <b-button size="" class="is-danger" icon-pack="fas"
                    v-on:click="handleClickSignOut"
                    v-if="appInfo && appInfo.isLoggedIn"
                    icon-left="sign-out-alt">
            {{appInfo.username}}
          </b-button>
          <b-image
            style="width: 25px; margin-bottom: 8px; padding-right:3px;"
            v-if="appInfo && appInfo.isLoggedIn"
            v-bind:src="appInfo.pictureUrl"
            :rounded="rounded"
          ></b-image>
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
        pictureUrl: null,
        rounded: true,
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
          if (!googleUser) {
            return null;
          }
          console.log("googleUser", googleUser);
          console.log("getId", googleUser.getId());
          console.log("getBasicProfile", googleUser.getBasicProfile());
          console.log("getAuthResponse", googleUser.getAuthResponse());
          console.log("getAuthResponse", this.$gAuth.GoogleAuth.currentUser.get().getAuthResponse());
          let accessToken = this.$gAuth.GoogleAuth.currentUser.get().getAuthResponse()['access_token'];
          console.log("accessToken", accessToken);
          this.isSignIn = this.$gAuth.isAuthorized;
          let self = this;
          this.$axios.post(this.remoteServer + '/api/sign-in', { 'accessToken': accessToken }, { headers: { 'Content-Type': 'APPLICATION/JSON' } })
            .then((response) => {
              this.isLoading = false;
              console.log(response.data);
              // this.sessions.push(response.data);
              this.pictureUrl = response.data['pictureUrl'];
              this.$store.commit('setAppInfo', response.data);
              console.log(this.pictureUrl)
            }).catch((err) => {
              self.isLoading = false;
              this.message = err
            });
        } catch (error) {
          this.isLoading = false;
          //on fail do something
          console.error(error);
        }
      },
      async handleClickSignOut() {
    try {
      this.isLoading = true;
      await this.$gAuth.signOut();
      let isSignIn = this.$gAuth.isAuthorized;
      if (isSignIn) {
        this.isLoading = false;
      } else {
        let self = this;
        this.$axios.post(this.remoteServer + '/api/sign-out', {}, { headers: { 'Content-Type': 'APPLICATION/JSON' } })
          .then((response) => {
          this.isLoading = false;
        console.log(response.data);
        // this.sessions.push(response.data);
        this.$store.commit('setAppInfo', response.data);
      }).catch((err) => {
          self.isLoading = false;
        this.message = err
      });
      }
    } catch (error) {
      this.isLoading = false;
      //on fail do something
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
      appInfo () {
        console.log(this.$store.state.appInfo);
        if(this.$store.state.appInfo)
          console.log(this.$store.state.appInfo.pictureUrl)
        return this.$store.state.appInfo
      }
    }
}


</script>

<style lang="css">
.navbar-burger {
  margin-left: unset;
}

.navbar-start {
  margin-right: unset;
}

.navbar-end {
  margin-left: unset;
}

.button .icon:last-child:not(:first-child) {
  margin-right: 0.25em;
  margin-left: calc(-0.5em - 1px);
}

.button .icon:first-child:not(:last-child) {
  margin-left: 0.25em;
  margin-right: calc(-0.5em - 1px);
}
</style>

<template lang="html">
  <div class="container" id="bulma-sample-page">
    <div class="columns is-mobile is-multiline">
      <div class="column is-full-mobile is-one-quarter-desktop">
        <h3 class="has-text-centered title has-background-grey-light" style="padding-bottom: 10px; margin-bottom: 0px; border-radius: 4px;">فهرست</h3>
        <aside class="menu">
          <!--style=" overflow-y: auto; display: flex; flex-direction: column; max-height: 800px;"-->
          <ul class="menu-list" >
          <li><pictorial-menu-item></pictorial-menu-item></li>
          <li><pictorial-menu-item></pictorial-menu-item></li>
          <li><pictorial-menu-item></pictorial-menu-item></li>
          <li><pictorial-menu-item></pictorial-menu-item></li>
          <li><pictorial-menu-item></pictorial-menu-item></li>
          <li><pictorial-menu-item></pictorial-menu-item></li>
          <li><pictorial-menu-item></pictorial-menu-item></li>
          <li><pictorial-menu-item></pictorial-menu-item></li>
          <li><pictorial-menu-item></pictorial-menu-item></li>
          </ul>
        </aside>
      </div>
      <div class="column is-full-mobile is-three-quarters-desktop">
        <div class="card">
          <div class="card-image">
            <figure class="image is-4by3">
              <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image">
            </figure>
          </div>
          <div class="card-content">
            <div class="media">
              <div class="media-left">
                <figure class="image is-48x48">
                  <img src="https://bulma.io/images/placeholders/96x96.png" alt="Placeholder image">
                </figure>
              </div>
              <div class="media-content">
                <p class="title is-4">John Smith</p>
                <p class="subtitle is-6">@johnsmith</p>
              </div>
            </div>

            <div class="content">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit.
              Phasellus nec iaculis mauris. <a>@bulmaio</a>.
              <a href="#">#css</a> <a href="#">#responsive</a>
              <br>
              <time datetime="2016-1-1">11:09 PM - 1 Jan 2016</time>
            </div>
          </div>
          <footer class="card-footer">
            <p class="card-footer-item">
                            <span>
                                <a href="#">Next SLIDE</a>
                            </span>
            </p>
            <p class="card-footer-item">
                            <span>
                                <a href="#">PREV SLIDE</a>
                            </span>
            </p>
          </footer>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="js">
  import PictorialMenuItem from './modules/sample/PictorialMenuItem';
  import PictorialMenuItemCreator from './modules/sample/PictorialMenuItemCreator';

  export default {
    data: function () {
      return {}
    },
    methods: {},
    components: {
      PictorialMenuItem,
      PictorialMenuItemCreator,
    },
  };
</script>

<style lang="css">
  #bulma-sample-page {
    padding-top: 20px;
    padding-bottom: 20px;
  }
</style>

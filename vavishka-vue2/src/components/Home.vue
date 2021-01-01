<template lang="html">
  <section class="section" id="bulma-home-page">
    <div class="container has-text-centered">
      <h2 class="title">واویشکا چیست</h2>
      <vue-editor  v-model="content" :editorToolbar="customToolbar"></vue-editor>
      <div v-html="content"></div>
      <p>از معروف ترین خوراکی های گیلانی واویشکا است که بسیار خوشمزه می باشد و شما می توانید این خوراک اصیل گیلانی را در خانه تهیه کنید و از طعم و مزه بی نظیر این غذا نهایت لذت را ببرید. واویشکا چند روش پخت مختلف دارد که در این مطلب شما را با بهترین دستور پخت واویشکا آشنا خواهیم کرد.</p>

      <button @click="isImageModalActive = true" class="button is-primary is-medium">modal</button>
      <b-modal v-model="isImageModalActive">
        <pictorial-sample-item-creator
                                       :edited-item="d"
                                       :form-name="'uploader1'"
                                       :property-name="'image'"></pictorial-sample-item-creator>
      </b-modal>

      <div class="columns is-centered" style="padding: 2rem">
        <div class="column">
          <div class="card">
            <div class="card-image">
              <figure class="image is-2by1">
                <img src="../assets/img/lake.jpg" alt="Placeholder image">
              </figure>
            </div>
            <div class="card-content">
              <div class="media">
                <div class="media-content">
                  <p class="title is-4">John Smith</p>
                  <p class="subtitle is-6">@johnsmith</p>
                </div>
              </div>

              <div class="content">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec iaculis mauris.
                <a>@bulmaio</a>.
                <a href="#">#css</a>
                <a href="#">#responsive</a>
              </div>
            </div>
          </div>
        </div>
        <div class="column">
          <div class="card">
            <div class="card-image">
              <figure class="image is-2by1">
                <img src="../assets/img/halong.jpg" alt="Placeholder image">
              </figure>
            </div>
            <div class="card-content">
              <div class="media">
                <div class="media-content">
                  <p class="title is-4">John Smith</p>
                  <p class="subtitle is-6">@johnsmith</p>
                </div>
              </div>

              <div class="content">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec iaculis mauris.
                <a>@bulmaio</a>.
                <a href="#">#css</a>
                <a href="#">#responsive</a>
              </div>
            </div>
          </div>
        </div>
        <div class="column">
          <div class="card">
            <div class="card-image">
              <figure class="image is-2by1">
                <img src="../assets/img/forest.jpg" alt="Placeholder image">
              </figure>
            </div>
            <div class="card-content">
              <div class="media">
                <div class="media-content">
                  <p class="title is-4">John Smith</p>
                  <p class="subtitle is-6">@johnsmith</p>
                </div>
              </div>

              <div class="content">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec iaculis mauris.
                <a>@bulmaio</a>.
                <a href="#">#css</a>
                <a href="#">#responsive</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </section>
</template>

<script lang="js">

  import { VueEditor } from "vue2-editor";
  import PictorialSampleItemCreator from './modules/sample/PictorialSampleItemCreator';

  export default  {
    name: 'Home',
    props: [],
    mounted () {

    },
    data () {
      return {
        isImageModalActive: false,
        content: '',
        customToolbar: [[{ 'align': ''}, { 'align': 'right' }], ['link'], [{ 'direction': 'rtl' }]]
      }
    },
    methods: {

    },
    computed: {

    },
    components: {
      VueEditor,
      PictorialSampleItemCreator
    }
}


</script>

<style scoped lang="css">
  .home {

  }

  #editor1 {
    height: 350px;
  }
</style>

<template lang="html">
  <footer id="mt-footer" class="footer">
    <div class="container">
      <div class="columns">
        <div class="column is-4">
          <p class="footer-link-title has-text-weight-bold">Navigation</p>
          <!-- Loop through the navigation items -->
          <a class="navbar-item nav-home is-active" href="https://biron.bironthemes.com/">Home</a>
          <a class="navbar-item nav-style-guide" href="https://biron.bironthemes.com/style-guide/">Style Guide</a>
          <a class="navbar-item nav-features" href="https://biron.bironthemes.com/features/">Features</a>
          <a class="navbar-item nav-tech" href="https://biron.bironthemes.com/tag/technology/">#Tech</a>
          <a class="navbar-item nav-web" href="https://biron.bironthemes.com/tag/web/">#Web</a>

        </div>
        <div class="column is-4">
          <p class="footer-link-title has-text-weight-bold">Follow Us</p>
          <div class="social-media">
            <a class="social-link facebook" href="https://www.facebook.com/bironthemes" target="_blank" title="Facebook" aria-label="Facebook">
              <div class="icon facebook">
                <svg class="icon__svg">
                  <use xlink:href="#facebook"></use>
                </svg>
              </div>
              <p>Facebook</p>
            </a>
            <a class="social-link twitter" href="https://twitter.com/bironthemes" target="_blank" title="Twitter" aria-label="Twitter">
              <div class="icon twitter">
                <svg class="icon__svg">
                  <use xlink:href="#twitter"></use>
                </svg>
              </div>
              <p>Twitter</p>
            </a>
            <a class="social-link instagram" href="https://instagram.com/bironthemes" target="_blank" title="Instagram" aria-label="Instagram">
              <div class="icon instagram">
                <svg class="icon__svg">
                  <use xlink:href="#instagram"></use>
                </svg>
              </div>
              <p>Instagram</p>
            </a>
            <a class="social-link linkedin" href="https://linkedin.com" target="_blank" title="Linkedin" aria-label="Linkedin">
              <div class="icon linkedin">
                <svg class="icon__svg">
                  <use xlink:href="#linkedin"></use>
                </svg>
              </div>
              <p>Linkedin</p>
            </a>
          </div>
        </div>
        <div class="column is-4">
          <p class="footer-link-title has-text-weight-bold">Subscribe</p>
          <p class="footer-text">Get the latest posts delivered right to your inbox</p>
          <form method="post" action="/subscribe/" class="">
            <input class="confirm" type="hidden" name="confirm"><input class="location" type="hidden" name="location" value="https://biron.bironthemes.com/"><input class="referrer" type="hidden" name="referrer" value="">

            <div class="form-group">
              <input class="input is-rounded" type="email" name="email" placeholder="Your email address">
            </div>
            <button class="button is-primary is-rounded has-text-weight-bold" type="submit"><span>Subscribe</span></button>



          </form>


        </div>
      </div>
      <hr>
      <div class="footer-bottom has-text-centered">
        © 2020 <a href="http://vavishka.piana.ir">Piana</a>.
        Published with <a href="http://piana.ir">Piana Group</a>.
        Theme by <a href="http://piana.ir">Piana Themes</a>.
      </div>
    </div>
  </footer>
</template>

<script lang="js">

  export default  {
    name: 'AppFooter',
    props: [],
    mounted () {

    },
    data () {
      return {

      }
    },
    methods: {

    },
    computed: {

  }
}

</script>

<style scoped lang="css">
  .footer {
    margin-top: 16px;
    padding: 2em 1.5em
  }

  .footer-link-title {
    margin-bottom: 1em
  }

  .footer .navbar-item {
    padding: 0;
    margin-bottom: .25em
  }

  .footer .footer-text,.footer .form-group {
    margin-bottom: 1em
  }

  .social-link {
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    margin-bottom: .5em
  }

  .social-link .icon {
    margin-right: .5em
  }

  .social-link p {
    line-height: 25px
  }

  .has-text-weight-semibold {
    font-weight: 600!important
  }

  .has-text-weight-bold {
    font-weight: 700!important
  }

  hr {
    border: none;
    display: block;
    height: 2px;
    margin: 1.5rem 0
  }

  code, hr {
    background-color: #f5f5f5;
  }

</style>

<template>
  <div class="home">
    <h1 class="title has-text-centered">BYU OIT Assessment</h1>
    <SearchForm></SearchForm>
    <SearchResults></SearchResults>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import HelloWorld from '@/components/HelloWorld.vue';
import SearchForm from '@/components/SearchForm.vue'; // @ is an alias to /src
import SearchResults from '@/components/SearchResults.vue';

@Component({
  components: {
    SearchForm,
    SearchResults,
    HelloWorld,
  },
})
export default class Home extends Vue {}
</script>

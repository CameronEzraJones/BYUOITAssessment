<template>
    <div class="tile is-ancestor is-vertical">
        <div class="tile is-parent" v-for="movie in movies">
            <article class="tile is-child is-4 box">
                <p class="title">{{movie.title}}</p>
                <p class="subtitle">{{movie.popularity_summary}}</p>
                <img :src="movie.poster_image_url" :alt="movie.title">
            </article>
        </div>
    </div>
</template>

<script lang="ts">
    import Vue, { PropTypes } from 'vue';

    export default Vue.extend({
        computed: {
            movies() {
                return this.$store.state.movies
            }
        }
    })
</script>

<style scoped>

</style>
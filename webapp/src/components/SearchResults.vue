<template>
    <div class="tile is-ancestor is-vertical">
        <div class="tile" v-if="error">
            <div class="notification is-danger">
                {{error.message}}
            </div>
        </div>
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
    import Vue from 'vue';

    export default Vue.extend({
        computed: {
            movies(): any[] {
                return this.$store.state.movies;
            },
            error(): Error {
                return this.$store.state.error;
            },
        },
    });
</script>

<style scoped>

</style>
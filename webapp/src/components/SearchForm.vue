<template>
    <div class="field has-addons">
        <div class="control">
            <input class="input" type="text" placeholder="Search movies" v-on:keyup.enter="doSearch" v-model="search">
        </div>
        <div class="control">
            <button type="submit" class="button is-info" v-bind:class="{ 'is-loading': loading }" v-on:click="doSearch" :disabled="loading">
                Search
            </button>
        </div>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';

    export default Vue.extend({
        computed: {
            loading(): boolean {
                return this.$store.state.loading;
            },
        },

        data() {
            return {
                search: '',
            };
        },

        methods: {
            doSearch() {
                this.$store.dispatch('getMovies', this.search);
            },
        },
    });
</script>

<style scoped>

</style>
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loading: false,
    movies: [],
    error: null
  },
  mutations: {
    getMoviesLoading (state) {
      state.loading = true;
      state.error = null;
    },
    getMoviesSuccess (state, movies) {
      state.loading = false;
      state.movies = movies;
      state.error = null;
    },
    getMoviesFailure (state, error) {
      state.loading = false;
      state.error = error;
    }
  },
  actions: {
    getMovies ({ commit }, search) {
      commit('getMoviesLoading');
      fetch('http://localhost:8080/movies?search=' + search).then((response) => {
        if(response.ok) {
          return response.json();
        }
        throw new Error('Unable to get movies with search term ' + search + '. message is ' + response.statusText);
      })
          .then((data) => {
            commit('getMoviesSuccess', data);
          })
          .catch((error) => {
            commit('getMoviesFailure', error);
          })
    }
  },
});

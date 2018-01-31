var app = new Vue({
    el: '#app',
    data: {
        query: '',
        page: 1,
        size: 5,
        items: [],
        hasMore: false
    },
    methods: {
        search: function () {
            if (this.query) {
                this.page = 1;
                this.$http.get('/query', {params: {value: this.query, page: this.page, size: this.size}}).then(response => {
                    paging = response.body;
                this.items = paging.items
                this.hasMore = paging.hasMore;
            }, response => {});
            }
        },
        more: function () {
            if (!this.hasMore) {
                return;
            }
            this.page++;
            this.$http.get('/query', {params: {value: this.query, page: this.page, size: this.size}}).then(response => {
                paging = response.body;
            this.items = this.items.concat(paging.items);
            this.hasMore = paging.hasMore;
        }, response => {})
        }
    }
})
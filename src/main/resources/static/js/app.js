var app = new Vue({
    el: '#app',
    data: {
        query: '',
        page: 1,
        size: 5,
        items: [],
        hasMore: false,
        loading: false
    },
    methods: {
        search: function () {
            this.loading = true;
            this.items = [];
            if (this.query) {
                this.page = 1;
                this.$http.get('/query', {params: {value: this.query, page: this.page, size: this.size}}).then(response => {
                    paging = response.body;
                this.items = paging.items
                this.hasMore = paging.hasMore;
                this.loading = false;
            }, response => {
                    this.loading = false;
                });
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
        },
        dateFormat: function (dateNanosec){
            return moment(dateNanosec).format("DD MMM YYYY hh:mm a");
        }
    }
})
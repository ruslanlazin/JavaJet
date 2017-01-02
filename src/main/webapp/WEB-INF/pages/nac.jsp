<script type="text/javascript">


    function formatRepo(repo) {
        if (repo.loading) return repo.code;

        var markup = '<div class="clearfix">' +
                '<div class="col-sm-1">' +
//                            '<img src="' + repo.city_fullname + '" style="max-width: 100%" />' +
                '</div>' +
                '<div class="col-sm-10">' +
                '<div class="clearfix">' +
                '<div class="col-sm-6">' + repo.city_fullname + '</div>' +
                '<div class="col-sm-3"><i class="fa fa-code-fork"></i> ' + repo.code + '</div>' +
                '<div class="col-sm-2"><i class="fa fa-star"></i> ' + repo.title + '</div>' +
                '</div>';

        if (repo._score) {
            markup += '<div>' + repo._score + '</div>';
        }

        markup += '</div></div>';

        return markup;
    }

    function formatRepoSelection(repo) {
        return repo.code;
    }


    $(".js-data-example-ajax").select2({
        ajax: {
            url: "http://autocomplete.travelpayouts.com/jravia",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    q: params.term, // search term
                    locale: 'ru',
                    with_countries: false
                };
            },
            processResults: function (data, params) {
                // parse the results into the format expected by Select2
                // since we are using custom formatting functions we do not need to
                // alter the remote JSON data, except to indicate that infinite
                // scrolling can be used
                params.page = params.page || 1;

                return {
                    results: data,
                    pagination: {
                        more: (params.page * 30) < data.total_count
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) {
            return markup;
        }, // let our custom formatter work
        minimumInputLength: 1,
        templateResult: formatRepo, // omitted for brevity, see the source of this page
        templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
    });


</script>

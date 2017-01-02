/**
 * @author Ruslan Lazin
 */
$(document).ready(function () {

    // Cache
    var $departureTime = $('#departureTime').val();
    var $language = $('#language').val();

    // Calendar config section
    $(function () {
        $('#datetimepicker').datetimepicker({
            format: 'DD/MM/YYYY HH:mm',
            defaultDate: $departureTime,
            minDate: moment(),
            maxDate: moment().add(90, 'days'),
            locale: $language
        });
    });

    // Select2 plugin config for multiSelect
    $(".js-multiple").select2();

    //Select2 config for airport autoComplete
    $(".airport-select").select2({
        ajax: {
            url: "http://autocomplete.travelpayouts.com/jravia",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    q: params.term, // search term
                    locale: $language,
                    page: params.page
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;

                data = $.grep(data, function (el) {
                    return el._type === 'airport'
                });

                return {
                    results: $.map(data, function (item) {
                        return {text: item.name + ' ' + item.city_fullname, id: item.code}
                    }), pagination: {
                        more: (params.page * 30) < data.total_count
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) {
            return markup;
        }, // custom formatter
        minimumInputLength: 1,
        templateResult: formatAirport,
        templateSelection: formatAirportSelection
    });

    function formatAirport(airport) {

        var markup = '<div class="clearfix">' +
            '<div class="col-sm-2">' + airport.id + '</div>' +
            '<div class="col-sm-10"><i class="fa fa-code-fork"></i> ' + airport.text + '</div>' +
            '</div>';

        return markup;
    }

    function formatAirportSelection(airport) {
        return airport.id;
    }
});
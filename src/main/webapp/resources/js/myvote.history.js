var restAjaxUrl = "ajax/history/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: restAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(restAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: restAjaxUrl,
        datatableOpts: {
            "columns": [
                {
                    "data": "voteDate"
                },
                {
                    "data": "restaurantName"
                },
                {
                    "data": "menu"
                },
                {
                    "data": "vote"
                },
            ],
            "order": [
                [
                    0,
                    "desc",
                    3,
                    "desc"
                ]
            ],
        },
        updateTable: updateFilteredTable
    });

    $.datetimepicker.setLocale(localeCode);

//  http://xdsoft.net/jqplugins/datetimepicker/
    var startDate = $('#startDate');
    var endDate = $('#endDate');
    startDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
        onShow: function () {
            this.setOptions({
                maxDate: endDate.val() ? endDate.val() : false
            })
        }
    });
    endDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
        onShow: function () {
            this.setOptions({
                minDate: startDate.val() ? startDate.val() : false
            })
        }
    });

});
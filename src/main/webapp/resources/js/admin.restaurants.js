var restAjaxUrl = "ajax/admin/restaurants/";

// http://api.jquery.com/jQuery.ajax/#using-converters
/*$.ajaxSetup({
    converters: {
        "text json": function (stringData) {
            const json = JSON.parse(stringData);
            $(json).each(function () {
                this.dateTime = this.dateTime.replace('T', ' ').substr(0, 16);
            });
            return json;
        }
    }
});*/

$(function () {
    makeEditable({
        ajaxUrl: restAjaxUrl,
        datatableOpts: {
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "votes"
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render":  renderEditBtn
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ],
            /*"createdRow": function (row, data, dataIndex) {
                if (data.dishes.size() > 0){
                    $(row).attr("data-mealExcess", false);
                }

            },*/
        },
        updateTable: function () {
            $.get(restAjaxUrl, updateTableByData);
        }
    });

});
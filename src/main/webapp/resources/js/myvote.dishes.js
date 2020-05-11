var restAjaxUrl = "ajax/admin/dishes/";

$(function () {
    makeEditable({
        ajaxUrl: restAjaxUrl,
        datatableOpts: {
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "cost"
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderEditBtn
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
        },
        updateTable: function () {
            $.get(restAjaxUrl, updateTableByData);
        }
    });

});
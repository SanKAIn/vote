var restAjaxUrl = "ajax/profile/restaurants/";

$(function () {
    makeEditable({
        ajaxUrl: restAjaxUrl,
        datatableOpts: {
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "dishes",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            if (date.length > 0){
                                return date[0].name + " " + date[0].cost/100;
                            }
                        }
                        return "";
                    }
                },
                {
                    "data": "dishes",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            if (date.length > 1){
                                return date[1].name + " " + date[1].cost/100;
                            }
                        }
                        return "";
                    }
                },
                {
                    "data": "dishes",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            if (date.length > 2){
                                return date[2].name + " " + date[2].cost/100;
                            }
                        }
                        return "";
                    }
                },
                {
                    "data": "dishes",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            if (date.length > 3){
                                return date[3].name + " " + date[3].cost/100;
                            }
                        }
                        return "";
                    }
                },
                {
                    "data": "dishes",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            if (date.length > 4){
                                return date[4].name + " " + date[4].cost/100;
                            }
                        }
                        return "";
                    }
                },
                {
                    "data": "votes"
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderVoteBtn
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
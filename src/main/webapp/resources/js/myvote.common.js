var context, form;

function makeEditable(ctx) {
    context = ctx;
    context.datatableApi = $("#datatable").DataTable(
        // https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
        $.extend(true, ctx.datatableOpts,
            {
                "ajax": {
                    "url": context.ajaxUrl,
                    "dataSrc": ""
                },
                "paging": false,
                "info": true,
                "language": {
                    "search": "Search"
                }
            }
        ));

    form = $('#detailsForm');
    //обработка ошибок во всем документе
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function add() {
    $("#modalTitle").html("Add");
    form.find(":input").val("");
    $("#editRow").modal();
}

function add2() {
    $("#modalTitle2").html("Add dish");
    form.find(":input").val("");
    $("#editRow2").modal();
}

function updateRow(id) {
    $("#modalTitle").html("Update");
    $.get(context.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            if (key === "menu"){
                $.each(data.menu, function (key, value) {
                    var nom = key+1;
                    $("#dish" + nom + "id").val(value.id)
                    $("#dish" + nom + "name").val(value.name)
                    $("#dish" + nom + "cost").val(value.cost)
                })
            }
        });
        $('#editRow').modal();
    });
}

function deleteRow(id) {
    if (confirm("Are you sure?")) {
        $.ajax({
            url: context.ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            context.updateTable();
            successNoty("Record deleted");
        });
    }
}

function deleteRow2(id) {
    if (confirm("Are you sure?")) {
        let dishId = $('#dish'+ id +'id');
        let url = context.ajaxUrl + $('#id').val() +"/dishes/" + dishId.val()
        $.ajax({
            url: url,
            type: "DELETE"
        }).done(function () {
            context.updateTable();
            successNoty("Record deleted");
        });
        dishId.val(0)
        $('#dish'+ id +'name').val("")
        $('#dish'+ id +'cost').val("0")
    }
}

function voteRow(id) {
    $.ajax({
        url: context.ajaxUrl + id,
        type: "POST"
    }).done(function (data) {
        context.updateTable();
        successNoty(data);
    })
}

function updateTableByData(data) {
    context.datatableApi.clear().rows.add(data).draw();
}

function save() {
    /*var data1 = $('#detailsForm').serializeArray();
    var data = {"id":$('#id').val(),
                "name":$('#restaurantName').val(),
                "vote":$('#vote').val()}*/
    // console.info(data1);
    $.ajax({
        type: "POST",
        url: context.ajaxUrl,
        data: $('#detailsForm').serialize
    }).done(function () {
        $("#editRow").modal("hide");
        context.updateTable();
        successNoty("Record saved");
    });
}

function save2(kay) {
    var data1 = {
        "id":$('#dish'+ kay +'id').val(),
        "name":$('#dish'+ kay +'name').val(),
        "cost":$('#dish'+ kay +'cost').val()*100
    };
    console.info(data1);
    $.ajax({
        type: "POST",
        url: "ajax/admin/restaurants/" + $("#id").val() + "/dishes",
        data: data1
    }).done(function () {
        $("#editRow2").modal("hide");
        context.updateTable();
        successNoty("Record saved");
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + key,
        type: 'success',
        layout: "bottomRight",
        timeout: 2000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + jqXHR.statusText + "<br>" + jqXHR.responseJSON,
        type: "error",
        layout: "bottomRight"
    }).show();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}

function renderVoteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='voteRow(" + row.id + ");'><span class='fa fa-arrow-up'></span>";
    }
}

function getRest(data) {
    if (data.length === 12){
        /*var ar = [
            {"name": "id", value: data[0].value},
            {"name": "name", value: data[1].value},
            {"dishes": [
                    {"id": null, "name": data[2].value, "cost": data[3].value*100},
                    {"id": null, "name": data[4].value, "cost": data[5].value*100},
                    {"id": null, "name": data[6].value, "cost": data[7].value*100},
                    {"id": null, "name": data[8].value, "cost": data[9].value*100},
                    {"id": null, "name": data[10].value, "cost": data[11].value*100}
                ]}
        ];*/
        var ar = {
            "id": data[0].value,
            "name": data[1].value,
            /*,
        "menu":[
            {"name": data[2].value, "cost": data[3].value*100},
            {"name": data[4].value, "cost": data[5].value*100},
            {"name": data[6].value, "cost": data[7].value*100},
            {"name": data[8].value, "cost": data[9].value*100},
            {"name": data[10].value, "cost": data[11].value*100}
        ]*/
            "vote": data[1].value
        };

        return ar;
    }else {
        return data;
    }
}
var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            $(row).addClass(data.exceed ? 'exceeded' : 'normal');
        }
    });
    makeEditable();
    makeFilterable();

    var startDate = $('#startDate');
    var endDate = $('#endDate');
    startDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
        onShow: function (ct) {
            this.setOptions({
                maxDate: endDate.val() ? endDate.val() : false
            })
        }
    });
    endDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
        onShow: function (ct) {
            this.setOptions({
                minDate: startDate.val() ? startDate.val() : false
            })
        }
    });

    $('#startTime, #endTime').datetimepicker({
        datepicker: false,
        format: 'H:i'
    });

    $('#dateTime').datetimepicker({
        format: 'Y-m-d H:i'
    });
});


function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filtersForm").serialize(),
        success: updateTableByData
    });
}

function clearFilter() {
    $("#filtersForm")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

function filterRows(startDate, endDate, startTime, endTime) {
    $.ajax({
        url: ajaxUrl + "filter",
        type: "POST",
        data: {"startDate" : startDate, "endDate" : endDate, "startTime" : startTime, "endTime" : endTime},
        success: function (response) {
            datatableApi.clear().rows.add(response).draw();
            successNoty("filtered");
        }
    });
}

function makeFilterable() {
    $("#filtersForm").submit(function () {
        filterRows($('#startDate').val(), $('#endDate').val(), $('#startTime').val(), $('#endTime').val())
        return false;
    });


    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}
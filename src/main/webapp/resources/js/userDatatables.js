var ajaxUrl = "ajax/admin/users/";
var datatableApi;

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + id +"?enabled=" + enabled,
        type: "PUT",
        success: function () {
            chkbox.closest("tr").toggleClass("disabled");
            successNoty(enabled ? "common.enabled" : "common.disabled");
        },
        error: function () {
            $(chkbox).prop("checked", !enabled);
        }
    });
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + ($(data).attr('checked') ? "checked" : "") + " onclick='enable($(this)," + row.DT_RowId + ");'/>";
                    }
                    return data;
                }
            },
            {
                "data": "registered"
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
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).addClass("disabled");
            }
        }
    });
    makeEditable();
});
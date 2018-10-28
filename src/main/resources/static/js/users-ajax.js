// $("#download-users").click(function() { // id="download-users"
// $(".download-users").click(function() { обратка события click для элементов с class="download-users"
$(document).on("click", "#download-users", function () {
    var shownIds = [];
    var $ajaxTable = $('#ajax-table');

    var hiddenAttribute = $ajaxTable.attr("hidden");
    if (typeof hiddenAttribute === 'undefined') {
        var userIdsTrs = $('th.userId');
        for (var i = 0; i < userIdsTrs.length; i++) {
            shownIds.push($(userIdsTrs[i]).text());
        }
    }

    $.ajax({
        type: "get",
        url: "/ajax/show/users",
        dataType: 'json',
        async: false,
        data: {
            shownIds: shownIds.toString()
        },
        success: (function (data) {
            if (data.length > 0) {
                $ajaxTable.removeAttr("hidden");
                for (var i = 0; i < data.length; i++) {
                    $ajaxTable.append(
                        "<tr><th class=\"userId\">" + data[i].id + "</th>" +
                        "<th>" + data[i].firstName + "</th>" +
                        "<th>" + data[i].lastName + "</th>" +
                        "<th>" + data[i].status + "</th>" +
                        "<th>" + data[i].email + "</th>" +
                        "<th>" + data[i].username + "</th></tr>");
                }
            }
        }),
        error: function () {
            console.log("error");
        }
    });
});
$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8520/greeting"
    }).then(function (data) {
        $('.greeting-id').append(data.id);
        $('.greeting-content').append(data.content);
    });
    $('#personInfo').load('http://localhost:8520/personHtml', function () {
        console.log('Load html was performed.');
    });
});
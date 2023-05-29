$(document).ready(function () {
    $('#title').autocomplete({
            source: function (request, response) {
                $.get("http://localhost:8080/movies/suggestions?", { query: request.term }, function (data, status) {
                    $("#results").html("");
                    if (status === 'success') {
                        response(data);
                    }
                });
            }
        }
    );
})
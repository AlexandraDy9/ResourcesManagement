$(document).on("click", "updateTopic", function () {
    var myBookId = $(this).data('value');
    $(".modal-body #topicDescriptionUpdate").val( myBookId );
    console.log("asd");
    // As pointed out in comments,
    // it is unnecessary to have to manually call the modal.
    // $('#addBookDialog').modal('show');
});

// var updateTopic = document.getElementsByClassName("updateTopic");
// updateTopic.forEach(function(topic) {
//     var description = topic.value
// })
/*------------------------------Login---------------------------------*/
function publishPhoto() {
    var ata = {content: $("#detail").val()}
    var userId = localStorage.getItem("userId")
    $.ajax({
        type: 'post',
        url: '/' + userId + '/invitation/publish',
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function (data) {

        }
    });
    //window.location.href = "/" + userId + "/profile"
    alert("发布成功~")
};

/*------------------------Register---------------------------------*/




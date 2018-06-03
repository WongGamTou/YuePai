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
            window.location.href = "/" + data.userID + "/profile"
        }
    });
};

/*------------------------Register---------------------------------*/
function publishShare() {
    var ata = {
        category: $("#inputImageType").val(),
        shootTime: $("#photoTime").val(),
        cameraModel: $("#cameraModel").val(),
        cameraParam: $("#parameter").val(),
        description: $("#description").val(),
    }
    var userId = localStorage.getItem("userId")
    $.ajax({
        type: 'post',
        url: '/' + userId + '/invitation/publish',
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function (data) {
            window.location.href = "/" + userId + "/profile"
        }
    });
}
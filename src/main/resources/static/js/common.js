function jumpphoto(cid) {
    window.location.href = '/' + cid + '/photo';
}

function jumpshare(cid) {
    window.location.href = '/' + cid + '/share';
}

function jumpprofile(cid) {
    window.location.href = '/' + cid + '/profile';
}

function jumppublish(cid) {
    window.location.href = '/' + cid + '/publish';
}
/*------------------------------Login---------------------------------*/
function login() {
    var data = {userName: $("#loginUsername").val(), password: $("#loginPassword").val()}
    $.ajax({
        type: 'post',
        url: '/login',
        dataType: "json",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (data) {
            window.localStorage.setItem("userId", data.userId);
            window.location.href = "/" + data.userId + "/profile"

        },
        statusCode: {
            401: function () {
                $("#loginPassword").val("");
                alert("用户名或密码错误！");
            }
        }
    });
};
/*------------------------Register---------------------------------*/
function register() {
    var password = $("#registerPassword").val()
    var confirmPassword = $("#registerConfirmPassword").val()
    if (password == confirmPassword) {
        var data = {
            phone: $("#registerTel").val(),
            name: $("#registerName").val(),
            password: $("#registerPassword").val(),
        }
        $.ajax({
            type: 'post',
            url: '/register',
            dataType: "json",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (data) {
                window.localStorage.setItem("userId", data.userId);
                window.location.href = "/" + data.userId + "/profile"

            },
            statusCode: {
                401: function () {
                    $("#loginPassword").val("");
                    alert("用户名或密码错误！");
                }
            }
        });
    } else {
        $("#registerConfirmPassword").val('')
        $("#registerPassword").val('')

    }
}
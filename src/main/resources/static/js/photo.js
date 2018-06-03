// ------- support functions ---------------
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

// -------- photo page ---------------

function followphotolist() {
    $.ajax({
        type:'get',
        url:'/' + localStorage.getItem("userId") + '/invitation/attention',
        dataType: "json",
        contentType: "application/json;",
        success: function (invitation, textStatus, xhr) {
            if(xhr.status == 200) {
                var content = document.getElementById("getfollow");
                var str = "";
                for(var i=0; i<invitation.length; i++) {
                    str += "<div class=\"panel panel-default\">\n" +
                        "                <div class=\"panel-body\">\n" +
                        "                    <div class=\"row clearfix\">\n" +
                        "                        <div class=\"col-md-12 column\">\n" +
                        "                            <div class=\"row clearfix\">\n" +
                        "                                <div class=\"col-md-1 column\">\n" +
                        "                                    <img class=\"img-circle\" alt=\"140x140\" src=" + invitation[i].userImage + "\"/>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"col-md-2 column\">\n" +
                        "                                    <p class=\"panel-name\">\n" +
                                                             invitation[i].userName +
                        "                                    </p>\n" +
                        "                                    <span class=\"panel-time\">" + invitation[i].releaseTime + "</span>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"col-md-9 column\">\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                            <div class=\"row clearfix\">\n" +
                        "                                <div class=\"col-md-1 column\">\n" +
                        "                                </div>\n" +
                        "                                <div class=\"col-md-11 column\">\n" +
                        "                                    <p class=\"panel-content\">\n" +
                                                             invitation[i].content +
                        "                                    </p>\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "                <div class=\"panel-footer\">\n" +
                        "                    <div class=\"row clearfix\">\n" +
                        "                        <div class=\"col-md-4 column footer-icons\">\n" +
                        "                            <img class=\"navbar-icons\" src=\"../../static/img/share.png\" alt=\"...\">\n" +
                        "                            转发\n" +
                        "                        </div>\n" +
                        "                        <div class=\"col-md-4 column footer-icons\">\n" +
                        "                            <img class=\"navbar-icons\" src=\"../../static/img/comment.png\" alt=\"...\">\n" +
                        "                            评论\n" +
                        "                        </div>\n" +
                        "                        <div class=\"col-md-4 column footer-icons\">\n" +
                        "                            <span class=\"card-icon left like\">&#10084;</span>\n" +
                        "                            <span>" + invitation[i].loveNumber + "</span>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "            </div>";
                }
                content.innerHTML = str;
            }
        },
        statusCode:{
            401: function (){//statuscode unknown
                alert("获取信息失败");
            }
        }
    });
}
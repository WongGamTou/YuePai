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

function followphotolist(){
    $.ajax({
        type:'get',
        url:'/' + localStorage.getItem("userId") + '/imageshare/attention',
        dataType: "json",
        contentType: "application/json;",
        success: function (invitation, textStatus, xhr) {
            if(xhr.status == 200) {
                var content = document.getElementById("getfollow");
                var str = "";
                for(var i=0; i<data.length; i++) {
                    str +=         "                                        <div class=\"row\">\n"+
                                   "                                            <div class=\"col-sm-6 col-md-4\">\n"+
                                   "                                                <div class=\"thumbnail\">\n"+
                                   "                                                    <img data-toggle=\"modal\" data-target=\"#myModal\" src=\"../../static/img/img1.png\" alt=\"...\">\n"+
                                   "                                                    <div class=\"caption\">\n"+
                                   "                                                        <h3>匪我思存</h3>\n"+
                                   "                                                        <p>一只可爱小猫咪的艺术照哈哈哈</p>\n"+
                                   "                                                        <p class=\"card-icon like\">&#10084;</p>\n"+
                                   "                                                    </div>\n"+
                                   "                                                </div>\n"+
                                   "                                            </div>\n"+
                                   "                                            <div class=\"col-sm-6 col-md-4\">\n"+
                                   "                                                <div class=\"thumbnail\">\n"+
                                   "                                                    <img data-toggle=\"modal\" data-target=\"#myModal\" src=\"../../static/img/img2.png\" alt=\"...\">\n"+
                                   "                                                    <div class=\"caption\">\n"+
                                   "                                                        <h3>匪我思存</h3>\n"+
                                   "                                                        <p>一只可爱小猫咪的艺术照哈哈哈</p>\n"+
                                   "                                                        <p class=\"card-icon like\">&#10084;</p>\n"+
                                   "                                                    </div>\n"+
                                   "                                                </div>\n"+
                                   "                                            </div>\n"+
                                   "                                            <div class=\"col-sm-6 col-md-4\">\n"+
                                   "                                                <div class=\"thumbnail\">\n"+
                                   "                                                    <img data-toggle=\"modal\" data-target=\"#myModal\" src=\"../../static/img/img3.png\" alt=\"...\">\n"+
                                   "                                                    <div class=\"caption\">\n"+
                                   "                                                        <h3>匪我思存</h3>\n"+
                                   "                                                        <p>一只可爱小猫咪的艺术照哈哈哈</p>\n"+
                                   "                                                        <p class=\"card-icon like\">&#10084;</p>\n"+
                                   "                                                    </div>\n"+
                                   "                                                </div>\n"+
                                   "                                            </div>\n"+
                                   "                                        </div>";
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
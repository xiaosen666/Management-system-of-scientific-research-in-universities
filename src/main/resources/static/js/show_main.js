$(document).ready(function(){
    getNewsList();
    getUserLogin();

})

function showNews () {
    var docWidth = $('body').width(),
        $wrap = $('#wrap'),
        $images = $('#wrap .hb'),
        slidesWidth = $wrap.width();

    $(window).on('resize', function () {
        docWidth = $('body').width();
        slidesWidth = $wrap.width();
    })

    $(document).mousemove(function (e) {
        var mouseX = e.pageX,
            offset = mouseX / docWidth * slidesWidth - mouseX / 2;

        $images.css({
            '-webkit-transform': 'translate3d(' + -offset + 'px,0,0)',
            'transform': 'translate3d(' + -offset + 'px,0,0)'
        });
    });
}

var list;
function getNewsList(){
    $.ajax({
        type:"post",
        url:"./getNewsList",
        dataType:"JSON",
        data:{
        },
        success:function(data){
            if(data.code=="0"){
                list=data.news_list;
                for(i in list)
                {
                    $("#wrap").append("   <a href=\"#\" class=\"hb\">\n" +
                        "            <div class=\"c\">\n" +
                        "                <img src='"+list[i].news_photo+"' alt=\"\" />\n" +
                        "                <div id=\"txt\">\n" +
                        "                    <h1>"+list[i].news_title+"</h1>\n" +
                        "                    <p>"+list[i].news_date+"</p>\n" +
                        "                    <p>"+list[i].news_anthor+"</p>\n" +
                        "                     <p>"+list[i].news_class+"</p>\n"+
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </a>\n" +
                        "        <div class=\"fullBg\">\n" +
                        "            <img src='"+list[i].news_photo+"' alt=\"\" />\n" +
                        "        </div>");
                }
                showNews();
            }
            else{
                alert("获取文章列表失败")
            }


        },
        error:function(){
            alert("获取文章列表发生错误")
        }
    })
}

function getUserLogin(){
    $.ajax({
        type:"post",
        url:"./getUserLogin",
        dataType:"JSON",
        data:{
        },
        success:function(data){
            if(data.code=="0"){

                $("#loginok").css('display','block');
                var urlString="pages/myCenter.html?power="+data.power+"&userid="+data.userid;
                $("#mycenter").attr('href',urlString);
            }
            else{

                $("#nologin").css('display','block');
            }



        },
        error:function(){
            alert("获取用户登陆状态失败！")
        }
    })
}

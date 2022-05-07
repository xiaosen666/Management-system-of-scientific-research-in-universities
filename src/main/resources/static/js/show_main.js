$(document).ready(function(){
    getNewsList();
    getUserLogin();
})
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
                    $("#box1").append("<p>标题： "+list[i].news_title+"</p><p>作者： "+list[i].news_anthor+"</p><p>日期： "+list[i].news_date+"</p><p>类型： "+list[i].news_class+"</p>图片：<img style='width: 50px;height: 50px;' src='"+list[i].news_photo+"'/><hr><hr>");
                }
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

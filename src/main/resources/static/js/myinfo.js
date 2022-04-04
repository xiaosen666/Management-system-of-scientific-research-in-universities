$(document).ready(function(){
    getLendList();

})
var list;
function getLendList(){
    $.ajax({
        type:"post",
        url:"../getLendList",
        dataType:"JSON",
        data:{
        },
        success:function(data){
            if(data.code=="0"){
                list=data.lend_list;
                for(i in list)
                {
                    $("#lendlist").append(" <div class=\"pos-relative flex-wr-sb-s\" id=\"lendlist\"> <a href=\"blog-detail-2.html\" class=\"size-w-8 wrap-pic-w hov1 trans-03 w-full-sr575 m-b-10\"> <span class=\"dis-sub-item-tag\">设备申请审核中</span> <img src=\"../img/post-43.jpg\" alt=\"IMG\"> </a>\n" +
                        "                        <div class=\"size-w-9 w-full-sr575 m-b-25\">\n" +
                        "                            <div class=\"cl8\"><a href=\"#\" class=\"f1-s-4 cl8 hov-cl10 trans-03\">模块</a> <span class=\"f1-s-3 m-rl-3\"> - </span> <span class=\"f1-s-3\">时间</span></div>\n" +
                        "                            <h5 class=\"p-t-10 p-b-18\"><a href=\"blog-detail-2.html\" class=\"f1-l-2 cl2 hov-cl10 trans-03 respon2\">实验设备简介</a></h5>\n" +
                        "                            <p class=\"f1-s-1 cl6 p-b-15\">"+list[i].name+"</p>\n" +
                        "                            <p class=\"f1-s-1 cl6 p-b-15\">"+list[i].num+"</p>\n" +
                        "                            <p class=\"f1-s-1 cl6 p-b-15\">"+list[i].phone+"</p>\n" +
                        "                            <p class=\"f1-s-1 cl6 p-b-15\">"+list[i].message+"</p>\n" +
                        "                            \n" +
                        "                        </div>\n" +
                        "                    </div>");

                    // <tr><td>"+list[i].name+"</td><td>"+list[i].num+"</td><td>"+list[i].phone+"</td><td>"+list[i].message+"</td></tr>
                }
            }
            else{
                alert("获取租借列表失败")
            }



        },
        error:function(){
            alert("获取租借列表发生错误")
        }
    })
}

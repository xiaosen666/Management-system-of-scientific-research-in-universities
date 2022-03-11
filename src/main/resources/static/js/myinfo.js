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
                    $("#lendlist").append("<tr><td>"+list[i].equiment+"</td><td>"+list[i].num+"</td><td>"+list[i].phone+"</td></tr>");
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

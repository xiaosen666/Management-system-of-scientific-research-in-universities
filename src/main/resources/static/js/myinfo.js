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
                    if(list[i].checkUname!=null)
                        $("#lendlist").append("<tr><td>"+list[i].name+"</td><td>"+list[i].num+"</td><td>"+list[i].phone+"</td><td>"+list[i].message+"</td>  <td>"+list[i].lendDate+"</td><td>"+list[i].backDate+"</td><td>"+list[i].equiment+"</td><td>"+list[i].checkUname+"</td><td>已确认</td></tr>");
                    else
                        $("#lendlist").append("<tr><td>"+list[i].name+"</td><td>"+list[i].num+"</td><td>"+list[i].phone+"</td><td>"+list[i].message+"</td>  <td>"+list[i].lendDate+"</td><td>"+list[i].backDate+"</td><td>"+list[i].equiment+"</td><td>"+list[i].checkUname+"</td><td><button id='"+list[i].lendid+"' onclick='insertCheckname(this.id)'>确认租借</button></td></tr>");
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

function insertCheckname(lendid)
{
    $.ajax({
        type:"post",
        url:"../set_check_name",
        dataType:"JSON",
        data:{
            "lendid":lendid,
        },
        success:function(data){
            if(data.code=="0"){
                alert("已经确认租借，请通知学生进行租借");
                window.location.reload();
            }
            else{
                alert("确认失败")
            }
        },
        error:function(){
            alert("系统发生错误")
        }
    })
}
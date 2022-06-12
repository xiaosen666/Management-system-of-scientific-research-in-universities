$(document).ready(function(){
    getLendList();

})
var list;
function getLendList(){
    $.ajax({
        type:"post",
        url:"../getLendListbyUid",
        dataType:"JSON",
        data:{
        },
        success:function(data){
            if(data.code=="0"){
                list=data.lend_list;
                for(i in list)
                {
                    if(list[i].checkUname!=null)
                        $("#lendlist").append("<tr><td>"+list[i].equiment+"</td><td>"+list[i].num+"</td><td>"+list[i].lendDate+"</td><td>"+list[i].backDate+"</td><td>"+list[i].checkUname+"</td><td><button id='"+list[i].lendid+"' data-tool='"+list[i].equiment+"' data-num='"+list[i].num+"' onclick='backTool(this.id,event)'>一键归还</button></td></tr>")
                    else
                        $("#lendlist").append("<tr><td>"+list[i].equiment+"</td><td>"+list[i].num+"</td><td>"+list[i].lendDate+"</td><td>"+list[i].backDate+"</td><td>"+list[i].checkUname+"</td><td><button id='"+list[i].lendid+"' data-tool='"+list[i].equiment+"' data-num='"+list[i].num+"' onclick='backTool(this.id,event)'>取消预约</button></td></tr>")
                }
            }
            else{
                alert("获取个人租借列表失败")
            }



        },
        error:function(){
            alert("获取个人租借列表发生错误")
        }
    })
}

function backTool(lid,event){
    var tool=$(event.target).data("tool");
    var num=$(event.target).data("num");
    $.ajax({
        type:"post",
        url:"../backTool",
        dataType:"JSON",
        data:{
            "lid":lid,
            "tool":tool,
            "num":num,
        },
        success:function(data){
            if(data.code=="0"){
                alert("退还成功");
                window.location.reload();
            }
            else{
                alert("退还失败")
            }
        },
        error:function(){
            alert("系统发生错误")
        }
    })
}
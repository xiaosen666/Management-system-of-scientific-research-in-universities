$(document).ready(function(){
    getToolList();
})


var tabs=document.getElementById("tab").getElementsByTagName("li");

var divs=document.getElementById("tab_con").getElementsByTagName("div");

function change(obj){
    for (var i=0;i<tabs.length;i++){
        if(tabs[i]==obj) {
            tabs[i].className="fli";

            divs[i].className="fdiv";

        }

        else {
            tabs[i].className="";

            divs[i].className=""
        }
    }
}

function getToolList()
{
    $.ajax({
        type:"GET",
        url:"../get_equiment_list",
        success:function(data){
            if(data.code=="0"){
                var toolList=data.tool_list;
                for(var i in toolList)
                {
                    var list_str1="<tr><td>"+toolList[i].id+"</td><td>"+toolList[i].tool+"</td><td>"+toolList[i].amount+"</td></tr>";
                    var list_str2="<tr><td>"+toolList[i].id+"</td><td>"+toolList[i].tool+"</td><td>"+toolList[i].amount+"</td><td><button id='"+toolList[i].id+"' onclick='delectToolList(this.id)'>删除</button><button id='U"+toolList[i].id+"' data-toolname='"+toolList[i].tool+"' onclick='showUpdate(this.id,event)'>修改</button></td></tr>";
                    $("#tablewatch").append(list_str1);
                    $("#tableset").append(list_str2);
                }

            }
            else if(data.code=="-1"){
                alert("退出失败！！")
            }
        },
        error:function(){
            alert("退出失败");
        }
    });
}


function delectToolList(tid){
    $.ajax({
        type:"POST",
        url:"../delect_tool",
        data:{
            "id":tid,
        },
        success:function(data){
            if(data.code=="0"){
                alert("成功删除！");
            }
            else if(data.code=="-1"){
                alert("删除失败！！")
            }
        },
        error:function(){
            alert("系统出错");
        }
    });
}

function showUpdate(tid,event){
    var toolname=$(event.target).data("toolname");
    var toolid=tid.substr(1);
    document.getElementById("update_tool").style.display="block";
    $("#equiment").val(toolname);
    $("#toolid").empty();
    $("#toolid").append(toolid);
}

function InsertToolList(){

    var equiment=document.getElementById("iequiment").value;
    var amount=document.getElementById("iamount").value;

    $.ajax({

        type:"POST",
        url:"../insert_tool",
        data:{
            "equiment":equiment,
            "amount":amount,
        },
        success:function(data){
            if(data.code=="0"){
                alert("添加成功！");
            }
            else if(data.code=="-1"){
                alert("添加失败！！")
            }
        },
        error:function(){
            alert("系统出错");
        }
    });
}

function updateToolList(){
    var toolid=document.getElementById("toolid").innerText;
    var equiment=document.getElementById("equiment").value;
    var amount=document.getElementById("amount").value;

    $.ajax({

        type:"POST",
        url:"../update_tool",
        data:{
            "id":toolid,
            "equiment":equiment,
            "amount":amount,
        },
        success:function(data){
            if(data.code=="0"){
                alert("更新信息成功！");
            }
            else if(data.code=="-1"){
                alert("更新失败！！")
            }
        },
        error:function(){
            alert("系统出错");
        }
    });
}
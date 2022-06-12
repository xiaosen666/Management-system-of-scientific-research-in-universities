$(document).ready(function(){
    get_op_app_List();

})
var list;
var state_str;
function get_op_app_List(){
    $.ajax({
        type:"post",
        url:"../get_op_app_List",
        dataType:"JSON",
        data:{
        },
        success:function(data){
            if(data.code=="0"){
                list=data.op_list;
                for(i in list)
                {
                    if(list[i].state==-1)
                    {
                        state_str="未审核";
                        $("#app_list").append("<tr><td>"+list[i].application_id+"</td><td>"+list[i].p_name+"</td><td>"+list[i].your_name+"</td><td>"+list[i].your_id+"</td><td>"+list[i].phone+"</td><td>"+list[i].p_type+"</td><td>"+list[i].t_name+"</td><td>"+state_str+"</td><td><button id='b1_"+list[i].application_id+"' onclick='set_state(0,this.id)'>允许通过</button>&nbsp&nbsp<button id='b2_"+list[i].application_id+"' onclick='set_state(2,this.id)'>拒绝通过</button></td></tr>");
                    }

                    else if(list[i].state==1)
                    {
                        state_str="已允许通过";
                        $("#app_list").append("<tr><td>"+list[i].application_id+"</td><td>"+list[i].p_name+"</td><td>"+list[i].your_name+"</td><td>"+list[i].your_id+"</td><td>"+list[i].phone+"</td><td>"+list[i].p_type+"</td><td>"+list[i].t_name+"</td><td>"+state_str+"</td><td><button style='display: none' id='b1_"+list[i].application_id+"' onclick='set_state(1,this.id)'>允许通过</button>&nbsp&nbsp<button style='display: none' id='b2_"+list[i].application_id+"' onclick='set_state(0,this.id)'>拒绝通过</button></td></tr>");
                    }

                    else
                    {
                        state_str="已拒绝通过";
                        $("#app_list").append("<tr><td>"+list[i].application_id+"</td><td>"+list[i].p_name+"</td><td>"+list[i].your_name+"</td><td>"+list[i].your_id+"</td><td>"+list[i].phone+"</td><td>"+list[i].p_type+"</td><td>"+list[i].t_name+"</td><td>"+state_str+"</td><td><button style='display: none' id='b1_"+list[i].application_id+"' onclick='set_state(1,this.id)'>允许通过</button>&nbsp&nbsp<button style='display: none' id='b2_"+list[i].application_id+"' onclick='set_state(0,this.id)'>拒绝通过</button></td></tr>");
                    }
                }
            }
            else{
                alert("获取开题申请列表失败")
            }
        },
        error:function(){
            alert("获取开题申请列表发生错误")
        }
    })
}

function set_state(state,b_id){
    if(confirm("只有一次操作机会！是否进行下一步操作？")==false)
        return;
    var p_id=b_id.split("_")
    if(p_id[0]=="b1")
        Insert_been_checked(p_id[1]);
    else
        alert("该项目已不通过！");

    $.ajax({
        type:"post",
        url:"../update_state",
        dataType:"JSON",
        data:{
            "state":state,
            "p_id":p_id[1],
        },
        success:function(data){
            if(data.code=="0"){
                var urlString="check_op_application.html";
                window.location.href=urlString;
            }
            else{
                alert("获取开题申请列表失败")
            }
        },
        error:function(){
            alert("获取开题申请列表发生错误")
        }
    })
}

function Insert_been_checked(pid){
    $.ajax({
        type:"POST",
        url:"../get_checked",
        dataType:"JSON",
        data:{
            "p_id":pid,
        },
        success:function(data){

            if(data.code=="0"){
                alert("已经添加到项目管理！")
            }
            else if(data.code=="-1"){
                alert("添加项目管理失败！")
            }
        },
        error:function(){
            alert("添加项目发生错误");
        }
    });
}
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
                        state_str="未审核";
                    else if(list[i].state==0)
                        state_str="已拒绝通过";
                    else
                        state_str="已允许通过";
                    $("#app_list").append("<tr><td>"+list[i].application_id+"</td><td>"+list[i].p_name+"</td><td>"+list[i].your_name+"</td><td>"+list[i].your_id+"</td><td>"+list[i].phone+"</td><td>"+list[i].p_type+"</td><td>"+list[i].t_name+"</td><td>"+state_str+"</td><td><button id='b1_"+list[i].application_id+"' onclick='set_state(1,this.id)'>允许通过</button>&nbsp&nbsp<button id='b2_"+list[i].application_id+"' onclick='set_state(0,this.id)'>拒绝通过</button></td></tr>");
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
    var p_id=b_id.split("_")

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
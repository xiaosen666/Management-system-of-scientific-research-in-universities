var pageNum=1;
var pageSize=8;
var l;
var moneynum=0;


$(document).ready(function(){
    getopList();
    getendList();

    $("#pre").on('click',function(){
        getPre();
    });
    $("#next").on('click',function(){
        getNext();
    });

})

//判断对象/JSON是否为空 空返回1 非空返回0
function isEmptyObject(e) {
    var t;
    for (t in e)
        return 0;
    return 1;
}

function getopList(){
    $.ajax({
        type:"post",
        url:"../get_op_app_List",
        dataType:"JSON",

        success:function(data){

                var oplist=data.op_list;
                var htmlStr=" ";
                var btnStr=" ";
                var state=" ";

                $("#opList").empty();
                $("#opList").append("<tr class=\"table-primary\"><th>项目申请人</th><th>学号</th><th>申请经费金额</th><th>状态</th><th>操作</th></tr>")

                for(i in oplist){

                    if(oplist[i].state=="0"){
                        state="已审核但未批款";
                        btnStr="<input  type=\"button\"  class=\"btn btn-info\" data-p_id=\""+oplist[i].application_id+"\" id=\"showProject\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"><input type=\"button\" class=\"btn btn-success\" data-orderid=\""+oplist[i].application_id+"\" data-state='1' id=\"giveMoney\" value=\"确认支付经费\">";
                    }
                    else if(oplist[i].state=="1"){
                        state="已审核";
                        btnStr="<input type=\"button\"  class=\"btn btn-info\" data-p_id=\""+oplist[i].application_id+"\" id=\"showProject\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"> ";
                    }
                    else if(oplist[i].state=="2"){
                        state="项目已取消";
                        btnStr="<input type=\"button\"  class=\"btn btn-info\" data-p_id=\""+oplist[i].application_id+"\" id=\"showProject\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"> <input type=\"button\" class=\"btn btn-success\"  data-orderid=\""+oplist[i].application_id+"\" id=\"delOrder\" value=\"删除\">";
                    }
                    else{
                        state="项目未审核";
                        btnStr="<input type=\"button\"  class=\"btn btn-info\" data-p_id=\""+oplist[i].application_id+"\" id=\"showProject\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"> <input type=\"button\" onclick=\"window.location.href='check_op_application.html'\"  class=\"btn btn-success\"  data-orderid=\""+oplist[i].application_id+"\" id=\"delOrder\" value=\"现在审核\">";
                    }
                    htmlStr="<tr data-orderid=\""+oplist[i].application_id+"\"><td>"+oplist[i].your_name+"</td><td>"+oplist[i].your_id+"</td><td>"+oplist[i].money+"</td><td>"+state+"</td><td>"+btnStr+"</td></tr>";
                    $("#opList").append(htmlStr);
                    moneynum+=parseInt(oplist[i].money);
                }
                $("#totalMoney1").empty();
                $("#totalRoom1").empty();
                $("#totalMoney1").append(moneynum);
                $("#totalRoom1").append(oplist.length);

                btnOn();


        },
        error:function(){
            alert("获取项目列表发生错误")
        }
    })
}

function getendList(){
    $.ajax({
        type:"post",
        url:"../get_end_app_List",
        dataType:"JSON",

        success:function(data){

                var endlist=data.end_list;
                var htmlStr=" ";
                var btnStr=" ";
                var state=" ";
                $("#endList").empty();
                $("#endList").append("<tr class=\"table-primary\"><th>项目申请人</th><th>学号</th><th>状态</th><th>操作</th></tr>")

                for(i in endlist){

                    if(endlist[i].state=="0"){
                        state="结项失败";
                        btnStr="<input  type=\"button\"  class=\"btn btn-info\" data-p_id=\""+endlist[i].application_id+"\" id=\"showProject2\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"><input type=\"button\" class=\"btn btn-success\" data-orderid=\""+endlist[i].application_id+"\" data-state='1' id=\"nonebtn\" value=\"删除该项目\">";
                    }
                    else if(endlist[i].state=="1"){
                        state="已结项且设为科研成果";
                        btnStr="<input type=\"button\"  class=\"btn btn-info\" data-p_id=\""+endlist[i].application_id+"\" id=\"showProject2\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"> ";
                    }
                    else if(endlist[i].state=="2"){
                        state="已结项但未设置为科研成果";
                        btnStr="<input type=\"button\"  class=\"btn btn-info\" data-p_id=\""+endlist[i].application_id+"\" id=\"showProject2\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"><input type=\"button\" class=\"btn btn-success\" data-orderid=\""+endlist[i].application_id+"\" data-state='1' id=\"nonebtn\" value=\"设置为科研成果\">";
                    }
                    else{
                        state="项目未审核";
                        btnStr="<input type=\"button\"  class=\"btn btn-info\" data-p_id=\""+endlist[i].application_id+"\" id=\"showProject2\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"> <input type=\"button\" onclick=\"window.location.href='check_end_application.html'\" class=\"btn btn-success\"  data-orderid=\""+endlist[i].application_id+"\" id=\"delOrder\" value=\"现在审核\">";
                    }
                    htmlStr="<tr data-orderid=\""+endlist[i].application_id+"\"><td>"+endlist[i].your_name+"</td><td>"+endlist[i].your_id+"</td><td>"+state+"</td><td>"+btnStr+"</td></tr>";
                    $("#endList").append(htmlStr);


                }

                $("#totalRoom").empty();

                $("#totalRoom").append(endlist.length);
                btnOn();


        },
        error:function(){
            alert("获取项目列表发生错误")
        }
    })
}

function btnOn(){

    $("input").filter("#showProject").on('click',function(event){
        showProject(event);
    })

    $("input").filter("#showProject2").on('click',function(event){
        showProject2(event);
    })

    $("input").filter("#giveMoney").on('click',function(event){
        giveMoney(event);
    });
}

function EditopState(p_id,state){
    $.ajax({
        type:"POST",
        url:"../update_state",
        dataType:"JSON",
        data:{
            "p_id":p_id,
            "state":state,
        },
        success:function(data){
            if(data.code==0){
            }
        },
        error:function(){
            alert("更新开题状态出现错误");
        }
    })

}

function giveMoney(event){
    var pid=$(event.target).data("orderid");
    var state=$(event.target).data("state");
    EditopState(pid,state);
    $.ajax({
        type:"POST",
        url:"../update_money_state",
        dataType:"JSON",
        data:{
            "pid":pid,
            "state":state,
        },
        success:function(data){
            if(data.code==0){
                alert("经费已下达");
            }
            else
                alert("经费支出失败")
        },
        error:function(){
            alert("经费支出出现错误");
        }
    })

}




function showProject(event){
    var pid=$(event.target).data("p_id");
    $.ajax({
        type:"POST",
        url:"../room/getProjectById.do",
        dataType:"JSON",
        data:{
            "pid":pid
        },
        success:function(data){
            var project=data.project;
            $("#roomTable").empty();
            if(data.code==0){
                var htmlStr=" ";

                htmlStr="<tr><th>项目名称：</th><td>"+project.p_name+"</td></tr><tr><th>项目申请人</th><td>"+project.your_name+"</td></tr><tr><th>所在的学院</th><td>"+project.p_type+"</td></tr><tr><th>联系方式</th><td>"+project.phone+"</td></tr>"
                $("#roomTable").append(htmlStr);
            }
            else
                alert("获取失败")
        },
        error:function(){
            alert("获取信息出现错误");
        }
    })
}

function showProject2(event){
    var pid=$(event.target).data("p_id");
    $.ajax({
        type:"POST",
        url:"../room/get_e_ProjectById.do",
        dataType:"JSON",
        data:{
            "pid":pid
        },
        success:function(data){
            var project=data.project;
            $("#roomTable").empty();
            if(data.code==0){
                var htmlStr=" ";

                htmlStr="<tr><th>项目名称：</th><td>"+project.p_name+"</td></tr><tr><th>项目申请人</th><td>"+project.your_name+"</td></tr><tr><th>所在的学院</th><td>"+project.p_type+"</td></tr><tr><th>联系方式</th><td>"+project.phone+"</td></tr>"
                $("#roomTable").append(htmlStr);
            }
            else
                alert("获取失败")
        },
        error:function(){
            alert("获取信息出现错误");
        }
    })
}
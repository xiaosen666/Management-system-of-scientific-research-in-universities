
$(document).ready(function(){
    geteProjectList();

})

function geteProjectList(){
    $.ajax({
        type:"post",
        url:"../get_e_project",
        dataType:"JSON",

        success:function(data){

                var ep_list=data.ep_list;
                var end_applist=data.end_applist;
                var check_u_name=data.check_uname;

                var htmlStr=" ";
                var headStr="";

                $("#end_p_List").empty();
                headStr="<tr class=\"table-primary\"><th>项目名称</th><th>项目负责人</th><th>学号</th><th>项目指导老师</th><th>审核人</th><th>成果资料</th></tr>";
                $("#end_p_List").append(headStr);
                for(i in ep_list){

                    htmlStr="<tr data-pid=\""+ep_list[i].pid+"\"><td>"+end_applist[i].p_name+"</td><td>"+ep_list[i].name+"</td><td>"+end_applist[i].your_id+"</td><td>"+ep_list[i].t_name+"</td><td>"+check_u_name[i].username+"</td><td><a href='#'>"+end_applist[i].file_name+"</a></td></tr>";

                    $("#end_p_List").append(htmlStr);

                }

                $("#result").empty();
                $("#result").append(ep_list.length);
        },
        error:function(){
            alert("获取项目成果列表发生错误")
        }
    })
}

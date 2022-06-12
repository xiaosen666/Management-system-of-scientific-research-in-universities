var op_id=window.location.search.split("?");

$(document).ready(function(){
    op_id=op_id[1].split("=");
    setInfo(op_id[1]);
})

function setInfo(opid){

    $.ajax({
        type:"POST",
        url:"../get_one_op_app",
        dataType:"JSON",
        data:{
            "opid":opid,
        },
        success:function(data){
            if(data.code==0){
                var op_app=data.op_app;
                $("#p_name").val(op_app.p_name);
                $("#your_name").val(op_app.your_name);
                $("#your_id").val(op_app.your_id);
                $("#phone").val(op_app.phone);
                $("#p_type").val(op_app.p_type);
                $("#t_name").val(op_app.t_name);
                $("#money").val(op_app.money);
            }
            else
                alert("获取结题信息失败")
        },
        error:function(){
            alert("系统出现错误");
        }
    })

}

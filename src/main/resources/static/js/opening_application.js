$(document).ready(function(){


})



//开题申请
function To_opening_application(){

    var p_name=$("#p_name").val();
    //var your_name=$("#your_name").val();
    var your_id=$("#your_id").val();
    var phone=$("#phone").val();
    var p_type=$("#p_type").val();
    var t_name=$("#t_name").val();
    var money=$("#money").val();
    $.ajax({
        type:"POST",
        url:"../opening_application",
        dataType:"JSON",
        data:{
            "p_name":p_name,
            //"your_name":your_name,
            "your_id":your_id,
            "phone":phone,
            "p_type":p_type,
            "t_name":t_name,
            "money":money,
        },
        success:function(data){
            console.log(data)
            if(data.code=="0"){
                alert("申请成功！等待老师审核完毕...即将返回主页")
                var urlString="../index.html";
                window.location.href=urlString;
            }
            else if(data.code=="-1"){
                alert("验证码或密码错误")
            }
        },
        error:function(){
            alert(" 发生错误");
        }
    });
}
$(document).ready(function(){

    $("#to_test").click(function(){

    })

})



//登录
function To_test(){
    var name=$("#your_name").val();
    var phone=$("#your_phone").val();
    var num=$("#num").val();
    var equiment=$("#equiment").val();
    $.ajax({
        type:"POST",
        url:"../test",
        dataType:"JSON",
        data:{
            "name":name,
            "phone":phone,
            "num":num,
            "equiment":equiment,
        },
        success:function(data){
            console.log(data)
            if(data.code=="0"){
                var urlString="myinfo.html";
                window.location.href=urlString;
            }
            else if(data.code=="-1"){
                alert("验证码或密码错误")
            }
        },
        error:function(){
            alert("登录 发生错误");
        }
    });
}
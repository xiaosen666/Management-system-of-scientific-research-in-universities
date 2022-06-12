$(document).ready(function(){
    $("#ResignBtn").click(function(){
        resign();
    })
})


//注册
function resign(){
    var useraccount=$("#useraccount").val();
    var pwd=$("#pwd").val();
    var username=$("#username").val();
    var age=$("#age").val();
    var power=$("#power").val();
    var ID=$("#IDnumber").val();
    var money=$("#money").val();
    var phone=$("#phonenumber").val();
    $.ajax({
        type:"POST",
        url:"./user/addUser.do",
        dataType:"JSON",
        data:{
            "useraccount":useraccount,
            "password":pwd,
            "username":username,
            "age":age,
            "power":power,
            "ID":ID,
            "money":money,
            "phone":phone,
        },
        success:function(data){
            if(data.code=="0"){
                var urlString="./login.html";
                window.location.href=urlString;
            }
            else if(data.code=="-1"){
                alert("注册失败")
            }
        },
        error:function(){
            alert("注册 发生错误");
        }
    });
}
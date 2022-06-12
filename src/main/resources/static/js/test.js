$(document).ready(function(){
getAllEquiment();

})

var elist=[];
var amountnum;
var equimentindex;

function whatToolSelected(){
    equimentindex=$("#equiment").get(0).selectedIndex;
    $("#amount").empty();
    $("#amount").append("库存数量："+elist[equimentindex].amount);
    amountnum=elist[equimentindex].amount;
}

function getAllEquiment(){

    $.ajax({
        type:"POST",
        url:"../get_equiment_list",
        dataType:"JSON",

        success:function(data){
            if(data.code=="0"){
                var elist2 = data.tool_list;

                for(var i in elist2)
                {
                    if(elist2[i].amount>0) {
                        $("#equiment").append("<option value='" + elist2[i].tool + "'>" + elist2[i].tool + "</option>");
                        elist.push(elist2[i]);
                    }

                }
                whatToolSelected();

            }
            else if(data.code=="-1"){
                alert("获取仪器列表错误")
            }
        },
        error:function(){
            alert("发生错误");
        }
    });
}


function To_test(){

    var phone=$("#your_phone").val();
    var num=$("#num").val();
    var name=$("#name").val();
    var message=$("#message").val();

    var backDate=$("#backDate").val();
    var equiment=$("#equiment").val();

    if(num>parseInt(amountnum))
    {
        alert("租借的数量大于库存量，无法租借");
        return;
    }


    $.ajax({
        type:"POST",
        url:"../test",
        dataType:"JSON",
        data:{
            "phone":phone,
            "num":num,
            "name":name,
            "message":message,
            "backDate":backDate,
            "equiment":equiment,
            "tid":elist[equimentindex].id
        },
        success:function(data){
            console.log(data)
            if(data.code=="0"){
                window.location.reload();
                alert("申请租借设备成功，请耐心等待审核...");
            }
            else if(data.code=="-1"){
                alert("租借发生错误")
            }
        },
        error:function(){
            alert("系统发生错误");
        }
    });
}
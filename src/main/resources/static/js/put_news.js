
function put_News(){
    var n_title=$("#n_title").val();
    var n_anthor=$("#n_author").val();
    var n_date=$("#n_date").val();
    var n_class=$("#n_class").val();
    var n_photo=$("#n_photo").val();

    $.ajax({
        type:"POST",
        url:"../putNews",
        dataType:"JSON",
        data:{
            "anthor":n_anthor,
            "myclass":n_class,
            "date":n_date,
            "photo":n_photo,
            "title":n_title,
        },
        success:function(data){

            if(data.code=="0"){

                window.location.href="../index.html";
            }
            else if(data.code=="-1"){
                alert("添加新闻失败！")
            }
        },
        error:function(){
            alert("添加新闻发生错误");
        }
    });
}
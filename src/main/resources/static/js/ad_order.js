var pageNum=1;
var pageSize=8;
var l;

$(document).ready(function(){
	getorderList();
	//getConfig();
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

var list;
function getorderList(){
	$.ajax({
		type:"post",
		url:"../order/getAllOrder.do",
		dataType:"JSON",
		data:{
			"pageNum":pageNum,
			"pageSize":pageSize		
		},
		success:function(data){
			if(isEmptyObject(data.List)&&pageNum>0){
				pageNum=pageNum-1;
				getorderList();
			}
			else{
				list=data.List;
				var htmlStr=" ";
				var btnStr=" ";
				var state=" ";
				l=0;
				$("#pre").css("display","block");
				$("#next").css("display","block");
				$("#orderList").empty();
				$("#orderList").append("<tr><th>项目申请人</th><th>学号</th><th>申请经费金额</th><th>状态</th><th>操作</th></tr>")
				
				for(i in list){

					if(list[i].state=="0"){
						state="未支出经费";
						btnStr="<input  type=\"button\"  class=\"btn btn-info\" data-p_id=\""+list[i].pid+"\" id=\"showProject\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\">";
					}
					else if(list[i].state=="1"){
						state="已支出经费";
						btnStr="<input type=\"button\"  class=\"btn btn-info\" data-p_id=\""+list[i].pid+"\" id=\"showProject\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"> ";
					}
					else if(list[i].state=="2"){
						state="项目已完成";
						btnStr="<input type=\"button\"  class=\"btn btn-info\" data-p_id=\""+list[i].pid+"\" id=\"showProject\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"> <input type=\"button\" class=\"btn btn-success\"  data-orderid=\""+list[i].pid+"\" id=\"delOrder\" value=\"删除\">";
					}
					else{
						state="项目已取消";
						btnStr="<input type=\"button\"  class=\"btn btn-info\" data-p_id=\""+list[i].pid+"\" id=\"showProject\"  data-toggle=\"modal\" data-target='#showProjectT' value=\"查看项目详情\"> <input type=\"button\"  class=\"btn btn-success\"  data-orderid=\""+list[i].pid+"\" id=\"delOrder\" value=\"删除\">";
					}
					htmlStr="<tr data-orderid=\""+list[i].pid+"\"><td>"+list[i].name+"</td><td>"+list[i].id+"</td><td>"+list[i].money+"</td><td>"+state+"</td><td>"+btnStr+"</td></tr>";
					$("#orderList").append(htmlStr);
					l++;
				}
				if(pageNum=="1") $("#pre").css("display","none");
				if(pageSize>l) $("#next").css("display","none");
				btnOn();
			}

		},
		error:function(){
			alert("获取项目列表发生错误")
		}
	})
}

function btnOn(){

	$("input").filter("#setPageBtn").on('click',function( ){
		setPage( );
	});
	$("input").filter("#showProject").on('click',function(event){
		showProject(event);
	})
	$("input").filter("#delOrder").on('click',function(event){
		delOrder(event);
	});
}

function getPre(){
	pageNum=pageNum-1;
	getorderList();
}

function getNext(){
	pageNum=pageNum+1;
	getorderList();	
}

function setPage(){
	
	if($("#inputPage").val()<0 || $("#inputPage").val()==0)
		alert("请输入正确页码");
	else{
		pageNum=$("#inputPage").val();
		getorderList();
	}
	
}

function delOrder(event){
	var orderid=$(event.target).data("orderid");
	$.ajax({
		type:"POST",
		url:"../order/delOrder.do",
		dataType:"JSON",
		data:{
			"orderid":orderid
		},
		success:function(data){
				if(data.code==0){
					alert("删除成功");
				if(l==1)
					pageNum=pageNum-1;
				getorderList();
			}
			else
				alert("删除失败")
		},
		error:function(){
			alert("删除出现错误");
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

				htmlStr="<tr><th>项目名称：</th><td>"+project.p_name+"</td></tr><tr><th>项目申请人</th><td>"+project.your_name+"</td></tr><tr><th>项目类型</th><td>"+project.p_type+"</td></tr><tr><th>联系方式</th><td>"+project.phone+"</td></tr>"
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

function getConfig(){
	$.ajax({
		type:"POST",
		url:"../config/getConfig.do",
		dataType:"JSON",
		data:{},
		success:function(data){
			if(data.code=="0"){
				var config=data.config;
				$("#totalMoney").text(config.totalmoney);
				$("#totalRoom").text(config.totalroom);
			}
			else{
				alert("获取配置错误");
			}

		},
		error:function(){
			alert("获取配置发生错误")
		}

	});
}
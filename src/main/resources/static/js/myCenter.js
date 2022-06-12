(function($){
	$.getData=function(name){
		var reg=new RegExp("(^|&)"+name+"=([^&]+)(&|$)?");
		var result = window.location.search.substr(1).match(reg);
		if (result!= null) return result[2]; return null;		
	}
})(jQuery);

var power=$.getData('power');
var userid=$.getData('userid');


$(document).ready(function(){
	$("#mainFrame").attr("src","all_infor.html?userid="+userid+"&power="+power);
	$("inputNone").attr("value",userid);
	var h=$(window).height();
	$("#tagList").css("height",h);
	$("#mainFrame").css("height",h);
	setList();
	getUname();
})

function setList(){
	var tagList=" ";
	var whatPort="";
	if(power=="0"){
		whatPort="超级管理员端";
		tagList="<a href=\"all_infor.html?userid="+userid+"&power="+power+"\"  target=\"mainFrame\" class=\"list-group-item active\"><span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span>个人信息</a><a href=\"ad_manage.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-list\" aria-hidden=\"true\"></span>人员管理</a> <a href=\"getAllProject.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-shopping-cart\" aria-hidden=\"true\"></span> 项目管理</a> <a href=\"ad_salary.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span>经费管理</a><a href=\"achievement_manage.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span>科研成果管理</a><a href=\"put_news.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span>添加科研新闻</a><a href=\"manage_equiment.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span>仪器设备管理</a>"
	}
	else if(power=="1"){
		whatPort="普通管理员端";
		tagList="<a href=\"all_infor.html?userid="+userid+"&power="+power+"\" target=\"mainFrame\" class=\"list-group-item active\"><span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span>个人信息</a><a href=\"check_op_application.html\" target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-list\" aria-hidden=\"true\"></span>开题审核</a><a href=\"check_end_application.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-home\" aria-hidden=\"true\"></span>结题审核</a><a href=\"getAllProject.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-shopping-cart\" aria-hidden=\"true\"></span> 项目管理</a><a href=\"myinfo.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span>设备管理</a>"
	}
	else if(power=="2"){
		whatPort="科研人员端";
		tagList="<a href=\"all_infor.html?userid="+userid+"&power="+power+"\"  target=\"mainFrame\" class=\"list-group-item active\"><span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span>个人信息</a><a href=\"rental_of_equipment.html\" target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-home\" aria-hidden=\"true\"></span>申请租借设备</a><a href=\"Opening_application.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-shopping-cart\" aria-hidden=\"true\"></span> 开题申请</a><a href=\"ad_order.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span>立项查询</a><a href=\"e_ad_order.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span>结项查询</a><a href=\"backTool.html\"  target=\"mainFrame\" class=\"list-group-item\"><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span>归还仪器设备</a>"
	}
	//<a href="end_application.html" target="mainFrame" class="list-group-item"><span class="glyphicon glyphicon-list" aria-hidden="true"></span>结题申请</a>

	$("#tagList").append(tagList);
	$("#thePort").append(whatPort);
	tagList=$("#tagList").children("a");
	tagList.on('click',function(event){
		changeColor(event)
	});
}

function changeColor(event){
	var obj=event.target;
	var objSi=$(obj).siblings();
	$(obj).attr("class","list-group-item active");
	$(objSi).attr("class","list-group-item ");
}

function Out_login(){
	$.ajax({
		type:"POST",
		url:"../out_login",
		success:function(data){
			if(data.code=="0"){
				alert("成功退出！");
			}
			else if(data.code=="-1"){
				alert("退出失败！！")
			}
		},
		error:function(){
			alert("退出失败");
		}
	});
}

function getUname(){
	$.ajax({
		type:"POST",
		url:"../getuname",
		success:function(data){
			if(data.code=="0"){
				$("#uname").append(data.uname);
			}
			else if(data.code=="-1"){
				alert("登录已超时，请重新登录")
			}
		},
		error:function(){
			alert("系统出错");
		}
	});
}
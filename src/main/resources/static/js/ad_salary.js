$(document).ready(function(){
	getConfig();
	$("#showConfig").click(function(){
		changeTab();
	});
	$("#alterConfig").click(function(){
		changeTab();
	});
	$("#alterConfigBtn").click(function(){
		alterConfig();
	})
})

var config;

//判断字符串是否为空 空返回1 非空返回0
function isEmptyString(str){
	if(str=='null'||str=='')
		return 1;
	return 0;
}


function getConfig(){
	$.ajax({
		type:"POST",
		url:"../config/getConfig.do",
		dataType:"JSON",
		data:{},
		success:function(data){
			if(data.code=="0"){
				var ppp=data.p_List;
				var oppp=data.op_List;
				for(i in ppp){
					if(ppp[i].state==0)
						var htmlStr="<tr><td>"+oppp[i].p_name+"</td><td>"+ppp[i].name+"</td><td>"+oppp[i].phone+"</td><td>"+ppp[i].money+"</td><td>暂无人审批</td><td>经费未审批</td></tr>";
					else if(ppp[i].state==1)
						var htmlStr="<tr><td>"+oppp[i].p_name+"</td><td>"+ppp[i].name+"</td><td>"+oppp[i].phone+"</td><td>"+ppp[i].money+"</td><td>"+ppp[i].gm_admin+"</td><td>经费已在"+ppp[i].date+"审批</td></tr>";
					$("#configList").append(htmlStr);
					console.log(ppp[i])
				}

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

function getDate(){
	var date = new Date();
	var year = date.getFullYear();    //  返回的是年份
	var month = date.getMonth() + 1;  //  返回的月份上个月的月份，记得+1才是当月
	var dates = date.getDate();       //  返回的是几号
	// var day = date.getDay();          //  周一返回的是1，周六是6，但是周日是0
	// var arr = [ "星期日","星期一","星期二","星期三","星期四","星期五","星期六",];
	return {
		year ,month ,dates
	}
}

function changeTab(){
	var info=$("#showConfigDiv").css("display");
	var alter=$("#alterConfigDiv").css("display");
	if(info=="block"){
		$("#showConfigDiv").css("display","none");
		$("#alterConfigDiv").fadeIn();	
	}
	else{
		$("#alterConfigDiv").css("display","none")
		$("#showConfigDiv").fadeIn();
	}
}


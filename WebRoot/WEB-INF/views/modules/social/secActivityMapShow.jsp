<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		img {
		    max-width: inherit;
		}
		/*去除百度地图版权*/
		/* .anchorBL{
			display:none;
		} */
	</style>
	<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=g2SL4p9fY2AlcLtpu2rjIHGa"></script>
	<script type="text/javascript">
		/* $(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		}); */
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">活动分布图</li>
	</ul>
	<form:form>
	<div id="container" style="width:100%;height:480px;"></div>
	</form:form>
<script type="text/javascript">

	//创建地图实例 
	var map = new BMap.Map("container");
	//地图样式
	/* var mapStyle ={
		features: ["road","building","water","land"],//隐藏地图上的"poi",
		style : 'dark',
	};
	map.setMapStyle(mapStyle); */
	//创建中心点坐标
	var point = new BMap.Point(116.404, 39.915);
	//初始化地图，设置中心点坐标和地图级别 
	map.centerAndZoom(point, 12);
	//开启鼠标滚轮缩放
	map.enableScrollWheelZoom(true);   
	
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
	var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
	/*缩放控件type有四种类型:
	BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮*/
	
	//添加控件和比例尺
	map.addControl(top_left_control);        
	map.addControl(top_left_navigation);     
	map.addControl(top_right_navigation);   
	
	// 添加定位控件
    var geolocationControl = new BMap.GeolocationControl();
    geolocationControl.addEventListener("locationSuccess", function(e){
      // 定位成功事件
      /* var address = '';
      address += e.addressComponent.province;
      address += e.addressComponent.city;
      address += e.addressComponent.district;
      address += e.addressComponent.street;
      address += e.addressComponent.streetNumber;
      alert("当前定位地址为：" + address); */
    });
    geolocationControl.addEventListener("locationError",function(e){
      // 定位失败事件
      alert(e.message);
    });
    map.addControl(geolocationControl);
	
	//信息弹出框
	var opts = {
	    width : 200,     // 信息窗口宽度
	    height: 100,     // 信息窗口高度
	    title : "活动详情"  // 信息窗口标题
	}
	
	var secActivities = eval(${secActivities});	//字符串转数组
	for(var i=0;i< secActivities.length;i++){
		var lat = secActivities[i].activityLat;
		var lon = secActivities[i].activityLon;
		var point = new BMap.Point(lon, lat);   
		
		//按种类显示不同图标
		var activityType = secActivities[i].type;
		var myIcon ;
		if(activityType == 0){
			myIcon = new BMap.Icon("${ctxStatic}/images/zhuoyou.png", new BMap.Size(30,40),{imageSize:new BMap.Size(30,40)});
		}else if(activityType == 1){
			myIcon = new BMap.Icon("${ctxStatic}/images/qipai.png", new BMap.Size(30,40),{imageSize:new BMap.Size(30,40)});
		}else if(activityType == 2){
			myIcon = new BMap.Icon("${ctxStatic}/images/ktv.png", new BMap.Size(30,40),{imageSize:new BMap.Size(30,40)});
		}else{
			myIcon = new BMap.Icon("${ctxStatic}/images/other.png", new BMap.Size(30,40),{imageSize:new BMap.Size(30,40)});
		}
		
		var marker = new BMap.Marker(point,{icon:myIcon});        // 创建标注   
		//var marker = new BMap.Marker(point);        // 创建标注   
		
		map.addOverlay(marker);                     // 将标注添加到地图中
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		
		var content = "标题："+ secActivities[i].title + "<br>位置：" + secActivities[i].locationDetatil + "<br>联系人手机号：" + secActivities[i].contactMobile;
		addClickHandler(content,marker);
	}
	
	function addClickHandler(content,marker){
		marker.addEventListener("click",function(e){
			openInfo(content,e);
		});
	}
	function openInfo(content,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}

	
</script> 
</body>
</html>
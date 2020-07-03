<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/social/secActivity/">活动列表</a></li>
		<shiro:hasPermission name="social:secActivity:edit"><li><a href="${ctx}/social/secActivity/form">添加活动</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="secActivity" action="${ctx}/social/secActivity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>活动类别：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('activity_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>日期范围：&nbsp;</label><input id="beginDate" name="beginDate" type="text" style="width: 170px;" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${secActivity.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;--&nbsp;
				<input id="endDate" name="endDate" type="text" style="width: 170px;" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${secActivity.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</li>
			<li><label>活动状态：</label>
				<form:select path="activityStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('activity_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>活动类别</th>
				<th>开始时间</th>
				<th>报名截止时间</th>
				<th>联系人电话</th>
				<th>活动状态</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="social:secActivity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="secActivity">
			<tr>
				<td><a href="${ctx}/social/secActivity/form?id=${secActivity.id}">
					${secActivity.title}
				</a></td>
				<td>${fns:getDictLabel(secActivity.type, 'activity_type', '')}</td>
				<td><fmt:formatDate value="${secActivity.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${secActivity.closingDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${secActivity.contactMobile}</td>
				<td>${fns:getDictLabel(secActivity.activityStatus, 'activity_status', '')}</td>
				<td>
					<fmt:formatDate value="${secActivity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${secActivity.remarks}
				</td>
				<td>
				<shiro:hasPermission name="social:secActivityApply:view">
					<a href="${ctx}/social/secActivityApply/list?activityId=${secActivity.id}">报名详情</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="social:secActivity:edit">
    				<a href="${ctx}/social/secActivity/form?id=${secActivity.id}">修改</a>
					<a href="${ctx}/social/secActivity/delete?id=${secActivity.id}" onclick="return confirmx('确认要删除该活动基本信息吗？', this.href)">删除</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="social:secActivity:approve">
					<c:if test="${secActivity.state == 0}">
	   					<a href="${ctx}/social/secActivity/approveForm?id=${secActivity.id}">审核</a>
	   					<%-- <a href="${ctx}/social/secActivity/approve?id=${secActivity.id}&state=1" onclick="return confirmx('确认要不通过该活动吗？', this.href)">不通过</a>
						<a href="${ctx}/social/secActivity/approve?id=${secActivity.id}&state=2" onclick="return confirmx('确认要通过该活动吗？', this.href)">通过</a> --%>
					</c:if>
				</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
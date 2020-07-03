<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户基本信息管理</title>
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
		<li class="active"><a href="${ctx}/social/secUser/">用户基本信息列表</a></li>
		<shiro:hasPermission name="social:secUser:edit"><li><a href="${ctx}/social/secUser/form">用户基本信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="secUser" action="${ctx}/social/secUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>昵称：</label>
				<sys:treeselect id="user" name="user.id" value="${testDataMain.user.id}" labelName="user.name" labelValue="${testDataMain.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li><label>昵称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>用户类型：</label>
				<form:select path="userType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>用户状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否实名：</label>
				<form:select path="isRealName" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>昵称</th>
				<th>电话</th>
				<th>用户类型</th>
				<th>用户状态</th>
				<th>注册时间</th>
				<th>是否实名</th>
				<th>备注</th>
				<shiro:hasPermission name="social:secUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="secUser">
			<tr>
				<td><a href="${ctx}/social/secUser/form?id=${secUser.id}">
					${secUser.nickname}
				</a></td>
				<td>
					${secUser.mobile}
				</td>
				<td>
					${fns:getDictLabel(secUser.userType, 'user_type', '')}
				</td>
				<td>
					${fns:getDictLabel(secUser.status, 'user_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${secUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(secUser.isRealName, 'yes_no', '')}
				</td>
				<td>
					${secUser.remarks}
				</td>
				<shiro:hasPermission name="social:secUser:edit"><td>
    				<a href="${ctx}/social/secUser/form?id=${secUser.id}">修改</a>
    				<a href="${ctx}/social/secUserDetail/form?userId=${secUser.id}">实名</a>
					<a href="${ctx}/social/secUser/delete?id=${secUser.id}" onclick="return confirmx('确认要删除该用户基本信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
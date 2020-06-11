<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li class="active"><a href="${ctx}/social/secOrder/">订单列表</a></li>
		<shiro:hasPermission name="social:secOrder:edit"><li><a href="${ctx}/social/secOrder/form">订单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="secOrder" action="${ctx}/social/secOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单号：</label>
				<form:input path="orderId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>订单类型：</label>
				<form:select path="orderType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>支付方式：</label>
				<form:select path="payType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>支付状态：</label>
				<form:select path="payStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>订单号</th>
				<th>订单类型</th>
				<th>总金额</th>
				<th>支付方式</th>
				<th>支付状态</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="social:secOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="secOrder">
			<tr>
				<td><a href="${ctx}/social/secOrder/form?orderId=${secOrder.orderId}">
					${secOrder.orderId}
				</a></td>
				<td>${fns:getDictLabel(secOrder.orderType, 'order_type', '')} 
				</td>
				<td>${secOrder.totalAmount}
				</td>
				<td>${fns:getDictLabel(secOrder.payType, 'pay_type', '')} 
				</td>
				<td>${fns:getDictLabel(secOrder.payStatus, 'pay_status', '')} 
				</td>
				<td><fmt:formatDate value="${secOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${secOrder.remarks}
				</td>
				<shiro:hasPermission name="social:secOrder:edit"><td>
    				<a href="${ctx}/social/secOrder/form?orderId=${secOrder.orderId}">修改</a>
					<a href="${ctx}/social/secOrder/delete?orderId=${secOrder.orderId}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
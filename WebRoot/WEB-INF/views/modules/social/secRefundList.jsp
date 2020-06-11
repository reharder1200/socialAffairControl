<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>退款管理</title>
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
		<li class="active"><a href="${ctx}/social/secRefund/">退款列表</a></li>
		<shiro:hasPermission name="social:secRefund:edit"><li><a href="${ctx}/social/secRefund/form">退款添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="secRefund" action="${ctx}/social/secRefund/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单号：</label>
				<form:input path="orderId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>退款状态：</label>
				<form:select path="refundStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('refund_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>商品订单号</th>
				<th>退款原因</th>
				<th>退款状态</th>
				<th>申请时间</th>
				<th>备注</th>
				<shiro:hasPermission name="social:secRefund:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="secRefund">
			<tr>
				<td><a href="${ctx}/social/secOrder/form?orderId=${secRefund.orderId}">
					${secRefund.orderId}
				</a></td>
				<td>
					${secRefund.refundReason}
				</td>
				<td>
					${fns:getDictLabel(secRefund.refundStatus, 'refund_status', '')} 
				</td>
				<td>
					<fmt:formatDate value="${secRefund.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${secRefund.remarks}
				</td>
				<shiro:hasPermission name="social:secRefund:edit"><td>
    				<a href="${ctx}/social/secRefund/form?id=${secRefund.id}">修改</a>
					<a href="${ctx}/social/secRefund/delete?id=${secRefund.id}" onclick="return confirmx('确认要删除该退款吗？', this.href)">删除</a>
					<c:if test="${secRefund.refundStatus == '0'}">
						<a href="${ctx}/social/secRefund/dealRefund?id=${secRefund.id}" onclick="return confirmx('确认要执行退款操作吗？', this.href)">退款</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
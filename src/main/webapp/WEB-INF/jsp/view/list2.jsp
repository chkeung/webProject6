<!DOCTYPE html>
<html>
    <head>
        <title>Online Bidding System</title>
    </head>
    <body>
        <a href="<c:url value="/login" />">Login</a><br/><br/>

        <h2>Items</h2>
        <c:choose>
            <c:when test="${fn:length(ticketDatabase) == 0}">
                <i>There are no items in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${ticketDatabase}" var="ticket">
                    Item ${ticket.id}:
                    <a href="<c:url value="/unuser/view2/${ticket.id}" />">
                        <c:out value="${ticket.itemName}" /></a>
                    (Item owner: <c:out value="${ticket.customerName}" />)
                    
                    <br /><br />
                </c:forEach>
            </c:otherwise>
        </c:choose>
        
    </body>
</html>

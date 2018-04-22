<!DOCTYPE html>
<html>
    <head>
        <title>Online Bidding System</title>
    </head>
    <body>
   

        <h2>Item #${ticket.id}: <c:out value="${ticket.itemName}" /></h2>
        
        <br /><br />
        <i>Item Owner - <c:out value="${ticket.customerName}" /></i><br /><br />
        Description: <c:out value="${ticket.description}" /><br /><br />
        Expected Price: $<c:out value="${ticket.expectedPrice}" /><br /><br />
        <c:if test="${fn:length(ticket.attachments) > 0}">
            Attachments:<br/>
            <table>
                <tr>
            <c:forEach items="${ticket.attachments}" var="attachment"
                       varStatus="status">
                <c:if test="${!status.first}"></c:if>
                
                    <td><img src="<c:out value="/onlineBiddingSystem/unuser/${ticket.id}/attachment/${attachment.name}" />" width="150px" height="100px"/>
                    <br/>
                    <a href="<c:url value="/unuser/${ticket.id}/attachment/${attachment.name}" />">
                        <c:out value="${attachment.name}" /></a></td>
                
            </c:forEach>
                </tr>
            </table>
            <br />
        </c:if>
        <!--<a href="<c:url value="/comment/addComment/${ticket.id}" />">Add Comment</a><br/><br/>
        <a href="<c:url value="/comment/viewComment/${ticket.id}" />">View Comment</a><br/><br/>-->
        <a href="<c:url value="/login" />">Return to login</a>
    </body>
</html>
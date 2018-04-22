<!DOCTYPE html>
<html>
    <head>
        <title>Online Bidding System</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Item #${ticket.id}: <c:out value="${ticket.itemName}" /></h2>
        <security:authorize access="hasRole('ADMIN') or principal.username=='${ticket.customerName}'">            
            [<a href="<c:url value="/item/edit/${ticket.id}" />">Edit</a>]
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">            
            [<a href="<c:url value="/item/delete/${ticket.id}" />">Delete</a>]
        </security:authorize>
        <br /><br />
        <i>Item Owner - <c:out value="${ticket.customerName}" /></i><br /><br />
        Description: <c:out value="${ticket.description}" /><br /><br />
        Expected Price: $<c:out value="${ticket.expectedPrice}" /><br /><br />
        Status: <c:out value="${ticket.status}" /><br /><br />
        Winner: <c:out value="${ticket.winner}" /><br /><br />
        Current Number of Bids: ${entries.size()}
        <br/><br/>
        <security:authorize access="hasRole('ADMIN') or principal.username=='${ticket.customerName}'">
            Bidding Records:
            <c:if test="${fn:length(entries) == 0}">
                There is no biding records.
            </c:if>
            <br/>
            <c:if test="${fn:length(entries) > 0}">
                <table border="1" style="width:80%; text-align:center;">
                    <tr><th>Customer Name</th><th>Price</th><th>Date</th></tr>
                            <c:forEach var="entry" items="${entries}">
                        <tr>
                            <td>${entry.customerName}</td> 
                            <td>$<c:out value="${entry.price}" escapeXml="true" /></td> 
                            <td><fmt:formatDate value="${entry.date}" pattern="yyyy-MM-dd" /></td> 
                            <!--<security:authorize access="hasRole('ADMIN')"><td>
                                    [<a href="<c:url value="/comment/edit?id=${entry.id}" />">Edit</a>] 
                                    [<a href="<c:url value="/comment/delete?id=${entry.id}" />">Delete</a>]
                                </td></security:authorize>-->
                            </tr>
                    </c:forEach>
                </table>
            </c:if>
        </security:authorize>
        <br/>

        <c:if test="${fn:length(ticket.attachments) > 0}">
            Attachments:<br/>
            <table>

                <c:forEach items="${ticket.attachments}" var="attachment" varStatus="status">
                    <c:if test="${!status.first}"></c:if>

                        <tr><td><img src="<c:out value="/onlineBiddingSystem/item/${ticket.id}/attachment/${attachment.name}" />" width="150px" height="100px"/>
                            <br/>
                            <a href="<c:url value="/item/${ticket.id}/attachment/${attachment.name}" />">
                                <c:out value="${attachment.name}" /></a></td></tr>

                </c:forEach>

            </table>

            <br /><br />
        </c:if>
        <a href="<c:url value="/comment/addComment/${ticket.id}" />">Add Comment</a><br/><br/>
        <a href="<c:url value="/comment/viewComment/${ticket.id}" />">View Comment</a><br/><br/>
        <security:authorize access="hasRole('ADMIN') or principal.username!='${ticket.customerName}'">
            <c:if test="${ticket.status eq "available"}">
                <a href="<c:url value="/bidding/bidPrice/${ticket.id}" />">Bid this item</a><br/><br/>
            </c:if>
        </security:authorize>


        <a href="<c:url value="/item" />">Return to list tickets</a>
    </body>
</html>
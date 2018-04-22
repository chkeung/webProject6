<!DOCTYPE html>
<html>
    <head>
        <title>Comment</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>    

        <h1>Comments - item #${ticket.id}: <c:out value="${ticket.itemName}" /></h1>

        <c:if test="${fn:length(entries) == 0}">
            <p>There is no message yet.</p>
        </c:if>
        <c:if test="${fn:length(entries) > 0}">
            <table border="1" style="width:80%; text-align:center;">
                <tr><th>Customer Name</th><th>Message</th><th>Date</th></tr>
                        <c:forEach var="entry" items="${entries}">
                    <tr>
                        <td><c:out value="${entry.customer}" /></td> 
                        <td><c:out value="${entry.message}"/></td> 
                        <td><fmt:formatDate value="${entry.date}" pattern="yyyy-MM-dd-hh-mm-ss" /></td> 
                        
                        <security:authorize access="hasRole('ADMIN')"><td>
                                [<a href="<c:url value="/comment/delete?id=${entry.id}" />">Delete</a>]</td></security:authorize>
                        </tr>
                </c:forEach>
            </table>          
        </c:if>
        <p><a href="<c:url value="/comment/addComment/${ticket.id}" />">Add Comment</a></p>
        <p><a href="<c:url value="/item" />">Return to list tickets</a></p>
    </body>
</html>

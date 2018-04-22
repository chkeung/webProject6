<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Item #${ticket.id}</h2>

        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="ticketForm">   
            <form:label path="itemName">Name</form:label><br/>
            <form:input type="text" path="itemName" /><br/><br/>

            <form:label path="description">Description</form:label><br/>
            <form:textarea path="description" rows="5" cols="30" /><br/><br/>

            <form:label path="expectedPrice">Expected Price</form:label><br/>
            <form:input type="text" path="expectedPrice" /><br/><br/>

            <form:label path="status">Status</form:label><br/>
            <form:select path="status">
                <form:option value="available">Available</form:option>
                <form:option value="ended">Ended</form:option>
            </form:select><br/><br/>

            <form:label path="winner">Select a Winner</form:label><br/>
            <form:select path="winner">
                <form:option value="no winner">No winner</form:option>
                <c:forEach var="entry" items="${entries}">   
                    <form:option value="${entry.customerName}"><c:out value="${entry.customerName}" /></form:option>                   
                </c:forEach>
            </form:select><br/><br/>
            <c:forEach var="entry" items="${entries}">                
                <c:out value="${entry.customerName}" />
            </c:forEach>
            <br/>
            <c:if test="${fn:length(ticket.attachments) > 0}">
                <b>Attachments:</b><br/>
                <ul>
                    <c:forEach items="${ticket.attachments}" var="attachment">
                        <li>
                            <c:out value="${attachment.name}" />
                            [<a href="<c:url 
                                    value="/item/${ticket.id}/delete/${attachment.name}"
                                    />">Delete</a>]
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <b>Add attachments</b><br />
            <input type="file" name="attachments" multiple="multiple"/><br/><br/>
            <input type="submit" value="Save"/><br/><br/>
        </form:form>
        <a href="<c:url value="/item" />">Return to list tickets</a>
    </body>
</html>
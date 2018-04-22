<!DOCTYPE html>
<html>
    <head>
        <title>Add Comment</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h1>Bid item #${ticket.id}: <c:out value="${ticket.itemName}" /></h1>

        <form:form method="post"> 
            <form:input type="hidden" path="ticket_id"  value="${ticket.id}"/> <br />
            <form:label path="price">Price: $</form:label>
            <form:input type="text" path="price" /><br/><br/>
            <% 
            String  s1  = (String) session.getAttribute("var");        
            %>
            <form:input type="hidden" path="customerName"  value="<%= s1 %>"/> <br />
            <input type="submit" name="bid" value="Bid" />
        </form:form>
    </body>
</html>

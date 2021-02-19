<%@attribute name="align" required="true" %>
<%@attribute name="size" required="true" %>
<table align="${align}" style="font-size:${size}px;">
    <thead>
    <th>
        <jsp:doBody/>
    </th>
    </thead>
    <tr>
        <td></td>
    </tr>
</table>
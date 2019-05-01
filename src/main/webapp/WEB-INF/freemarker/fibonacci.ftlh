<html>
<head>
    <link rel="stylesheet" href="/css/style.css"/>
    <meta charset="UTF-8"/>
</head>
<body>
<div id="result">
    <#if n?ends_with("1")>
        The ${n}st
    <#elseif n?ends_with("2")>
        The ${n}nd
    <#elseif n?ends_with("3")>
        The ${n}rd
    <#else>
        The ${n}th
    </#if>
    element is ${result}
</div>
<table>
    <tr>
        <th>n</th>
        <th>Result</th>
    </tr>
    <#list resultList>
        <#items as number>
            <tr>
                <td>${number_index+1}</td>
                <td>${number}</td>
            </tr>
        </#items>
    </#list>
</table>
</body>
</html>
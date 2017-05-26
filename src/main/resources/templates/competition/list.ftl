<@layout title="Competition list">
    <#list competitions as competition>
    <p>${competition.id}: ${competition.title}</p>
    </#list>
</@layout>

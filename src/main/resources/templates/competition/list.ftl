<@layout title="Competition list">
    <#list competitions as competition>
    <p>${competition.id}: ${competition.title} (${competition.exercise.title})</p>
    </#list>
</@layout>

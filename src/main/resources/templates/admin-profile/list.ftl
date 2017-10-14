<#-- @ftlvariable name="profiles" type="org.springframework.data.domain.Page<com.github.igorf.shoot.logic.domain.Profile>" -->

<#include '../layout/admin.ftl'>
<@admin_layout title="Profiles">
    <h2>Profiles</h2>

    <table class="table table-bordered table-striped">
        <tr>
            <th>#</th>
            <th>Login</th>
        </tr>
        <#list profiles.content as profile>
            <tr>
                <td><a href="/admin/profile/view/${profile.id}">${profile.id}</a></td>
                <td>${profile.login}</td>
            </tr>
        </#list>
    </table>

    <ul class="pager">
        <li class="previous <#if !profiles.hasPrevious()>disabled</#if>">
            <#if !profiles.hasPrevious()>
                <a href="#">&larr; Previous</a>
            </#if>
            <#if profiles.hasPrevious()>
                <a href="/admin/profile/list?page=${profiles.number - 1}">&larr; Previous</a>
            </#if>
        </li>
        <li class="next <#if !profiles.hasNext()>disabled</#if>">
            <#if !profiles.hasNext()>
                <a href="#">Next &rarr; </a>
            </#if>
            <#if profiles.hasNext()>
                <a href="/admin/profile/list?page=${profiles.number + 1}">Next &rarr; </a>
            </#if>
        </li>
    </ul>
</@admin_layout>

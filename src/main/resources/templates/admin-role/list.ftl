<#-- @ftlvariable name="roles" type="java.lang.Iterable<com.github.igorf.shoot.logic.domain.Role>" -->
<#include '../layout/admin.ftl'>
<@admin_layout title="Roles">
    <h2>Competitions</h2>

    <table class="table table-bordered table-striped">
        <tr>
            <th>#</th>
            <th>Title</th>
        </tr>
        <#list roles as role>
            <tr>
                <td><a href="/admin/role/view/${(role.id)!}">${(role.id)!}</a></td>
                <td>${role.name}</td>
            </tr>
        </#list>
    </table>
    <p>
        <a class="btn btn-sm btn-primary" href="/admin/role/create">
            <span class="glyphicon glyphicon-plus"></span>
            Add new role
        </a>
    </p>
</@admin_layout>

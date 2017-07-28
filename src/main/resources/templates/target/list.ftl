<#-- @ftlvariable name="targets" type="java.util.Collection<com.github.igorf.shoot.logic.domain.Target>" -->
<#include '../layout/admin.ftl'>
<@admin_layout title="Targets">
    <h2>Targets</h2>

    <table class="table table-bordered table-striped">
        <tr>
            <th>#</th>
            <th>Title</th>
        </tr>
        <#list targets as target>
            <tr>
                <td><a href="/admin/target/view/${(target.id)!}">${target.id}</a></td>
                <td>${target.title}</td>
            </tr>
        </#list>
    </table>
    <p>
        <a class="btn btn-sm btn-primary" href="/admin/target/create">
            <span class="glyphicon glyphicon-plus"></span>
            Add new target
        </a>
    </p>
</@admin_layout>

<#-- @ftlvariable name="competitions" type="org.springframework.data.domain.Page<com.github.igorf.shoot.logic.domain.Competition>" -->
<#include '../layout/admin.ftl'>
<@admin_layout title="Competitions">
    <h2>Competitions</h2>

    <table class="table table-bordered table-striped">
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Exercise</th>
            <th>Stared at</th>
            <th>Finished at</th>
        </tr>
        <#list competitions.content as competition>
            <tr>
                <td><a href="/admin/competition/view/${(competition.id)!}">${(competition.id)!}</a></td>
                <td>${competition.title}</td>
                <td>${competition.exercise.title}</td>
                <td>${competition.start?string["dd.MM.yyyy"]}</td>
                <td>${competition.end?string["dd.MM.yyyy"]}</td>
            </tr>
        </#list>
    </table>
    <p>
        <a class="btn btn-sm btn-primary" href="/admin/competition/create">
            <span class="glyphicon glyphicon-plus"></span>
            Add new competition
        </a>
        <a class="btn btn-sm btn-primary" href="/admin/competition/multicreate">
            <span class="glyphicon glyphicon-plus"></span>
            Add multi competition
        </a>
    </p>
</@admin_layout>

<#-- @ftlvariable name="exercises" type="org.springframework.data.domain.Page<com.github.igorf.shoot.logic.domain.Exercise>" -->
<#include '../layout/admin.ftl'>
<@admin_layout title="Exercises">
    <h2>Exercises</h2>

    <table class="table table-bordered table-striped">
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Shots</th>
            <th>Time limit, min</th>
            <th>Distance, m</th>
            <th>Target</th>
        </tr>
        <#list exercises.content as exercise>
            <tr>
                <td><a href="/admin/exercise/view/${(exercise.id)!}">${(exercise.id)!}</a></td>
                <td>${exercise.title}</td>
                <td>${exercise.shots}</td>
                <td>${exercise.timeLimit}</td>
                <td>${exercise.distance}</td>
                <td><a href="/admin/target/view/${(exercise.target.id)!}">${(exercise.target.title)!"UNDEFINED"}</a></td>
            </tr>
        </#list>
    </table>
    <p>
        <a class="btn btn-sm btn-primary" href="/admin/exercise/create">
            <span class="glyphicon glyphicon-plus"></span>
            Add new exercise
        </a>
    </p>
</@admin_layout>

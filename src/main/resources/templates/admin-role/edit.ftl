<#-- @ftlvariable name="role" type="com.github.igorf.shoot.logic.domain.Role" -->

<#include '../layout/admin.ftl'>
<@admin_layout title="Edit role ${(role.name)!}">

    <script type="text/javascript" src="/bower_components/moment/min/moment.min.js"></script>

    <ul class="breadcrumb">
        <li><a href="/admin/role/list">Roles</a></li>
        <li class="active">Edit role</li>
    </ul>

    <div class="well">
        <form class="form-horizontal" method="post" action="/admin/role/save">
            <input type="hidden" name="id" value="${(role.id)!}">
            <fieldset>
                <legend>Edit competition ${(role.name)!}</legend>

                <div class="form-group">
                    <label for="inputTitle" class="col-lg-2 control-label">Title</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputTitle" name="name" value="${(role.name)!}">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary btn-sm">
                            <span class="glyphicon glyphicon-save"></span>
                            Save
                        </button>
                        <#if role ??>
                            <a class="btn btn-sm btn-danger" href="/admin/role/delete/#{(role.id)!}">
                                <span class="glyphicon glyphicon-erase"></span>
                                Delete
                            </a>
                        </#if>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</@admin_layout>

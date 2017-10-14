<#-- @ftlvariable name="role" type="com.github.igorf.shoot.logic.domain.Role" -->
<#import "/spring.ftl" as spring/>

<#include '../layout/admin.ftl'>
<@admin_layout title="Edit role ${(role.name)!}">

    <ul class="breadcrumb">
        <li><a href="/admin/role/list">Roles</a></li>
        <li class="active">Edit role</li>
    </ul>

    <div class="well">
        <form class="form-horizontal" method="post" action="/admin/role/save">
            <input type="hidden" name="id" value="${(role.id)!}">
            <fieldset>
                <legend>Edit role ${(role.name)!}</legend>

                <div class="form-group">
                    <label for="inputName" class="col-lg-2 control-label">Role</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputName" name="name" value="${(role.name)!}">
                    </div>
                    <@spring.bind "role.name" />
                    <#list spring.status.errorMessages as error>
                        <div class="col-lg-10 col-lg-offset-2 error">
                            <b>${error}</b>
                        </div>
                    </#list>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary btn-sm">
                            <span class="glyphicon glyphicon-save"></span>
                            Save
                        </button>
                        <#if role.id ??>
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

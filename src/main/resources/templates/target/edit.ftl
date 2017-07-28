<#-- @ftlvariable name="target" type="com.github.igorf.shoot.logic.domain.Target" -->
<#include '../layout/admin.ftl'>
<@admin_layout title="Edit target ${(target.title)!}">

    <ul class="breadcrumb">
        <li><a href="/admin/target/list">Target list</a></li>
        <li class="active">Edit target</li>
    </ul>

    <div class="well">
        <form class="form-horizontal" method="post" action="/admin/target/save">
            <input type="hidden" name="id" value="${(target.id)!}">
            <fieldset>
                <legend>Edit target ${(target.title)!}</legend>
                <div class="form-group">
                    <label for="inputTitle" class="col-lg-2 control-label">Title</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputTitle" name="title" value="${(target.title)!}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary btn-sm">
                            <span class="glyphicon glyphicon-save"></span>
                            Save
                        </button>
                        <#if target ??>
                            <a class="btn btn-sm btn-danger" href="/admin/target/delete/#{(target.id)!}">
                                <span class="glyphicon glyphicon-erase"></span>
                                Delete
                            </a>
                        </#if>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>

    <#if (target.rings) ??>
        <h3>Rings</h3>
        <table class="table table-bordered table-striped">
            <tr>
                <th>#</th>
                <th>Denomination</th>
                <th>Diameter</th>
                <th>Color</th>
            </tr>
            <#list target.rings as ring>
                <tr>
                    <td><a href="/admin/ring/view/${(ring.id)!}">${ring.id}</a></td>
                    <td>${ring.denomination}</td>
                    <td>${ring.diameter}</td>
                    <td>${ring.color}</td>
                </tr>
            </#list>
        </table>
        <div class="form-group">
            <a class="btn btn-sm btn-default" href="/admin/ring/create/#{(target.id)!}">
                <span class="glyphicon glyphicon-plus-sign"></span>
                Add new ring
            </a>
        </div>
    </#if>

</@admin_layout>

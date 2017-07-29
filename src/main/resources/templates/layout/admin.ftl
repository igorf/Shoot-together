<#-- @ftlvariable name="errorMessage" type="String" -->
<#-- @ftlroot ".." -->
<#macro admin_layout title>
    <#include 'app.ftl'>

    <@layout title="${title}">
        <div class="row">
            <#if errorMessage ?? >
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Error!</strong> ${errorMessage}
                </div>
            </#if>
            <div class="col-lg-3 col-md-3 col-sm-4">
                <#include "/admin/menu.ftl">
            </div>
            <div class="col-lg-9">
                <#nested>
            </div>
        </div>
    </@layout>
</#macro>
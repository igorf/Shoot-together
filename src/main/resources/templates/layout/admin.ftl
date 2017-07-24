<#macro admin_layout title>
    <#include 'app.ftl'>

    <@layout title="${title}">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-4">
                <!--menu here -->
            </div>
            <div class="col-lg-9">
                <#nested>
            </div>
        </div>
    </@layout>
</#macro>
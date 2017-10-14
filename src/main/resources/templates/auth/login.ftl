<#-- @ftlvariable name="message" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#include '../layout/basic.ftl'>
<@basic_layout title="Login">


<form method="POST" action="/login" class="form-horizontal">
    <fieldset>
        <div class="form-group">
            <div class="col-lg-4 col-lg-offset-4">
                <h2>Log in</h2>
            </div>
        </div>
        <#if message??>
            <div class="form-group">
                <div class="col-lg-6 col-lg-offset-3">
                    <span>${message}</span>
                </div>
            </div>
        </#if>

        <div class="form-group<#if error??> has-error</#if>">
            <div class="col-lg-4 col-lg-offset-4">
                <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true"/>
            </div>
        </div>
        <div class="form-group<#if error??> has-error</#if>">
            <div class="col-lg-4 col-lg-offset-4">
                <input name="password" type="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <#if error??>
            <div class="form-group has-error">
                <div class="col-lg-4 col-lg-offset-4">
                    <span>${error}</span>
                </div>
            </div>
        </#if>
        <div class="form-group">
            <div class="col-lg-4 col-lg-offset-4">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                <h4 class="text-center"><a href="/registration">Create an account</a></h4>
            </div>
        </div>
    </fieldset>
</form>

</@basic_layout>

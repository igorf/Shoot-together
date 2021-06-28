<#-- @ftlvariable name="message" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#include '../layout/basic.ftl'>
<@basic_layout title="Login">



<div class="back">
  <div class="div-center">
      <div class="content">
          <h3>Login</h3>
          <hr/>
          <#if message??>
            <div class="form-group">
                <div class="col-lg-6 col-lg-offset-3">
                    <span>${message}</span>
                </div>
            </div>
          </#if>

          <form method="POST" action="/login" class="form-horizontal">
              <fieldset>
                  <div class="form-group <#if error??> has-error</#if>">
                      <label for="usernameId">Login</label>
                      <input type="text" name="username" class="form-control" id="usernameId" placeholder="Username" autofocus="true">
                  </div>
                  <div class="form-group <#if error??> has-error</#if>">
                      <label for="passwordId">Password</label>
                      <input type="password" name="password" class="form-control" id="passwordId" placeholder="Password">
                  </div>

                  <button type="submit" class="btn btn-primary form-submit">Login</button>
              </fieldset>
              <hr/>

              <#if error??>
                <div class="form-group has-error">
                    <span>${error}</span>
                </div>
              </#if>

              <a href="/registration">Create an account</a>
          </form>
      </div>
  </div>

</@basic_layout>

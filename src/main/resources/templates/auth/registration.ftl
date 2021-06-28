<#import "/spring.ftl" as spring/>
<#include '../layout/basic.ftl'>
<@basic_layout title="Register">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<form method="POST" action="/registration" class="form-horizontal">
    <fieldset>
        <div class="form-group">
            <div class="col-lg-4 col-lg-offset-4">
                <h2>Sign in</h2>
            </div>
        </div>

        <@spring.bind "userForm.captcha" />
        <#list spring.status.errorMessages as error>
            <div class="col-lg-4 col-lg-offset-4 alert alert-danger" role="alert">
                <b>${error}</b>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </#list>

        <div class="form-group">
            <div class="col-lg-4 col-lg-offset-4">
                <input type="text" name="login" class="form-control" placeholder="Login" autofocus="true">
            </div>
            <@spring.bind "userForm.login" />
            <#list spring.status.errorMessages as error>
            <div class="col-lg-4 col-lg-offset-4 error">
                <b>${error}</b>
            </div>
            </#list>
        </div>

        <div class="form-group">
            <div class="col-lg-4 col-lg-offset-4">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
            <@spring.bind "userForm.password" />
            <#list spring.status.errorMessages as error>
                <div class="col-lg-4 col-lg-offset-4 error">
                    <b>${error}</b>
                </div>
            </#list>
        </div>

        <div class="form-group">
            <div class="col-lg-4 col-lg-offset-4">
                <input type="password" name="passwordConfirm" class="form-control" placeholder="Password confirmation" />
            </div>
            <@spring.bind "userForm.passwordConfirm" />
            <#list spring.status.errorMessages as error>
                <div class="col-lg-4 col-lg-offset-4 error">
                    <b>${error}</b>
                </div>
            </#list>
        </div>
        <div class="form-group">
            <div class="col-lg-4 col-lg-offset-4">
                <div class="g-recaptcha" data-sitekey="6LdRKWIbAAAAAMRmkdSrU6gEmrmtZ1XxQxgx4vjr"></div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-lg-4 col-lg-offset-4">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign In</button>
            </div>
        </div>
    </fieldset>
</form>

</@basic_layout>

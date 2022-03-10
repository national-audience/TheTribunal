package io.github.nationalaudience.thetribunal.constant;

public class LoginStaticValues {

    public static final String END_POINT_LOGIN = "/login";
    public static final String END_POINT_ERROR_LOGIN = "/errorLogin";
    public static final String END_POINT_SIGNUP = "/signup";
    public static final String END_POINT_POST_SIGNUP = "/postSignup";

    public static final String PARAMETER_USER = "user";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_PASSWORD_CONFIRM = "passwordConfirm";

    public static final String ATTRIBUTE_INVALID_LOGIN = "invalidLogin";
    public static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    public static final String ATTRIBUTE_USERNAME = "username";

    public static final String TEMPLATE_LOGIN = "login/login_template";
    public static final String TEMPLATE_SIGNUP = "login/signup_template";
    public static final String TEMPLATE_SUCCESSFUL_SIGNUP = "login/signup_successful_template";

    public static final String CACHE_LOGGED_USER = "loggedUser";

}

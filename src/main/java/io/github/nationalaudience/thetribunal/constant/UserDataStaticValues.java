package io.github.nationalaudience.thetribunal.constant;

public class UserDataStaticValues {

    public static final String END_POINT_USER_DATA = "/user/{inUsername}";
    public static final String END_POINT_FOLLOW_USER_DATA = "/user/follow";
    public static final String END_POINT_UNFOLLOW_USER_DATA = "/user/unfollow";
    public static final String END_POINT_DELETE_USER_DATA = "/user/delete";
    public static final String END_POINT_USER_FOLLOWS_DATA = "/user/{inUsername}/follows";
    public static final String END_POINT_USER_FOLLOWERS_DATA = "/user/{inUsername}/followers";
    public static final String END_POINT_USER_STUDIOS_FOLLOWS_DATA = "/user/{inUsername}/studios-follows";

    public static final String PARAMETER_USER = "inUsername";

    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_USER_FOLLOWERS = "count_followers";
    public static final String ATTRIBUTE_USER_FOLLOWS = "count_follows";
    public static final String ATTRIBUTE_USER_STUDIO_FOLLOWS = "count_studio_follows";
    public static final String ATTRIBUTE_USER_FOLLOWING = "following_this_user";
    public static final String ATTRIBUTE_USER_OWN_PAGE = "own_page";

    public static final String TEMPLATE_USER_DATA = "data/userdata_template";
    public static final String TEMPLATE_USER_DATA_NOT_FOUND = "data/userdata_not_found_template";
    public static final String TEMPLATE_USER_DATA_FOLLOWS = "data/userfollowsdata_template";
    public static final String TEMPLATE_USER_DATA_FOLLOWERS = "data/userfollowersdata_template";
    public static final String TEMPLATE_USER_DATA_STUDIOS_FOLLOWS = "data/userstudiosfollowsdata_template";

}

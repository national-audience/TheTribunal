package io.github.nationalaudience.thetribunal.constant;

public class StudioDataStaticValues {

    public static final String END_POINT_STUDIO_DATA = "/studio/{inName}";
    public static final String END_POINT_DELETE_STUDIO_DATA = "/studio/delete";
    public static final String END_POINT_FOLLOW_STUDIO_DATA = "/studio/follow";
    public static final String END_POINT_STUDIO_FOLLOWERS_DATA = "/studio/{inName}/followers";
    public static final String END_POINT_NEW_STUDIO_TO_DB = "/newStudioToDb";
    public static final String END_POINT_POST_NEW_STUDIO_TO_DB = "/postNewStudioToDb";

    public static final String PARAMETER_STUDIO = "inName";

    public static final String PARAMERTER_STUDIO_NAME = "studioName";
    public static final String PARAMERTER_STUDIO_DESCRIPTION = "studioDescription";
    public static final String PARAMERTER_STUDIO_EMPLOYEES = "employees";
    public static final String PARAMERTER_STUDIO_LOCATION = "location";

    public static final String ATTRIBUTE_STUDIO_NAME = "studio";
    public static final String ATTRIBUTE_STUDIO_FOLLOWERS = "count_followers";

    public static final String TEMPLATE_STUDIO_DATA = "data/studiodata_template";
    public static final String TEMPLATE_STUDIO_DATA_NOT_FOUND = "data/studiodata_not_found_template";
    public static final String TEMPLATE_STUDIO_FOLLOWERS_DATA = "data/studiofollowersdata_template";
    public static final String TEMPLATE_NEW_STUDIO = "new_studio/new_studio_template";
    public static final String TEMPLATE_POST_NEW_STUDIO = "new_studio/post_new_studio_template";

}

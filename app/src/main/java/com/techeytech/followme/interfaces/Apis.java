package com.techeytech.followme.interfaces;

import androidx.annotation.Nullable;

import com.techeytech.followme.beans.response_beans.LoginBean;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by androiddev on 8/24/2018.
 */
public interface Apis {

    @FormUrlEncoded
    @POST("signup")
    Call<LoginBean> signUp(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("login")
    Call<LoginBean> login(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("forgot_password")
    Call<LoginBean> forgotPassword(@Field("email") String email);

    @GET("checkEmailAvailability")
    Call<LoginBean> checkEmailAvailability(@Query("email") String email);

    @Multipart
    @POST("update_profile_picture")
    Call<LoginBean> updateProfilePicture(@HeaderMap Map<String, String> header,
                                         @Nullable @Part MultipartBody.Part part);

    @PUT("sign_out")
    Call<LoginBean> logOut(@HeaderMap Map<String, String> headerMap);


    /*@GET("get_countries")
    Call<FlagsBean> getFlags();

    @GET("qod.json")
    Call<QuoteResponseBean> getQuoteOfDay(@Query("category") String category);

    @FormUrlEncoded
    @POST("signup")
    Call<LoginBean> signUp(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("forgot_password")
    Call<LoginBean> forgotPassword(@Field("email") String email);

    @FormUrlEncoded
    @PUT("change_password")
    Call<LoginBean> changePassword(@HeaderMap Map<String, String> headermap, @FieldMap Map<String, String> map);

    @PUT("sign_out")
    Call<LoginBean> logOut(@HeaderMap Map<String, String> headermap);

    @FormUrlEncoded
    @POST("update_profile")
    Call<LoginBean> updateProfile(@HeaderMap Map<String, String> headermap, @FieldMap Map<String, String> map);

    @GET("get_profile")
    Call<LoginBean> getProfile(@HeaderMap Map<String, String> headermap);

    @GET("get_favourite/{page}/{limit}")
    Call<FavouritesBean> getFavourites(@HeaderMap Map<String, String> headermap, @Path("page") String page,
                                       @Path("limit") String limit, @Query("search") String search);

    @GET("get_notification/{page}/{limit}")
    Call<NotificationBean> getNotifications(@HeaderMap Map<String, String> headermap, @Path("page") String page,
                                            @Path("limit") String limit, @Query("timezone") String timezone);
    *//*@FormUrlEncoded
    @POST("login")
    Call<LoginBean> login(@FieldMap Map<String, String> map);

    @PUT("sign_out")
    Call<LoginBean> logOut(@HeaderMap Map<String, String> headermap);

    @FormUrlEncoded
    @POST("update_profile")
    Call<LoginBean> updateProfile(@HeaderMap Map<String, String> headermap, @FieldMap Map<String, String> map);


    @GET("get_service/{page}/{limit}")
    Call<ServiceBean> getServices(@HeaderMap Map<String, String> headermap, @Path("page") String page, @Path("limit") String limit);

    @DELETE("delete_service/{service_id}")
    Call<ServiceBean> deleteServices(@HeaderMap Map<String, String> headermap, @Path("service_id") String service_id);

    @GET("get_appointment/{page}/{limit}/{type}")
    Call<AppointmentBean> getAppointments(@HeaderMap Map<String, String> headermap, @Path("page") String page,
                                          @Path("limit") String limit, @Path("type") String type,
                                          @Query("timezone") String timezone);

    @GET("get_service_provider/{page}/{limit}")
    Call<ServiceProviderBean> getServiceProviders(@HeaderMap Map<String, String> headermap, @Path("page") String page,
                                                  @Path("limit") String limit, @Query("latitude") String latitude, @Query("longitude") String longitude);

    @GET("get_favourite/{page}/{limit}")
    Call<FavouritesBean> getFavourites(@HeaderMap Map<String, String> headermap, @Path("page") String page,
                                       @Path("limit") String limit, @Query("search") String search);

    @GET("get_reviews/{page}/{limit}")
    Call<RatingReviewBean> getRatingReviews(@HeaderMap Map<String, String> headermap, @Path("page") String page,
                                            @Path("limit") String limit);

    @GET("get_message")
    Call<ChatBean> getChatMessages(@HeaderMap Map<String, String> headermap, @Query("page") String page,
                                   @Query("friendid") String friendid, @Query("limit") String limit);

    @GET("get_inbox")
    Call<InboxBean> getInbox(@HeaderMap Map<String, String> headermap, @Query("page") String page,
                             @Query("limit") String limit);

    @FormUrlEncoded
    @PUT("change_password")
    Call<LoginBean> changePassword(@HeaderMap Map<String, String> headermap, @FieldMap Map<String, String> map);

    @Multipart
    @POST("update_profile_picture")
    Call<LoginBean> addProfilePicture(@HeaderMap Map<String, String> headermap, @Nullable @Part MultipartBody.Part picture);

    @Multipart
    @POST("rate_user")
    Call<LoginBean> rateUser(@HeaderMap Map<String, String> headermap, @PartMap Map<String, RequestBody> map, @Nullable @Part MultipartBody.Part picture);

    @FormUrlEncoded
    @POST("add_service")
    Call<LoginBean> addService(@HeaderMap Map<String, String> headermap, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @PUT("edit_service/{service_id}")
    Call<LoginBean> editService(@HeaderMap Map<String, String> headermap, @Path("service_id") String service_id, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("add_availability")
    Call<LoginBean> addAvailability(@HeaderMap Map<String, String> headermap, @Field("availability") JSONArray jsonArray);

    @Multipart
    @POST("send_message")
    Call<LoginBean> sendMessage(@HeaderMap Map<String, String> headermap, @PartMap Map<String, RequestBody> map, @Nullable @Part List<MultipartBody.Part> partList);

    @POST("add_favourite/{user_id}")
    Call<LoginBean> addRemoveFavourite(@HeaderMap Map<String, String> headermap, @Path("user_id") String user_id);

    @FormUrlEncoded
    @POST("book_appointment")
    Call<LoginBean> bookAppointment(@HeaderMap Map<String, String> headermap, @FieldMap Map<String, String> map);

    @DELETE("cancel_service/{job_id}")
    Call<LoginBean> cancelAppointment(@HeaderMap Map<String, String> headermap, @Path("job_id") String job_id);

    @GET("get_fixed_schedule/{service_provider_id}")
    Call<GetFixedScheduleBean> getFixedSchedule(@HeaderMap Map<String, String> headermap,
                                                @Path("service_provider_id") String service_provider_id,
                                                @Query("timezone") String timezone);

    @GET("get_availability")
    Call<GetAvailabilityBean> getAvailability(@HeaderMap Map<String, String> headermap);

    @GET("get_unread_msg_count")
    Call<UnreadMessageCountBean> getUnreadMsgCount(@HeaderMap Map<String, String> headermap, @Query("friendid") String friendid);

    @GET("get_notification/{page}/{limit}")
    Call<NotificationBean> getNotifications(@HeaderMap Map<String, String> headermap, @Path("page") String page,
                                            @Path("limit") String limit, @Query("timezone") String timezone);

    @FormUrlEncoded
    @POST("confirm_request")
    Call<LoginBean> cancelConfirmRequest(@HeaderMap Map<String, String> headermap, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @PUT("edit_availability/{availability_id}")
    Call<LoginBean> editAvailability(@HeaderMap Map<String, String> headermap, @Path("availability_id") String availability_id, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @PUT("update_location")
    Call<LoginBean> updateLocation(@HeaderMap Map<String, String> map, @Field("latitude") String lat, @Field("longitude") String longitude);

    @GET("get_notification_count")
    Call<NotificationCountBean> getNotificationCount(@HeaderMap Map<String, String> map);*//*
     */
}

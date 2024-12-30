package com.techeytech.followme.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;

import com.techeytech.followme.R;
import com.techeytech.followme.beans.ApiErrorBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.URLDecoder;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * Created by The-Bawa on 10/25/2017.
 */

public class UtilMethods {


    public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public static byte[] getBytes(Uri uri, Context context) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public static void createDirectory(ByteArrayBuffer imageToSave, String fileName) {
        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        File direct = new File(Environment.getExternalStorageDirectory() + "/ElementsExample/");
        if (!direct.exists()) {
            direct.mkdirs();
        }
        File file = new File(direct,fileName);
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(imageToSave.toByteArray());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getSavedImages() {
        List<String> list = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().toString() + "/ElementsExample/";
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files!= null) {
            for (File file : files) list.add(file.getAbsolutePath());
        }
        return list;
    }


    public static int[] getScreenWidthInPixel(Context context) {

        int[] widthHeight = new int[2];
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display1 = wm.getDefaultDisplay();
        Point size1 = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

            display1.getSize(size1);
            widthHeight[0] = size1.x;
            widthHeight[1] = size1.y;

        } else {
            widthHeight[0] = display1.getWidth();
            widthHeight[1] = display1.getHeight();
        }
        return widthHeight;
    }

    public static void shareApplication(Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app Soulmate --Play Store application URL will be here--");
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static String uniqueId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static int getMonthNumber(String month) {
        switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return 0;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void fadeIn(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.1f, 1f);
        anim.setDuration(1500);
        view.startAnimation(anim);
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    public static void fadeOut(View view) {
        AlphaAnimation anim = new AlphaAnimation(1f, 0.1f);
        anim.setDuration(1500);
        view.startAnimation(anim);
    }

    public static Date getdate_from_string(String datae) {
        /*2017-11-28 18:18:04*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(datae);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return date;
        }
    }

    /*2017-11-28 18:18:04*/
    public static String getOnlyDateFromStringDate(String dateWithTime) {
        /*2017-11-28 18:18:04*/
        if (dateWithTime != null && dateWithTime.length() > 0) {
            String array[] = dateWithTime.split(" ");
            return array[0];
        }

        return "";
    }

    public static boolean compareDates(String current, String givenDate) {
        try {
            // If you already have date objects then skip 1

            //1
            // Create 2 dates starts
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(current);
            Date date2 = sdf.parse(givenDate);

            System.out.println("Date1" + sdf.format(date1));
            System.out.println("Date2" + sdf.format(date2));
            System.out.println();

            // before() will return true if and only if date1 is before date2
            if (date1.before(date2) || date1.equals(date2)) {
                System.out.println("Date1 is before Date2");
                return true;
            }

            System.out.println();
            return false;
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return false;

    }

    public static String getdate_current() {
        /*2017-11-28 18:18:04*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = Calendar.getInstance().getTime();
        return simpleDateFormat.format(date);
    }

    public static String getdate_current(Calendar calendar) {
        /*2017-11-28 18:18:04*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = calendar.getTime();
        return simpleDateFormat.format(date);


    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    public static String decodeString(String stringToDecode) {
        String decodedString = null;
        try {
            decodedString = URLDecoder.decode(stringToDecode, "UTF-8");
        } catch (Exception e) {

        }

        if (decodedString == null) {
            return stringToDecode;
        }
        Log.e("after decoding", decodedString);
        return decodedString;
    }

    public static Bitmap getBitmapFromFilePath(File file){
        return BitmapFactory.decodeFile(file.getAbsolutePath());

    }

    public static Bitmap getbitmap(byte[] arr) {
        return BitmapFactory.decodeStream(new ByteArrayInputStream(arr));
    }

    public static boolean checkText(String text) {
        if (text != null && !text.equalsIgnoreCase("")) {
            return true;
        } else
            return false;
    }

    public static byte[] compressImage(Bitmap bitmapImage) {
        byte[] byteImage;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteImage = stream.toByteArray();
        return byteImage;
    }

    /*public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) AppController.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
*/
    public static float dpToPx(float dp) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (dp * scale + 0.5f);
    }

    // getting only month from full date
    public static String getOnlyMonth(int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date * 1000);
        return String.valueOf(new SimpleDateFormat("MMM").format(calendar.getTime()));
    }

    public static String getOnlyMonth(Calendar date) {
        return String.valueOf(new SimpleDateFormat("MMM").format(date.getTime()));
    }

    // getting only date from full date
    public static String getOnlyDate(int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date * 1000);
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    //  validation to check phonenumber
    public static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;

    }

    // hide keyboard
    private static InputMethodManager inputMethodManager;

    public static void hideSoftKeyboard(Activity activity) {
        if (inputMethodManager == null)
            inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void showSoftKeyboard(Activity activity) {
        if (inputMethodManager == null)
            inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null)
            inputMethodManager.showSoftInput(activity.getCurrentFocus(), 0);
    }

    // Make a phone call
    public static void dialPhoneNumber(Context context, String phoneNumber) {
        if (phoneNumber != null && !TextUtils.isEmpty(phoneNumber)) {
            String number = phoneNumber;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + number));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        }
    }

    // Send a SMS
    public static void composeSmsMessage(Context context, String message, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static SimpleDateFormat commanDateTimeFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static String getOnlyDateFromTimeStamp(String timeStamp) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(timeStamp) * 1000);
            return String.valueOf(commanDateTimeFormat.format(calendar.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String gettimecounting(String sobertime) {
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            long seconds = Calendar.getInstance().getTimeInMillis() / 1000 - Long.parseLong(sobertime);
            long year = seconds / (3600 * 24 * 365);
            long month = (seconds % (3600 * 24 * 365)) / (30 * 24 * 3600);
            long days = ((seconds % (3600 * 24 * 365)) % (30 * 24 * 3600)) / (3600 * 24);

            if (year > 0) {
                stringBuilder.append(year + " year" + ((year > 1) ? "s " : " "));
            } else if (month > 0) {
                stringBuilder.append(month + " month" + ((month > 1) ? "s " : " "));
            }
            if (days > 0) {
                stringBuilder.append(days + " day" + ((days > 1) ? "s " : " "));
            }


        } catch (Exception e) {
        } finally {
            return stringBuilder.toString();
        }
    }

    public static String[] gettcoincounting(String sobertime) {
        String[] s = new String[2];
        try {
            long seconds = Calendar.getInstance().getTimeInMillis() / 1000 - Long.parseLong(sobertime);
            long year = seconds / (3600 * 24 * 365);
            if (year > 0) {
                s[0] = year + "";
                s[1] = "year" + (year > 1 ? "s" : "");
            } else {
                long sd = (seconds % (3600 * 24 * 365)) / (24 * 3600);
                s[0] = sd + "";
                s[1] = "day" + (sd > 1 ? "s" : "");
            }
        } catch (Exception e) {
        } finally {
            return s;
        }
    }

    public static String getTimeZone() {
        return Calendar.getInstance().getTimeZone().getID();
    }

    public static String getTimeStamp() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000);
    }

    public static long getUtcTimeStamp() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return calendar.getTimeInMillis() / 1000;
    }

    public static Calendar getCalenderObject(long epoch) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(epoch * 1000);
        return c;
    }

    public static Calendar getCalenderObject(String epoch) {
        return getCalenderObject(Long.parseLong(epoch));
    }

    /* public static Calendar getCalenderInChat(long epoch) {
         Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
         cal.setTimeInMillis(epoch);
         cal.setTimeZone(TimeZone.getDefault());
        return cal;

     }*/
    public static String getTimeStamp(Calendar calendar) {
        return String.valueOf(calendar.getTimeInMillis() / 1000);
    }

    private static final SimpleDateFormat dateFormanew = new SimpleDateFormat("dd MMM yyyy");
    private static final SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm a");

    public static String getMessageId() {

        return String.valueOf(System.currentTimeMillis());
    }

    public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {

        ActivityManager manager = (ActivityManager) context.
                getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : manager.
                getRunningServices(Integer.MAX_VALUE)) {

            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static Bitmap getCircleBitmap(Bitmap bit) {
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bit, 100, 100, true);
        final Bitmap output = Bitmap.createBitmap(bitmap1.getWidth(),
                bitmap1.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap1.getWidth(), bitmap1.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap1, rect, rect, paint);

        bitmap1.recycle();

        return output;
    }

   /* public static String getAgoTime(long time) {
        try {
            long m = System.currentTimeMillis() - (time * 1000);
            m = m / 60000;
            if (m == 0) {
                return "just now";
            } else if (m > 0 && m < 11) {
                if (m > 2)
                    return m + " mins ago";
                else
                    return m + " min ago";

            } else {
                return ChatAdapter.dateFormat.format(UtilMethods.getCalenderObject(time).getTime());
            }
        } catch (NumberFormatException e) {
            return "error";
        }
    }*/

  /*  public static String getAgoWithdate(long s) {
        Calendar now = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(s * 1000);
        StringBuilder builder = new StringBuilder();
        if (now.get(Calendar.DATE) == time.get(Calendar.DATE)) {
            builder.append("Today");
        } else if (now.get(Calendar.DATE) - time.get(Calendar.DATE) == 1) {
            builder.append("Yesterday");
        } else
            builder.append(DateFormat.format("dd MMMM yyyy", time.getTimeInMillis()));

        builder.append(" " + getAgoTime(s));
        return builder.toString();
    }*/

    public static int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) throws Exception {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    public static byte[] getThumbnil(File file) {
        byte[] imageData = null;
        try {
            final int THUMBNAIL_SIZE = 300;
            FileInputStream fis = new FileInputStream(file);
            Bitmap imageBitmap = BitmapFactory.decodeStream(fis);
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
            imageData = baos.toByteArray();

        } catch (Exception ex) {

        } finally {
            return imageData;
        }
    }

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static boolean deleteDir(File dir) throws Exception {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static void setRotrationEffectiveBackground(Activity context, Configuration configuration, int protimage, int landimage) {
        switch (configuration.orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                context.getWindow().setBackgroundDrawableResource(protimage);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                context.getWindow().setBackgroundDrawableResource(landimage);
                break;
        }
    }

    public static boolean isDateInCurrentWeek(long epoch) {
        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTimeInMillis(epoch * 1000);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return week == targetWeek && year == targetYear;
    }

    /* load json from assets folder*/
    public static String loadJSONFromAsset(Context context, String fileName) throws Exception {
        String json = null;
        InputStream is = context.getAssets().open(fileName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        return json;
    }
    /*public static String resolveNetworkError(Context basecontext, Throwable cause) {
        if (cause instanceof UnknownHostException)
            return basecontext.getString(R.string.no_internet);
        else if (cause instanceof SocketTimeoutException)
            return basecontext.getString(R.string.server_error);
        else if (cause instanceof ConnectException)
            return basecontext.getString(R.string.no_internet);
        else if (cause instanceof JsonSyntaxException)
            return basecontext.getString(R.string.parser_error);
        return basecontext.getString(R.string.wrong);
    }
*/
   /* public static void logoutUser(Activity context) {
        if(PrefUtils.getInstance().getUser()!=null){
            if (PrefUtils.getInstance().clear()) {
                Intent intent = new Intent(context, LoginAct.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                PrefUtils.getInstance().setFirstTime();
                context.finishAffinity();
            }}
    }*/

    public static String getTime(String createdon) {
        //data and time coming from response ......"createdon": "2018-10-30 06:57:50"
        String[] split1 = createdon.split(" ");
        String value_split1 = split1[1];
        String[] split2 = value_split1.split(":");
        String time = split2[0] + ":" + split2[1];
        if (Integer.parseInt(split2[0]) < 12)
            time += " AM";
        else
            time += " PM";
        return time;
    }

    public static String getAMPMTime(String hour) {
        if (Integer.parseInt(hour) < 12)
            hour += " AM";
        else
            hour += " PM";
        return hour;
    }

    public static String get12ClockAMPMTime(String hours) {
        String timeSet;
        int hour = Integer.parseInt(hours);
        if (hour > 12) {
            hour -= 12;
            timeSet = " PM";
        } else if (hour == 0) {
            hour += 12;
            timeSet = " AM";
        } else if (hour == 12) {
            timeSet = " PM";
        } else {
            timeSet = " AM";
        }

        return hour + timeSet;
    }

    public static String getTimeinAmPm(String time) {
        String timenew = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat outFormat = new SimpleDateFormat("hh:mm a");
        Date dt;
        try {
            dt = inputFormat.parse(time);
            timenew = outFormat.format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timenew;

    }


    public static String getDateFromCalTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd");
        return simpleDateFormat.format(date);
    }

    public static String getShortDateFromString(String date) {

        Date dateObject = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dateObject = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return UtilMethods.getDateFromCalTime(dateObject);
    }

    public static String getDayFromCalTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
        return simpleDateFormat.format(date);
    }

    public static String getDayFromTimeStamp(String timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(timeStamp) * 1000);
            return String.valueOf(simpleDateFormat.format(calendar.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Date convertStringToDate(String mydate) {

        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            date = format.parse(mydate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    //dd/MM/yyyy
    public static String getDayNameFromDate(String mydate) {

        SimpleDateFormat inFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = inFormat.parse(mydate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");

        return outFormat.format(date);
    }

    public static String getDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    public static String getDateIfExist(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    public static String getHyphenFormatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }

    public static void showAlertNoInternet(Context context) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.no_internet)
                .setMessage(R.string.turn_on_internet)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .create()
                .show();
    }

    private String getCalDate(String createdon) {
        // coming createdon date looks like this  -----  2018-11-01 04:49:16
        //and output should look like TUES, AUG 11

        Date newDate = null;
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss",Locale.US);
        try {
            newDate = spf.parse(createdon);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("E, MMM dd",Locale.US);
        String final_date = spf.format(newDate);
        return final_date.toUpperCase();

    }

    public static String getDateWithDots(String startDate) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy",Locale.US);
        Date date = null;
        try {
            date = format1.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(date);
    }


    public static ApiErrorBean handleError(ResponseBody errorbody) {

        Converter<ResponseBody, ApiErrorBean> converter = AppController.getInstance().getRetrofit().responseBodyConverter(ApiErrorBean.class, new Annotation[0]);
        ApiErrorBean error = null;
        try {
            error = converter.convert(errorbody);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return error;
        }
    }
}

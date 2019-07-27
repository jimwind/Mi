package com.up360.mi.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.up360.mi.R;
import com.up360.mi.utils.PermissionUtils;
import com.up360.mi.widget.PromptDialog;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author liangpingyy
 * 2018/6/22 11:17
 * 请用一句话概括功能
 */
public class PermissionBaseFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks,
        EasyPermissions.RationaleCallbacks {
    private final int REQ_PERMISSION_CALENDAR = 1150;//日历
    private final int REQ_PERMISSION_CAMERA = 1151;//相机
    private final int REQ_PERMISSION_CONTACTS = 1152;//联系人
    private final int REQ_PERMISSION_LOCATION = 1153;//定位
    private final int REQ_PERMISSION_MICROPHONE = 1154;//麦克风
    private final int REQ_PERMISSION_PHONE = 1155;//手机相关
    private final int REQ_PERMISSION_SENSORS = 1156;//传感器
    private final int REQ_PERMISSION_SMS = 1157;//短信
    private final int REQ_PERMISSION_STORAGE = 1158;//存储
    private final int REQ_PERMISSION_NOTIFATION = 1159;//

    private int reqSettingCode = -1;
    private boolean isShowAgain = true;

    @Override
    protected void loadViewLayout() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }


    //权限管理开始 liangpingyy
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_PERMISSION_CALENDAR) {
            grantedCalendarPermission(hasCalendarPermission() ? PermissionUtils.PERMISSION_STATUS_GRANTED :
                    PermissionUtils.PERMISSION_STATUS_DENY_SETTING);
            reqSettingCode = -1;
        }
        if (requestCode == REQ_PERMISSION_CAMERA) {
            grantedCaremaPermission(hasCameraPermission() ? PermissionUtils.PERMISSION_STATUS_GRANTED :
                    PermissionUtils.PERMISSION_STATUS_DENY_SETTING);
            reqSettingCode = -1;
        }
        if (requestCode == REQ_PERMISSION_LOCATION) {
            grantedLocationPermission(hasLocationPermissions() ? PermissionUtils.PERMISSION_STATUS_GRANTED :
                    PermissionUtils.PERMISSION_STATUS_DENY_SETTING);
            reqSettingCode = -1;
        }
        if (requestCode == REQ_PERMISSION_MICROPHONE) {
            grantedMicrophonePermission(hasMicroPhonePermission() ? PermissionUtils.PERMISSION_STATUS_GRANTED :
                    PermissionUtils.PERMISSION_STATUS_DENY_SETTING);
            reqSettingCode = -1;
        }
        if (requestCode == REQ_PERMISSION_SMS) {
            grantedSmsPermission(hasSmsPermission() ? PermissionUtils.PERMISSION_STATUS_GRANTED :
                    PermissionUtils.PERMISSION_STATUS_DENY_SETTING);
            reqSettingCode = -1;
        }
        if (requestCode == REQ_PERMISSION_STORAGE) {
            grantedStoragePermission(hasStoragePermission() ? PermissionUtils.PERMISSION_STATUS_GRANTED :
                    PermissionUtils.PERMISSION_STATUS_DENY_SETTING);
            reqSettingCode = -1;
        }
        if (requestCode == REQ_PERMISSION_PHONE) {
            grantedPhoneStatePermission(hasReadPhoneStatePermission() ? PermissionUtils.PERMISSION_STATUS_GRANTED :
                    PermissionUtils.PERMISSION_STATUS_DENY_SETTING);
            reqSettingCode = -1;
        }
        if (requestCode == REQ_PERMISSION_CONTACTS) {
            grantedContactsPermission(hasContactsPermissions() ? PermissionUtils.PERMISSION_STATUS_GRANTED :
                    PermissionUtils.PERMISSION_STATUS_DENY_SETTING);
            reqSettingCode = -1;
        }
        if (requestCode == REQ_PERMISSION_SENSORS) {
            grantedSensorPermission(hasSensorPermission() ? PermissionUtils.PERMISSION_STATUS_GRANTED :
                    PermissionUtils.PERMISSION_STATUS_DENY_SETTING);
            reqSettingCode = -1;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            showPromptDialog(getPermissionTipsByReqCode(requestCode), requestCode);
        } else {
            onDenyByRequest(getPermissionTipsByReqCode(requestCode),requestCode);
        }
    }

    private void showPromptDialog(String text, final int reqCode) {
        reqSettingCode = reqCode;
        PromptDialog.Builder builder = new PromptDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_permission, null);
        builder.setContentView(view);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_permission_message);
        Button btnCancel = (Button) view.findViewById(R.id.btn_permission_cancel);
        Button btnToSetting = (Button) view.findViewById(R.id.btn_permission_setting);
        tvContent.append(text);
        final PromptDialog dialog = builder.create();
        if (((Activity) context).isFinishing()) {
            return;
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            if (((Activity) context).isDestroyed()) {
                return;
            }
        }
        btnToSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                .setData(Uri.fromParts("package", context.getPackageName(), null)),
                        reqCode);
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                checkPermsDenied(reqCode, PermissionUtils.PERMISSION_STATUS_DENY_CANCEL);
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    return true;
                }
                return false;
            }
        });
        dialog.show();
    }

    private void checkPermsDenied(int reqCode, int status) {
        switch (reqCode) {
            case REQ_PERMISSION_CALENDAR:
                grantedCalendarPermission(status);
                break;
            case REQ_PERMISSION_CAMERA:
                grantedCaremaPermission(status);
                break;
            case REQ_PERMISSION_CONTACTS:
                grantedContactsPermission(status);
                break;
            case REQ_PERMISSION_LOCATION:
                grantedLocationPermission(status);
                break;
            case REQ_PERMISSION_MICROPHONE:
                grantedMicrophonePermission(status);
                break;
            case REQ_PERMISSION_PHONE:
                grantedPhoneStatePermission(status);
                break;
            case REQ_PERMISSION_SENSORS:
                grantedSensorPermission(status);
                break;
            case REQ_PERMISSION_SMS:
                grantedSmsPermission(status);
                break;
            case REQ_PERMISSION_STORAGE:
                grantedStoragePermission(status);
                break;
        }
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        Log.e("liangpingyy", "in accepted in " + requestCode);
    }

    @Override
    public void onRationaleDenied(int requestCode) {
        Log.e("liangpingyy", "in onRationaleDenied in " + requestCode);
//        checkPermsDenied(requestCode, PermissionUtils.PERMISSION_STATUS_DENY_RATIONAL);
//        onDenyByRequest(PermissionUtils.getPermissionTipsByReqCode(requestCode),requestCode);
    }

    /**
     * 是否有相机权限
     *
     * @return
     */
    protected boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(context, PermissionUtils.CAMERA);
    }

    /**
     * 是否有定位权限
     *
     * @return
     */
    protected boolean hasLocationPermissions() {
        return EasyPermissions.hasPermissions(context, PermissionUtils.LOCATION);
    }

    /**
     * 是否有获取联系人权限
     *
     * @return
     */
    protected boolean hasContactsPermissions() {
        return EasyPermissions.hasPermissions(context, PermissionUtils.CONTACTS);
    }

    /**
     * 是否有阅读短信权限
     *
     * @return
     */
    protected boolean hasSmsPermission() {
        return EasyPermissions.hasPermissions(context, PermissionUtils.SMS);
    }

    /**
     * 是否有存储权限
     *
     * @return
     */
    protected boolean hasStoragePermission() {
        return EasyPermissions.hasPermissions(context, PermissionUtils.STORAGE);
    }

    /**
     * 是否有读取手机信息权限
     *
     * @return
     */
    protected boolean hasReadPhoneStatePermission() {
        return EasyPermissions.hasPermissions(context, PermissionUtils.PHONE);
    }

    /**
     * 是否有麦克风权限
     *
     * @return
     */
    protected boolean hasMicroPhonePermission() {
        return EasyPermissions.hasPermissions(context, PermissionUtils.MICROPHONE);
    }

    /**
     * 是否有日历权限
     *
     * @return
     */
    protected boolean hasCalendarPermission() {
        return EasyPermissions.hasPermissions(context, PermissionUtils.CALENDAR);
    }

    /**
     * 是否获取了传感器权限
     *
     * @return
     */
    protected boolean hasSensorPermission() {
        return EasyPermissions.hasPermissions(context, PermissionUtils.SENSORS);
    }

    /**
     * 相机权限判定
     */
    @AfterPermissionGranted(REQ_PERMISSION_CAMERA)
    public void cameraTask() {
        if (hasCameraPermission()) {
            grantedCaremaPermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permission_tips_camera),
                    REQ_PERMISSION_CAMERA,
                    PermissionUtils.CAMERA);
        }
    }

    /**
     * 定位权限判定
     */
    @AfterPermissionGranted(REQ_PERMISSION_LOCATION)
    public void locationTask() {
        if (hasLocationPermissions()) {
            grantedLocationPermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permission_tips_location),
                    REQ_PERMISSION_LOCATION,
                    PermissionUtils.LOCATION);
        }
    }

    /**
     * 日历权限判定
     */
    @AfterPermissionGranted(REQ_PERMISSION_CALENDAR)
    public void calendarTask() {
        if (hasCalendarPermission()) {
            grantedCalendarPermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permission_tips_calendar),
                    REQ_PERMISSION_CALENDAR,
                    PermissionUtils.CALENDAR);
        }
    }

    /**
     * 麦克风权限判定
     */
    @AfterPermissionGranted(REQ_PERMISSION_MICROPHONE)
    public void microphoneTask() {
        if (hasMicroPhonePermission()) {
            grantedMicrophonePermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permission_tips_microphone),
                    REQ_PERMISSION_MICROPHONE,
                    PermissionUtils.MICROPHONE);
        }
    }

    /**
     * 短信权限判定
     */
    @AfterPermissionGranted(REQ_PERMISSION_SMS)
    public void smsTask() {
        if (hasSmsPermission()) {
            grantedSmsPermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permission_tips_sms),
                    REQ_PERMISSION_SMS,
                    PermissionUtils.SMS);
        }
    }

    /**
     * 存储权限判定
     */
    @AfterPermissionGranted(REQ_PERMISSION_STORAGE)
    public void storageTask() {
        if (hasStoragePermission()) {
            grantedStoragePermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permission_tips_storage),
                    REQ_PERMISSION_STORAGE,
                    PermissionUtils.STORAGE);
        }
    }

    /**
     * 传感器权限判定
     */
    @AfterPermissionGranted(REQ_PERMISSION_SENSORS)
    public void sensorTask() {
        if (hasSensorPermission()) {
            grantedSensorPermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permission_tips_sensor),
                    REQ_PERMISSION_SENSORS,
                    PermissionUtils.SENSORS);
        }
    }

    /**
     * 读取手机信息权限判定
     */
    @AfterPermissionGranted(REQ_PERMISSION_PHONE)
    public void phoneStateTask() {
        if (hasReadPhoneStatePermission()) {
            grantedPhoneStatePermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permission_tips_phone),
                    REQ_PERMISSION_PHONE,
                    PermissionUtils.PHONE);
        }
    }

    public void phoneStateTask(boolean isShowAgain) {
        if (hasReadPhoneStatePermission()) {
            grantedPhoneStatePermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
        } else {
            // Ask for both permissions
//            if (!isShowAgain) {
//                grantedPhoneStatePermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
//            } else {
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permission_tips_phone),
                    REQ_PERMISSION_PHONE,
                    PermissionUtils.PHONE);
//            }
        }
    }

    /**
     * 读取联系人权限判定
     */
    @AfterPermissionGranted(REQ_PERMISSION_CONTACTS)
    public void contactsTask() {
        if (hasContactsPermissions()) {
            // Have permissions, do the thing!
            grantedContactsPermission(PermissionUtils.PERMISSION_STATUS_GRANTED);
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permission_tips_contacts),
                    REQ_PERMISSION_CONTACTS,
                    PermissionUtils.CONTACTS);
        }
    }

    protected void onDenyByRequest(String text, int reqCode){
        showPromptDialog(text,reqCode);
    }


    /**
     * 读取短信权限获得
     */
    protected void grantedSmsPermission(int status) {

    }

    /**
     * 获得相机权限
     */
    protected void grantedCaremaPermission(int status) {

    }

    /**
     * 获得读取信息权限
     */
    protected void grantedPhoneStatePermission(int status) {

    }

    /**
     * 获得麦克风权限
     */
    protected void grantedMicrophonePermission(int status) {

    }

    /**
     * 获得日历权限
     */
    protected void grantedCalendarPermission(int status) {

    }

    /**
     * 获得联系人权限
     */
    protected void grantedContactsPermission(int status) {

    }

    /**
     * 获得存储权限
     */
    protected void grantedStoragePermission(int status) {

    }

    /**
     * 获得定位权限
     */
    protected void grantedLocationPermission(int status) {

    }

    /**
     * 获得传感器权限
     */
    protected void grantedSensorPermission(int status) {

    }


    //权限管理结束 liangpingyy

    private final String PERMISSION_STORAGE_TIPS = "读写手机存储权限，才能正常使用向上网";
    private final String PERMISSION_CALENDAR_TIPS = "";
    private final String PERMISSION_CONTACTS_TIPS = "";
    private final String PERMISSION_LOCATION_TIPS = "";
    private final String PERMISSION_MICROPHONE_TIPS = "录音权限，才能正常录音";
    private final String PERMISSION_PHONE_TIPS = "获取手机信息权限，才能正常使用向上网";
    private final String PERMISSION_SENSORS_TIPS = "";
    private final String PERMISSION_SMS_TIPS = "";
    private final String PERMISSION_CAREMA_TIPS = "相机权限，才能正常拍照";
    public String getPermissionTipsByReqCode(int reqCode){
        String tips = "";
        switch (reqCode){
            case REQ_PERMISSION_CAMERA:
                tips = PERMISSION_CAREMA_TIPS;
                break;
            case REQ_PERMISSION_CALENDAR:
                break;
            case REQ_PERMISSION_CONTACTS:
                break;
            case REQ_PERMISSION_SENSORS:
                break;
            case REQ_PERMISSION_SMS:
                break;
            case REQ_PERMISSION_STORAGE:
                tips = PERMISSION_STORAGE_TIPS;
                break;
            case REQ_PERMISSION_LOCATION:
                tips = PERMISSION_LOCATION_TIPS;
                break;
            case REQ_PERMISSION_PHONE:
                tips = PERMISSION_PHONE_TIPS;
                break;
            case REQ_PERMISSION_MICROPHONE:
                tips = PERMISSION_MICROPHONE_TIPS;
                break;
        }
        return tips;
    }
}

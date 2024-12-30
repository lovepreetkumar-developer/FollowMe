package com.techeytech.followme.beans.response_beans;

import androidx.annotation.Keep;

@Keep
public class LoginBean {


    /**
     * status : 200
     * message : Login successfull
     * data : {"id":"16","fullname":"Maninder22","address":"Green Enclave, Punjab2","location_detail":"location 2","phone":"54234523452345","contact_no":"242342342343242","pincode":"1446012","email":"abc@gmail.com","businessname":" contact2 business2","abn":"23ffvg43","copy_of_insurance":"","copy_of_pilotlicence":"","payment_method":"1","profilepic":"./uploads/profilepic_a48d3dcd31e8cbbe1e04d8f4d696f996.jpg","account_type":"0","longitude":"12312.3423","latitude":"23412.3412","rate":"2500","createdon":"2019-09-27 09:20:31","modifiedon":"2019-09-27 09:20:31","device_type":"0","device_token":"234","session_key":"25cc4dff8a6228b08207194a2023a79b","user_type":"1","isactive":"1"}
     * method : login
     * success : 1
     */

    private int status;
    private String message;
    private DataBean data;
    private String method;
    private int success;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public static class DataBean {
        private String id;
        private String fullname;
        private String address;
        private String location_detail;
        private String phone;
        private String contact_no;
        private String pincode;
        private String email;
        private String businessname;
        private String abn;
        private String copy_of_insurance;
        private String copy_of_pilotlicence;
        private String payment_method;
        private String profilepic;
        private String longitude;
        private String latitude;
        private String rate;
        private String createdon;
        private String modifiedon;
        private String device_type;
        private String device_token;
        private String sessionkey;
        private String user_type;
        private String isactive;
        private String postal_address;
        private String show_adds;

        public String getShow_adds() {
            return show_adds;
        }

        public void setShow_adds(String show_adds) {
            this.show_adds = show_adds;
        }

        public String getPostal_address() {
            return postal_address;
        }

        public void setPostal_address(String postal_address) {
            this.postal_address = postal_address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLocation_detail() {
            return location_detail;
        }

        public void setLocation_detail(String location_detail) {
            this.location_detail = location_detail;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getContact_no() {
            return contact_no;
        }

        public void setContact_no(String contact_no) {
            this.contact_no = contact_no;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBusinessname() {
            return businessname;
        }

        public void setBusinessname(String businessname) {
            this.businessname = businessname;
        }

        public String getAbn() {
            return abn;
        }

        public void setAbn(String abn) {
            this.abn = abn;
        }

        public String getCopy_of_insurance() {
            return copy_of_insurance;
        }

        public void setCopy_of_insurance(String copy_of_insurance) {
            this.copy_of_insurance = copy_of_insurance;
        }

        public String getCopy_of_pilotlicence() {
            return copy_of_pilotlicence;
        }

        public void setCopy_of_pilotlicence(String copy_of_pilotlicence) {
            this.copy_of_pilotlicence = copy_of_pilotlicence;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }

        public String getProfilepic() {
            return profilepic;
        }

        public void setProfilepic(String profilepic) {
            this.profilepic = profilepic;
        }


        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getCreatedon() {
            return createdon;
        }

        public void setCreatedon(String createdon) {
            this.createdon = createdon;
        }

        public String getModifiedon() {
            return modifiedon;
        }

        public void setModifiedon(String modifiedon) {
            this.modifiedon = modifiedon;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public String getSessionkey() {
            return sessionkey;
        }

        public void setSessionkey(String sessionkey) {
            this.sessionkey = sessionkey;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getIsactive() {
            return isactive;
        }

        public void setIsactive(String isactive) {
            this.isactive = isactive;
        }
    }
}

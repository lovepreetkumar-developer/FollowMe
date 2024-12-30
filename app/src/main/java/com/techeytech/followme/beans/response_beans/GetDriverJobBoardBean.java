package com.techeytech.followme.beans.response_beans;

import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.List;

@Keep
public class GetDriverJobBoardBean {


    /**
     * status : 200
     * message : Job Board successfully loaded.
     * data : [{"job_id":"6","company_id":"5","companyname":"fullname_cm","address":"address","need":"Pilot","start_location":"sloc12","start_latitude":"30.715851","start_longitude":"76.688802","end_location":"eloc","end_latitude":"end_latitude","end_longitude":"end_longitude","start_date":"2019-10-12 12:36:20","end_date":"2019-10-13 12:36:20","contact_name":"Comapny123","contact_phone":"contact_phone","approx_km":"5","image_urls":[{"image_url":"./uploads/copy_of_insurance_63dbf4e1ad2c3e12f82ac8d58f66ae9f.jpg"},{"image_url":"./uploads/copy_of_insurance_63dbf4e1ad2c3e12f82ac8d58f66ae9f.jpg"},{"image_url":"./uploads/copy_of_insurance_63dbf4e1ad2c3e12f82ac8d58f66ae9f.jpg"}],"distance_in_km":"1.9176819337005555"},{"job_id":"5","company_id":"5","companyname":"fullname_cm","address":"address","need":"Pilot","start_location":"Mohali","start_latitude":"30.695534","start_longitude":"76.762161","end_location":"kharar","end_latitude":"end_latitude","end_longitude":"end_longitude","start_date":"2019-08-02 12:36:20","end_date":"2019-08-07 12:36:20","contact_name":"contact_name","contact_phone":"contact_phone","approx_km":"approx_km","image_urls":[{"image_url":"./uploads/copy_of_insurance_63dbf4e1ad2c3e12f82ac8d58f66ae9f.jpg"}],"distance_in_km":"8.924483597184583"}]
     * method : getJobBoardDriver
     * success : 1
     */

    private int status;
    private String message;
    private String method;
    private String success;
    private List<DataBean> data;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * job_id : 6
         * company_id : 5
         * companyname : fullname_cm
         * address : address
         * need : Pilot
         * start_location : sloc12
         * start_latitude : 30.715851
         * start_longitude : 76.688802
         * end_location : eloc
         * end_latitude : end_latitude
         * end_longitude : end_longitude
         * start_date : 2019-10-12 12:36:20
         * end_date : 2019-10-13 12:36:20
         * contact_name : Comapny123
         * contact_phone : contact_phone
         * approx_km : 5
         * image_urls : [{"image_url":"./uploads/copy_of_insurance_63dbf4e1ad2c3e12f82ac8d58f66ae9f.jpg"},{"image_url":"./uploads/copy_of_insurance_63dbf4e1ad2c3e12f82ac8d58f66ae9f.jpg"},{"image_url":"./uploads/copy_of_insurance_63dbf4e1ad2c3e12f82ac8d58f66ae9f.jpg"}]
         * distance_in_km : 1.9176819337005555
         */

        private String job_id;
        private String company_id;
        private String companyname;
        private String address;
        private String need;
        private String start_location;
        private String start_latitude;
        private String start_longitude;
        private String end_location;
        private String end_latitude;
        private String end_longitude;
        private String start_date;
        private String end_date;
        private String contact_name;
        private String contact_phone;
        private String approx_km;
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        private String distance_in_km;
        private List<ImageUrlsBean> image_urls;

        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNeed() {
            return need;
        }

        public void setNeed(String need) {
            this.need = need;
        }

        public String getStart_location() {
            return start_location;
        }

        public void setStart_location(String start_location) {
            this.start_location = start_location;
        }

        public String getStart_latitude() {
            return start_latitude;
        }

        public void setStart_latitude(String start_latitude) {
            this.start_latitude = start_latitude;
        }

        public String getStart_longitude() {
            return start_longitude;
        }

        public void setStart_longitude(String start_longitude) {
            this.start_longitude = start_longitude;
        }

        public String getEnd_location() {
            return end_location;
        }

        public void setEnd_location(String end_location) {
            this.end_location = end_location;
        }

        public String getEnd_latitude() {
            return end_latitude;
        }

        public void setEnd_latitude(String end_latitude) {
            this.end_latitude = end_latitude;
        }

        public String getEnd_longitude() {
            return end_longitude;
        }

        public void setEnd_longitude(String end_longitude) {
            this.end_longitude = end_longitude;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getContact_phone() {
            return contact_phone;
        }

        public void setContact_phone(String contact_phone) {
            this.contact_phone = contact_phone;
        }

        public String getApprox_km() {
            return approx_km;
        }

        public void setApprox_km(String approx_km) {
            this.approx_km = approx_km;
        }

        public String getDistance_in_km() {
            return distance_in_km;
        }

        public void setDistance_in_km(String distance_in_km) {
            this.distance_in_km = distance_in_km;
        }

        public List<ImageUrlsBean> getImage_urls() {
            return image_urls;
        }

        public void setImage_urls(List<ImageUrlsBean> image_urls) {
            this.image_urls = image_urls;
        }

        public static class ImageUrlsBean implements Serializable {
            /**
             * image_url : ./uploads/copy_of_insurance_63dbf4e1ad2c3e12f82ac8d58f66ae9f.jpg
             */

            private String image_url;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }
    }
}

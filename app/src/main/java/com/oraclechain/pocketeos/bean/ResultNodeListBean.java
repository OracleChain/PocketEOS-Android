package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by pocketEos on 2018/6/11.
 */

public class ResultNodeListBean {

    /**
     * code : 0
     * msg : SUCCESS
     * data : {"currentPage":0,"rowsPerPage":10,"totalPages":2,"totalRows":18,"data":[{"owner":"oraclegogogo","total_votes":"74145520018.94651794433593750","producer_key":"EOS5c2fvwkzAcBK57irykyeGd8cuhag43U77peqYzMU71JKvmf91R","is_active":1,"url":"https://oraclechain.io/","unpaid_blocks":0,"last_claim_time":0,"location":0,"producerUrlInfo":{"producer_account_name":"oraclegogogo","org":{"branding":{"logo_256":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle.png","logo_1024":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle_big.png","logo_svg":""},"candidate_name":"oraclechain","website":"https://oraclechain.io","email":"decai.zeng@oracleChain.io"}}}]}
     */

    private String code;
    private String msg;
    private DataBeanX data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * currentPage : 0
         * rowsPerPage : 10
         * totalPages : 2
         * totalRows : 18
         * data : [{"owner":"oraclegogogo","total_votes":"74145520018.94651794433593750","producer_key":"EOS5c2fvwkzAcBK57irykyeGd8cuhag43U77peqYzMU71JKvmf91R","is_active":1,"url":"https://oraclechain.io/","unpaid_blocks":0,"last_claim_time":0,"location":0,"producerUrlInfo":{"producer_account_name":"oraclegogogo","org":{"branding":{"logo_256":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle.png","logo_1024":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle_big.png","logo_svg":""},"candidate_name":"oraclechain","website":"https://oraclechain.io","email":"decai.zeng@oracleChain.io"}}}]
         */

        private int currentPage;
        private int rowsPerPage;
        private int totalPages;
        private int totalRows;
        private List<DataBean> data;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getRowsPerPage() {
            return rowsPerPage;
        }

        public void setRowsPerPage(int rowsPerPage) {
            this.rowsPerPage = rowsPerPage;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Parcelable {
            /**
             * owner : oraclegogogo
             * total_votes : 74145520018.94651794433593750
             * producer_key : EOS5c2fvwkzAcBK57irykyeGd8cuhag43U77peqYzMU71JKvmf91R
             * is_active : 1
             * url : https://oraclechain.io/
             * unpaid_blocks : 0
             * last_claim_time : 0
             * location : 0
             * producerUrlInfo : {"producer_account_name":"oraclegogogo","org":{"branding":{"logo_256":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle.png","logo_1024":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle_big.png","logo_svg":""},"candidate_name":"oraclechain","website":"https://oraclechain.io","email":"decai.zeng@oracleChain.io"}}
             */

            private String owner;
            private String total_votes;
            private String producer_key;
            private String is_active;
            private String url;
            private String unpaid_blocks;
            private String last_claim_time;
            private String location;
            private ProducerUrlInfoBean producerUrlInfo = null;
            private Boolean select = false;

            public Boolean getSelect() {
                return select;
            }

            public void setSelect(Boolean select) {
                this.select = select;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getTotal_votes() {
                return total_votes;
            }

            public void setTotal_votes(String total_votes) {
                this.total_votes = total_votes;
            }

            public String getProducer_key() {
                return producer_key;
            }

            public void setProducer_key(String producer_key) {
                this.producer_key = producer_key;
            }

            public String getIs_active() {
                return is_active;
            }

            public void setIs_active(String is_active) {
                this.is_active = is_active;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUnpaid_blocks() {
                return unpaid_blocks;
            }

            public void setUnpaid_blocks(String unpaid_blocks) {
                this.unpaid_blocks = unpaid_blocks;
            }

            public String getLast_claim_time() {
                return last_claim_time;
            }

            public void setLast_claim_time(String last_claim_time) {
                this.last_claim_time = last_claim_time;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public ProducerUrlInfoBean getProducerUrlInfo() {
                return producerUrlInfo;
            }

            public void setProducerUrlInfo(ProducerUrlInfoBean producerUrlInfo) {
                this.producerUrlInfo = producerUrlInfo;
            }

            public static class ProducerUrlInfoBean implements Parcelable {
                /**
                 * producer_account_name : oraclegogogo
                 * org : {"branding":{"logo_256":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle.png","logo_1024":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle_big.png","logo_svg":""},"candidate_name":"oraclechain","website":"https://oraclechain.io","email":"decai.zeng@oracleChain.io"}
                 */

                private String producer_account_name;
                private OrgBean org;

                public String getProducer_account_name() {
                    return producer_account_name;
                }

                public void setProducer_account_name(String producer_account_name) {
                    this.producer_account_name = producer_account_name;
                }

                public OrgBean getOrg() {
                    return org;
                }

                public void setOrg(OrgBean org) {
                    this.org = org;
                }

                public static class OrgBean implements Parcelable {
                    /**
                     * branding : {"logo_256":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle.png","logo_1024":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle_big.png","logo_svg":""}
                     * candidate_name : oraclechain
                     * website : https://oraclechain.io
                     * email : decai.zeng@oracleChain.io
                     */

                    private BrandingBean branding;
                    private String candidate_name;
                    private String website;
                    private String email;

                    protected OrgBean(Parcel in) {
                        branding = in.readParcelable(BrandingBean.class.getClassLoader());
                        candidate_name = in.readString();
                        website = in.readString();
                        email = in.readString();
                    }

                    public static final Creator<OrgBean> CREATOR = new Creator<OrgBean>() {
                        @Override
                        public OrgBean createFromParcel(Parcel in) {
                            return new OrgBean(in);
                        }

                        @Override
                        public OrgBean[] newArray(int size) {
                            return new OrgBean[size];
                        }
                    };

                    public BrandingBean getBranding() {
                        return branding;
                    }

                    public void setBranding(BrandingBean branding) {
                        this.branding = branding;
                    }

                    public String getCandidate_name() {
                        return candidate_name;
                    }

                    public void setCandidate_name(String candidate_name) {
                        this.candidate_name = candidate_name;
                    }

                    public String getWebsite() {
                        return website;
                    }

                    public void setWebsite(String website) {
                        this.website = website;
                    }

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeParcelable(branding, flags);
                        dest.writeString(candidate_name);
                        dest.writeString(website);
                        dest.writeString(email);
                    }

                    public static class BrandingBean implements Parcelable {
                        /**
                         * logo_256 : https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle.png
                         * logo_1024 : https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle_big.png
                         * logo_svg :
                         */

                        private String logo_256;
                        private String logo_1024;
                        private String logo_svg;

                        public String getLogo_256() {
                            return logo_256;
                        }

                        public void setLogo_256(String logo_256) {
                            this.logo_256 = logo_256;
                        }

                        public String getLogo_1024() {
                            return logo_1024;
                        }

                        public void setLogo_1024(String logo_1024) {
                            this.logo_1024 = logo_1024;
                        }

                        public String getLogo_svg() {
                            return logo_svg;
                        }

                        public void setLogo_svg(String logo_svg) {
                            this.logo_svg = logo_svg;
                        }

                        @Override
                        public int describeContents() {
                            return 0;
                        }

                        @Override
                        public void writeToParcel(Parcel dest, int flags) {
                            dest.writeString(this.logo_256);
                            dest.writeString(this.logo_1024);
                            dest.writeString(this.logo_svg);
                        }

                        public BrandingBean() {
                        }

                        protected BrandingBean(Parcel in) {
                            this.logo_256 = in.readString();
                            this.logo_1024 = in.readString();
                            this.logo_svg = in.readString();
                        }

                        public static final Creator<BrandingBean> CREATOR = new Creator<BrandingBean>() {
                            @Override
                            public BrandingBean createFromParcel(Parcel source) {
                                return new BrandingBean(source);
                            }

                            @Override
                            public BrandingBean[] newArray(int size) {
                                return new BrandingBean[size];
                            }
                        };
                    }
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.producer_account_name);
                    dest.writeParcelable(this.org, flags);
                }

                public ProducerUrlInfoBean() {
                }

                protected ProducerUrlInfoBean(Parcel in) {
                    this.producer_account_name = in.readString();
                    this.org = in.readParcelable(OrgBean.class.getClassLoader());
                }

                public static final Creator<ProducerUrlInfoBean> CREATOR = new Creator<ProducerUrlInfoBean>() {
                    @Override
                    public ProducerUrlInfoBean createFromParcel(Parcel source) {
                        return new ProducerUrlInfoBean(source);
                    }

                    @Override
                    public ProducerUrlInfoBean[] newArray(int size) {
                        return new ProducerUrlInfoBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.owner);
                dest.writeString(this.total_votes);
                dest.writeString(this.producer_key);
                dest.writeString(this.is_active);
                dest.writeString(this.url);
                dest.writeString(this.unpaid_blocks);
                dest.writeString(this.last_claim_time);
                dest.writeString(this.location);
                dest.writeParcelable(this.producerUrlInfo, flags);
                dest.writeValue(this.select);
            }

            public DataBean() {
            }

            protected DataBean(Parcel in) {
                this.owner = in.readString();
                this.total_votes = in.readString();
                this.producer_key = in.readString();
                this.is_active = in.readString();
                this.url = in.readString();
                this.unpaid_blocks = in.readString();
                this.last_claim_time = in.readString();
                this.location = in.readString();
                this.producerUrlInfo = in.readParcelable(ProducerUrlInfoBean.class.getClassLoader());
                this.select = (Boolean) in.readValue(Boolean.class.getClassLoader());
            }

            public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel source) {
                    return new DataBean(source);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };
        }
    }
}

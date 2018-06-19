package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/6/9.
 */

public class ResultTableRowBean {

    /**
     * code : 0
     * msg : SUCCESS
     * data : {"info":{"owner":"aaaaaavotera","proxy":"","producers":["oraclegogogo"],"staked":200000,"last_vote_weight":"74145520018.94651794433593750","proxied_vote_weight":"0.00000000000000000","is_proxy":"1"},"producers":[{"owner":"oraclegogogo","total_votes":"74145520018.94651794433593750","producer_key":"EOS5c2fvwkzAcBK57irykyeGd8cuhag43U77peqYzMU71JKvmf91R","is_active":1,"url":"https://oraclechain.io/","unpaid_blocks":0,"last_claim_time":0,"location":0,"producerUrlInfo":{"producer_account_name":"oraclegogogo","org":{"branding":{"logo_256":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle.png","logo_1024":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle_big.png","logo_svg":""},"candidate_name":"oraclechain","website":"https://oraclechain.io","email":"decai.zeng@oracleChain.io"}}}]}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * info : {"owner":"aaaaaavotera","proxy":"","producers":["oraclegogogo"],"staked":200000,"last_vote_weight":"74145520018.94651794433593750","proxied_vote_weight":"0.00000000000000000","is_proxy":"1"}
         * producers : [{"owner":"oraclegogogo","total_votes":"74145520018.94651794433593750","producer_key":"EOS5c2fvwkzAcBK57irykyeGd8cuhag43U77peqYzMU71JKvmf91R","is_active":1,"url":"https://oraclechain.io/","unpaid_blocks":0,"last_claim_time":0,"location":0,"producerUrlInfo":{"producer_account_name":"oraclegogogo","org":{"branding":{"logo_256":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle.png","logo_1024":"https://pocketeos.oss-cn-beijing.aliyuncs.com/P1/oracle_big.png","logo_svg":""},"candidate_name":"oraclechain","website":"https://oraclechain.io","email":"decai.zeng@oracleChain.io"}}}]
         */

        private InfoBean info;
        private List<ProducersBean> producers;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ProducersBean> getProducers() {
            return producers;
        }

        public void setProducers(List<ProducersBean> producers) {
            this.producers = producers;
        }

        public static class InfoBean {
            /**
             * owner : aaaaaavotera
             * proxy :
             * producers : ["oraclegogogo"]
             * staked : 200000
             * last_vote_weight : 74145520018.94651794433593750
             * proxied_vote_weight : 0.00000000000000000
             * is_proxy : 1
             */

            private String owner;
            private String proxy;
            private String staked;
            private String last_vote_weight;
            private String proxied_vote_weight;
            private String is_proxy;
            private List<String> producers;

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getProxy() {
                return proxy;
            }

            public void setProxy(String proxy) {
                this.proxy = proxy;
            }

            public String getStaked() {
                return staked;
            }

            public void setStaked(String staked) {
                this.staked = staked;
            }

            public String getLast_vote_weight() {
                return last_vote_weight;
            }

            public void setLast_vote_weight(String last_vote_weight) {
                this.last_vote_weight = last_vote_weight;
            }

            public String getProxied_vote_weight() {
                return proxied_vote_weight;
            }

            public void setProxied_vote_weight(String proxied_vote_weight) {
                this.proxied_vote_weight = proxied_vote_weight;
            }

            public String getIs_proxy() {
                return is_proxy;
            }

            public void setIs_proxy(String is_proxy) {
                this.is_proxy = is_proxy;
            }

            public List<String> getProducers() {
                return producers;
            }

            public void setProducers(List<String> producers) {
                this.producers = producers;
            }
        }

        public static class ProducersBean {
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
            private ProducerUrlInfoBean producerUrlInfo;

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

            public static class ProducerUrlInfoBean {
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

                public static class OrgBean {
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

                    public static class BrandingBean {
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
                    }
                }
            }
        }
    }
}

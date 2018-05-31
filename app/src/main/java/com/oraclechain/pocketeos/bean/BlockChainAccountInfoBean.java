package com.oraclechain.pocketeos.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/2/6.
 */

public class BlockChainAccountInfoBean {

    /**
     * code : 0
     * message : ok
     * data : {"staked_balance":"0.0001 EOS","permissions":[{"parent":"owner","required_auth":{"keys":[{"weight":1,"key":"EOS84iZ6QEqwTKrrUFMaUJ7gouRtMJiqev5MRsRubV12nonPri3QE"}],"threshold":1,"accounts":[]},"perm_name":"active"},{"parent":"","required_auth":{"keys":[{"weight":1,"key":"EOS7exru5dUsyKYqHZGGpzFuVCjoJV8uuw579fvoZ3K6b8VdY86kj"}],"threshold":1,"accounts":[]},"perm_name":"owner"}],"account_name":"oracle1","last_unstaking_time":"2106-02-07T06:28:15","unstaking_balance":"0.0000 EOS","eos_balance":"10070.0000 EOS"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
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

    public static class DataBean {
        /**
         * staked_balance : 0.0001 EOS
         * permissions : [{"parent":"owner","required_auth":{"keys":[{"weight":1,"key":"EOS84iZ6QEqwTKrrUFMaUJ7gouRtMJiqev5MRsRubV12nonPri3QE"}],"threshold":1,"accounts":[]},"perm_name":"active"},{"parent":"","required_auth":{"keys":[{"weight":1,"key":"EOS7exru5dUsyKYqHZGGpzFuVCjoJV8uuw579fvoZ3K6b8VdY86kj"}],"threshold":1,"accounts":[]},"perm_name":"owner"}]
         * account_name : oracle1
         * last_unstaking_time : 2106-02-07T06:28:15
         * unstaking_balance : 0.0000 EOS
         * eos_balance : 10070.0000 EOS
         */

        private String staked_balance;
        private String account_name;
        private String last_unstaking_time;
        private String unstaking_balance;
        private String eos_balance;
        private List<PermissionsBean> permissions;

        public String getStaked_balance() {
            return staked_balance == null ? "" : staked_balance;
        }

        public void setStaked_balance(String staked_balance) {
            this.staked_balance = staked_balance;
        }

        public String getAccount_name() {
            return account_name == null ? "" : account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getLast_unstaking_time() {
            return last_unstaking_time == null ? "" : last_unstaking_time;
        }

        public void setLast_unstaking_time(String last_unstaking_time) {
            this.last_unstaking_time = last_unstaking_time;
        }

        public String getUnstaking_balance() {
            return unstaking_balance == null ? "" : unstaking_balance;
        }

        public void setUnstaking_balance(String unstaking_balance) {
            this.unstaking_balance = unstaking_balance;
        }

        public String getEos_balance() {
            return eos_balance == null ? "" : eos_balance;
        }

        public void setEos_balance(String eos_balance) {
            this.eos_balance = eos_balance;
        }

        public List<PermissionsBean> getPermissions() {
            if (permissions == null) {
                return new ArrayList<>();
            }
            return permissions;
        }

        public void setPermissions(List<PermissionsBean> permissions) {
            this.permissions = permissions;
        }

        public static class PermissionsBean {
            /**
             * parent : owner
             * required_auth : {"keys":[{"weight":1,"key":"EOS84iZ6QEqwTKrrUFMaUJ7gouRtMJiqev5MRsRubV12nonPri3QE"}],"threshold":1,"accounts":[]}
             * perm_name : active
             */

            private String parent;
            private RequiredAuthBean required_auth;
            private String perm_name;

            public String getParent() {
                return parent == null ? "" : parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }

            public RequiredAuthBean getRequired_auth() {
                return required_auth;
            }

            public void setRequired_auth(RequiredAuthBean required_auth) {
                this.required_auth = required_auth;
            }

            public String getPerm_name() {
                return perm_name == null ? "" : perm_name;
            }

            public void setPerm_name(String perm_name) {
                this.perm_name = perm_name;
            }

            public static class RequiredAuthBean {
                /**
                 * keys : [{"weight":1,"key":"EOS84iZ6QEqwTKrrUFMaUJ7gouRtMJiqev5MRsRubV12nonPri3QE"}]
                 * threshold : 1
                 * accounts : []
                 */

                private int threshold;
                private List<KeysBean> keys;
                private List<?> accounts;

                public int getThreshold() {
                    return threshold;
                }

                public void setThreshold(int threshold) {
                    this.threshold = threshold;
                }

                public List<KeysBean> getKeys() {
                    if (keys == null) {
                        return new ArrayList<>();
                    }
                    return keys;
                }

                public void setKeys(List<KeysBean> keys) {
                    this.keys = keys;
                }

                public List<?> getAccounts() {
                    if (accounts == null) {
                        return new ArrayList<>();
                    }
                    return accounts;
                }

                public void setAccounts(List<?> accounts) {
                    this.accounts = accounts;
                }

                public static class KeysBean {
                    /**
                     * weight : 1
                     * key : EOS84iZ6QEqwTKrrUFMaUJ7gouRtMJiqev5MRsRubV12nonPri3QE
                     */

                    private int weight;
                    private String key;

                    public int getWeight() {
                        return weight;
                    }

                    public void setWeight(int weight) {
                        this.weight = weight;
                    }

                    public String getKey() {
                        return key == null ? "" : key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }
                }
            }
        }
    }
}

package com.oraclechain.pocketeos.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/1/19.
 */

public class EosRegisterBean {


    /**
     * code : 0
     * message : ok
     * data : {"transaction_id":"b62dcc54263220d29cc9eccdba19f0a3ac4707f74497e907aee361f9bd0e9357","processed":{"output":[{"deferred_trxs":[],"notify":[]}],"ref_block_prefix":2555203186,"scope":["eos","inita"],"messages":[{"authorization":[{"permission":"active","account":"inita"}],"hex_data":"000000000093dd74000000800c11beb1010000000103e63fcce8e0374bf5a0d376a37ab6d3eca461b2fb14e4f7942acff19f90221e81010000010000000102ebb3f50ec826ed2079097eb2ecac235d101ef3245dc5090fe9bcc403c0364c67010000010000000001000000000093dd7400000000a8ed32320100010000000000000004454f5300000000","code":"eos","data":{"owner":{"keys":[{"weight":1,"key":"EOS8adwKXtTvPLC5qEJ84GWHS2YQBmJPNTER2rb7fZjmVVqV9Wp1R"}],"threshold":1,"accounts":[]},"creator":"inita","name":"qaz1234","active":{"keys":[{"weight":1,"key":"EOS6gJ4yZsmoaxWvXWG9o4a5JS4unu99JCUqetLZLPidpp6XwvKUn"}],"threshold":1,"accounts":[]},"deposit":"0.0001 EOS","recovery":{"keys":[],"threshold":1,"accounts":[{"weight":1,"permission":{"permission":"active","account":"inita"}}]}},"type":"newaccount"}],"expiration":"2018-03-13T05:23:08","ref_block_num":64052,"signatures":["203ca8360aaf4e8d8ccaa342e6525f6873edc9017c8c446ef4669affca3bc8799e7c4176dd7c9832c2907fbcb129bd05b213a917b84fd2dc93f2c7c21cfcd1885f"]}}
     */

    private int code;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * transaction_id : b62dcc54263220d29cc9eccdba19f0a3ac4707f74497e907aee361f9bd0e9357
         * processed : {"output":[{"deferred_trxs":[],"notify":[]}],"ref_block_prefix":2555203186,"scope":["eos","inita"],"messages":[{"authorization":[{"permission":"active","account":"inita"}],"hex_data":"000000000093dd74000000800c11beb1010000000103e63fcce8e0374bf5a0d376a37ab6d3eca461b2fb14e4f7942acff19f90221e81010000010000000102ebb3f50ec826ed2079097eb2ecac235d101ef3245dc5090fe9bcc403c0364c67010000010000000001000000000093dd7400000000a8ed32320100010000000000000004454f5300000000","code":"eos","data":{"owner":{"keys":[{"weight":1,"key":"EOS8adwKXtTvPLC5qEJ84GWHS2YQBmJPNTER2rb7fZjmVVqV9Wp1R"}],"threshold":1,"accounts":[]},"creator":"inita","name":"qaz1234","active":{"keys":[{"weight":1,"key":"EOS6gJ4yZsmoaxWvXWG9o4a5JS4unu99JCUqetLZLPidpp6XwvKUn"}],"threshold":1,"accounts":[]},"deposit":"0.0001 EOS","recovery":{"keys":[],"threshold":1,"accounts":[{"weight":1,"permission":{"permission":"active","account":"inita"}}]}},"type":"newaccount"}],"expiration":"2018-03-13T05:23:08","ref_block_num":64052,"signatures":["203ca8360aaf4e8d8ccaa342e6525f6873edc9017c8c446ef4669affca3bc8799e7c4176dd7c9832c2907fbcb129bd05b213a917b84fd2dc93f2c7c21cfcd1885f"]}
         */

        private String transaction_id;
        private ProcessedBean processed;

        public String getTransaction_id() {
            return transaction_id == null ? "" : transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public ProcessedBean getProcessed() {
            return processed;
        }

        public void setProcessed(ProcessedBean processed) {
            this.processed = processed;
        }

        public static class ProcessedBean {
            /**
             * output : [{"deferred_trxs":[],"notify":[]}]
             * ref_block_prefix : 2555203186
             * scope : ["eos","inita"]
             * messages : [{"authorization":[{"permission":"active","account":"inita"}],"hex_data":"000000000093dd74000000800c11beb1010000000103e63fcce8e0374bf5a0d376a37ab6d3eca461b2fb14e4f7942acff19f90221e81010000010000000102ebb3f50ec826ed2079097eb2ecac235d101ef3245dc5090fe9bcc403c0364c67010000010000000001000000000093dd7400000000a8ed32320100010000000000000004454f5300000000","code":"eos","data":{"owner":{"keys":[{"weight":1,"key":"EOS8adwKXtTvPLC5qEJ84GWHS2YQBmJPNTER2rb7fZjmVVqV9Wp1R"}],"threshold":1,"accounts":[]},"creator":"inita","name":"qaz1234","active":{"keys":[{"weight":1,"key":"EOS6gJ4yZsmoaxWvXWG9o4a5JS4unu99JCUqetLZLPidpp6XwvKUn"}],"threshold":1,"accounts":[]},"deposit":"0.0001 EOS","recovery":{"keys":[],"threshold":1,"accounts":[{"weight":1,"permission":{"permission":"active","account":"inita"}}]}},"type":"newaccount"}]
             * expiration : 2018-03-13T05:23:08
             * ref_block_num : 64052
             * signatures : ["203ca8360aaf4e8d8ccaa342e6525f6873edc9017c8c446ef4669affca3bc8799e7c4176dd7c9832c2907fbcb129bd05b213a917b84fd2dc93f2c7c21cfcd1885f"]
             */

            private long ref_block_prefix;
            private String expiration;
            private int ref_block_num;
            private List<OutputBean> output;
            private List<String> scope;
            private List<MessagesBean> messages;
            private List<String> signatures;

            public long getRef_block_prefix() {
                return ref_block_prefix;
            }

            public void setRef_block_prefix(long ref_block_prefix) {
                this.ref_block_prefix = ref_block_prefix;
            }

            public String getExpiration() {
                return expiration == null ? "" : expiration;
            }

            public void setExpiration(String expiration) {
                this.expiration = expiration;
            }

            public int getRef_block_num() {
                return ref_block_num;
            }

            public void setRef_block_num(int ref_block_num) {
                this.ref_block_num = ref_block_num;
            }

            public List<OutputBean> getOutput() {
                if (output == null) {
                    return new ArrayList<>();
                }
                return output;
            }

            public void setOutput(List<OutputBean> output) {
                this.output = output;
            }

            public List<String> getScope() {
                if (scope == null) {
                    return new ArrayList<>();
                }
                return scope;
            }

            public void setScope(List<String> scope) {
                this.scope = scope;
            }

            public List<MessagesBean> getMessages() {
                if (messages == null) {
                    return new ArrayList<>();
                }
                return messages;
            }

            public void setMessages(List<MessagesBean> messages) {
                this.messages = messages;
            }

            public List<String> getSignatures() {
                if (signatures == null) {
                    return new ArrayList<>();
                }
                return signatures;
            }

            public void setSignatures(List<String> signatures) {
                this.signatures = signatures;
            }

            public static class OutputBean {
                private List<?> deferred_trxs;
                private List<?> notify;

                public List<?> getDeferred_trxs() {
                    if (deferred_trxs == null) {
                        return new ArrayList<>();
                    }
                    return deferred_trxs;
                }

                public void setDeferred_trxs(List<?> deferred_trxs) {
                    this.deferred_trxs = deferred_trxs;
                }

                public List<?> getNotify() {
                    if (notify == null) {
                        return new ArrayList<>();
                    }
                    return notify;
                }

                public void setNotify(List<?> notify) {
                    this.notify = notify;
                }
            }

            public static class MessagesBean {
                /**
                 * authorization : [{"permission":"active","account":"inita"}]
                 * hex_data : 000000000093dd74000000800c11beb1010000000103e63fcce8e0374bf5a0d376a37ab6d3eca461b2fb14e4f7942acff19f90221e81010000010000000102ebb3f50ec826ed2079097eb2ecac235d101ef3245dc5090fe9bcc403c0364c67010000010000000001000000000093dd7400000000a8ed32320100010000000000000004454f5300000000
                 * code : eos
                 * data : {"owner":{"keys":[{"weight":1,"key":"EOS8adwKXtTvPLC5qEJ84GWHS2YQBmJPNTER2rb7fZjmVVqV9Wp1R"}],"threshold":1,"accounts":[]},"creator":"inita","name":"qaz1234","active":{"keys":[{"weight":1,"key":"EOS6gJ4yZsmoaxWvXWG9o4a5JS4unu99JCUqetLZLPidpp6XwvKUn"}],"threshold":1,"accounts":[]},"deposit":"0.0001 EOS","recovery":{"keys":[],"threshold":1,"accounts":[{"weight":1,"permission":{"permission":"active","account":"inita"}}]}}
                 * type : newaccount
                 */

                private String hex_data;
                private String code;
                private DataBean data;
                private String type;
                private List<AuthorizationBean> authorization;

                public String getHex_data() {
                    return hex_data == null ? "" : hex_data;
                }

                public void setHex_data(String hex_data) {
                    this.hex_data = hex_data;
                }

                public String getCode() {
                    return code == null ? "" : code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public DataBean getData() {
                    return data;
                }

                public void setData(DataBean data) {
                    this.data = data;
                }

                public String getType() {
                    return type == null ? "" : type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<AuthorizationBean> getAuthorization() {
                    if (authorization == null) {
                        return new ArrayList<>();
                    }
                    return authorization;
                }

                public void setAuthorization(List<AuthorizationBean> authorization) {
                    this.authorization = authorization;
                }

                public static class DataBean {
                    /**
                     * owner : {"keys":[{"weight":1,"key":"EOS8adwKXtTvPLC5qEJ84GWHS2YQBmJPNTER2rb7fZjmVVqV9Wp1R"}],"threshold":1,"accounts":[]}
                     * creator : inita
                     * name : qaz1234
                     * active : {"keys":[{"weight":1,"key":"EOS6gJ4yZsmoaxWvXWG9o4a5JS4unu99JCUqetLZLPidpp6XwvKUn"}],"threshold":1,"accounts":[]}
                     * deposit : 0.0001 EOS
                     * recovery : {"keys":[],"threshold":1,"accounts":[{"weight":1,"permission":{"permission":"active","account":"inita"}}]}
                     */

                    private OwnerBean owner;
                    private String creator;
                    private String name;
                    private ActiveBean active;
                    private String deposit;
                    private RecoveryBean recovery;

                    public OwnerBean getOwner() {
                        return owner;
                    }

                    public void setOwner(OwnerBean owner) {
                        this.owner = owner;
                    }

                    public String getCreator() {
                        return creator == null ? "" : creator;
                    }

                    public void setCreator(String creator) {
                        this.creator = creator;
                    }

                    public String getName() {
                        return name == null ? "" : name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public ActiveBean getActive() {
                        return active;
                    }

                    public void setActive(ActiveBean active) {
                        this.active = active;
                    }

                    public String getDeposit() {
                        return deposit == null ? "" : deposit;
                    }

                    public void setDeposit(String deposit) {
                        this.deposit = deposit;
                    }

                    public RecoveryBean getRecovery() {
                        return recovery;
                    }

                    public void setRecovery(RecoveryBean recovery) {
                        this.recovery = recovery;
                    }

                    public static class OwnerBean {
                        /**
                         * keys : [{"weight":1,"key":"EOS8adwKXtTvPLC5qEJ84GWHS2YQBmJPNTER2rb7fZjmVVqV9Wp1R"}]
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
                             * key : EOS8adwKXtTvPLC5qEJ84GWHS2YQBmJPNTER2rb7fZjmVVqV9Wp1R
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

                    public static class ActiveBean {
                        /**
                         * keys : [{"weight":1,"key":"EOS6gJ4yZsmoaxWvXWG9o4a5JS4unu99JCUqetLZLPidpp6XwvKUn"}]
                         * threshold : 1
                         * accounts : []
                         */

                        private int threshold;
                        private List<KeysBeanX> keys;
                        private List<?> accounts;

                        public int getThreshold() {
                            return threshold;
                        }

                        public void setThreshold(int threshold) {
                            this.threshold = threshold;
                        }

                        public List<KeysBeanX> getKeys() {
                            if (keys == null) {
                                return new ArrayList<>();
                            }
                            return keys;
                        }

                        public void setKeys(List<KeysBeanX> keys) {
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

                        public static class KeysBeanX {
                            /**
                             * weight : 1
                             * key : EOS6gJ4yZsmoaxWvXWG9o4a5JS4unu99JCUqetLZLPidpp6XwvKUn
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

                    public static class RecoveryBean {
                        /**
                         * keys : []
                         * threshold : 1
                         * accounts : [{"weight":1,"permission":{"permission":"active","account":"inita"}}]
                         */

                        private int threshold;
                        private List<?> keys;
                        private List<AccountsBean> accounts;

                        public int getThreshold() {
                            return threshold;
                        }

                        public void setThreshold(int threshold) {
                            this.threshold = threshold;
                        }

                        public List<?> getKeys() {
                            if (keys == null) {
                                return new ArrayList<>();
                            }
                            return keys;
                        }

                        public void setKeys(List<?> keys) {
                            this.keys = keys;
                        }

                        public List<AccountsBean> getAccounts() {
                            if (accounts == null) {
                                return new ArrayList<>();
                            }
                            return accounts;
                        }

                        public void setAccounts(List<AccountsBean> accounts) {
                            this.accounts = accounts;
                        }

                        public static class AccountsBean {
                            /**
                             * weight : 1
                             * permission : {"permission":"active","account":"inita"}
                             */

                            private int weight;
                            private PermissionBean permission;

                            public int getWeight() {
                                return weight;
                            }

                            public void setWeight(int weight) {
                                this.weight = weight;
                            }

                            public PermissionBean getPermission() {
                                return permission;
                            }

                            public void setPermission(PermissionBean permission) {
                                this.permission = permission;
                            }

                            public static class PermissionBean {
                                /**
                                 * permission : active
                                 * account : inita
                                 */

                                private String permission;
                                private String account;

                                public String getPermission() {
                                    return permission == null ? "" : permission;
                                }

                                public void setPermission(String permission) {
                                    this.permission = permission;
                                }

                                public String getAccount() {
                                    return account == null ? "" : account;
                                }

                                public void setAccount(String account) {
                                    this.account = account;
                                }
                            }
                        }
                    }
                }

                public static class AuthorizationBean {
                    /**
                     * permission : active
                     * account : inita
                     */

                    private String permission;
                    private String account;

                    public String getPermission() {
                        return permission == null ? "" : permission;
                    }

                    public void setPermission(String permission) {
                        this.permission = permission;
                    }

                    public String getAccount() {
                        return account == null ? "" : account;
                    }

                    public void setAccount(String account) {
                        this.account = account;
                    }
                }
            }
        }
    }
}

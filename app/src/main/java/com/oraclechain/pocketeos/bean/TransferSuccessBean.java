package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/4/9.
 */

public class TransferSuccessBean {

    /**
     * code : 0
     * message : ok
     * data : {"transaction_id":"8fbcb99c89d60956029907ce91a8def391da9ae3208d034b5c38f099b638bd5c","processed":{"output":[{"deferred_trxs":[],"notify":[{"output":{"deferred_trxs":[],"notify":[]},"name":"qett5511"},{"output":{"deferred_trxs":[],"notify":[]},"name":"qwerasdfzxcv"}]}],"ref_block_prefix":4145737620,"scope":["qett5511","qwerasdfzxcv"],"messages":[{"authorization":[{"permission":"active","account":"qwerasdfzxcv"}],"hex_data":"b051ff2b617315b7000000219492b3b2102700000000000000","code":"eos","data":{"amount":10000,"memo":"","from":"qwerasdfzxcv","to":"qett5511"},"type":"transfer"}],"expiration":"2018-04-09T07:59:57","ref_block_num":46963,"signatures":["1f4835f67723dca7870c2bf191d6321cc32e59c1ad7dc2a72ee94f59acf21062c3296cce1bb2e8920159f56fb3abb8039ce17630a9368be9042bba673388a13d47"]}}
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
        return message;
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
         * transaction_id : 8fbcb99c89d60956029907ce91a8def391da9ae3208d034b5c38f099b638bd5c
         * processed : {"output":[{"deferred_trxs":[],"notify":[{"output":{"deferred_trxs":[],"notify":[]},"name":"qett5511"},{"output":{"deferred_trxs":[],"notify":[]},"name":"qwerasdfzxcv"}]}],"ref_block_prefix":4145737620,"scope":["qett5511","qwerasdfzxcv"],"messages":[{"authorization":[{"permission":"active","account":"qwerasdfzxcv"}],"hex_data":"b051ff2b617315b7000000219492b3b2102700000000000000","code":"eos","data":{"amount":10000,"memo":"","from":"qwerasdfzxcv","to":"qett5511"},"type":"transfer"}],"expiration":"2018-04-09T07:59:57","ref_block_num":46963,"signatures":["1f4835f67723dca7870c2bf191d6321cc32e59c1ad7dc2a72ee94f59acf21062c3296cce1bb2e8920159f56fb3abb8039ce17630a9368be9042bba673388a13d47"]}
         */

        private String transaction_id;
        private ProcessedBean processed;

        public String getTransaction_id() {
            return transaction_id;
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
             * output : [{"deferred_trxs":[],"notify":[{"output":{"deferred_trxs":[],"notify":[]},"name":"qett5511"},{"output":{"deferred_trxs":[],"notify":[]},"name":"qwerasdfzxcv"}]}]
             * ref_block_prefix : 4145737620
             * scope : ["qett5511","qwerasdfzxcv"]
             * messages : [{"authorization":[{"permission":"active","account":"qwerasdfzxcv"}],"hex_data":"b051ff2b617315b7000000219492b3b2102700000000000000","code":"eos","data":{"amount":10000,"memo":"","from":"qwerasdfzxcv","to":"qett5511"},"type":"transfer"}]
             * expiration : 2018-04-09T07:59:57
             * ref_block_num : 46963
             * signatures : ["1f4835f67723dca7870c2bf191d6321cc32e59c1ad7dc2a72ee94f59acf21062c3296cce1bb2e8920159f56fb3abb8039ce17630a9368be9042bba673388a13d47"]
             */

            private long ref_block_prefix;
            private String expiration;
            private int ref_block_num;
            private List<OutputBeanX> output;
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
                return expiration;
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

            public List<OutputBeanX> getOutput() {
                return output;
            }

            public void setOutput(List<OutputBeanX> output) {
                this.output = output;
            }

            public List<String> getScope() {
                return scope;
            }

            public void setScope(List<String> scope) {
                this.scope = scope;
            }

            public List<MessagesBean> getMessages() {
                return messages;
            }

            public void setMessages(List<MessagesBean> messages) {
                this.messages = messages;
            }

            public List<String> getSignatures() {
                return signatures;
            }

            public void setSignatures(List<String> signatures) {
                this.signatures = signatures;
            }

            public static class OutputBeanX {
                private List<?> deferred_trxs;
                private List<NotifyBean> notify;

                public List<?> getDeferred_trxs() {
                    return deferred_trxs;
                }

                public void setDeferred_trxs(List<?> deferred_trxs) {
                    this.deferred_trxs = deferred_trxs;
                }

                public List<NotifyBean> getNotify() {
                    return notify;
                }

                public void setNotify(List<NotifyBean> notify) {
                    this.notify = notify;
                }

                public static class NotifyBean {
                    /**
                     * output : {"deferred_trxs":[],"notify":[]}
                     * name : qett5511
                     */

                    private OutputBean output;
                    private String name;

                    public OutputBean getOutput() {
                        return output;
                    }

                    public void setOutput(OutputBean output) {
                        this.output = output;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public static class OutputBean {
                        private List<?> deferred_trxs;
                        private List<?> notify;

                        public List<?> getDeferred_trxs() {
                            return deferred_trxs;
                        }

                        public void setDeferred_trxs(List<?> deferred_trxs) {
                            this.deferred_trxs = deferred_trxs;
                        }

                        public List<?> getNotify() {
                            return notify;
                        }

                        public void setNotify(List<?> notify) {
                            this.notify = notify;
                        }
                    }
                }
            }

            public static class MessagesBean {
                /**
                 * authorization : [{"permission":"active","account":"qwerasdfzxcv"}]
                 * hex_data : b051ff2b617315b7000000219492b3b2102700000000000000
                 * code : eos
                 * data : {"amount":10000,"memo":"","from":"qwerasdfzxcv","to":"qett5511"}
                 * type : transfer
                 */

                private String hex_data;
                private String code;
                private DataBean data;
                private String type;
                private List<AuthorizationBean> authorization;

                public String getHex_data() {
                    return hex_data;
                }

                public void setHex_data(String hex_data) {
                    this.hex_data = hex_data;
                }

                public String getCode() {
                    return code;
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
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<AuthorizationBean> getAuthorization() {
                    return authorization;
                }

                public void setAuthorization(List<AuthorizationBean> authorization) {
                    this.authorization = authorization;
                }

                public static class DataBean {
                    /**
                     * amount : 10000
                     * memo :
                     * from : qwerasdfzxcv
                     * to : qett5511
                     */

                    private int amount;
                    private String memo;
                    private String from;
                    private String to;

                    public int getAmount() {
                        return amount;
                    }

                    public void setAmount(int amount) {
                        this.amount = amount;
                    }

                    public String getMemo() {
                        return memo;
                    }

                    public void setMemo(String memo) {
                        this.memo = memo;
                    }

                    public String getFrom() {
                        return from;
                    }

                    public void setFrom(String from) {
                        this.from = from;
                    }

                    public String getTo() {
                        return to;
                    }

                    public void setTo(String to) {
                        this.to = to;
                    }
                }

                public static class AuthorizationBean {
                    /**
                     * permission : active
                     * account : qwerasdfzxcv
                     */

                    private String permission;
                    private String account;

                    public String getPermission() {
                        return permission;
                    }

                    public void setPermission(String permission) {
                        this.permission = permission;
                    }

                    public String getAccount() {
                        return account;
                    }

                    public void setAccount(String account) {
                        this.account = account;
                    }
                }
            }
        }
    }
}

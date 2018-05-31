package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/2/5.
 */

public class TransferHistoryBean {


    /**
     * code : 0
     * message : ok
     * data : {"transactions":[{"transaction_id":"72a5ca796487458f0187951f1f6b26fb8128bbfb2fffa630ee953042e98a9a67","seq_num":0,"transaction":{"packed_trx":"d0deeb5a00008f18dfecd799000000000100a6823403ea3055000000572d3ccdcd01000000428869908e00000000a8ed32323a000000428869908e00a6823403ea3055102700000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","packed_context_free_data":"00","compression":"none","signatures":["EOSKcyq1uQgfSmgP5THRyHnecCMZQj2HRFtRejEfwAvJ83zetcYRozuv2NqApQG9uY6tUXnkoZSttfsMXaDZ4AYP4UC868DkP"],"transaction":{"ref_block_prefix":2581064927,"context_free_actions":[],"expiration":"2018-05-04T04:17:20","max_net_usage_words":0,"max_kcpu_usage":0,"delay_sec":0,"region":0,"ref_block_num":6287,"actions":[{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea3055102700000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"1.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"}]},"context_free_data":[]}},{"transaction_id":"8db62d6094e8ce997464f1c4528c9c7273f70a9621276c0f064f0dc4d6f491bd","seq_num":1,"transaction":{"packed_trx":"23d9eb5a0000350d077e2223000000000100a6823403ea3055000000572d3ccdcd010000000000ea305500000000a8ed3232250000000000ea3055000000428869908e40420f000000000004454f53000000000468616861","packed_context_free_data":"00","compression":"none","signatures":["EOSKbMiBY4tmLrgXjnUrWoQi8QpNWnppPnoaygfXmhXm2yJAgAn71BicBdfGvnBZBqAVQutdL5kwmHBppeindP1sC1pMXUpCb"],"transaction":{"ref_block_prefix":589463047,"context_free_actions":[],"expiration":"2018-05-04T03:53:07","max_net_usage_words":0,"max_kcpu_usage":0,"delay_sec":0,"region":0,"ref_block_num":3381,"actions":[{"authorization":[{"actor":"eosio","permission":"active"}],"hex_data":"0000000000ea3055000000428869908e40420f000000000004454f53000000000468616861","data":{"quantity":"100.0000 EOS","memo":"haha","from":"eosio","to":"lucan222"},"name":"transfer","account":"eosio.token"}]},"context_free_data":[]}},{"transaction_id":"12ea518efb4ec12f605b75d62c11da7ccd11ea86eabcba9b452e573bf9b2a5ce","seq_num":2,"transaction":{"packed_trx":"1bd9eb5a0000260d664f830d000000000100a6823403ea3055000000572d3ccdcd010000000000ea305500000000a8ed3232250000000000ea3055000000428869908e40420f000000000004454f53000000000468616861","packed_context_free_data":"00","compression":"none","signatures":["EOSK9aMUWLbSE8QPegfUV85QSBs5HjLkf3J7reD9W7ZQZVjqmtoydwnm5GaGBReT3bdHeD1qM8qdoyYBaXHQXaNWcqAUPq57t"],"transaction":{"ref_block_prefix":226709350,"context_free_actions":[],"expiration":"2018-05-04T03:52:59","max_net_usage_words":0,"max_kcpu_usage":0,"delay_sec":0,"region":0,"ref_block_num":3366,"actions":[{"authorization":[{"actor":"eosio","permission":"active"}],"hex_data":"0000000000ea3055000000428869908e40420f000000000004454f53000000000468616861","data":{"quantity":"100.0000 EOS","memo":"haha","from":"eosio","to":"lucan222"},"name":"transfer","account":"eosio.token"}]},"context_free_data":[]}}]}
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
        private List<TransactionsBean> transactions;

        public List<TransactionsBean> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<TransactionsBean> transactions) {
            this.transactions = transactions;
        }

        public static class TransactionsBean {
            /**
             * transaction_id : 72a5ca796487458f0187951f1f6b26fb8128bbfb2fffa630ee953042e98a9a67
             * seq_num : 0
             * transaction : {"packed_trx":"d0deeb5a00008f18dfecd799000000000100a6823403ea3055000000572d3ccdcd01000000428869908e00000000a8ed32323a000000428869908e00a6823403ea3055102700000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","packed_context_free_data":"00","compression":"none","signatures":["EOSKcyq1uQgfSmgP5THRyHnecCMZQj2HRFtRejEfwAvJ83zetcYRozuv2NqApQG9uY6tUXnkoZSttfsMXaDZ4AYP4UC868DkP"],"transaction":{"ref_block_prefix":2581064927,"context_free_actions":[],"expiration":"2018-05-04T04:17:20","max_net_usage_words":0,"max_kcpu_usage":0,"delay_sec":0,"region":0,"ref_block_num":6287,"actions":[{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea3055102700000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"1.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"}]},"context_free_data":[]}
             */

            private String transaction_id;
            private int seq_num;
            private TransactionBeanX transaction;

            public String getTransaction_id() {
                return transaction_id;
            }

            public void setTransaction_id(String transaction_id) {
                this.transaction_id = transaction_id;
            }

            public int getSeq_num() {
                return seq_num;
            }

            public void setSeq_num(int seq_num) {
                this.seq_num = seq_num;
            }

            public TransactionBeanX getTransaction() {
                return transaction;
            }

            public void setTransaction(TransactionBeanX transaction) {
                this.transaction = transaction;
            }

            public static class TransactionBeanX {
                /**
                 * packed_trx : d0deeb5a00008f18dfecd799000000000100a6823403ea3055000000572d3ccdcd01000000428869908e00000000a8ed32323a000000428869908e00a6823403ea3055102700000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9
                 * packed_context_free_data : 00
                 * compression : none
                 * signatures : ["EOSKcyq1uQgfSmgP5THRyHnecCMZQj2HRFtRejEfwAvJ83zetcYRozuv2NqApQG9uY6tUXnkoZSttfsMXaDZ4AYP4UC868DkP"]
                 * transaction : {"ref_block_prefix":2581064927,"context_free_actions":[],"expiration":"2018-05-04T04:17:20","max_net_usage_words":0,"max_kcpu_usage":0,"delay_sec":0,"region":0,"ref_block_num":6287,"actions":[{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea3055102700000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"1.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"}]}
                 * context_free_data : []
                 */

                private String packed_trx;
                private String packed_context_free_data;
                private String compression;
                private TransactionBean transaction;
                private List<String> signatures;
                private List<?> context_free_data;

                public String getPacked_trx() {
                    return packed_trx;
                }

                public void setPacked_trx(String packed_trx) {
                    this.packed_trx = packed_trx;
                }

                public String getPacked_context_free_data() {
                    return packed_context_free_data;
                }

                public void setPacked_context_free_data(String packed_context_free_data) {
                    this.packed_context_free_data = packed_context_free_data;
                }

                public String getCompression() {
                    return compression;
                }

                public void setCompression(String compression) {
                    this.compression = compression;
                }

                public TransactionBean getTransaction() {
                    return transaction;
                }

                public void setTransaction(TransactionBean transaction) {
                    this.transaction = transaction;
                }

                public List<String> getSignatures() {
                    return signatures;
                }

                public void setSignatures(List<String> signatures) {
                    this.signatures = signatures;
                }

                public List<?> getContext_free_data() {
                    return context_free_data;
                }

                public void setContext_free_data(List<?> context_free_data) {
                    this.context_free_data = context_free_data;
                }

                public static class TransactionBean {
                    /**
                     * ref_block_prefix : 2581064927
                     * context_free_actions : []
                     * expiration : 2018-05-04T04:17:20
                     * max_net_usage_words : 0
                     * max_kcpu_usage : 0
                     * delay_sec : 0
                     * region : 0
                     * ref_block_num : 6287
                     * actions : [{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea3055102700000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"1.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"}]
                     */

                    private long ref_block_prefix;
                    private String expiration;
                    private int max_net_usage_words;
                    private int max_kcpu_usage;
                    private int delay_sec;
                    private int region;
                    private int ref_block_num;
                    private List<?> context_free_actions;
                    private List<ActionsBean> actions;

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

                    public int getMax_net_usage_words() {
                        return max_net_usage_words;
                    }

                    public void setMax_net_usage_words(int max_net_usage_words) {
                        this.max_net_usage_words = max_net_usage_words;
                    }

                    public int getMax_kcpu_usage() {
                        return max_kcpu_usage;
                    }

                    public void setMax_kcpu_usage(int max_kcpu_usage) {
                        this.max_kcpu_usage = max_kcpu_usage;
                    }

                    public int getDelay_sec() {
                        return delay_sec;
                    }

                    public void setDelay_sec(int delay_sec) {
                        this.delay_sec = delay_sec;
                    }

                    public int getRegion() {
                        return region;
                    }

                    public void setRegion(int region) {
                        this.region = region;
                    }

                    public int getRef_block_num() {
                        return ref_block_num;
                    }

                    public void setRef_block_num(int ref_block_num) {
                        this.ref_block_num = ref_block_num;
                    }

                    public List<?> getContext_free_actions() {
                        return context_free_actions;
                    }

                    public void setContext_free_actions(List<?> context_free_actions) {
                        this.context_free_actions = context_free_actions;
                    }

                    public List<ActionsBean> getActions() {
                        return actions;
                    }

                    public void setActions(List<ActionsBean> actions) {
                        this.actions = actions;
                    }

                    public static class ActionsBean {
                        /**
                         * authorization : [{"actor":"lucan222","permission":"active"}]
                         * hex_data : 000000428869908e00a6823403ea3055102700000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9
                         * data : {"quantity":"1.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"}
                         * name : transfer
                         * account : eosio.token
                         */

                        private String hex_data;
                        private DataBean data;
                        private String name;
                        private String account;
                        private List<AuthorizationBean> authorization;

                        public String getHex_data() {
                            return hex_data;
                        }

                        public void setHex_data(String hex_data) {
                            this.hex_data = hex_data;
                        }

                        public DataBean getData() {
                            return data;
                        }

                        public void setData(DataBean data) {
                            this.data = data;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getAccount() {
                            return account;
                        }

                        public void setAccount(String account) {
                            this.account = account;
                        }

                        public List<AuthorizationBean> getAuthorization() {
                            return authorization;
                        }

                        public void setAuthorization(List<AuthorizationBean> authorization) {
                            this.authorization = authorization;
                        }

                        public static class DataBean {
                            /**
                             * quantity : 1.0000 EOS
                             * memo : 恭喜发财,大吉大利
                             * from : lucan222
                             * to : eosio.token
                             */

                            private String quantity;
                            private String memo;
                            private String from;
                            private String to;

                            public String getQuantity() {
                                return quantity;
                            }

                            public void setQuantity(String quantity) {
                                this.quantity = quantity;
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
                             * actor : lucan222
                             * permission : active
                             */

                            private String actor;
                            private String permission;

                            public String getActor() {
                                return actor;
                            }

                            public void setActor(String actor) {
                                this.actor = actor;
                            }

                            public String getPermission() {
                                return permission;
                            }

                            public void setPermission(String permission) {
                                this.permission = permission;
                            }
                        }
                    }
                }
            }
        }
    }
}

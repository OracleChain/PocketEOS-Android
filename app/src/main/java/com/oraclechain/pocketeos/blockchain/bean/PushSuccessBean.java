package com.oraclechain.pocketeos.blockchain.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/4/26.
 */

public class PushSuccessBean {

    /**
     * code : 0
     * message : ok
     * data : {"transaction_id":"989af986dae459ad11574f7620bf246fafeb7fe9f9979545c3dc9cfe4aaff43e","processed":{"packed_trx_digest":"8dcc39cbb942184ea5a52cf4dd5a384d01975baf62624710a534e56310e8f661","_profiling_us":663,"deferred_transaction_requests":[],"read_locks":[{"scope":"........ehbo5","account":"eosio.token"}],"region_id":0,"action_traces":[{"context_free":false,"console":"transfer","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"eosio.token","_profiling_us":600,"cpu_usage":12017,"data_access":[{"sequence":43,"code":"eosio.token","scope":"eosio.token","type":"write"},{"sequence":5,"code":"eosio.token","scope":"lucan222","type":"write"},{"sequence":2,"code":"eosio.token","scope":"........ehbo5","type":"read"}]},{"context_free":false,"console":"","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"lucan222","_profiling_us":2,"cpu_usage":0,"data_access":[]}],"net_usage":280,"cycle_index":1,"kcpu_usage":112,"net_usage_words":35,"id":"989af986dae459ad11574f7620bf246fafeb7fe9f9979545c3dc9cfe4aaff43e","_setup_profiling_us":143,"write_locks":[{"scope":"eosio.token","account":"eosio.token"},{"scope":"lucan222","account":"eosio.token"}],"cpu_usage":114688,"shard_index":0,"status":"executed"}}
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
         * transaction_id : 989af986dae459ad11574f7620bf246fafeb7fe9f9979545c3dc9cfe4aaff43e
         * processed : {"packed_trx_digest":"8dcc39cbb942184ea5a52cf4dd5a384d01975baf62624710a534e56310e8f661","_profiling_us":663,"deferred_transaction_requests":[],"read_locks":[{"scope":"........ehbo5","account":"eosio.token"}],"region_id":0,"action_traces":[{"context_free":false,"console":"transfer","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"eosio.token","_profiling_us":600,"cpu_usage":12017,"data_access":[{"sequence":43,"code":"eosio.token","scope":"eosio.token","type":"write"},{"sequence":5,"code":"eosio.token","scope":"lucan222","type":"write"},{"sequence":2,"code":"eosio.token","scope":"........ehbo5","type":"read"}]},{"context_free":false,"console":"","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"lucan222","_profiling_us":2,"cpu_usage":0,"data_access":[]}],"net_usage":280,"cycle_index":1,"kcpu_usage":112,"net_usage_words":35,"id":"989af986dae459ad11574f7620bf246fafeb7fe9f9979545c3dc9cfe4aaff43e","_setup_profiling_us":143,"write_locks":[{"scope":"eosio.token","account":"eosio.token"},{"scope":"lucan222","account":"eosio.token"}],"cpu_usage":114688,"shard_index":0,"status":"executed"}
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
             * packed_trx_digest : 8dcc39cbb942184ea5a52cf4dd5a384d01975baf62624710a534e56310e8f661
             * _profiling_us : 663
             * deferred_transaction_requests : []
             * read_locks : [{"scope":"........ehbo5","account":"eosio.token"}]
             * region_id : 0
             * action_traces : [{"context_free":false,"console":"transfer","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"eosio.token","_profiling_us":600,"cpu_usage":12017,"data_access":[{"sequence":43,"code":"eosio.token","scope":"eosio.token","type":"write"},{"sequence":5,"code":"eosio.token","scope":"lucan222","type":"write"},{"sequence":2,"code":"eosio.token","scope":"........ehbo5","type":"read"}]},{"context_free":false,"console":"","act":{"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"},"receiver":"lucan222","_profiling_us":2,"cpu_usage":0,"data_access":[]}]
             * net_usage : 280
             * cycle_index : 1
             * kcpu_usage : 112
             * net_usage_words : 35
             * id : 989af986dae459ad11574f7620bf246fafeb7fe9f9979545c3dc9cfe4aaff43e
             * _setup_profiling_us : 143
             * write_locks : [{"scope":"eosio.token","account":"eosio.token"},{"scope":"lucan222","account":"eosio.token"}]
             * cpu_usage : 114688
             * shard_index : 0
             * status : executed
             */

            private String packed_trx_digest;
            private int _profiling_us;
            private int region_id;
            private int net_usage;
            private int cycle_index;
            private int kcpu_usage;
            private int net_usage_words;
            private String id;
            private int _setup_profiling_us;
            private int cpu_usage;
            private int shard_index;
            private String status;
            private List<?> deferred_transaction_requests;
            private List<ReadLocksBean> read_locks;
            private List<ActionTracesBean> action_traces;
            private List<WriteLocksBean> write_locks;

            public String getPacked_trx_digest() {
                return packed_trx_digest;
            }

            public void setPacked_trx_digest(String packed_trx_digest) {
                this.packed_trx_digest = packed_trx_digest;
            }

            public int get_profiling_us() {
                return _profiling_us;
            }

            public void set_profiling_us(int _profiling_us) {
                this._profiling_us = _profiling_us;
            }

            public int getRegion_id() {
                return region_id;
            }

            public void setRegion_id(int region_id) {
                this.region_id = region_id;
            }

            public int getNet_usage() {
                return net_usage;
            }

            public void setNet_usage(int net_usage) {
                this.net_usage = net_usage;
            }

            public int getCycle_index() {
                return cycle_index;
            }

            public void setCycle_index(int cycle_index) {
                this.cycle_index = cycle_index;
            }

            public int getKcpu_usage() {
                return kcpu_usage;
            }

            public void setKcpu_usage(int kcpu_usage) {
                this.kcpu_usage = kcpu_usage;
            }

            public int getNet_usage_words() {
                return net_usage_words;
            }

            public void setNet_usage_words(int net_usage_words) {
                this.net_usage_words = net_usage_words;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int get_setup_profiling_us() {
                return _setup_profiling_us;
            }

            public void set_setup_profiling_us(int _setup_profiling_us) {
                this._setup_profiling_us = _setup_profiling_us;
            }

            public int getCpu_usage() {
                return cpu_usage;
            }

            public void setCpu_usage(int cpu_usage) {
                this.cpu_usage = cpu_usage;
            }

            public int getShard_index() {
                return shard_index;
            }

            public void setShard_index(int shard_index) {
                this.shard_index = shard_index;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<?> getDeferred_transaction_requests() {
                return deferred_transaction_requests;
            }

            public void setDeferred_transaction_requests(List<?> deferred_transaction_requests) {
                this.deferred_transaction_requests = deferred_transaction_requests;
            }

            public List<ReadLocksBean> getRead_locks() {
                return read_locks;
            }

            public void setRead_locks(List<ReadLocksBean> read_locks) {
                this.read_locks = read_locks;
            }

            public List<ActionTracesBean> getAction_traces() {
                return action_traces;
            }

            public void setAction_traces(List<ActionTracesBean> action_traces) {
                this.action_traces = action_traces;
            }

            public List<WriteLocksBean> getWrite_locks() {
                return write_locks;
            }

            public void setWrite_locks(List<WriteLocksBean> write_locks) {
                this.write_locks = write_locks;
            }

            public static class ReadLocksBean {
                /**
                 * scope : ........ehbo5
                 * account : eosio.token
                 */

                private String scope;
                private String account;

                public String getScope() {
                    return scope;
                }

                public void setScope(String scope) {
                    this.scope = scope;
                }

                public String getAccount() {
                    return account;
                }

                public void setAccount(String account) {
                    this.account = account;
                }
            }

            public static class ActionTracesBean {
                /**
                 * context_free : false
                 * console : transfer
                 * act : {"authorization":[{"actor":"lucan222","permission":"active"}],"hex_data":"000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9","data":{"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"},"name":"transfer","account":"eosio.token"}
                 * receiver : eosio.token
                 * _profiling_us : 600
                 * cpu_usage : 12017
                 * data_access : [{"sequence":43,"code":"eosio.token","scope":"eosio.token","type":"write"},{"sequence":5,"code":"eosio.token","scope":"lucan222","type":"write"},{"sequence":2,"code":"eosio.token","scope":"........ehbo5","type":"read"}]
                 */

                private boolean context_free;
                private String console;
                private ActBean act;
                private String receiver;
                private int _profiling_us;
                private int cpu_usage;
                private List<DataAccessBean> data_access;

                public boolean isContext_free() {
                    return context_free;
                }

                public void setContext_free(boolean context_free) {
                    this.context_free = context_free;
                }

                public String getConsole() {
                    return console;
                }

                public void setConsole(String console) {
                    this.console = console;
                }

                public ActBean getAct() {
                    return act;
                }

                public void setAct(ActBean act) {
                    this.act = act;
                }

                public String getReceiver() {
                    return receiver;
                }

                public void setReceiver(String receiver) {
                    this.receiver = receiver;
                }

                public int get_profiling_us() {
                    return _profiling_us;
                }

                public void set_profiling_us(int _profiling_us) {
                    this._profiling_us = _profiling_us;
                }

                public int getCpu_usage() {
                    return cpu_usage;
                }

                public void setCpu_usage(int cpu_usage) {
                    this.cpu_usage = cpu_usage;
                }

                public List<DataAccessBean> getData_access() {
                    return data_access;
                }

                public void setData_access(List<DataAccessBean> data_access) {
                    this.data_access = data_access;
                }

                public static class ActBean {
                    /**
                     * authorization : [{"actor":"lucan222","permission":"active"}]
                     * hex_data : 000000428869908e00a6823403ea305560ea00000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9
                     * data : {"quantity":"6.0000 EOS","memo":"恭喜发财,大吉大利","from":"lucan222","to":"eosio.token"}
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
                         * quantity : 6.0000 EOS
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

                public static class DataAccessBean {
                    /**
                     * sequence : 43
                     * code : eosio.token
                     * scope : eosio.token
                     * type : write
                     */

                    private int sequence;
                    private String code;
                    private String scope;
                    private String type;

                    public int getSequence() {
                        return sequence;
                    }

                    public void setSequence(int sequence) {
                        this.sequence = sequence;
                    }

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public String getScope() {
                        return scope;
                    }

                    public void setScope(String scope) {
                        this.scope = scope;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }
            }

            public static class WriteLocksBean {
                /**
                 * scope : eosio.token
                 * account : eosio.token
                 */

                private String scope;
                private String account;

                public String getScope() {
                    return scope;
                }

                public void setScope(String scope) {
                    this.scope = scope;
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

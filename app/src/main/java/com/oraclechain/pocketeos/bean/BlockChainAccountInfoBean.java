package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/2/6.
 */

public class BlockChainAccountInfoBean {

    /**
     * code : 0
     * message : ok
     * data : {"head_block_num":1850653,"refund_request":null,"total_resources":{"owner":"oraclechain4","ram_bytes":7920,"net_weight":"0.3333 EOS","cpu_weight":"0.3333 EOS"},"head_block_time":"2018-06-21T09:12:57.500","created":"2018-06-10T13:09:52.000","ram_quota":7920,"net_limit":{"max":153653,"available":152415,"used":1238},"self_delegated_bandwidth":null,"net_weight":3333,"cpu_weight":3333,"privileged":false,"ram_usage":3310,"permissions":[{"parent":"owner","required_auth":{"waits":[],"keys":[{"weight":1,"key":"EOS67pa5ex64cECp2esLp6km78QfZDyEY8mAPieBHkD7JvfxiFzTG"}],"threshold":1,"accounts":[]},"perm_name":"active"},{"parent":"","required_auth":{"waits":[],"keys":[{"weight":1,"key":"EOS7KX6pa4g4bchgvW1824CuuBYWD5idLkCgDia7ZuTpx7rGEkey8"}],"threshold":1,"accounts":[]},"perm_name":"owner"}],"account_name":"oraclechain4","last_code_update":"1970-01-01T00:00:00.000","cpu_limit":{"max":29424,"available":19518,"used":9906},"voter_info":{"owner":"oraclechain4","proxy":"","last_vote_weight":"0.00000000000000000","proxied_vote_weight":"0.00000000000000000","staked":0,"is_proxy":0,"producers":["oraclegogogo"]}}
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

    public static class DataBean implements Parcelable {
        /**
         * head_block_num : 1850653
         * refund_request : null
         * total_resources : {"owner":"oraclechain4","ram_bytes":7920,"net_weight":"0.3333 EOS","cpu_weight":"0.3333 EOS"}
         * head_block_time : 2018-06-21T09:12:57.500
         * created : 2018-06-10T13:09:52.000
         * ram_quota : 7920
         * net_limit : {"max":153653,"available":152415,"used":1238}
         * self_delegated_bandwidth : null
         * net_weight : 3333
         * cpu_weight : 3333
         * privileged : false
         * ram_usage : 3310
         * permissions : [{"parent":"owner","required_auth":{"waits":[],"keys":[{"weight":1,"key":"EOS67pa5ex64cECp2esLp6km78QfZDyEY8mAPieBHkD7JvfxiFzTG"}],"threshold":1,"accounts":[]},"perm_name":"active"},{"parent":"","required_auth":{"waits":[],"keys":[{"weight":1,"key":"EOS7KX6pa4g4bchgvW1824CuuBYWD5idLkCgDia7ZuTpx7rGEkey8"}],"threshold":1,"accounts":[]},"perm_name":"owner"}]
         * account_name : oraclechain4
         * last_code_update : 1970-01-01T00:00:00.000
         * cpu_limit : {"max":29424,"available":19518,"used":9906}
         * voter_info : {"owner":"oraclechain4","proxy":"","last_vote_weight":"0.00000000000000000","proxied_vote_weight":"0.00000000000000000","staked":0,"is_proxy":0,"producers":["oraclegogogo"]}
         */

        private String head_block_num;
        private Object refund_request;
        private TotalResourcesBean total_resources;
        private String head_block_time;
        private String created;
        private String ram_quota;
        private NetLimitBean net_limit;
        private Object self_delegated_bandwidth;
        private String net_weight;
        private String cpu_weight;
        private boolean privileged;
        private String ram_usage;
        private String account_name;
        private String last_code_update;
        private CpuLimitBean cpu_limit;
        private VoterInfoBean voter_info;
        private List<PermissionsBean> permissions;

        public String getHead_block_num() {
            return head_block_num;
        }

        public void setHead_block_num(String head_block_num) {
            this.head_block_num = head_block_num;
        }

        public Object getRefund_request() {
            return refund_request;
        }

        public void setRefund_request(Object refund_request) {
            this.refund_request = refund_request;
        }

        public TotalResourcesBean getTotal_resources() {
            return total_resources;
        }

        public void setTotal_resources(TotalResourcesBean total_resources) {
            this.total_resources = total_resources;
        }

        public String getHead_block_time() {
            return head_block_time;
        }

        public void setHead_block_time(String head_block_time) {
            this.head_block_time = head_block_time;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getRam_quota() {
            return ram_quota;
        }

        public void setRam_quota(String ram_quota) {
            this.ram_quota = ram_quota;
        }

        public NetLimitBean getNet_limit() {
            return net_limit;
        }

        public void setNet_limit(NetLimitBean net_limit) {
            this.net_limit = net_limit;
        }

        public Object getSelf_delegated_bandwidth() {
            return self_delegated_bandwidth;
        }

        public void setSelf_delegated_bandwidth(Object self_delegated_bandwidth) {
            this.self_delegated_bandwidth = self_delegated_bandwidth;
        }

        public String getNet_weight() {
            return net_weight;
        }

        public void setNet_weight(String net_weight) {
            this.net_weight = net_weight;
        }

        public String getCpu_weight() {
            return cpu_weight;
        }

        public void setCpu_weight(String cpu_weight) {
            this.cpu_weight = cpu_weight;
        }

        public boolean isPrivileged() {
            return privileged;
        }

        public void setPrivileged(boolean privileged) {
            this.privileged = privileged;
        }

        public String getRam_usage() {
            return ram_usage;
        }

        public void setRam_usage(String ram_usage) {
            this.ram_usage = ram_usage;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getLast_code_update() {
            return last_code_update;
        }

        public void setLast_code_update(String last_code_update) {
            this.last_code_update = last_code_update;
        }

        public CpuLimitBean getCpu_limit() {
            return cpu_limit;
        }

        public void setCpu_limit(CpuLimitBean cpu_limit) {
            this.cpu_limit = cpu_limit;
        }

        public VoterInfoBean getVoter_info() {
            return voter_info;
        }

        public void setVoter_info(VoterInfoBean voter_info) {
            this.voter_info = voter_info;
        }

        public List<PermissionsBean> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<PermissionsBean> permissions) {
            this.permissions = permissions;
        }

        public static class TotalResourcesBean implements Parcelable {
            /**
             * owner : oraclechain4
             * ram_bytes : 7920
             * net_weight : 0.3333 EOS
             * cpu_weight : 0.3333 EOS
             */

            private String owner;
            private String ram_bytes;
            private String net_weight;
            private String cpu_weight;

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getRam_bytes() {
                return ram_bytes;
            }

            public void setRam_bytes(String ram_bytes) {
                this.ram_bytes = ram_bytes;
            }

            public String getNet_weight() {
                return net_weight;
            }

            public void setNet_weight(String net_weight) {
                this.net_weight = net_weight;
            }

            public String getCpu_weight() {
                return cpu_weight;
            }

            public void setCpu_weight(String cpu_weight) {
                this.cpu_weight = cpu_weight;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.owner);
                dest.writeString(this.ram_bytes);
                dest.writeString(this.net_weight);
                dest.writeString(this.cpu_weight);
            }

            public TotalResourcesBean() {
            }

            protected TotalResourcesBean(Parcel in) {
                this.owner = in.readString();
                this.ram_bytes = in.readString();
                this.net_weight = in.readString();
                this.cpu_weight = in.readString();
            }

            public static final Creator<TotalResourcesBean> CREATOR = new Creator<TotalResourcesBean>() {
                @Override
                public TotalResourcesBean createFromParcel(Parcel source) {
                    return new TotalResourcesBean(source);
                }

                @Override
                public TotalResourcesBean[] newArray(int size) {
                    return new TotalResourcesBean[size];
                }
            };
        }

        public static class NetLimitBean implements Parcelable {
            /**
             * max : 153653
             * available : 152415
             * used : 1238
             */

            private String max;
            private String available;
            private String used;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getAvailable() {
                return available;
            }

            public void setAvailable(String available) {
                this.available = available;
            }

            public String getUsed() {
                return used;
            }

            public void setUsed(String used) {
                this.used = used;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.max);
                dest.writeString(this.available);
                dest.writeString(this.used);
            }

            public NetLimitBean() {
            }

            protected NetLimitBean(Parcel in) {
                this.max = in.readString();
                this.available = in.readString();
                this.used = in.readString();
            }

            public static final Creator<NetLimitBean> CREATOR = new Creator<NetLimitBean>() {
                @Override
                public NetLimitBean createFromParcel(Parcel source) {
                    return new NetLimitBean(source);
                }

                @Override
                public NetLimitBean[] newArray(int size) {
                    return new NetLimitBean[size];
                }
            };
        }

        public static class CpuLimitBean implements Parcelable {
            /**
             * max : 29424
             * available : 19518
             * used : 9906
             */

            private String max;
            private String available;
            private String used;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getAvailable() {
                return available;
            }

            public void setAvailable(String available) {
                this.available = available;
            }

            public String getUsed() {
                return used;
            }

            public void setUsed(String used) {
                this.used = used;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.max);
                dest.writeString(this.available);
                dest.writeString(this.used);
            }

            public CpuLimitBean() {
            }

            protected CpuLimitBean(Parcel in) {
                this.max = in.readString();
                this.available = in.readString();
                this.used = in.readString();
            }

            public static final Creator<CpuLimitBean> CREATOR = new Creator<CpuLimitBean>() {
                @Override
                public CpuLimitBean createFromParcel(Parcel source) {
                    return new CpuLimitBean(source);
                }

                @Override
                public CpuLimitBean[] newArray(int size) {
                    return new CpuLimitBean[size];
                }
            };
        }

        public static class VoterInfoBean implements Parcelable {
            /**
             * owner : oraclechain4
             * proxy :
             * last_vote_weight : 0.00000000000000000
             * proxied_vote_weight : 0.00000000000000000
             * staked : 0
             * is_proxy : 0
             * producers : ["oraclegogogo"]
             */

            private String owner;
            private String proxy;
            private String last_vote_weight;
            private String proxied_vote_weight;
            private String staked;
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

            public String getStaked() {
                return staked;
            }

            public void setStaked(String staked) {
                this.staked = staked;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.owner);
                dest.writeString(this.proxy);
                dest.writeString(this.last_vote_weight);
                dest.writeString(this.proxied_vote_weight);
                dest.writeString(this.staked);
                dest.writeString(this.is_proxy);
                dest.writeStringList(this.producers);
            }

            public VoterInfoBean() {
            }

            protected VoterInfoBean(Parcel in) {
                this.owner = in.readString();
                this.proxy = in.readString();
                this.last_vote_weight = in.readString();
                this.proxied_vote_weight = in.readString();
                this.staked = in.readString();
                this.is_proxy = in.readString();
                this.producers = in.createStringArrayList();
            }

            public static final Creator<VoterInfoBean> CREATOR = new Creator<VoterInfoBean>() {
                @Override
                public VoterInfoBean createFromParcel(Parcel source) {
                    return new VoterInfoBean(source);
                }

                @Override
                public VoterInfoBean[] newArray(int size) {
                    return new VoterInfoBean[size];
                }
            };
        }

        public static class PermissionsBean implements Parcelable {
            /**
             * parent : owner
             * required_auth : {"waits":[],"keys":[{"weight":1,"key":"EOS67pa5ex64cECp2esLp6km78QfZDyEY8mAPieBHkD7JvfxiFzTG"}],"threshold":1,"accounts":[]}
             * perm_name : active
             */

            private String parent;
            private RequiredAuthBean required_auth;
            private String perm_name;

            public String getParent() {
                return parent;
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
                return perm_name;
            }

            public void setPerm_name(String perm_name) {
                this.perm_name = perm_name;
            }

            public static class RequiredAuthBean implements Parcelable {
                /**
                 * waits : []
                 * keys : [{"weight":1,"key":"EOS67pa5ex64cECp2esLp6km78QfZDyEY8mAPieBHkD7JvfxiFzTG"}]
                 * threshold : 1
                 * accounts : []
                 */

                private String threshold;
                private List<?> waits;
                private List<KeysBean> keys;
                private List<?> accounts;

                public String getThreshold() {
                    return threshold;
                }

                public void setThreshold(String threshold) {
                    this.threshold = threshold;
                }

                public List<?> getWaits() {
                    return waits;
                }

                public void setWaits(List<?> waits) {
                    this.waits = waits;
                }

                public List<KeysBean> getKeys() {
                    return keys;
                }

                public void setKeys(List<KeysBean> keys) {
                    this.keys = keys;
                }

                public List<?> getAccounts() {
                    return accounts;
                }

                public void setAccounts(List<?> accounts) {
                    this.accounts = accounts;
                }

                public static class KeysBean implements Parcelable {
                    /**
                     * weight : 1
                     * key : EOS67pa5ex64cECp2esLp6km78QfZDyEY8mAPieBHkD7JvfxiFzTG
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
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeInt(this.weight);
                        dest.writeString(this.key);
                    }

                    public KeysBean() {
                    }

                    protected KeysBean(Parcel in) {
                        this.weight = in.readInt();
                        this.key = in.readString();
                    }

                    public static final Creator<KeysBean> CREATOR = new Creator<KeysBean>() {
                        @Override
                        public KeysBean createFromParcel(Parcel source) {
                            return new KeysBean(source);
                        }

                        @Override
                        public KeysBean[] newArray(int size) {
                            return new KeysBean[size];
                        }
                    };
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.threshold);
                    dest.writeList(this.waits);
                    dest.writeList(this.keys);
                    dest.writeList(this.accounts);
                }

                public RequiredAuthBean() {
                }

                protected RequiredAuthBean(Parcel in) {
                    this.threshold = in.readString();
                    this.keys = new ArrayList<KeysBean>();
                    in.readList(this.keys, KeysBean.class.getClassLoader());
                }

                public static final Creator<RequiredAuthBean> CREATOR = new Creator<RequiredAuthBean>() {
                    @Override
                    public RequiredAuthBean createFromParcel(Parcel source) {
                        return new RequiredAuthBean(source);
                    }

                    @Override
                    public RequiredAuthBean[] newArray(int size) {
                        return new RequiredAuthBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.parent);
                dest.writeParcelable(this.required_auth, flags);
                dest.writeString(this.perm_name);
            }

            public PermissionsBean() {
            }

            protected PermissionsBean(Parcel in) {
                this.parent = in.readString();
                this.required_auth = in.readParcelable(RequiredAuthBean.class.getClassLoader());
                this.perm_name = in.readString();
            }

            public static final Creator<PermissionsBean> CREATOR = new Creator<PermissionsBean>() {
                @Override
                public PermissionsBean createFromParcel(Parcel source) {
                    return new PermissionsBean(source);
                }

                @Override
                public PermissionsBean[] newArray(int size) {
                    return new PermissionsBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.head_block_num);
            dest.writeParcelable(this.total_resources, flags);
            dest.writeString(this.head_block_time);
            dest.writeString(this.created);
            dest.writeString(this.ram_quota);
            dest.writeParcelable(this.net_limit, flags);
            dest.writeString(this.net_weight);
            dest.writeString(this.cpu_weight);
            dest.writeByte(this.privileged ? (byte) 1 : (byte) 0);
            dest.writeString(this.ram_usage);
            dest.writeString(this.account_name);
            dest.writeString(this.last_code_update);
            dest.writeParcelable(this.cpu_limit, flags);
            dest.writeParcelable(this.voter_info, flags);
            dest.writeList(this.permissions);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.head_block_num = in.readString();
            this.refund_request = in.readParcelable(Object.class.getClassLoader());
            this.total_resources = in.readParcelable(TotalResourcesBean.class.getClassLoader());
            this.head_block_time = in.readString();
            this.created = in.readString();
            this.ram_quota = in.readString();
            this.net_limit = in.readParcelable(NetLimitBean.class.getClassLoader());
            this.self_delegated_bandwidth = in.readParcelable(Object.class.getClassLoader());
            this.net_weight = in.readString();
            this.cpu_weight = in.readString();
            this.privileged = in.readByte() != 0;
            this.ram_usage = in.readString();
            this.account_name = in.readString();
            this.last_code_update = in.readString();
            this.cpu_limit = in.readParcelable(CpuLimitBean.class.getClassLoader());
            this.voter_info = in.readParcelable(VoterInfoBean.class.getClassLoader());
            this.permissions = new ArrayList<PermissionsBean>();
            in.readList(this.permissions, PermissionsBean.class.getClassLoader());
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

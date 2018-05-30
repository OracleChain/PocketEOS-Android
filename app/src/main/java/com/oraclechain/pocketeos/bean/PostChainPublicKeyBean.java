package com.oraclechain.pocketeos.bean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/2/1.
 */

public class PostChainPublicKeyBean {

    /**
     * available_keys : ["EOS6GVnbyrtf5kbTGw5WERbLebgveLRstks5UorqBTxgBHeWBfDQ9","EOS8GA8AxZVdCTdbxL97VNK3r3irFYWf1NS3QNeZRVLZhsi1tFtKv","EOS6pnoM9Pzwn97xgwSbWRqsxDirznEYcpU8exhV1veM9n2CmyW2r","EOS5z9JTTcvzmoGPWdBGLgm8MsYnvRXmVij5yjdx8ja8MJ99qZqFg","EOS7nVGuJjRSQJ7P4LE6ejVPBVtG54Dj8g8s9Muzb1VLraWBWp6RS","EOS5oJ1oYJYcSdQaP3gkFen72mYtg6qzABysiGzGTFqPmmpdnsVsA","EOS7gKwNq28HihnkJk7GxPGXA9JN7WmKCWbg4FGZVyGYY1czoqeZ6","EOS63w825PVzNHXsBYyFwE4n8A3pG8rmyjFnAVnCx3KhYNH6WXNCL","EOS5fLHK96aGyEKQBPAWJskvpzmHAydMCFhD2rkj69hbbVemYog5J","EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV"]
     * transaction : {"expiration":"2018-02-01T08:16:16","messages":[{"authorization":[{"account":"oracle.test1","permission":"active"}],"data":"10325619a888cca520325619a888cca501000000000000001741207472616e7366657220656f7320332e3020746f2042","code":"eos","type":"transfer"}],"ref_block_num":16598,"ref_block_prefix":1771986333,"signatures":[],"scope":["oracle.test1","oracle.test2"],"read_scope":[]}
     */

    private TransactionBean transaction;
    private List<String> available_keys;

    public TransactionBean getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionBean transaction) {
        this.transaction = transaction;
    }

    public List<String> getAvailable_keys() {
        if (available_keys == null) {
            return new ArrayList<>();
        }
        return available_keys;
    }

    public void setAvailable_keys(List<String> available_keys) {
        this.available_keys = available_keys;
    }

    public static class TransactionBean {
        /**
         * expiration : 2018-02-01T08:16:16
         * messages : [{"authorization":[{"account":"oracle.test1","permission":"active"}],"data":"10325619a888cca520325619a888cca501000000000000001741207472616e7366657220656f7320332e3020746f2042","code":"eos","type":"transfer"}]
         * ref_block_num : 16598
         * ref_block_prefix : 1771986333
         * signatures : []
         * scope : ["oracle.test1","oracle.test2"]
         * read_scope : []
         */

        private String expiration;
        private BigInteger ref_block_num;
        private BigInteger ref_block_prefix;
        private List<MessagesBean> messages;
        private List<String> signatures;
        private List<String> scope;
        private List<String> read_scope;

        public String getExpiration() {
            return expiration == null ? "" : expiration;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }

        public BigInteger getRef_block_num() {
            return ref_block_num;
        }

        public void setRef_block_num(BigInteger ref_block_num) {
            this.ref_block_num = ref_block_num;
        }

        public BigInteger getRef_block_prefix() {
            return ref_block_prefix;
        }

        public void setRef_block_prefix(BigInteger ref_block_prefix) {
            this.ref_block_prefix = ref_block_prefix;
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

        public List<String> getScope() {
            if (scope == null) {
                return new ArrayList<>();
            }
            return scope;
        }

        public void setScope(List<String> scope) {
            this.scope = scope;
        }

        public List<String> getRead_scope() {
            if (read_scope == null) {
                return new ArrayList<>();
            }
            return read_scope;
        }

        public void setRead_scope(List<String> read_scope) {
            this.read_scope = read_scope;
        }

        public static class MessagesBean {
            /**
             * authorization : [{"account":"oracle.test1","permission":"active"}]
             * data : 10325619a888cca520325619a888cca501000000000000001741207472616e7366657220656f7320332e3020746f2042
             * code : eos
             * type : transfer
             */

            private String data;
            private String code;
            private String type;
            private List<AuthorizationBean> authorization;

            public String getData() {
                return data == null ? "" : data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getCode() {
                return code == null ? "" : code;
            }

            public void setCode(String code) {
                this.code = code;
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

            public static class AuthorizationBean {
                /**
                 * account : oracle.test1
                 * permission : active
                 */

                private String account;
                private String permission;

                public AuthorizationBean(String account, String permission) {
                    this.account = account;
                    this.permission = permission;
                }

                public String getAccount() {
                    return account == null ? "" : account;
                }

                public void setAccount(String account) {
                    this.account = account;
                }

                public String getPermission() {
                    return permission == null ? "" : permission;
                }

                public void setPermission(String permission) {
                    this.permission = permission;
                }
            }
        }
    }
}

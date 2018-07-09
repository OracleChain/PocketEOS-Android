package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/2/5.
 */

public class TransferHistoryBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"actions":[{"doc":{"account":"eosio.token","name":"transfer","authorization":[{"actor":"oraclechengj","permission":"active"}],"data":{"from":"oraclechengj","to":"oraclechain4","quantity":"0.0010 EOS","memo":"恭喜发财,大吉大利"},"hex_data":"f0d8540da988cca540a6330da988cca50a0000000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9"},"blockNum":2039599,"trxid":"f7b6975fb294a62c5a694eee201699465937705352acbd6813fceeb2cd3435f0"},{"doc":{"account":"eosio.token","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"shaoyuanbin2","quantity":"0.0010 EOS","memo":"恭喜发财,大吉大利"},"hex_data":"40a6330da988cca520a63bd3684f4dc30a0000000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9"},"blockNum":2048515,"trxid":"fc49d4197c1d589325b87e5fdc881ebbce4baaf9475be40a9da0a3e8b60cccc4"},{"doc":{"account":"eosio.token","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"webstormshi1","quantity":"0.0001 EOS","memo":""},"hex_data":"40a6330da988cca5105cc3f2d28c8fe2010000000000000004454f530000000000"},"blockNum":2320390,"trxid":"1e5d7d788dd6d8207401f7cb284d9258659146c2506c6d5507c7e3e36f301db0"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"helloworldgo","permission":"active"}],"data":{"from":"helloworldgo","to":"oraclechain4","quantity":"50.0000 AAA","memo":""},"hex_data":"40598a97721aa36a40a6330da988cca520a1070000000000044141410000000000"},"blockNum":2525746,"trxid":"42bc2137eb1ad7b26cc4693cbe9dd2eb896bf2adf5132f72ab40b12a25cde755"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2525802,"trxid":"fc7e6cb456f70cd210db0caeee7d407d3ffaab9930ce597ec733310e40aa7ccb"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2526228,"trxid":"72ed7470e38f0befeb36539e21a6e6c97a0ddcf75628e070d8ce6dd8dac32b7d"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2526228,"trxid":"72ed7470e38f0befeb36539e21a6e6c97a0ddcf75628e070d8ce6dd8dac32b7d"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2526428,"trxid":"7fb4225b88a95dfae3213fcb050a207c9755fcff690b13dada55d77f37f1cbd0"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2526869,"trxid":"b402ab1f6c9ac7388279a90b579d8bc10e5e35da10e37aaae54e013fc3af00c5"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2527069,"trxid":"66310cb4cb32799ce645b7b402baa9a4d316142fe75b7678bf72ee0bb2d5390d"}],"page":0,"pageSize":10,"hasMore":true}
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
         * actions : [{"doc":{"account":"eosio.token","name":"transfer","authorization":[{"actor":"oraclechengj","permission":"active"}],"data":{"from":"oraclechengj","to":"oraclechain4","quantity":"0.0010 EOS","memo":"恭喜发财,大吉大利"},"hex_data":"f0d8540da988cca540a6330da988cca50a0000000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9"},"blockNum":2039599,"trxid":"f7b6975fb294a62c5a694eee201699465937705352acbd6813fceeb2cd3435f0"},{"doc":{"account":"eosio.token","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"shaoyuanbin2","quantity":"0.0010 EOS","memo":"恭喜发财,大吉大利"},"hex_data":"40a6330da988cca520a63bd3684f4dc30a0000000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9"},"blockNum":2048515,"trxid":"fc49d4197c1d589325b87e5fdc881ebbce4baaf9475be40a9da0a3e8b60cccc4"},{"doc":{"account":"eosio.token","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"webstormshi1","quantity":"0.0001 EOS","memo":""},"hex_data":"40a6330da988cca5105cc3f2d28c8fe2010000000000000004454f530000000000"},"blockNum":2320390,"trxid":"1e5d7d788dd6d8207401f7cb284d9258659146c2506c6d5507c7e3e36f301db0"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"helloworldgo","permission":"active"}],"data":{"from":"helloworldgo","to":"oraclechain4","quantity":"50.0000 AAA","memo":""},"hex_data":"40598a97721aa36a40a6330da988cca520a1070000000000044141410000000000"},"blockNum":2525746,"trxid":"42bc2137eb1ad7b26cc4693cbe9dd2eb896bf2adf5132f72ab40b12a25cde755"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2525802,"trxid":"fc7e6cb456f70cd210db0caeee7d407d3ffaab9930ce597ec733310e40aa7ccb"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2526228,"trxid":"72ed7470e38f0befeb36539e21a6e6c97a0ddcf75628e070d8ce6dd8dac32b7d"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2526228,"trxid":"72ed7470e38f0befeb36539e21a6e6c97a0ddcf75628e070d8ce6dd8dac32b7d"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2526428,"trxid":"7fb4225b88a95dfae3213fcb050a207c9755fcff690b13dada55d77f37f1cbd0"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2526869,"trxid":"b402ab1f6c9ac7388279a90b579d8bc10e5e35da10e37aaae54e013fc3af00c5"},{"doc":{"account":"helloworldgo","name":"transfer","authorization":[{"actor":"oraclechain4","permission":"active"}],"data":{"from":"oraclechain4","to":"prizequiz233","quantity":"3.0000 AAA","memo":"buy"},"hex_data":"40a6330da988cca53086f84e5bf5ddad3075000000000000044141410000000003627579"},"blockNum":2527069,"trxid":"66310cb4cb32799ce645b7b402baa9a4d316142fe75b7678bf72ee0bb2d5390d"}]
         * page : 0
         * pageSize : 10
         * hasMore : true
         */

        private int page;
        private int pageSize;
        private boolean hasMore;
        private List<ActionsBean> actions;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public boolean isHasMore() {
            return hasMore;
        }

        public void setHasMore(boolean hasMore) {
            this.hasMore = hasMore;
        }

        public List<ActionsBean> getActions() {
            return actions;
        }

        public void setActions(List<ActionsBean> actions) {
            this.actions = actions;
        }

        public static class ActionsBean {
            /**
             * doc : {"account":"eosio.token","name":"transfer","authorization":[{"actor":"oraclechengj","permission":"active"}],"data":{"from":"oraclechengj","to":"oraclechain4","quantity":"0.0010 EOS","memo":"恭喜发财,大吉大利"},"hex_data":"f0d8540da988cca540a6330da988cca50a0000000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9"}
             * blockNum : 2039599
             * trxid : f7b6975fb294a62c5a694eee201699465937705352acbd6813fceeb2cd3435f0
             */

            private DocBean doc;
            private int blockNum;
            private String trxid;

            public DocBean getDoc() {
                return doc;
            }

            public void setDoc(DocBean doc) {
                this.doc = doc;
            }

            public int getBlockNum() {
                return blockNum;
            }

            public void setBlockNum(int blockNum) {
                this.blockNum = blockNum;
            }

            public String getTrxid() {
                return trxid;
            }

            public void setTrxid(String trxid) {
                this.trxid = trxid;
            }

            public static class DocBean {
                /**
                 * account : eosio.token
                 * name : transfer
                 * authorization : [{"actor":"oraclechengj","permission":"active"}]
                 * data : {"from":"oraclechengj","to":"oraclechain4","quantity":"0.0010 EOS","memo":"恭喜发财,大吉大利"}
                 * hex_data : f0d8540da988cca540a6330da988cca50a0000000000000004454f530000000019e681ade5969ce58f91e8b4a22ce5a4a7e59089e5a4a7e588a9
                 */

                private String account;
                private String name;
                private DataBean data;
                private String hex_data;
                private List<AuthorizationBean> authorization;

                public String getAccount() {
                    return account;
                }

                public void setAccount(String account) {
                    this.account = account;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public DataBean getData() {
                    return data;
                }

                public void setData(DataBean data) {
                    this.data = data;
                }

                public String getHex_data() {
                    return hex_data;
                }

                public void setHex_data(String hex_data) {
                    this.hex_data = hex_data;
                }

                public List<AuthorizationBean> getAuthorization() {
                    return authorization;
                }

                public void setAuthorization(List<AuthorizationBean> authorization) {
                    this.authorization = authorization;
                }

                public static class DataBean {
                    /**
                     * from : oraclechengj
                     * to : oraclechain4
                     * quantity : 0.0010 EOS
                     * memo : 恭喜发财,大吉大利
                     */

                    private String from;
                    private String to;
                    private String quantity;
                    private String memo;

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
                }

                public static class AuthorizationBean {
                    /**
                     * actor : oraclechengj
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

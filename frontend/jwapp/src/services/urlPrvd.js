module.exports = function(app){
    app.provider('urlSrvc', function(){
        var self = this, environments = new Map(), currentEnvName, commonUrls = new Map();

        function Environment(name){

            var nameValue = name, portValue, sslPortValue, serverValue, protocolValue, endPointBaseValue, urls = new Map();

            this.port = function(port){
                portValue = port;
                return this;
            };

            this.sslPort = function(sslPort){
                sslPortValue = sslPort;
                return this;
            };

            this.server = function(server){
                serverValue = server;
                return this;
            };

            this.protocol = function(protocol){
                protocolValue = protocol;
                return this;
            };

            this.endPointBase = function(endPointBase){
                endPointBaseValue = endPointBase;
                return this;
            };

            this.getUrl = function(key){
                var portInAction = (protocolValue === 'http'? portValue : sslPortValue);
                var url = protocolValue + '://' + serverValue + ":" + portInAction + endPointBaseValue;
                var lastPart = (urls.get(key) ? urls.get(key) : commonUrls.get(key));
                return url + lastPart;
            };

            this.addUrl = function(key, url){
                urls.set(key, url);
            };
        };

        self.addEnvironment = function(envName){
            var env = new Environment(envName);
            environments.set(envName, env);
            return env;
        };

        self.setCurrentEnvironment = function(CurrentEnvironmentName){
            currentEnvName = CurrentEnvironmentName;
        };

        self.addUrl = function(key, url, envName){
            if(typeof envName !== "undefined"){
                environments.get(envName).addUrl(key, url);
            }else{
                commonUrls.set(key, url);
            }
        };

        function ReturnObj(){

            this.getUrl = function(key){
               return environments.get(currentEnvName).getUrl(key);
            };
        };

        self.$get = function(){
           
            return new ReturnObj();
        };
    });
};
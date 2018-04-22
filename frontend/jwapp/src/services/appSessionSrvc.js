module.exports = function(app){
    app.service('appSessionSrvc', function($cookieStore){
        var currentSessionCookieKey = 'currentUser', self = this;

        self.getAuthenticationToken = function(){
            return  $cookieStore.get(currentSessionCookieKey).token.value;
        };

        self.getCurrentUser = function(){
            return  $cookieStore.get(currentSessionCookieKey);
        };

        self.getIsAuthenticated = function(){
            return !angular.isUndefined($cookieStore.get(currentSessionCookieKey));
        }; 

        self.getMaxInactiveInterval = function(){
            return  $cookieStore.get(currentSessionCookieKey).token.maxInactiveInterval;
        }; 

        self.invalidateCurrentSession = function(){
            $cookieStore.remove(currentSessionCookieKey);
        };

        self.createSession = function(currentUser){
            $cookieStore.put(currentSessionCookieKey, currentUser);
        };
    });
};
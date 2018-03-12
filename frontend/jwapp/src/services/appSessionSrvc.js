module.exports = function(app){
    app.service('appSessionSrvc', function(){
        var self = this, isAuthenticated = false, currentUser, authenticationToken, maxInactiveInterval;

        self.getAuthenticationToken = function(){
            return authenticationToken;
        };

        self.setAuthenticationToken = function(authenticationTokenValue){
            authenticationToken = authenticationTokenValue;
        };

        self.getCurrentUser = function(){
            return currentUser;
        };      

        self.setCurrentUser = function(currentUserValue){
            currentUser = currentUserValue;
        };

        self.setIsAuthenticated = function(isAuthenticatedValue){
            isAuthenticated = isAuthenticatedValue;
        };

        self.getIsAuthenticated = function(){
            return isAuthenticated;
        }; 

        self.setMaxInactiveInterval = function(maxInactiveIntervalValue){
            maxInactiveInterval = maxInactiveIntervalValue;
        };

        self.getMaxInactiveInterval = function(){
            return maxInactiveInterval;
        }; 

        self.empty = function(){
            isAuthenticated = false;
            currentUser = null;
            authenticationToken = null;
            maxInactiveInterval = null;
        }; 
    });
};
module.exports = function(app){
    app.factory('messageFcty', function($rootScope, httpStatusSrvc){
        var self = this;
        var message = null;
        var messageWithLink = null;
        var messageDiscription = null;

        return {
            getMessage : function () {
                return message;
              },
              getMessageWithLink : function () {
                return messageWithLink;
              },

              getMessageDiscription : function () {
                return messageDiscription;
              },

            handleErrorMessage : function(response) {
                var status = httpStatusSrvc.getStatus(response.status);
                    if (status.code === httpStatusSrvc.preconditionFailed.code) {
                        messageWithLink = true;
                        message = $rootScope.messages[status.text];
                    } else {
                        messageWithLink = false;
                        messageDiscription = response.data.message ? response.data.message.split(":")[0] : response.data.errorDescription;
                        message = $rootScope.messages[status.text];
                    };
            },
    
            resetMessage : function () {
                message = null;
                messageDiscription = null;
            }

        };

    });
}
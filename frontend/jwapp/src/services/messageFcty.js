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
                    console.log(response);
                    if (status.code === httpStatusSrvc.preconditionFailed.code) {
                        messageWithLink = true;
                        message = $rootScope.messages[status.text];
                    } else {
                        messageWithLink = false;
                        messageDiscription = response.data.message.split(":")[0];
                        message = $rootScope.messages[status.text];
                    };
                    console.log(message);
            },
    
            resetMessage : function () {
                message = null;
                messageDiscription = null;
            }

        };

    });
}
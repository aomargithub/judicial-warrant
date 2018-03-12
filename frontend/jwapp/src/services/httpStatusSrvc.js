//to be converted to factory
module.exports = function(app){
    app.service('httpStatusSrvc', function(){
        var self = this, statuses;

        function init(){
            statuses = new Map();
            statuses.set(-1, {code : -1, text : 'SAME_ORIGIN_POLICY_VIOLATION'});
            statuses.set(200, {code : 200, text : 'OK'});
            statuses.set(201, {code : 201, text : 'CREATED'});
            statuses.set(400, {code : 400, text : 'BAD_REQUEST'});
            statuses.set(401, {code : 401, text : 'UNAUTHORIZED'});
            statuses.set(403, {code : 403, text : 'FORBIDDEN'});
            statuses.set(404, {code : 404, text : 'NOT_FOUND'});
            statuses.set(405, {code : 405, text : 'METHOD_NOT_ALLOWED'});
            statuses.set(500, {code : 500, text : 'INTERNAL_SERVER_ERROR'});
        }
        init();
        self.getStatus = function(code){
            return statuses.get(code);
        };
    });
};
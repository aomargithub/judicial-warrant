module.exports = function(app){
    require('./authenticationSrvc')(app);
    require('./urlPrvd')(app);
    require('./authenticationInterceptor')(app);
    require('./templatePrvd')(app);
    require('./appSessionSrvc')(app);
    require('./httpStatusSrvc')(app);
    require('./menuItemsFcty')(app);
    require('./appRoleFcty')(app);
    require('./organizationUnitSrvc')(app);
};
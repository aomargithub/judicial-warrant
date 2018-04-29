module.exports = function(app){
    require('./authenticationSrvc')(app);
    require('./urlPrvd')(app);
    require('./authenticationInterceptor')(app);
    require('./versionInterceptor')(app);
    require('./templatePrvd')(app);
    require('./appSessionSrvc')(app);
    require('./httpStatusSrvc')(app);
    require('./menuItemsFcty')(app);
    require('./appRoleFcty')(app);
    require('./organizationUnitSrvc')(app);
    require('./attachmentTypeSrvc')(app);
    require('./stringUtilSrvc')(app);
};
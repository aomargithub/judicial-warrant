module.exports = function(app){
    require('./requestEntitledRegistrationDrtv')(app);
    require('./requestEntitledRegistrationDrtvCtrl')(app);
}
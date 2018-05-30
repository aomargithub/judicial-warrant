module.exports = function(app){
    require('./internalUserDrtv')(app);
    require('./internalUserDrtvCtrl')(app);
}
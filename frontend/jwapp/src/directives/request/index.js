module.exports = function(app){
    require('./requestDrtv')(app);
    require('./requestDrtvCtrl')(app);
}
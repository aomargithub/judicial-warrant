module.exports = function(app){
    require('./paginationDrtv')(app);
    require('./paginationDrtvCtrl')(app);
    require('./paginationFltr')(app);
};
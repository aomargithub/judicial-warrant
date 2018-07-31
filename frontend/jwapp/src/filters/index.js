module.exports = function(app){
    require('./localeFltr')(app);
    require('./dateFltr')(app);
}
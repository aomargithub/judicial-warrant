module.exports = function(app){
    require('./directives')(app);
    require('./services')(app);
    require('./urlConfig')(app);
    require('./generalConfig')(app);
    require('./routeConfig')(app);
    require('./models')(app);
    require('./filters')(app);
};
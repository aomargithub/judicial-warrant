module.exports = function(app){
    require('./content-viewer')(app);
    require('./modalSrvc')(app);
};
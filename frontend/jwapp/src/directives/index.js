module.exports = function(app){
    require('./login')(app);
    require('./menu')(app);
};
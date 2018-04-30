module.exports = function(app){
    require('./login')(app);
    require('./menu')(app);
    require('./organizationUnit')(app);
    require('./message')(app);
    require('./pagination')(app);
    require('./logout')(app);
};
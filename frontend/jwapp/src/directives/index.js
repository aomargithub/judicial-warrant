module.exports = function(app){
    require('./login')(app);
    require('./menu')(app);
    require('./organizationUnit')(app);
    require('./attachmentType')(app);
    require('./message')(app);
    require('./pagination')(app);
    require('./logout')(app);
    require('./header')(app);
};
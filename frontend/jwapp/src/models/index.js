module.exports = function(app){
    require('./OrganizationUnit')(app);
    require('./AttachmentType')(app);
};
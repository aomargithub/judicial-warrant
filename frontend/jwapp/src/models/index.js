module.exports = function(app){
    require('./OrganizationUnit')(app);
    require('./AttachmentType')(app);
    require('./User')(app);
    require('./requestTypeAttachmentType')(app);
    require('./CapacityDelegation')(app);
    require('./RequestAttachment')(app);
};